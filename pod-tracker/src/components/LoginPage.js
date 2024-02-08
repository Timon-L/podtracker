import React, { useEffect, useState } from "react";
import {
	Container,
	Typography,
	TextField,
	Button,
	ButtonGroup,
	Avatar,
	Box,
	Link,
} from "@mui/material";
import { useTheme } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useNavigate, useLocation } from 'react-router-dom';
import { fetchToken, fetchUserData } from "../utils/apiUtils";
import { useUser } from "../hooks/contexts/UserContext";
import AlertSnackbar from "./AlertSnackbar";

function Copyright() {
	return (
		<Typography
			variant="body2"
			color="text.secondary"
			align="center"
		>
			{"Copyright © "}
			Team 1{" "}
			{new Date().getFullYear()}
			{"."}
		</Typography>
	);
}

const LoginPage = () => {
	const theme = useTheme();
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const { user, setBearToken, setUser } = useUser();
	const [displayError, setDisplayError] = useState(false);
	const navigate = useNavigate();

	const tryLogin = async () => {
		try {
			const token = await fetchToken(username, password);
			setBearToken(token);

			const userData = await fetchUserData(token, username);
			setUser(userData);

			const message = {
				type: "success",
				text: "Login successful. Welcome " + userData.name,
				toPage: "homepage"
			}
			
			setDisplayError(false);
			sessionStorage.setItem('message', JSON.stringify(message));

			if (userData.role.toLowerCase() === "admin") {
				navigate("/admin_dashboard");
			}
			else if (userData.role.toLowerCase() === "trainer") {
				navigate("/trainers");
			}
			else if (userData.role.toLowerCase() === "trainee") {
				navigate("/trainees");
			}
			else {
				navigate("/permission_denied");
			}

		} catch (error) {
			const message = {
				type: "error",
				text: error.message,
				toPage: "login"
			}

			setDisplayError(true);
			sessionStorage.setItem('message', JSON.stringify(message));
		}
	}

	useEffect(() => {
		setUsername("");
		setBearToken("");
		setUser("");
		sessionStorage.removeItem('user');
		sessionStorage.removeItem('bearToken');
	}, []);

	return (
		<Container component="main" maxWidth="xs">
			<Box
				sx={{
					marginTop: 8,
					display: 'flex',
					flexDirection: 'column',
					alignItems: 'center',
				}}
			>
				<Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
					<LockOutlinedIcon />
				</Avatar>
				<Typography
					variant="h5"
					sx={{ mt: 2, mb: 2 }}
					align="center"
				>
					Login Page
				</Typography>
				<TextField
					margin="normal"
					required
					fullWidth
					id="username"
					label="Username"
					value={username}
					onChange={e => setUsername(e.target.value)}
					autoFocus
				/>
				<TextField
					margin="normal"
					required
					fullWidth
					name="password"
					label="Password"
					type="password"
					value={password}
					onChange={e => setPassword(e.target.value)}
				/>
				<ButtonGroup
					sx={{
						mt: 3,
						mb: 2,
						'& .MuiButton-root': {
							textTransform: 'none',
							width: 200,
							mx: 1
						},
						'& .MuiButton-containedPrimary': {
							backgroundColor: theme.palette.primary.main,
							'&:hover': {
								backgroundColor: theme.palette.primary.dark,
							}
						}
					}}
				>
					<Button variant="contained" color="primary" onClick={tryLogin}>
						Login
					</Button>
				</ButtonGroup>
				<Link href="#" variant="body2">
					Forgot password?
				</Link>
			</Box>
			<AlertSnackbar page={"login"} displayError={displayError} setDisplayError={setDisplayError}/>
		</Container>
	);
}

export default LoginPage;