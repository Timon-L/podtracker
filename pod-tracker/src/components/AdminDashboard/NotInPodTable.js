import React, { useState } from 'react'
import { 
    Paper, 
    Tooltip, 
    Box, 
    Typography, 
    Select, 
    InputLabel, 
    FormControl, 
    MenuItem, 
    Button,
    IconButton
} from '@mui/material'
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { format } from 'date-fns';
import { useData } from '../../hooks/contexts/DataContext';
import { usePods } from '../../hooks/contexts/PodsContext';
import ViewListIcon from '@mui/icons-material/ViewList';

const NotInPodTable = ({toggleDrawer, isScreenSmall}) => {
    const { selectedUsers, traineesNotInPod, setSelectedUsers, setTraineesNotInPod } = useData();
    const [podName, setPodName] = useState('');
    const { pods, addToPod } = usePods();
    const podsNames = Object.keys(pods);
    const [paginationModel, setPaginationModel] = useState({
        pageSize: 5,
        page: 0
    });

    const columns = [
        { field: 'id', headerName: 'ID', width: 10 },
        {
            field: 'name',
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
            field: 'email',
            headerName: 'Email',
            width: 140,
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
            field: 'pondStartDate',
            headerName: 'Pond Start Date',
            width: 130,
            renderCell: (params) => {
                const date = params.value;
                return (
                    format(date, 'MMM d, yyyy')
                )
            }
        },
    ]

    const handleSelection = (selectionModel) => {
        const selectedData = traineesNotInPod.filter((row) =>
            selectionModel.includes(row.id)
        );

        setSelectedUsers(selectedData);
    };

    const handleAddToPod = () => {
        if (podName === '' || !podsNames.includes(podName)) return;
        addToPod(podName, selectedUsers);
        setTraineesNotInPod((prevTrainees) => {
            const newTrainees = prevTrainees.filter((trainee) => !selectedUsers.includes(trainee));
            const totalPages = Math.ceil(newTrainees.length / paginationModel.pageSize);
            if (paginationModel.page >= totalPages) {
                setPaginationModel((prev) => ({ ...prev, page: Math.max(totalPages - 1, 0) }));
            }
            return newTrainees;
        });
    }

    return (
        <Paper
            elevation={3}
        >
            <IconButton
                color="inherit"
                aria-label="open drawer"
                onClick={toggleDrawer(true)}
                edge="start"
                sx={{
                    display: (isScreenSmall) ? 'block' : 'none',
                    position: 'inherit',
                    ml: 1,
                }}
            >
                <ViewListIcon
                    fontSize='large'
                />
            </IconButton>
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
                Trainees Not In A Pod
            </Typography>
            <FormControl
                sx={{
                    minWidth: 140,
                    flexDirection: 'row',
                    ml: 2,
                }}
                size='small'
            >
                <InputLabel id="pod-select-label">Pod Name</InputLabel>
                <Select
                    id='pod-select'
                    labelId="pod-select-label"
                    label="Pod Name"
                    value={podName}
                    onChange={(e) => setPodName(e.target.value)}
                    sx={{
                        mb: 2,
                        minWidth: 120,
                    }}

                >
                    {podsNames.map((pod) => (
                        <MenuItem key={pod} value={pod}>
                            {pod}
                        </MenuItem>
                    ))}
                </Select>
                <Button
                    sx={{
                        ml: 2,
                        mb: 2,
                    }}
                    onClick={handleAddToPod}
                    disabled={podName === '' || !podsNames.includes(podName)}
                >
                    Add To Pod
                </Button>
            </FormControl>
            <DataGrid
                rows={traineesNotInPod}
                columns={columns}
                slots={{ toolbar: GridToolbar }}
                paginationModel={paginationModel}
                onPaginationModelChange={setPaginationModel}
                pageSizeOptions={[5, 10, 15, 20]}
                checkboxSelection
                onRowSelectionModelChange={handleSelection}
                density='compact'
            />
        </Paper>
    )
}

export default NotInPodTable