import React, { useState, useCallback } from 'react';
import {
    Button,
    Container,
    IconButton,
    Typography,
    Box,
    Tooltip,
} from '@mui/material';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import SaveAsIcon from '@mui/icons-material/SaveAs';
import VisibilityIcon from '@mui/icons-material/Visibility';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import { useData } from '../hooks/contexts/DataContext';
import { useNavigate } from 'react-router-dom';

const UserAccounts = () => {
    const { setSelectedUser } = useData();
    const navigate = useNavigate();
    const [editRowsModel, setEditRowsModel] = useState({});

    const handleEditCellChange = useCallback(
        ({ id, field, props }) => {
            const data = props;
            setEditRowsModel((state) => {
                return { ...state, [id]: data };
            });
        }, []);

    const handleSave = useCallback((id) => {
        const editData = editRowsModel[id];
        if (editData) {
            console.log('Save Data for row: ', id, 'Data: ', editData);
        }
    }, [editRowsModel]);

    const columns = [
        { field: 'username', headerName: 'Username', width: 300, editable: true },
        { field: 'email', headerName: 'Email', width: 300, editable: true },
        { field: 'role', headerName: 'Role', width: 300, editable: true },
        {
            field: 'edit',
            headerName: 'Edit',
            width: 100,
            renderCell: (params) => {
                const onClick = (e) => {
                    e.stopPropagation();
                    handleSave(params.row.id);
                };

                return (
                    <Tooltip title="Edit details and quick save">
                        <IconButton
                            onClick={onClick}
                        >
                            <SaveAsIcon />
                        </IconButton >
                    </Tooltip >
                )
            },
        },
        {
            field: 'view',
            headerName: 'View',
            width: 100,
            renderCell: (params) => {
                const onClick = (e) => {
                    e.stopPropagation();
                    const newUser = {
                        id: params.row.id,
                        username: params.row.username,
                        email: params.row.email,
                        role: params.row.role,
                    };

                    setSelectedUser(newUser);
                    navigate('/user_form', { state: { isNewUser: false } });
                };

                return (
                    <Tooltip title="View and edit details, including password changes">
                        <IconButton
                            onClick={onClick}
                        >
                            <VisibilityIcon />
                        </IconButton>
                    </Tooltip>
                )
            },
        }
    ];

    const rows = [
        { id: 1, username: 'user1', email: 'user1@mail.com', role: 'admin' },
        { id: 2, username: 'user2', email: 'user2@mail.com', role: 'trainee' },
        { id: 3, username: 'user3', email: 'user3@mail.com', role: 'trainer' },
        { id: 4, username: 'user4', email: 'user4@mail.com', role: 'trainee' },
        { id: 5, username: 'user5', email: 'user5@mail.com', role: 'trainer' },
        { id: 6, username: 'user6', email: 'user6@mail.com', role: 'trainee' },
        { id: 7, username: 'user7', email: 'user7@mail.com', role: 'trainer' },
        { id: 8, username: 'user8', email: 'user8@mail.com', role: 'trainee' },
        { id: 9, username: 'user9', email: 'user9@mail.com', role: 'trainer' },
        { id: 10, username: 'user10', email: 'user10@mail.com', role: 'trainee' },
        { id: 11, username: 'user11', email: 'user11@mail.com', role: 'trainer' },
        { id: 12, username: 'user12', email: 'user12@mail.com', role: 'trainee' },
    ];

    return (
        <Container
            maxWidth="lg"
            sx={{
                mt: 4,
                mb: 4,
            }}
        >
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    mb: 2,
                }}
            >
                <Typography
                    variant='h5'
                    sx={{
                        flex: 1,
                        color: 'primary.main',
                    }}
                >
                    User Accounts
                </Typography>
                <Button
                    startIcon={<PersonAddIcon />}
                    sx={{
                        alignSelf: 'flex-end',
                    }}
                    onClick={() => {
                        setSelectedUser(null);
                        navigate('/user_form', { state: { isNewUser: true } })
                    }}
                >
                    <Typography
                        sx={{
                            flex: 1,
                            fontSize: '1.1rem',
                            color: 'primary.main',
                            mr: 1,
                        }}
                    >
                        Add User
                    </Typography>
                </Button>
            </Box>
            <DataGrid
                rows={rows}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: { pageSize: 10 },
                    },
                }}
                editMode='row'
                pageSizeOptions={[5, 10, 15, 20]}
                slots={{
                    toolbar: GridToolbar,
                }}
                disableRowSelectionOnClick
                onEditCellChange={handleEditCellChange}
            />
        </Container>
    )
}

export default UserAccounts;