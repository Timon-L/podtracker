import React from 'react';
import { Paper, Typography, Box } from '@mui/material';
import {
    VictoryPie,
}
    from 'victory';

const VictoryPieChart = ({data, midContHeight}) => {
    const mainChartHeight = midContHeight - 40;
    const chartWidth = 700;

    return (
        <Paper
            sx={{
                p: 2,
                display: 'flex',
                flexDirection: 'column',
                height: midContHeight,

            }}
        >
            <VictoryPie
                width={chartWidth}
                height={mainChartHeight}
                colorScale={['tomato', 'navy', 'gold', 'cyan']}
                data={[
                    { x: 'January', y: 10 },
                    { x: 'February', y: 15 },
                    { x: 'March', y: 7 },
                    { x: 'April', y: 20 }
                ]}
            />
        </Paper>
    )
}

export default VictoryPieChart;