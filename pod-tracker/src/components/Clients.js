import React, { useEffect, useState } from 'react';
import { fetchClients, addNewClient } from '../utils/apiUtils';
import { useUser } from '../hooks/contexts/UserContext';
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
    const { clients, setClients, setRowModesModel } = props;

    const handleClick = () => {
        const id = Math.max(0, ...clients.map((client) => client.id)) + 1;

        setClients((oldClients) => [...oldClients, { id, name: '', state: '', city: '' }])
        setRowModesModel((oldModel) => ({
            ...oldModel,
            [id]: { mode: GridRowModes.Edit, fieldToFocus: 'name' },
        }));
    };

    return (
        <GridToolbarContainer>
            <Button color='primary' startIcon={<PersonAddIcon />} onClick={handleClick}>
                Add Client
            </Button>
        </GridToolbarContainer>
    )
}

const Clients = () => {
    const [clients, setClients] = useState([]);
    const { bearToken } = useUser();
    const [rowModesModel, setRowModesModel] = useState({});
    const [paginationModel, setPaginationModel] = useState({
        pageSize: 100,
        page: 0
    });

    useEffect(() => {
        if (clients.length === 0) {
            fetchClients(bearToken)
                .then(data => setClients(data))
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
        setClients(clients.filter((client) => client.id !== id));
    };

    const handleCancelClick = (id) => () => {
        setRowModesModel({
            ...rowModesModel,
            [id]: { mode: GridRowModes.View, ignoreModifications: true },
        });

        const editedClient = clients.find((client) => client.id === id);
        if (editedClient.isNew) {
            setClients(clients.filter((client) => client.id !== id));
        }
    };

    const processRowUpdate = (newClient) => {
        const updatedClient = { ...newClient, isNew: false };
        addNewClient(bearToken, updatedClient)
            .then(data => console.log(data))
            .catch(error => alert(error.message));

        setClients(clients.map((client) => (client.id === updatedClient.id ? updatedClient : client)));
        return updatedClient;
    };

    const handleRowModesModelChange = (newRowModesModel) => {
        setRowModesModel(newRowModesModel);
    };

    const columns = [
        { field: 'id', headerName: 'ID', width: 100 },
        { field: 'name', headerName: 'Name', width: 300, editable: true },
        {
            field: 'state',
            headerName: 'State',
            width: 300,
            type: 'singleSelect',
            valueOptions: ['NSW', 'VIC', 'QLD', 'WA', 'SA', 'TAS', 'NT', 'ACT'],
            editable: true
        },
        {
            field: 'city',
            headerName: 'City',
            width: 200,
            type: 'singleSelect',
            valueOptions: ['Sydney', 'Melbourne', 'Brisbane', 'Perth', 'Adelaide', 'Hobart', 'Darwin', 'Canberra'],
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
                    Clients
                </Typography>
            </Box>
            <DataGrid
                rows={clients}
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
                        clients,
                        setClients,
                        setRowModesModel,
                    },
                }}
            />
        </Container>
    )
}

export default Clients;