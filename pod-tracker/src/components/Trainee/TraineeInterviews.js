import React, { useState, useEffect } from "react";
import { Typography, TextField, Button, } from "@mui/material";
import {
	Select, MenuItem,
	Stack,
} from '@mui/material';
import { fetchAccountManagers, fetchClients } from "../../utils/apiUtils";
import { useUser } from "../../hooks/contexts/UserContext";

export default function TraineeInterviews(props) {
	const { bearToken, setBearToken } = useUser();
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const values = props.values
	const [accountManagers, setAccountManagers] = useState([]);
	const [clients, setClients] = useState([]);

	useEffect(() => {
		setOpenUpdateModal(props.selectedActivity === "Interviews");
	}, [props.selectedActivity]);

	useEffect(() => {
		fetchAccountManagers(bearToken)
			.then(data => setAccountManagers(data))
			.catch(error => alert(error.message));

		fetchClients(bearToken)
			.then(data => setClients(data))
			.catch(error => alert(error.message));


	}, [])

	return (
		<>
			{props.selectedActivity == "Interviews" && (
				<>
					<TextField
						type="text"
						variant='outlined'
						color='secondary'
						label="Position/Role"
						fullWidth
						required
						onChange={
							e=>values.position = (e.target.value)
						}
					/>
					<Stack spacing={2} direction="row"  >
						<Typography variant="h7" sx={{
							width: 150,
						}} >Account Manager</Typography>
						<Select sx={{
							width: 350, height: 30,
						}}
							onChange={
								e => values.accountManager = (e.target.value)
							}
						>
							<MenuItem disabled value="">
								Select Account Manager
							</MenuItem>

							{accountManagers.map((accountManager) =>

								<MenuItem value={accountManager}>
									{accountManager.name}
								</MenuItem>

							)}

						</Select>

					</Stack>
					<Stack spacing={2} direction="row" >
						<Typography variant="h7" sx={{
							width: 150,
						}}>Client</Typography>
						<Select sx={{
							width: 350, height: 30,
						}}
							onChange={
								e => values.client = (e.target.value)
							}
						>
							<MenuItem disabled value="">
								Select Client
							</MenuItem>

							{clients.map((client) =>

								<MenuItem value={client}>
									{client.name}
								</MenuItem>

							)}

						</Select>
					</Stack>
					<Stack spacing={2} direction="row" >
						<TextField
							type="number"
							variant='outlined'
							color='secondary'
							label="Hours"
							fullWidth
							required
							inputProps={{
								step: 1,
								min: 0
							}}
							onChange={
								e=>values.hours = (e.target.value)
							}
							>
						
						/</TextField>
			
						<TextField
							type="date"
							variant='outlined'
							color='secondary'
							label="Date"
							fullWidth
							required
							InputLabelProps={{
								shrink: true,
							}}
							InputProps={{
								inputProps: {
									placeholder: ''
								}
							}
						}
						
						/>
					</Stack>
					<TextField
						type="text"
						variant='outlined'
						color='secondary'
						label="Outcome"
						fullWidth
						required
					
					onChange={
						e=>values.outcome = (e.target)
					}
					/>
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
					TextField
					onChange={
						e=>values.feedback = (e.target.value)
					}>

					
					</TextField>
										<TextField
						type="text"
						variant='outlined'
						color='secondary'
						label="Preparation Done for the Interview"
						fullWidth
						required
					
					onChange={
						e=>values.interviewPrep = (e.target.value)
					}
					/>
				</>
			)}
		</>
	)
}