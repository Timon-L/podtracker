import React, { useState } from 'react';
import {
    Box,
    Collapse,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from '@mui/material';
import { format } from 'date-fns';
import styled from '@emotion/styled';

const RecentUpskillingTable = ({ data }) => {
    const [openRowId, setOpenRowId] = useState(null);

    const upskillings = [
        {
            id: 1,
            trainee: { id: 101, name: 'John Doe' },
            startDate: '2023-09-20',
            completionETA: '2023-09-25',
            finishDate: null,
            topic: 'ReactJS',
            content: 'Learn ReactJS basics',
            objective: 'Understand the fundamentals of ReactJS',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 2,
            trainee: { id: 102, name: 'Jane Smith' },
            startDate: '2023-09-19',
            completionETA: '2023-09-24',
            finishDate: '2023-09-23',
            topic: 'NodeJS',
            content: 'Create a NodeJS server',
            objective: 'Learn to set up a basic NodeJS server',
            status: 'COMPLETED',
            upskillingGoal: [/*...*/]
        },
        {
            id: 3,
            trainee: { id: 103, name: 'John Smith' },
            startDate: '2023-09-18',
            completionETA: '2023-09-23',
            finishDate: null,
            topic: 'Python',
            content: 'Learn Python basics',
            objective: 'Understand the fundamentals of Python',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 4,
            trainee: { id: 104, name: 'Bob Dylan' },
            startDate: '2023-09-17',
            completionETA: '2023-09-22',
            finishDate: null,
            topic: 'Java',
            content: 'Learn Java basics',
            objective: 'Understand the fundamentals of Java',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 5,
            trainee: { id: 105, name: 'Quest Love' },
            startDate: '2023-09-16',
            completionETA: '2023-09-21',
            finishDate: null,
            topic: 'C#',
            content: 'Learn C# basics',
            objective: 'Understand the fundamentals of C#',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 6,
            trainee: { id: 106, name: 'Sebastian Murphy' },
            startDate: '2023-09-15',
            completionETA: '2023-09-20',
            finishDate: null,
            topic: 'ReactJS',
            content: 'Learn ReactJS basics',
            objective: 'Understand the fundamentals of ReactJS',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 7,
            trainee: { id: 107, name: 'Erykah Badu' },
            startDate: '2023-09-14',
            completionETA: '2023-09-19',
            finishDate: null,
            topic: 'NodeJS',
            content: 'Create a NodeJS server',
            objective: 'Learn to set up a basic NodeJS server',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 8,
            trainee: { id: 108, name: 'John Coltrane' },
            startDate: '2023-09-13',
            completionETA: '2023-09-18',
            finishDate: null,
            topic: 'Python',
            content: 'Learn Python basics',
            objective: 'Understand the fundamentals of Python',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 9,
            trainee: { id: 109, name: 'Miles Davis' },
            startDate: '2023-09-12',
            completionETA: '2023-09-17',
            finishDate: null,
            topic: 'Java',
            content: 'Learn Java basics',
            objective: 'Understand the fundamentals of Java',
            status: 'IN_PROGRESS',
            upskillingGoal: [/*...*/]
        },
        {
            id: 10,
            trainee: { id: 110, name: 'Michael Johnson' },
            startDate: '2023-09-15',
            completionETA: '2023-09-20',
            finishDate: '2023-09-20',
            topic: 'Angular',
            content: 'Build an Angular app',
            objective: 'Understand how to structure an Angular application',
            status: 'COMPLETED',
            upskillingGoal: [/*...*/]
        }
    ];

    return (
        <Paper
            sx={{
                width: '100%',
                overflow: 'hidden',
                borderRadius: 2,
                boxShadow: 3,
            }}
        >
            <TableContainer
                sx={{
                    maxHeight: 400,
                }}
            >
                <Typography
                    sx={{
                        fontWeight: 'bold',
                        color: 'primary.main',
                        backgroundColor: 'background.default',
                        p: 2,
                    }}
                >
                    Recent Upskillings
                </Typography>
                <Table stickyHeader aria-label="collapsible table" size="small">
                    <TableHead>
                        <TableRow>
                            <TableCell >Trainee Name</TableCell>
                            <TableCell >Topic</TableCell>
                            <TableCell >Objective</TableCell>
                            <TableCell >Start Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {upskillings.map((upskilling) => (
                            <React.Fragment key={upskilling.id}>
                                <TableRow onClick={() => setOpenRowId(openRowId === upskilling.id ? null : upskilling.id)}>
                                    <TableCell>{upskilling.trainee.name}</TableCell>
                                    <TableCell>{upskilling.topic}</TableCell>
                                    <TableCell>{upskilling.objective}</TableCell>
                                    <TableCell>{format(new Date(upskilling.startDate), 'MMM d, yyyy')}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                                        <Collapse in={openRowId === upskilling.id} timeout="auto" unmountOnExit>
                                            <Box margin={1}>
                                                {/* Upskilling history to be added here. */}
                                            </Box>
                                        </Collapse>
                                    </TableCell>
                                </TableRow>
                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Paper>
    )
}

export default RecentUpskillingTable;