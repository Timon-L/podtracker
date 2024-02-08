import React, { useEffect, useState } from 'react';
import { addNewAccountManagers, fetchAccountManagersTwo, deleteAccountManager } from "../utils/apiUtils";
import { useUser } from "../hooks/contexts/UserContext";
import {
    Container,
    Typography,
    Box,
    Button,
} from '@mui/material';
import {
    DataGrid,
    GridRowModes,
    GridRowEditStopReasons,
    GridToolbarContainer,
    GridActionsCellItem,
} from '@mui/x-data-grid';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/DeleteOutlined';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Close';

function EditToolbar(props) {
    const { accountManagers, setAccountManagers, setRowModesModel } = props;
    const handleClick = () => {
        console.log(accountManagers);
        const id = Math.max(0, ...accountManagers.map((accountManager) => accountManager.id)) + 1;
        setAccountManagers((oldAccountManagers) => [...oldAccountManagers, { id, name: '', location: '' }])
        setRowModesModel((oldModel) => ({
            ...oldModel,
            [id]: { mode: GridRowModes.Edit, fieldToFocus: 'name' },
        }));
    };

    return (
        <GridToolbarContainer>
            <Button color='primary' startIcon={<PersonAddIcon />} onClick={handleClick}>
                Add Account Manager
            </Button>
        </GridToolbarContainer>
    )
}

const AccountManagers = () => {
    const [accountManagers, setAccountManagers] = useState([]);
    const { bearToken } = useUser();
    const [rowModesModel, setRowModesModel] = useState({});
    const [paginationModel, setPaginationModel] = useState({
        pageSize: 100,
        page: 0
    });

    useEffect(() => {
        if (accountManagers.length === 0) {
            fetchAccountManagersTwo(bearToken)
                .then(data => setAccountManagers(data))
                .catch(error => alert(error.message));
        }
    }, [bearToken]);

    const handleRowEditStop = (params, event) => {
        if (params.reason === GridRowEditStopReasons.rowFocusOut) {
            event.defaultMuiPrevented = true;
        }
    };

    const handleEditClick = (id) => () => {
        setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
    };

    const handleSaveClick = (id) => () => {
        setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
    };

    const handleDeleteClick = (id) => () => {
        setAccountManagers(accountManagers.filter((accountManager) => accountManager.id !== id));
    };

    const handleCancelClick = (id) => () => {
        setRowModesModel({
            ...rowModesModel,
            [id]: { mode: GridRowModes.View, ignoreModifications: true },
        });

        const editedAccountManager = accountManagers.find((accountManager) => accountManager.id === id);
        if (editedAccountManager.isNew) {
            setAccountManagers(accountManagers.filter((accountManager) => accountManager.id !== id));
        }
    }

    const processRowUpdate = (newAccountManager) => {
        const updatedAccountManager = { ...newAccountManager, isNew: false };
        addNewAccountManagers(bearToken, updatedAccountManager)
            .then(data => console.log(data))
            .catch(error => alert(error.message));

        setAccountManagers(accountManagers.map((accountManager) =>
            (accountManager.id === updatedAccountManager.id ? updatedAccountManager : accountManager)));
        return updatedAccountManager;
    };

    const handleRowModesModelChange = (newRowModesModel) => {
        setRowModesModel(newRowModesModel);
    };

    const columns = [
        { field: 'id', headerName: 'ID', width: 100 },
        { field: 'name', headerName: 'Name', width: 350, editable: true },
        {
            field: 'location',
            headerName: 'Location',
            width: 350,
            type: 'singleSelect',
            valueOptions: ['VIC_Melbourne', 'NSW_Sydney'],
            editable: true
        },
        {
            field: 'actions',
            type: 'actions',
            headerName: 'Actions',
            cellClassName: 'actions',
            width: 120,
            getActions: ({ id }) => {
                const isInEditMode = rowModesModel[id]?.mode === GridRowModes.Edit;

                if (isInEditMode) {
                    return [
                        <GridActionsCellItem
                            icon={<SaveIcon />}
                            label="Save"
                            sx={{
                                color: 'primary.main',
                            }}
                            onClick={handleSaveClick(id)}
                        />,
                        <GridActionsCellItem
                            icon={<CancelIcon />}
                            label="Cancel"
                            className="textPrimary"
                            onClick={handleCancelClick(id)}
                            color="inherit"
                        />,
                    ];
                }

                return [
                    <GridActionsCellItem
                        icon={<EditIcon />}
                        label="Edit"
                        className="textPrimary"
                        onClick={handleEditClick(id)}
                        color="inherit"
                    />,
                ];
            },
        },
    ]

    return (
        <Container
            maxWidth='lg'
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
                    Account Managers
                </Typography>
            </Box>
            <DataGrid
                rows={accountManagers}
                columns={columns}
                editMode='row'
                rowModesModel={rowModesModel}
                onRowModesModelChange={handleRowModesModelChange}
                onRowEditStop={handleRowEditStop}
                processRowUpdate={processRowUpdate}
                paginationModel={paginationModel}
                onPaginationModelChange={setPaginationModel}
                pageSizeOptions={[20, 40, 60, 100]}
                slots={{
                    toolbar: EditToolbar,
                }}
                slotProps={{
                    toolbar: {
                        accountManagers,
                        setAccountManagers,
                        setRowModesModel,
                    },
                }}
            />
        </Container>
    )
}

export default AccountManagers;