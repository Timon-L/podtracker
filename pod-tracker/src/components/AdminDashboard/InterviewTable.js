import React, { useState } from "react";
import { useData } from "../../hooks/contexts/DataContext";
import {
    Paper,
    Tooltip,
    Box,
    Typography,
    Button,
    Modal,
    IconButton,
} from '@mui/material';
import { format, parseISO } from 'date-fns';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import OpenInFullIcon from '@mui/icons-material/OpenInFull';
import CloseIcon from '@mui/icons-material/Close';

const InterviewTable = () => {
    const { interviews } = useData();
    const [openModal, setOpdenModal] = useState(false);
    const upcomingInterviews = interviews.filter((interview) =>
        parseISO(interview.date) >= new Date()
    ).sort((a, b) => parseISO(a.date) - parseISO(b.date));
    const [paginationModel, setPaginationModel] = useState({
        pageSize: 4,
        page: 0
    });

    console.log("Upcoming interviews: ", upcomingInterviews);
    const columns = [
        { field: 'id', headerName: 'ID', width: 10 },
        {
            field: 'trainee',
            headerName: 'Name',
            width: 150,
            renderCell: (params) => (
                <Tooltip title={params.value || ''} arrow>
                    <Box
                        sx={{
                            textOverflow: 'ellipsis',
                            overflow: 'hidden',
                            whiteSpace: 'nowrap',
                            cursor: 'pointer',
                        }}
                    >
                        {params.value}
                    </Box>
                </Tooltip>
            )
        },
        {
            field: 'client',
            headerName: 'Client',
            width: 150,
            renderCell: (params) => (
                <Tooltip title={params.value || ''} arrow>
                    <Box
                        sx={{
                            textOverflow: 'ellipsis',
                            overflow: 'hidden',
                            whiteSpace: 'nowrap',
                            cursor: 'pointer',
                        }}
                    >
                        {params.value}
                    </Box>
                </Tooltip>
            )
        },
        {
            field: 'date',
            headerName: 'Interview Date',
            width: 130,
            renderCell: (params) => {
                const date = parseISO(params.value);
                return (
                    format(date, 'MMM d, yyyy')
                )
            }
        }
    ]

    const fullColumns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'date', headerName: 'Date', width: 150 },
        { field: 'trainee', headerName: 'Trainee', width: 150 },
        { field: 'accountManager', headerName: 'Account Manager', width: 200 },
        { field: 'client', headerName: 'Client', width: 150 },
        { field: 'outcome', headerName: 'Outcome', width: 150 },
    ]

    const handleCloseModal = () => {
        setOpdenModal(false);
    }

    return (
        <Paper>
            <Typography
                variant='h6'
                align='center'
                sx={{
                    pt: 2,
                    pb: 1,
                    fontWeight: 'bold',
                    color: 'primary.main',
                    backgroundColor: 'background.default',
                }}
            >
                Upcoming Interviews
            </Typography>
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'flex-end',
                    alignItems: 'center',
                    px: 1,
                }}
            >
                <Button
                    startIcon={<OpenInFullIcon />}
                    onClick={() => setOpdenModal(true)}
                >
                    Expand
                </Button>
            </Box>
            <DataGrid
                rows={upcomingInterviews}
                columns={columns}
                paginationModel={paginationModel}
                onPaginationModelChange={setPaginationModel}
                pageSizeOptions={[4, 8, 12]}
                disableSelectionOnClick
                slots={{
                    toolbar: GridToolbar,
                }}
                density='compact'
            />
            <Modal
                open={openModal}
                onClose={handleCloseModal}
                aria-labelledby="interview-details-modal"
                aria-describedby="modal-to-display-interview-details"
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
                    <h2 id="interview-details-modal">Upcoming Interview Details</h2>
                    <div style={{ height: 400, width: '100%' }}>
                        <DataGrid
                            rows={upcomingInterviews}
                            slots={{ toolbar: GridToolbar }}
                            columns={fullColumns}
                            pageSize={5}  
                            checkboxSelection={true}
                        />
                    </div>
                </div>
            </Modal>
        </Paper>
    )
}

export default InterviewTable;