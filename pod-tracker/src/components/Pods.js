import React, { useEffect, useState } from 'react';
import {
    Container,
    Typography,
    Button,
    Modal,
    IconButton,
} from '@mui/material';
import {
    DataGrid,
    GridRowModes,
    GridRowEditStopReasons,
    GridToolbarContainer,
    GridActionsCellItem,
} from '@mui/x-data-grid';
import { useUser } from '../hooks/contexts/UserContext';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/DeleteOutlined';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Close';
import { fetchPods, addPod } from '../utils/apiUtils';
import VisibilityIcon from '@mui/icons-material/Visibility';
import CloseIcon from '@mui/icons-material/Close';
import { usePods } from '../hooks/contexts/PodsContext';

function EditToolbar(props) {
    const { pods, setPods, setRowModesModel } = props;

    const handleClick = () => {
        const podId = Math.max(0, ...pods.map((pod) => pod.podId)) + 1;

        setPods((oldPods) => [...oldPods, {
            podId, 
            podName: '',
            primaryTrainerId: {
                email: '',
                name: '',
                role: "TRAINER",
                username: '',
            },
            secondaryTrainerId: {
                email: '',
                name: '',
                role: "TRAINER",
                username: '',
            },
            project: {
                name: '',
                descriptionDate: '',
                startDate: '',
                completionDate: '',
            },
            trainees: [],
            capacity: 0,
        }])
        setRowModesModel((oldModel) => ({
            ...oldModel,
            [podId]: { mode: GridRowModes.Edit, fieldToFocus: 'podName' },
        }));
    };

    return (
        <GridToolbarContainer>
            <Button color='primary' startIcon={<PersonAddIcon />} onClick={handleClick}>
                Add Pod
            </Button>
        </GridToolbarContainer>
    )
}

const Pods = () => {
    const [pods, setPods] = useState([]);
    const [selectedPod, setSelectedPod] = useState(null);
    const { bearToken } = useUser();
    const [rowModesModel, setRowModesModel] = useState({});
    const [openModal, setOpenModal] = useState(false);
    const [paginationModel, setPaginationModel] = useState({
        pageSize: 100,
        page: 0
    });

    useEffect(() => {
        const loadPods = async () => {
            if (pods.length === 0) {
                const fetchedPods = await fetchPods(bearToken);
                if (fetchedPods && fetchedPods.length > 0) {
                    console.log(fetchedPods)
                    setPods(fetchedPods);
                }
            }
        };

        loadPods();
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
        setPods(pods.filter((pod) => pod.id !== id));
    }

    const handleCancelClick = (id) => () => {
        setRowModesModel({
            ...rowModesModel,
            [id]: { mode: GridRowModes.View, ignoreModifications: true },
        });

        const editedPod = pods.find((pod) => pod.podId === id);
        if (editedPod.isNew) {
            setPods(pods.filter((pod) => pod.podId !== id));
        }
    }

    const processRowUpdate = (newPod) => {
        const updatedPod = { ...newPod, isNew: false };
        /* addNewPod(bearToken, updatedPod)
            .then(data => console.log(data))
            .catch(error => alert(error.message)); */

        setPods(pods.map((pod) => (pod.podId === updatedPod.podId ? updatedPod : pod)));
        return updatedPod;
    }

    const handleRowModesModelChange = (newRowModesModel) => {
        setRowModesModel(newRowModesModel);
    }

    const handleViewDetails = (pod) => {
        setSelectedPod(pod);
        setOpenModal(true);
    }

    const handleCloseModal = () => {
        setOpenModal(false);
        setSelectedPod(null);
    }

    const columns = [
        { field: 'podId', headerName: 'Pod ID', width: 150, editable: true },
        { field: 'podName', headerName: 'Pod Name', width: 200, editable: true },
        {
            field: 'primaryTrainerName',
            headerName: 'Trainer 1',
            width: 200,
            valueGetter: (params) => params.row.primaryTrainerId.name
        },
        {
            field: 'secondaryTrainerName',
            headerName: 'Trainer 2',
            width: 200,
            valueGetter: (params) => params.row.secondaryTrainerId.name
        },
        { field: 'capacity', headerName: 'Capacity', width: 150, editable: true },
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
        {
            field: 'details',
            headerName: 'Details',
            width: 150,
            renderCell: (params) => (
                <Button
                    color="primary"
                    size="small"
                    sx={{ px: 1 }}
                    startIcon={<VisibilityIcon />}
                    onClick={() => handleViewDetails(params.row)}
                >
                    View Details
                </Button>
            ),
        },
    ];

    return (
        <Container maxWidth="xl">
            <Typography variant="h4" gutterBottom>
                Pods
            </Typography>
            <DataGrid
                rows={pods}
                getRowId={(row) => row.podId}
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
                        pods,
                        setPods,
                        setRowModesModel,
                    },
                }}
            />
            <Modal
                open={openModal}
                onClose={handleCloseModal}
                aria-labelledby="trainee-details-modal"
                aria-describedby="modal-to-display-trainee-details"
            >
                <div style={{
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    width: '80%',
                    maxHeight: '80%',
                    backgroundColor: 'white',
                    padding: '16px',
                    overflowY: 'auto'
                }}>
                    <IconButton onClick={handleCloseModal} style={{ float: 'right' }}>
                        <CloseIcon />
                    </IconButton>
                    {selectedPod ? (
                        <>
                            <h2>Pod Details for {selectedPod.podName}</h2>
                            <p><strong>Pod Name:</strong> {selectedPod.podName}</p>
                            <p><strong>Primary Trainer:</strong> {selectedPod.primaryTrainerId.name}</p>
                            <p><strong>Secondary Trainer:</strong> {selectedPod.secondaryTrainerId.name}</p>
                            <p><strong>Capacity:</strong> {selectedPod.capacity}</p>
                            <h3>Trainees:</h3>
                            <ul>
                                {selectedPod.trainees.map(trainee => (
                                    <li key={trainee.userId}>{trainee.name} ({trainee.stream})</li>
                                ))}
                            </ul>
                        </>
                    ) : (
                        <p>No pod details available</p>
                    )}
                </div>
            </Modal>
        </Container>
    );
}

export default Pods;
