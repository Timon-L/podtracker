import React, { useState } from 'react';
import { Paper, Typography } from '@mui/material';
import {
    VictoryChart,
    VictoryLine,
    VictoryZoomContainer,
    VictoryBrushContainer,
    VictoryAxis,
}
    from 'victory';

const BrushAndZoomChart = ({ data, midContHeight, largeContHeight }) => {
    const [zoomDomain, setZoomDomain] = useState({ x: [new Date(2023, 0, 1), new Date(2023, 3, 31)] });
    const mainChartHeight = midContHeight - 40;
    const chartWidth = 700;
    const brushChartHeight = 90;

    const currentYear = new Date().getFullYear();
    const monthTicks = Array.from({ length: 12 }, (_, i) => new Date(currentYear, i, 1));

    const handleZoom = (domain) => {
        setZoomDomain(domain);
    };

    return (
        <Paper
            sx={{
                p: 2,
                display: 'flex',
                flexDirection: 'column',
                height: largeContHeight,
                width: chartWidth + 120,
            }}
        >
            <Typography variant="h8">Interviews by Date</Typography>
            <VictoryChart
                width={chartWidth}
                height={mainChartHeight}
                scale={{ x: "time", y: "linear" }}
                containerComponent={
                    <VictoryZoomContainer
                        responsive={false}
                        zoomDimension="x"
                        zoomDomain={zoomDomain}
                        onZoomDomainChange={handleZoom}
                    />
                }
            >
                <VictoryLine
                    style={{ data: { stroke: "tomato" } }}
                    data={data}
                    x="x"
                    y="y"
                />
            </VictoryChart>
            <VictoryChart
                width={chartWidth}
                height={brushChartHeight}
                padding={{ top: 0, left: 50, right: 50, bottom: 30 }}
                scale={{ x: "time", y: "linear" }}
                containerComponent={
                    <VictoryBrushContainer
                        responsive={false}
                        brushDimension="x"
                        brushDomain={zoomDomain}
                        onBrushDomainChange={handleZoom}
                    />
                }
            >
                <VictoryAxis
                    tickValues={monthTicks}
                    tickFormat={(x) => x.toLocaleString('default', { month: 'short' })}
                />
                <VictoryLine
                    style={{ data: { stroke: "tomato" } }}
                    data={data}
                    x="x"
                    y="y"
                />
            </VictoryChart>
        </Paper>
    )
}

export default BrushAndZoomChart;