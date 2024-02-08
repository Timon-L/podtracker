import React, { useState, useEffect } from "react";
import { Container, Typography, TextField, Button, } from "@mui/material";

export default function TraineeComment(props) {
	const [openUpdateModal, setOpenUpdateModal] = useState(false);
	const [errorMsg, setErrorMsg] = useState("");
	const [value, setValue] = props.value;

	useEffect(() => {
		setOpenUpdateModal(props.selectedActivity === "Comment");
	}, [props.selectedActivity]);
	


	return (
		<>
			{props.selectedActivity == "Comment" && (
				<TextField
					multiline
					rows={4}
					maxRows={10}
					type="text"
					variant='outlined'
					color='secondary'
					label="Leave a Comment"
					fullWidth
					required
					value={value}
					onChange={
						e=>setValue(e.target.value)
					}
				/>
			)}
		</>
	)

}