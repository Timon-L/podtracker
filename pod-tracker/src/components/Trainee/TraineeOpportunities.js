import React, { useState, useEffect } from "react";
import { Container, Typography, TextField, Button, Stack, MenuItem, Select } from "@mui/material";
import { fetchAccountManagers, fetchClients } from "../../utils/apiUtils";
import { useUser } from "../../hooks/contexts/UserContext";

export default function TraineeOpportunities(props) {
	const { bearToken } = useUser();
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [accountManagers, setAccountManagers] = useState([]);
	const [clients, setClients] = useState([]);
	const values = props.values;

	useEffect(() => {
		setOpenUpdateModal(props.selectedActivity === "Opportunities");
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
			{props.selectedActivity == "Opportunities" && (
				<>
					<TextField
						type="text"
						variant='outlined'
						color='secondary'
						label="Position/Role"
						fullWidth
						required
						onChange={
							e => values.role = (e.target.value)
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
					<TextField
						multiline
						rows={4}
						maxRows={10}
						type="text"
						variant='outlined'
						color='secondary'
						label="Discussion Result"
						fullWidth
						required
						onChange={
							e => values.discussion = (e.target.value)
						}
					/>
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
							e => values.hours = (e.target.value)
						}
					/>
				</>
			)}
		</>
	)

}