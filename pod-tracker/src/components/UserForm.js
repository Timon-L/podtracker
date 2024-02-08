import React, { useState } from 'react';
import {
    Box,
    Button,
    TextField,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    Typography,
    IconButton,
    Tooltip,
} from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useData } from '../hooks/contexts/DataContext';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { useLocation, useNavigate } from 'react-router-dom';

const UserForm = () => {
    const { selectedUser, setSelectedUser } = useData();
    const location = useLocation();
    const isNewUser = location.state.isNewUser;
    const navigate = useNavigate();

    const [userData, setUserData] = useState({
        username: selectedUser?.username || '',
        email: selectedUser?.email || '',
        password: selectedUser?.password || '',
        confirmPassword: '',
        role: selectedUser?.role || '',
        pondStartDate: null,
    });

    const handleChange = (e) => {
        setUserData({
            ...userData,
            [e.target.name]: e.target.value,
        });
    };

    const handleDateChange = (date) => {
        setUserData({
            ...userData,
            pondStartDate: date,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(userData);
    };

    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ maxWidth: 400, margin: 'auto', mt: 4 }}>
            <Typography variant="h6" gutterBottom>
                <Tooltip title="Back to User Accounts Page">
                    <IconButton
                        onClick={() => {
                            setSelectedUser(null);
                            navigate('/user_accounts')
                        }}
                    >
                        <ArrowBackIcon />
                    </IconButton>
                </Tooltip>
                {(isNewUser) ? "Add New User" : "Edit User Information"}
            </Typography>
            <TextField
                label="Username"
                variant="outlined"
                name="username"
                value={userData.username}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Email"
                variant="outlined"
                name="email"
                type="email"
                value={userData.email}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label={(isNewUser) ? "Create Password" : "New Password"}
                variant="outlined"
                name="password"
                type="password"
                value={userData.password}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label={(isNewUser) ? "Confirm Password" : "Confirm New Password"}
                variant="outlined"
                name="confirmPassword"
                type="password"
                value={userData.confirmPassword}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel>Role</InputLabel>
                <Select
                    label="Role"
                    name="role"
                    value={userData.role}
                    onChange={handleChange}
                >
                    <MenuItem value="admin">Admin</MenuItem>
                    <MenuItem value="trainee">Trainee</MenuItem>
                    <MenuItem value="trainer">Trainer</MenuItem>
                </Select>
            </FormControl>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker
                    label="Pond Start Date"
                    value={userData.pondStartDate}
                    onChange={handleDateChange}
                    renderInput={(params) => <TextField {...params} fullWidth margin="normal" variant="outlined" />}
                    sx={{
                        display: (userData.role.toLowerCase() === 'trainee') ? 'block' : 'none',
                    }}
                />
            </LocalizationProvider>
            <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
                Submit
            </Button>
        </Box>
    );
};

export default UserForm;