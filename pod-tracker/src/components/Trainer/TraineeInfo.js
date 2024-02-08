import React, { useState, } from "react";
import { Link, } from "react-router-dom";
import {
	Container, Typography, Button,
	Box,
	Paper,
	Dialog, DialogContent, DialogActions,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
} from '@mui/material';
import FilterAltIcon from '@mui/icons-material/FilterAlt';
import SearchIcon from '@mui/icons-material/Search';
import TraineeGoalViewOnly from './TraineeGoalViewOnly';

export default function TraineeInfo() {
	const [openGoalInfoModal, setOpenGoalInfoModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [selectedActivity, setSelectedActivity] = useState('');
	const [selectedComponent, setSelectedComponent] = useState('');

	return (
		<Container
			component="main"
			sx={{
				marginTop: 5,
				display: "flex",
				flexDirection: "column",
				fontFamily: "monospace",
			}}>

			<Box sx={{
				display: "flex",
				justifyContent: "space-between",
				gap: 6
			}}>
				<Typography variant="h4">Harry Potter's Goals</Typography>
				<Box sx={{
					display: "flex",
					gap: 2
				}}>
					<Button
						variant="contained"
						color="inherit"
					>
						<Link to="/trainers"
							style={{ textDecoration: 'none' }}
						>Back to Home Page</Link>
					</Button>
					<Button
						variant="contained"
						color="primary"
						startIcon={<FilterAltIcon />}
						size="small"
					>
						Filter
					</Button>
					<Button
						variant="contained"
						color="primary"
						startIcon={<SearchIcon />}
						size="small"
					>
						Search
					</Button>
				</Box>
			</Box>

			<TableContainer component={Paper} sx={{ mt: 3, }}>
				<Table>
					<TableHead>
						<TableRow>
							<TableCell sx={{ fontWeight: "700", }}>
								<Typography variant="h6">Date</Typography>
							</TableCell>
							<TableCell sx={{ fontWeight: "700", }}>
								<Typography variant="h6">Pod</Typography>
							</TableCell>
							<TableCell sx={{ fontWeight: "700", }}>
								<Typography variant="h6">Project</Typography>
							</TableCell>
							<TableCell sx={{ fontWeight: "700" }}>
								<Typography variant="h6">UpSkilling</Typography>
							</TableCell>
							<TableCell sx={{ fontWeight: "700", }}>
								<Typography variant="h6">Opportunities</Typography>
							</TableCell>
							<TableCell sx={{ fontWeight: "700", }}>
								<Typography variant="h6">Interview</Typography>
							</TableCell>
						</TableRow>
					</TableHead>
					<TableBody>
						<TableCell>
							<Button>
								<Link
									onClick={() => {
										setOpenGoalInfoModal(true);
										setSelectedActivity("Trainee Goal View Only")
									}}
									style={{ textDecoration: 'none' }}
								>2023-10-01</Link>
							</Button>
						</TableCell>
						<TableCell>pod1</TableCell>
						<TableCell>Pod tracker</TableCell>
						<TableCell>GCS</TableCell>
						<TableCell>ANZ: SRE</TableCell>
						<TableCell>ANZ, SRE, 2023-10-01</TableCell>
					</TableBody>
				</Table>
			</TableContainer>

			<Dialog
				open={openGoalInfoModal}
				fullWidth
				maxWidth="xl">
				<DialogContent>
					{selectedActivity === "Trainee Goal View Only" && (
						<TraineeGoalViewOnly setOpenGoalInfoModal={setOpenGoalInfoModal} />
					)}
				</DialogContent>
			</Dialog>
		</Container >
	);
}