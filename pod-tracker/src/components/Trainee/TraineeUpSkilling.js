import React, { useState, useEffect } from "react";
import { TextField, Button, Stack, } from "@mui/material";

export default function TraineeUpSkilling(props) {
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const values = props.values

	useEffect(() => {
		setOpenUpdateModal(props.selectedActivity === "UpSkilling");
	}, [props.selectedActivity]);

	return (
		<>
			{props.selectedActivity == "UpSkilling" && (
				<>
					<Stack spacing={2} direction="row" >
						<TextField
							type="text"
							variant='outlined'
							color='secondary'
							label="Skill"
							fullWidth
							required
							onChange={
								e=>values.skill = (e.target.value)
							}
							
						/>
						<TextField
							type="date"
							variant='outlined'
							color='secondary'
							label="Target Date"
							fullWidth
							required
							InputLabelProps={{
								shrink: true,
							}}
							InputProps={{
								inputProps: {
									placeholder: ''
								}
							}}
							onChange={
								e=>values.targetDate = (e.target.value)
							}
						/>
					</Stack>
					<TextField
						multiline
						rows={4}
						maxRows={10}
						type="text"
						variant='outlined'
						color='secondary'
						label="Reason for Upskilling"
						fullWidth
						required
						onChange={
							e=>values.reason = (e.target.value)
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
							e=>values.hours = (e.target.value)
						}
					/>
					<TextField
						multiline
						rows={4}
						maxRows={10}
						type="text"
						variant='outlined'
						color='secondary'
						label="Achievement today"
						fullWidth
						required
						onChange={
							e=>values.achievement = (e.target.value)
						}
					/>
				</>
			)}
		</>
	)

}