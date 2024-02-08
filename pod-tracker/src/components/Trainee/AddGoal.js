import React, { useState, useEffect } from "react";
import {
	Container, Typography, TextField, Button, Box,
	Dialog, DialogTitle, DialogContent, DialogActions,
	Select, MenuItem,
} from '@mui/material';
import TraineeComment from './TraineeComment';
import TraineeInterview from './TraineeInterviews';
import TraineeOpportunities from './TraineeOpportunities';
import TraineeUpSkilling from './TraineeUpSkilling';
import TraineePodProject from './TraineePodProject';

export default function AddGoal({ setOpenModal }) {
	const [openAddActivityUpdateModal, setOpenAddActivityUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [selectedActivity, setSelectedActivity] = useState('');

	return (
		<Container
			component="main"
			sx={{
				marginTop: 4,
				display: "flex",
				flexDirection: "column",
				fontFamily: "monospace",
				marginBottom: 6,
			}}>
			<Box sx={{
				display: "flex",
				flexDirection: "column",
			}}>
				<Box sx={{ display: "flex", justifyContent: "space-between", gap:10, mb:2 }}>
					<Typography variant="h4">Add a Goal</Typography>
					<Box sx={{ display: "flex", gap: 1 }}>
						<Button variant="outlined" size="small"
							onClick={() => {
								setSelectedActivity("");
								setOpenModal(false);
							}}
						>
							Cancel
						</Button>
						<Button variant="contained" color="primary" size="small"
							onClick={() => {
								setOpenAddActivityUpdateModal(true)
								setSelectedActivity("Pod Project")
							}}>
							Add Activity
						</Button>
					</Box>
				</Box>
				<Typography variant="body1">2023-10-01</Typography>
			</Box>

			<Dialog open={openAddActivityUpdateModal}>
				<DialogContent>
					<Box sx={{ display: "flex", flexDirection: "column", gap: 3 }}>
						<Box sx={{ display: "flex" }}>
							<Typography variant="h6" sx={{ minWidth: 100 }}>
								Activity
							</Typography>
							<Select
								sx={{
									width: 250,
									height: 30,
								}}
								defaultValue={1}
								>
								<MenuItem value={1} onClick={() => { setSelectedActivity("Pod Project") }}>Pod Project</MenuItem>
								<MenuItem value={2} onClick={() => { setSelectedActivity("UpSkilling") }}>Upskilling</MenuItem>
								<MenuItem value={3} onClick={() => { setSelectedActivity("Opportunities") }}>Opportunities</MenuItem>
								<MenuItem value={4} onClick={() => { setSelectedActivity("Interviews") }}>Interviews</MenuItem>
								<MenuItem value={5} onClick={() => { setSelectedActivity("Comment") }}>Comment</MenuItem>
							</Select>
						</Box>
						<TraineePodProject selectedActivity={selectedActivity} />
						<TraineeUpSkilling selectedActivity={selectedActivity} />
						<TraineeOpportunities selectedActivity={selectedActivity} />
						<TraineeInterview selectedActivity={selectedActivity} />
						<TraineeComment selectedActivity={selectedActivity} />
					</Box>
				</DialogContent>
				<DialogActions>
					{selectedActivity && (
						<>
							<Button
								onClick={() => {
									setOpenAddActivityUpdateModal(false);
									setSelectedActivity("")
								}}
							>
								Cancel
							</Button>
							<Button
								onClick={() => {
									setOpenAddActivityUpdateModal(false);
									setSelectedActivity("")
								}}
							>
								Add Activity
							</Button>
						</>
					)}
				</DialogActions>
			</Dialog>
		</Container>
	)
}