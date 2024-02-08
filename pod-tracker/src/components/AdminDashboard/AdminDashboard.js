import React, { useEffect, useState } from 'react';
import {
    Container,
    Grid,
    Typography,
    Box,
    Drawer,
    List,
    ListItem,
    ListItemText,
    Collapse,
    IconButton,
    Divider,
}
    from '@mui/material';
import { ExpandLess, ExpandMore } from '@mui/icons-material';
import ChevronRightIcon from '@mui/icons-material/ChevronLeft';
import PersonRemoveIcon from '@mui/icons-material/PersonRemove';
import VisibilityIcon from '@mui/icons-material/Visibility';
import NotInPodTable from './NotInPodTable';
import RecentUpskillingTable from './RecentUpskillingTable';
import BarChart from './BarChart';
import AlertSnackBar from '../AlertSnackbar';
import InterviewTable from './InterviewTable';
import { useData } from '../../hooks/contexts/DataContext';
import { usePods } from '../../hooks/contexts/PodsContext';

const AdminDashboard = () => {
    const { setTraineesNotInPod } = useData();
    const { pods, removeFromPod } = usePods();
    const statusColor = new Map([
        ['In Progress', '#00b300'],
        ['Planning', '#ffcc00'],
        ['Completed', '#ff0000'],
    ])

    const [drawerOpen, setDrawerOpen] = useState(false);
    const [openPod, setOpenPod] = useState({});
    const [isScreenSmall, setIsScreenSmall] = useState(window.innerWidth < 1600);

    const handlePodClick = (pod) => {
        setOpenPod((prev) => ({ ...prev, [pod]: !prev[pod] }));
    };

    const handleRemoveTrainee = (pod, trainee) => {
        setTraineesNotInPod((prev) => [...prev, trainee]);
        removeFromPod(pod, trainee);
    }

    useEffect(() => {
        const handleResize = () => {
            setIsScreenSmall(window.innerWidth < 1200);
            if (window.innerWidth < 1200) {
                setDrawerOpen(false);
            }
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    const drawer = (
        <Box>
            <List>
                {Object.keys(pods).map((pod) => (
                    <Box key={pod}>
                        <ListItem onClick={() => handlePodClick(pod)}>
                            <ListItemText primary={pod} />
                            <Typography
                                sx={{
                                    color: statusColor.get(pods[pod].status),
                                    border: '1px solid',
                                    borderRadius: 5,
                                    px: 1,
                                    py: 0.5,
                                    fontSize: 10,
                                }}
                            >
                                Project status: {pods[pod].status}
                            </Typography>
                            {openPod[pod] ? <ExpandLess /> : <ExpandMore />}
                        </ListItem>
                        <Collapse in={openPod[pod]} timeout="auto" unmountOnExit>
                            <List component="div" disablePadding>
                                {pods[pod].trainees.map((trainee) => (
                                    <ListItem key={trainee.id} sx={{ pl: 4 }}>
                                        <ListItemText primary={trainee.name} />
                                        <IconButton>
                                            <VisibilityIcon />
                                        </IconButton>
                                        <IconButton
                                            onClick={() => handleRemoveTrainee(pod, trainee)}
                                        >
                                            <PersonRemoveIcon />
                                        </IconButton>
                                    </ListItem>
                                ))}
                            </List>
                        </Collapse>
                    </Box>
                ))}
            </List>
        </Box>
    );

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setDrawerOpen(open);
    }

    return (
        <Container
            maxWidth="xl"
            sx={{
                mt: 4,
                mb: 4,
                display: 'flex',
                flexDirection: 'column',
                width: '100%'
            }}
        >
            <Drawer
                variant={isScreenSmall ? 'temporary' : 'permanent'}
                sx={{
                    width: 340,
                    maxHeight: 'calc(100vh - 64)',
                    top: 64,
                    overflowY: 'auto',
                    flexShrink: 0,
                    overflowX: 'hidden',
                    '& .MuiDrawer-paper': {
                        width: 340,
                        maxHeight: 'calc(100vh - 64)',
                        top: 64,
                        overflowY: 'auto',
                        boxSizing: 'border-box',
                    },
                }}
                open={drawerOpen}
                onClose={toggleDrawer(false)}
            >
                <Box
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        mt: 1,
                        mb: 1,
                    }}
                >
                    <Typography
                        variant="h5"
                        sx={{
                            margin: 'auto',
                        }}
                    >
                        Pods
                    </Typography>
                    <IconButton
                        onClick={toggleDrawer(false)}
                        sx={{
                            display: (isScreenSmall) ? 'block' : 'none',
                            mr: 2,
                        }}
                    >
                        <ChevronRightIcon />
                    </IconButton>
                </Box>
                <Divider />
                {drawer}
            </Drawer>
            <Box
                sx={{
                    flexGrow: 1,
                    width: '100%',
                    ml: (isScreenSmall) ? 0 : 22,
                }}
            >
                <Grid container spacing={3}>
                    <Grid item xs={12} md={5} lg={5}>
                        <NotInPodTable toggleDrawer={toggleDrawer} isScreenSmall={isScreenSmall}/>
                    </Grid>
                    <Grid item xs={12} md={6} lg={6}>
                        <RecentUpskillingTable />
                    </Grid>
                    <Grid item xs={12} md={4} lg={4}>
                        <InterviewTable />
                    </Grid>
                    <Grid item xs={12} md={7} lg={7}>
                        <BarChart isScreenSmall={isScreenSmall} />
                    </Grid>
                </Grid>
            </Box>
            <AlertSnackBar page={"homepage"} />
        </Container>
    )
}

export default AdminDashboard;