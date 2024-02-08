import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    Box,
    List,
    ListItem,
    IconButton,
    Drawer,
}
    from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import LogoutIcon from '@mui/icons-material/Logout';
import { useTheme } from '@mui/material';
import { useUser } from '../hooks/contexts/UserContext';

function Navbar() {
    const location = useLocation();
    const [drawerOpen, setDrawerOpen] = useState(false);
    const [isScreenSmall, setIsScreenSmall] = useState(window.innerWidth < 1000);
    const { user, logout } = useUser();
    const userRole = (user.role === null || user.role === undefined) ? "" : user.role.toLowerCase();
    const theme = useTheme();

    const menuItemsMap = new Map();

    if (userRole === 'admin') {
        menuItemsMap.set('/admin_dashboard',
            {
                label: 'Dashboard',
            }
        );
        menuItemsMap.set('/user_accounts',
            {
                label: 'Accounts'
            }
        );
        menuItemsMap.set('/trainers',
            {
                label: 'Trainees'
            }
        );
        menuItemsMap.set('/pods',
            {
                label: 'Pods'
            }
        );
        menuItemsMap.set('/clients',
            {
                label: 'Clients'
            }
        );
        menuItemsMap.set('/account_managers',
            {
                label: 'Account Managers'
            }
        );
    } else if (userRole === 'trainer') {
        menuItemsMap.set('/trainers',
            {
                label: 'Trainees'
            }
        );
    } else if (userRole === 'trainee') {
        menuItemsMap.set('/trainees',
            {
                label: 'Home'
            }
        );
    }

    useEffect(() => {
        const handleResize = () => {
            setIsScreenSmall(window.innerWidth < 1200);
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setDrawerOpen(open);
    }

    const renderMenu = () => (
        <Box role="presentation" onClick={toggleDrawer(false)} onKeyDown={toggleDrawer(false)}>
            <List>
                {Array.from(menuItemsMap).map(([key, item]) => (
                    <ListItem
                        key={key}
                        component={Link}
                        to={key}
                        sx={{
                            cursor: location.pathname === key ? 'not-allowed' : 'pointer',
                            fontFamily: 'Roboto, sans-serif',
                            textTransform: 'none',
                        }}
                        onMouseEnter={(event) => (event.currentTarget.style.backgroundColor = '#e0e0e0')}
                        onMouseLeave={(event) => (event.currentTarget.style.backgroundColor = 'transparent')}
                    >
                        {item.label}
                    </ListItem>
                ))}
            </List>
        </Box>
    );

    return (
        <Box>
            <AppBar
                position="fixed"
                sx={{
                    minHeight: 64,
                    display: (userRole === "" || userRole === undefined || userRole === null) ? 'none' : 'flex',
                }}
            >
                <Toolbar>
                    {isScreenSmall && (
                        <IconButton
                            edge="start"
                            color="inherit"
                            aria-label="menu"
                            onClick={toggleDrawer(true)}
                            sx={{ mr: 1 }}
                        >
                            <MenuIcon />
                        </IconButton>
                    )}
                    <Typography variant="h6" style={{ flexGrow: 1 }}>
                        {userRole.toUpperCase()}
                    </Typography>
                    {!isScreenSmall &&
                        Array.from(menuItemsMap).map(([key, item]) => (
                            <Button
                                color="inherit"
                                key={key}
                                component={Link}
                                to={key}
                                disabled={location.pathname === key}
                                sx={{
                                    textTransform: 'none',
                                    fontFamily: 'Roboto, sans-serif',
                                    borderBottom: location.pathname === key ? '2px solid white' : 'none', 
                                    '&:hover': {
                                        backgroundColor: 'rgba(255, 255, 255, 0.1)' 
                                    },
                                    ml: 2,
                                    fontSize: '1.1rem',
                                }}
                            >
                                {item.label}
                            </Button>
                        ))}
                    <Button
                        variant='outlined'
                        startIcon={<LogoutIcon />}
                        sx={{
                            ml: 2,
                            mr: 2,
                            borderColor: 'white',
                            color: 'white',
                            textTransform: 'none',
                            fontFamily: 'Roboto, sans-serif',
                            '&:hover': {
                                borderColor: theme.palette.primary.dark,
                                color: theme.palette.primary.dark,
                            }
                        }}
                        onClick={logout}
                    >
                        LOGOUT
                    </Button>
                </Toolbar>
            </AppBar>
            <Drawer anchor="left" open={drawerOpen} onClose={toggleDrawer(false)}>
                {renderMenu()}
            </Drawer>
        </Box>
    );
}

export default Navbar;
