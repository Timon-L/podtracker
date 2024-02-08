import React, { useState, } from "react";
import { Link } from "react-router-dom";
import { Typography, Button } from "@mui/material";
import {
	Box,
	Paper,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
} from '@mui/material';


export default function TraineeTable() {

	return (
		<TableContainer component={Paper} sx={{ mt: 3, }}>
			<Table>
				<TableHead>
					<TableRow>
						<TableCell sx={{ fontWeight: "700", }}>
							<Typography variant="h6">Trainee</Typography>
						</TableCell>
						<TableCell sx={{ fontWeight: "700", }}>
							<Typography variant="h6">Pod</Typography>
						</TableCell>
						<TableCell sx={{ fontWeight: "700", }}>
							<Typography variant="h6">UpSkilling</Typography>
						</TableCell>
						<TableCell sx={{ fontWeight: "700" }}>
							<Typography variant="h6">Days in Pond</Typography>
						</TableCell>
						<TableCell sx={{ fontWeight: "700", }}>
							<Typography variant="h6">Flag</Typography>
						</TableCell>
					</TableRow>
				</TableHead>
				<TableBody>
					<TableCell>
						<Button>
							<Link to="/trainee_info"
							style={{ textDecoration: 'none' }}
							>
							Harry Potter</Link>
						</Button>
					</TableCell>
					<TableCell>pod1</TableCell>
					<TableCell>AWS</TableCell>
					<TableCell>15</TableCell>
					<TableCell>a flag</TableCell>
				</TableBody>
			</Table>
		</TableContainer>
	)
}