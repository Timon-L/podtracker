import React, { useState, useEffect } from "react";
import {
	Container, Typography, TextField, Button,
	Box,
	Paper,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
	Collapse,
	Dialog, DialogContent, DialogActions,
} from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';

export default function TraineeGoalViewOnly({ setOpenGoalInfoModal }) {
	const [errorMsg, setErrorMsg] = useState("");
	const [isSkillsOpen, setIsSkillsOpen] = useState(false);
	const [isInterviewsOpen, setIsInterviewsOpen] = useState(false);
	const [isTrainerFeedbackOpen, setIsTrainerFeedbackOpen] = useState(false);
	const [openAddFeedbackModal, setOpenAddFeedbackModal] = useState(false);
	const [selectedActivity, setSelectedActivity] = useState("");
	const [trainerFeedback, setTrainerFeedback] = useState("");
	const [textFieldValue, setTextFieldValue] = useState('');


	const toggleSkills = () => {
		setIsSkillsOpen(prevState => !prevState);
	};
	const toggleInterviews = () => {
		setIsInterviewsOpen(prevState => !prevState);
	};
	const toggleTrainerFeedback = () => {
		setIsTrainerFeedbackOpen(prevState => !prevState);
	};

	return (
		<>
			<Container
				component="main"
				sx={{
					marginTop: 4,
					display: "flex",
					flexDirection: "column",
					fontFamily: "monospace",
					marginBottom: 6,
				}}>
				<Box sx={{ display: "flex", justifyContent: "space-between" }}>
					<Typography variant="h6">Harry Potter, 2023-10-01</Typography>
					<Box sx={{ display: "flex", gap: 1 }}>
						<Button variant="outlined"
							onClick={() => {
								setOpenGoalInfoModal(false);
								setSelectedActivity("");
							}}
						>
							Back
						</Button>
						<Button variant="contained"
							onClick={() => {
								setOpenAddFeedbackModal(true);
								setSelectedActivity("Trainer Comment");
							}}
						>
							Add Feedback
						</Button>
					</Box>
				</Box>

				<Box sx={{ mb: 3 }}>
					<TableContainer component={Paper} sx={{ mt: 3, }}>
						<Table>
							<TableBody>
								<TableCell>Name: Harry Potter</TableCell>
								<TableCell>Stream: Dev</TableCell>
								<TableCell>City: Melbourne</TableCell>
							</TableBody>
							<TableBody>
								<TableCell>Pod: banks</TableCell>
								<TableCell>Pod Project: banking app</TableCell>
								<TableCell>Trainer(s): John Smith, Jane Smith</TableCell>
							</TableBody>
						</Table>
					</TableContainer>
				</Box>

				<Box sx={{
					display: "flex",
					flexDirection: "column",
					mb: 3
				}}>
					<Paper>
						<Typography
							variant="h6"
							sx={{
								backgroundColor: "#D3D3D3",
								textAlign: "center",
								cursor: "pointer",
								display: "flex",
								justifyContent: "space-between",
								padding: '8px'
							}}
							onClick={toggleSkills}
						>
							Skills
							{isSkillsOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
						</Typography>
					</Paper>
					<Collapse in={isSkillsOpen} timeout="auto" unmountOnExit>
						<TableContainer component={Paper} sx={{ mt: 1, }}>
							<Table>
								<TableHead>
									<TableRow>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Upskilling
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Plan
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Status
											</Typography>
										</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>
									<TableCell>AWS</TableCell>
									<TableCell>to complete AWS training</TableCell>
									<TableCell>Completed 1st module</TableCell>
								</TableBody>
							</Table>
						</TableContainer>
					</Collapse>
				</Box>

				<Box sx={{
					display: "flex",
					flexDirection: "column",
				}}>
					<Paper>
						<Typography
							variant="h6"
							sx={{
								backgroundColor: "#D3D3D3",
								textAlign: "center",
								cursor: "pointer",
								display: "flex",
								justifyContent: "space-between",
								padding: '8px'
							}}
							onClick={toggleInterviews}
						>
							Interviews
							{isInterviewsOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
						</Typography>
					</Paper>
					<Collapse in={isInterviewsOpen} timeout="auto" unmountOnExit>
						<TableContainer component={Paper} sx={{ mt: 1, }}>
							<Table>
								<TableHead>
									<TableRow>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Date
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Client
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Role
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Outcome
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Feedback
											</Typography>
										</TableCell>
										<TableCell>
											<Typography variant="body1" sx={{ fontWeight: "700", }}>
												Account Manager
											</Typography>
										</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>
									<TableCell>AWS</TableCell>
									<TableCell>to complete AWS training</TableCell>
									<TableCell>Completed 1st module</TableCell>
								</TableBody>
							</Table>
						</TableContainer>
					</Collapse>
				</Box>

				<Box sx={{
					display: "flex",
					flexDirection: "column",
					mb: 3,
					mt: 3
				}}>
					<Paper>
						<Typography
							variant="h6"
							sx={{
								backgroundColor: "#D3D3D3",
								textAlign: "center",
								cursor: "pointer",
								display: "flex",
								justifyContent: "space-between",
								padding: '8px'
							}}
							onClick={toggleTrainerFeedback}
						>
							Trainer's Feedback
							{isTrainerFeedbackOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
						</Typography>
					</Paper>
					<Collapse in={isTrainerFeedbackOpen} timeout="auto" unmountOnExit >
						<Box sx={{ mt: 1, }}>
							{trainerFeedback}
						</Box>
					</Collapse>
				</Box>

				<Dialog open={openAddFeedbackModal}>
					<DialogContent>
						{selectedActivity === "Trainer Comment" && (
							<TextField
								multiline
								rows={4}
								maxRows={10}
								type="text"
								variant='outlined'
								color='secondary'
								label="Feedback"
								fullWidth
								required
								onChange={(e) => setTextFieldValue(e.target.value)}
							/>
						)}
					</DialogContent>
					<DialogActions>
						<Button
							onClick={() => {
								setOpenAddFeedbackModal(false);
								setSelectedActivity("")
							}}
						>
							Cancel
						</Button>
						<Button
							type="submit"
							onClick={() => {
								setOpenAddFeedbackModal(false);
								setSelectedActivity("")
								setTrainerFeedback(textFieldValue);
								setIsTrainerFeedbackOpen(true);
							}}
						>
							Add Feedback
						</Button>
					</DialogActions>
				</Dialog>
			</Container>
		</>
	)
}