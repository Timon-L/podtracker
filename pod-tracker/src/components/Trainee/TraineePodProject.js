import React, { useState, useEffect } from "react";
import { TextField, Button, Stack, Select, MenuItem } from "@mui/material";
import { useUser } from "../../hooks/contexts/UserContext";
import { fetchPodProjects, fetchPods } from "../../utils/apiUtils";

export default function TraineePodProject(props) {
	const { bearToken } = useUser();
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [pods, setPods] = useState([]);	
	const [projects, setProjects] = useState([]);
	const values = props.values

	useEffect(() => {
		setOpenUpdateModal(props.selectedActivity === "Pod Project");
	}, [props.selectedActivity]);

	useEffect(() => {
		fetchPods(bearToken)
			.then(data => setPods(data))

			
		fetchPodProjects(bearToken)
		.then(data => setProjects(data))

			
		// fetchProjects(bearToken)
		// 	.then(data => setPods(data))



		// alert(fetchPods(bearToken))
		// if (p != null)
		// {
		// 	setPods()
		// }

	}, [])

	return (
		<>
			{props.selectedActivity == "Pod Project" && (
				<>
					<Stack spacing={2} direction="row" >
						<Select fullWidth
							onChange={
								e => values.pod = (e.target.value)
							}>
							<MenuItem disabled value="">
								Select Pod
							</MenuItem>

							{pods.map((pod) =>

								<MenuItem value={pod.podId}>
									{pod.podName}
								</MenuItem>

							)}

						</Select>
						<Select fullWidth
							onChange={
								e => values.project = (e.target.value)
							}>
							<MenuItem disabled value="">
								Select Project
							</MenuItem>
							{projects.map((project) =>

								<MenuItem value={project}>
									{project.name}
								</MenuItem>

							)}
						</Select>
					</Stack>
					<Stack spacing={2} direction="row">
						<TextField
							InputLabelProps={{ shrink: true }}
							type="date"
							variant='outlined'
							color='secondary'
							label="Start date"
							fullWidth
							required
							onChange={
								e => values.startDate = (e.target.value)
							}
						/>


						<TextField
							InputLabelProps={{ shrink: true }}
							type="date"
							variant='outlined'
							color='secondary'
							label="Estimated completion date"
							fullWidth
							required
							onChange={
								e => values.etaCompletionDate = (e.target.value)
							}
						/>

					</Stack>
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
					<TextField
						multiline
						rows={4}
						maxRows={10}
						type="text"
						variant='outlined'
						color='secondary'
						label="Tasks completed today"
						fullWidth
						required
						onChange={
							e => values.tasks = (e.target.value)
						}
					/>
					<Stack spacing={2} direction="row">
						<TextField
							type="text"
							variant='outlined'
							color='secondary'
							label="Skills developed today"
							fullWidth
							required
							onChange={
							e => values.skills = (e.target.value)
						}
						/>
					</Stack>
				</>
			)}
		</>
	)

}
