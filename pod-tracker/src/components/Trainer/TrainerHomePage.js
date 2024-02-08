import React, { useState, } from "react";
import { Link } from "react-router-dom";
import {
	Container, Typography, Button,
	Box,
	Dialog, DialogContent, DialogActions,
} from '@mui/material';
import FilterAltIcon from '@mui/icons-material/FilterAlt';
import SearchIcon from '@mui/icons-material/Search';
import ChangePassword from '../ChangePassword';
import FlagIcon from '@mui/icons-material/Flag';
import LockResetIcon from '@mui/icons-material/LockReset';
import { fetchUserData } from '../../utils/apiUtils';
import TraineeTable from './TraineeTable';

export default function TraineeHomePage() {
	const [openChangePasswordModal, setOpenChangePasswordModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [selectedActivity, setSelectedActivity] = useState('');
	const [traineeData, setTraineeData] = useState("");

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
						setOpenChangePasswordModal(true);
					}}
					startIcon={<LockResetIcon />}
					size="medium"
				>
					Change Password
				</Button>
			</Box>

			<Box sx={{
				mt: 4,
				display: "flex",
				justifyContent: "space-between",
				gap: 6
			}}>
				<Typography variant="h1" sx={{fontSize:32}}>List of Trainees
				</Typography>
				<Box sx={{
					display: "flex",
					gap: 2
				}}>
					<Button
						variant="contained"
						color="primary"
						startIcon={<FlagIcon />}
						size="small"
					>
						Flag
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

			<TraineeTable />

			<Dialog open={openChangePasswordModal}>
				<DialogContent>
					<ChangePassword selectedActivity={selectedActivity}
						setOpenModal={setOpenChangePasswordModal} />
				</DialogContent>
				<DialogActions ></DialogActions>
			</Dialog>
		</Container >
	);
}