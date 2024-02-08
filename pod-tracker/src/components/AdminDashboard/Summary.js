import React from 'react';
import { Paper, Typography } from '@mui/material';

const Summary = () => {

    return (
        <Paper
            sx={{
                p: 2,
                display: 'flex',
                flexDirection: 'column',
                height: largeContHeight,
            }}
        >
            <Typography variant="h5">Summary</Typography>
            <Typography>Total Trainees: 20</Typography>
            <Typography>Average Interviews per Week: 5</Typography>
            <Typography>Most Active Month: January</Typography>
        </Paper>
    )
}

export default Summary;