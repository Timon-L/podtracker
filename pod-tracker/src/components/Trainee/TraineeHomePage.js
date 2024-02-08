import React, { useEffect, useState, } from "react";
import { Link, useNavigate } from "react-router-dom";
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
import ChangePassword from '../ChangePassword';
import TraineeGoal from './TraineeGoal';
import AddGoal from './AddGoal';
import { useUser } from '../../hooks/contexts/UserContext';
import { fetchAPodProjectGoal, fetchAllOpportunities, fetchGoals, fetchUpskillingById, fetchUpskillings, fetchUpskillingsInGoal } from "../../utils/apiUtils";

export default function TraineeHomePage() {
	const [openAddActivityUpdateModal, setOpenAddActivityUpdateModal] = useState(false);
	const [openChangePasswordUpdateModal, setOpenChangePasswordUpdateModal] = useState(false);
	const [openTraineeInfoModal, setOpenTraineeInfoModal] = useState(false);
	const [openAddGoalModal, setOpenAddGoalModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [text, setText] = useState('');
	const [selectedActivity, setSelectedActivity] = useState('');
	const [selectedComponent, setSelectedComponent] = useState('');
	const [goals, setGoals] = useState([]);
	const [goal, setGoal] = useState([]);
	const [upskillings, setUpskillings] = useState([])
	const navigate = useNavigate("");
	const { bearToken, setBearToken } = useUser();

	const handleLogout = async () => {
		console.log("Before setBearToken:", bearToken);
		setBearToken(null);
		console.log("After setBearToken:", bearToken);
		navigate("/login");
	};

	useEffect(() => {
		fetchGoals(bearToken)
			.then(data => setGoals(data))


		fetchUpskillings(bearToken)
			.then(data => setUpskillings(data))

			fetchUpskillingsInGoal(bearToken, 0).then(data =>
				{console.log("UPSKILLINGS");
				console.log(data[0])})


		// fetchProjects(bearToken)
		// 	.then(data => setPods(data))



		// alert(fetchPods(bearToken))
		// if (p != null)
		// {
		// 	setPods()
		// }

	}, [])

	return (
		<Container
			component="main"
			sx={{
				marginTop: 6,
				display: "flex",
				flexDirection: "column",
				fontFamily: "monospace",
			}}>
			<Box sx={{
				display: "flex",
				justifyContent: "left",
				gap: 2
			}}>
				<Button
					variant="contained"
					sx={{
						backgroundColor: "red", "&:hover": {
							backgroundColor: "#8b0000"
						}
					}}
					onClick={() => {
						setSelectedActivity("Change Password")
						setOpenChangePasswordUpdateModal(true);
					}}>
					Change Password
				</Button>
			</Box>

			<Box sx={{
				mt: 4,
				display: "flex",
				justifyContent: "space-between",
				gap: 6
			}}>
				<Typography variant="h4">
					My Goals
				</Typography>
				<Box sx={{
					display: "flex",
					gap: 2
				}}>
					<Button
						variant="contained"
						color="primary"
						onClick={() => {
							setSelectedActivity("Add Goal")
							setSelectedComponent(selectedActivity)
							setOpenAddActivityUpdateModal(true)
							
							setGoal([])
						}}
					>
						+ Add Goal
					</Button>
					<Button
						variant="contained"
						color="primary"
					>
						Filter
					</Button>
					<Button
						variant="contained"
						color="primary"
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
							<Link
								onClick={() => {
									setSelectedActivity("Trainee Goal")
									setSelectedComponent(selectedActivity)
									setOpenAddActivityUpdateModal(true)
								}}
							>
								2023-10-01
							</Link>
						</TableCell>
						<TableCell>pod1</TableCell>
						<TableCell>Pod tracker</TableCell>
						<TableCell>GCS</TableCell>
						<TableCell>ANZ: SRE/TableCell</TableCell>
						<TableCell>1</TableCell>
					</TableBody>
					{goals.map((goal) => (
						<TableBody>
							<TableCell>
								<Link
								onClick={() => {
									setGoal(goal)
									setSelectedActivity("Trainee Goal")
									setSelectedComponent(selectedActivity)
									setOpenAddActivityUpdateModal(true)
								}}
								>
									{goal.date}
								</Link>
							</TableCell>
							<TableCell>pod</TableCell>
							<TableCell>project</TableCell>
							<TableCell/>
							<TableCell>
								{/* {fetchUpskillingsInGoal(bearToken, goal.id).then(data => data.map((upskillings) => (
									//upskillings.map((upskillingGoal) =>
									
										<Typography>{upskillings[0].upskilling.topic}</Typography>
									
									//)
								)))} */}
							</TableCell>

						</TableBody>
					))}
				</Table>
			</TableContainer>

			<Dialog
				fullWidth
				maxWidth="xl"
				open={openAddActivityUpdateModal}>
				<DialogContent>
					<TraineeGoal setOpenModal={setOpenAddActivityUpdateModal} isAdding={selectedActivity === "Add Goal"}  nextGoal={goal}/>
				</DialogContent>
			</Dialog>

			{/* <Dialog
				fullWidth
				maxWidth="xl"
				open={openAddActivityUpdateModal}
			>
				<DialogContent>
					<TraineeGoal
						selectedComponent={selectedActivity}
						setOpenModal={setOpenAddActivityUpdateModal}
						isAdding={false}
					/>
				</DialogContent>
			</Dialog> */}

			<Dialog open={openChangePasswordUpdateModal}>
				<DialogContent>
					<ChangePassword selectedActivity={selectedActivity}
						setOpenModal={setOpenChangePasswordUpdateModal} />
				</DialogContent>
				<DialogActions >

				</DialogActions>
			</Dialog>


		</Container >
	);
}