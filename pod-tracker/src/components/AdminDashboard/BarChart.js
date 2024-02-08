import { useState, useEffect, useCallback } from 'react'
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer,
    LabelList,
} from 'recharts';
import {
    startOfMonth,
    endOfMonth,
    eachDayOfInterval,
    eachWeekOfInterval,
    eachMonthOfInterval,
    startOfYear,
    endOfYear,
    format,
    startOfWeek,
    endOfWeek,
    isWithinInterval,
    parse,
    getMonth,
    isAfter,
    isBefore,
    parseISO,
} from 'date-fns';
import {
    Paper,
    Typography,
    Box,
    Select,
    MenuItem,
    Modal,
    IconButton,
    Tooltip as MuiTooltip,
} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import FileDownloadIcon from '@mui/icons-material/FileDownload';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { useData } from '../../hooks/contexts/DataContext';

const ChartComponent = ({ isScreenSmall }) => {
    const [range, setRange] = useState('months');
    const [selectedMonth, setSelectedMonth] = useState(new Date());
    const [selectedWeeks, setSelectedWeeks] = useState([startOfMonth(new Date()), endOfMonth(new Date())]);
    const [openModal, setOpenModal] = useState(false);
    const [selectedInterviews, setSelectedInterviews] = useState([]);
    const { interviews, setInterviews } = useData();

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'date', headerName: 'Date', width: 150 },
        { field: 'trainee', headerName: 'Trainee', width: 150 },
        { field: 'accountManager', headerName: 'Account Manager', width: 200 },
        { field: 'client', headerName: 'Client', width: 150 },
        { field: 'outcome', headerName: 'Outcome', width: 150 },
    ];

    const randomDate = (start, end) => {
        return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    }

    const randomAccountManager = (list) => {
        return list[Math.floor(Math.random() * list.length)];
    }

    const randomClient = (list) => {
        return list[Math.floor(Math.random() * list.length)];
    }

    const randomOutcome = (list) => {
        return list[Math.floor(Math.random() * list.length)];
    }

    const randomTrainee = (list) => {
        return list[Math.floor(Math.random() * list.length)];
    }

    useEffect(() => {
        const generateInterviews = () => {
            const interviews = [];
            const startDate = new Date('2023-01-01');
            const endDate = new Date('2023-12-31');
            const traineeNames = [
                'Woody',
                'Buzz Lightyear',
                'Jessie',
                'Homer Simpson',
                'Marge Simpson',
                'Bart Simpson',
                'Aang',
                'Katara',
                'Sokka',
                'SpongeBob SquarePants',
                'Patrick Star',
                'Squidward Tentacles',
                'Shrek',
                'Donkey',
                'Princess Fiona'
            ];
            const accountManagers = ['Nicholas Lloyd', 'Joe Mclaren', 'James McCarthy', 'Ed Bristow', 'Nina Evangelinides', 'Will Rayner', 'Yas Bellairs-Taylor'];
            const clients = ['CBA', 'App Dynamics', 'KPMG', 'Bupa', 'Zurich', 'IAG', 'NAB', 'SportsBet', 'Aruma', 'Kmart'];
            const outcomes = ['Successful', 'Unsuccessful', 'Rescheduled', 'Cancelled', 'Pending']

            for (let i = 1; i <= 100; i++) {
                const date = randomDate(startDate, endDate);
                const trainee = randomTrainee(traineeNames);
                const accountManager = randomAccountManager(accountManagers);
                const client = randomClient(clients);
                const outcome = randomOutcome(outcomes);
                interviews.push({
                    id: i,
                    date: date.toISOString().split('T')[0],
                    trainee,
                    accountManager,
                    client,
                    outcome,
                });
            }
            return interviews;
        };
        setInterviews(generateInterviews());
        setSelectedInterviews(generateInterviews());
    }, []);

    const processData = useCallback(() => {
        let interval;
        let formatStr;
        const today = new Date();

        switch (range) {
            case 'weeks':
                formatStr = 'MM/dd';
                const firstDateOfMonth = startOfMonth(selectedMonth);
                const lastDateOfMonth = endOfMonth(selectedMonth);

                interval = { start: firstDateOfMonth, end: lastDateOfMonth };
                break;
            case 'months':
                formatStr = 'MMM';
                const firstDateOfYear = startOfYear(today);
                const lastDateOfYear = endOfYear(today);

                interval = { start: firstDateOfYear, end: lastDateOfYear };
                break;
            default:
                formatStr = 'MM/dd';
                const firstDateOfWeek = selectedWeeks[0];
                const lastDateOfWeek = selectedWeeks[1];

                interval = { start: firstDateOfWeek, end: lastDateOfWeek };
                break;
        }

        const dateList = range === 'days' ? eachDayOfInterval(interval) : range === 'weeks' ? eachWeekOfInterval(interval) : eachMonthOfInterval(interval);
        const filteredInterviews = interviews.filter(interview => isWithinInterval(parseISO(interview.date), interval));

        const data = dateList.map(date => {

            if (range === 'weeks') {
                let start = startOfWeek(date);
                let end = endOfWeek(date);

                if (isBefore(start, interval.start)) {
                    start = interval.start;
                }

                if (isAfter(end, interval.end)) {
                    end = interval.end;
                }

                const name = `${format(start, formatStr)} - ${format(end, formatStr)}`;
                return {
                    name,
                    value: filteredInterviews.filter(interview => isWithinInterval(parseISO(interview.date), { start, end })).length
                }
            }

            const name = format(date, formatStr);
            const value = filteredInterviews.filter(interview => format(parseISO(interview.date), formatStr) === name).length;
            return { name, value };
        })

        return data;
    }, [range, selectedMonth, selectedWeeks, interviews]);

    const handleBarClick = (data) => {
        if (!data) return;

        if (range === 'months') {
            setRange('weeks');
            setSelectedMonth(parse(data.name, 'MMM', new Date()));
            setSelectedWeeks([startOfMonth(new Date()), endOfMonth(new Date())]);
            const interviewsOnThisMonth = interviews.filter(interview => getMonth(parseISO(interview.date)) === getMonth(parse(data.name, 'MMM', new Date())));
            setSelectedInterviews(interviewsOnThisMonth);
        } else if (range === 'weeks') {
            setRange('days');
            setSelectedMonth(new Date());
            setSelectedWeeks([parse(data.name.split(' - ')[0], 'MM/dd', new Date()), parse(data.name.split(' - ')[1], 'MM/dd', new Date())]);
            const interviewsWithinThisWeek = interviews.filter(interview =>
                isWithinInterval(parseISO(interview.date), { start: selectedWeeks[0], end: selectedWeeks[1] }));
            setSelectedInterviews(interviewsWithinThisWeek);
        } else if (range === 'days') {
            setOpenModal(true);
            const interviewsOnThisDay = interviews.filter(interview => format(parseISO(interview.date), 'MM/dd') === data.name);
            setSelectedInterviews(interviewsOnThisDay);
        }
    }

    const handleCloseModal = () => {
        setOpenModal(false);
    };

    const handleModalOpen = () => {
        let filteredInterviews = [];
        switch (range) {
            case 'weeks':
                filteredInterviews = interviews.filter(interview => getMonth(parseISO(interview.date)) === getMonth(selectedMonth));
                break;
            case 'months':
                filteredInterviews = interviews;
                break;
            default:
                filteredInterviews = interviews.filter(interview =>
                    isWithinInterval(parseISO(interview.date), { start: selectedWeeks[0], end: selectedWeeks[1] }));
                break;
        }
        setSelectedInterviews(filteredInterviews);
        setOpenModal(true);
    }

    return (
        <Paper elevation={3} style={{ padding: '16px', height: 380 }}>
            <Box
                display="flex"
                alignItems="center"
                justifyContent="space-between"
                sx={{
                    mb: 2,
                }}
            >
                <Typography
                    variant="h6"
                    sx={{
                        display: (!isScreenSmall) ? 'block' : 'none',
                    }}
                >
                    Interviews by {range}
                </Typography>
                <Typography variant="h6">
                    Total Interviews in {
                        range === 'weeks' ? format(selectedMonth, 'MMM') :
                            range === 'days' ? `${selectedWeeks[0].toLocaleDateString()} - ${selectedWeeks[1].toLocaleDateString()}` :
                                '2023'
                    }:
                    {" " + processData().reduce((acc, curr) => acc + curr.value, 0)}
                    <MuiTooltip title="Download">
                        <IconButton
                            onClick={() => handleModalOpen()}
                        >
                            <FileDownloadIcon />
                        </IconButton>
                    </MuiTooltip>
                </Typography>
            </Box>
            <Select value={range} onChange={(e) => setRange(e.target.value)}>
                <MenuItem value={'days'}>Days</MenuItem>
                <MenuItem value={'weeks'}>Weeks</MenuItem>
                <MenuItem value={'months'}>Months</MenuItem>
            </Select>
            <Box sx={{ width: '100%', height: 300 }}>
                <ResponsiveContainer
                    maxHeight={220}

                >
                    <BarChart data={processData()} margin={{ top: 20, right: 20, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" strokeOpacity={0.3} />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Bar
                            dataKey="value"
                            fill="#82ca9d"
                            onClick={(data) => handleBarClick(handleBarClick(data.payload))}
                        >
                            <LabelList dataKey="value" position="top" />
                        </Bar>
                    </BarChart>
                </ResponsiveContainer>
            </Box>
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
                    <h2 id="interview-details-modal">Interview Details</h2>
                    <div style={{ height: 400, width: '100%' }}>
                        <DataGrid
                            rows={selectedInterviews}
                            slots={{ toolbar: GridToolbar }}
                            columns={columns}
                            pageSize={5}
                            checkboxSelection={true}
                        />
                    </div>
                </div>
            </Modal>
        </Paper>
    );
};

export default ChartComponent;
