import React, { useState, useEffect } from "react";
import { Link as RouterLink } from "react-router-dom";
import { Container, Typography, TextField, Button, ButtonGroup, Box,} from "@mui/material";

export default function ChangePassword({ selectedActivity, setOpenModal }) {
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [activity, setActivity] = useState("");
	useEffect(() => {
		setOpenUpdateModal(selectedActivity === "Change Password");
	}, [selectedActivity]);


	return (
		<>
			{selectedActivity === "Change Password" && (
				<Container
					component="main"
					maxWidth="xs"
					sx={{
						marginBottom: 3,
						display: "flex",
						flexDirection: "column",
						alignItems: "center",
						fontFamily: "monospace",
					}}>
					<Typography
						variant="h4"
						sx={{ mt: 4, mb: 2, fontWeight: 700 }}
						align="center"
					>
						Change Password
					</Typography>
					<TextField
						margin="normal"
						required
						fullWidth
						id="username"
						label="Username (FDM Email)"
						autoFocus
					/>
					<TextField
						margin="normal"
						required
						fullWidth
						id="oldPassword"
						name="oldPassword"
						label="Old Password"
						type="password"
					/>
					<TextField
						margin="normal"
						required
						fullWidth
						id="newPassword"
						name="newPassword"
						label="New Password"
						type="password"
					/>
					<TextField
						margin="normal"
						required
						fullWidth
						id="confirmPassword"
						name="confirmPassword"
						label="Confirm Password"
						type="password"
					/>
					<Box sx={{ display: "flex", gap: 2 }}>
						<Button variant="outlined" color="primary" sx={{ mt: 3 }} onClick={() => {
							setActivity("");
							setOpenModal(false);
						}}>
							Cancel
						</Button>
						<Button variant="contained" color="primary" type="submit" sx={{ mt: 3 }} onClick={() => {
							setActivity("");
							setOpenModal(false);
						}}>
							Submit
						</Button>
					</Box>
				</Container>
			)}
		</>
	)

}