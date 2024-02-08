import React, { useEffect, useState } from 'react';
import {
    Snackbar,
    Alert
} from '@mui/material';

const AlertSnackbar = ({ page, displayError, setDisplayError }) => {
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState("");
    const [snackbarType, setSnackbarType] = useState("success");
    

    useEffect(() => {
        const checkSessionStorage = () => {
            const messageStr = sessionStorage.getItem('message');
            if (messageStr) {
                const message = JSON.parse(messageStr);
                if (message.toPage !== page) {
                    return;
                }
                setSnackbarMessage(message.text);
                setSnackbarType(message.type);
                setOpenSnackbar(true);
                sessionStorage.removeItem('message');
            }
        };
        checkSessionStorage();
        if(setDisplayError){
            setDisplayError(false);
        }
    }, [displayError]);

    const handleCloseSnackbar = () => {
        setOpenSnackbar(false);
        setSnackbarMessage("");
    }

    return (
        <Snackbar
            open={openSnackbar}
            autoHideDuration={6000}
            onClose={handleCloseSnackbar}
        >
            <Alert onClose={handleCloseSnackbar} severity={snackbarType} sx={{ width: '100%' }}>
                {snackbarMessage}
            </Alert>
        </Snackbar>
    )

}

export default AlertSnackbar;