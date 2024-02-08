import './App.css';
import { CssBaseline, ThemeProvider, createTheme, Box } from '@mui/material';
import LoginPage from './components/LoginPage';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import AdminDashboard from './components/AdminDashboard/AdminDashboard';
import PrivateRoute from './components/PrivateRoute';
import NotFound from './components/NotFound';
import PermissionDeniend from './components/PermissionDenied';
import TrainerHomePage from './components/Trainer/TrainerHomePage';
import TraineeHomePage from './components/Trainee/TraineeHomePage';
import TraineeInfo from './components/Trainer/TraineeInfo';
import UserAccounts from './components/UserAccounts';
import UserForm from './components/UserForm';
import Clients from './components/Clients';
import AccountManagers from './components/AccountManagers';
import Pods from './components/Pods';
import { UserProvider } from './hooks/contexts/UserContext';
import { DataProvider } from './hooks/contexts/DataContext';
import { PodsProvider } from './hooks/contexts/PodsContext';

const theme = createTheme({
	palette: {
		primary: {
			main: '#008000',
			dark: '#004000'
		},
		secondary: {
			main: '#0000ff',
			dark: '#0000b2'
		},
		error: {
			main: '#ff0000'
		},
		success: {
			main: '#00ff00'
		}
	},
});

function App() {
	return (
		<UserProvider>
			<PodsProvider>
				<DataProvider>
					<ThemeProvider theme={theme}>
						<CssBaseline />
						<Navbar />
						<Box component="main" sx={{ flexGrow: 1, pt: 8 }}>
							<Routes>
								<Route path="/" element={<PrivateRoute requiredRoles={["admin"]} />} >
									<Route path="/admin_dashboard" element={<AdminDashboard />} />
									<Route path="/user_accounts" element={<UserAccounts />} />
									<Route path="/user_form" element={<UserForm />} />
									<Route path="/clients" element={<Clients />} />
									<Route path="/account_managers" element={<AccountManagers />} />
									<Route path="/pods" element={<Pods />} />
								</Route>
								<Route path="/" element={<PrivateRoute requiredRoles={["trainee"]} />} >
									<Route path="/trainees" element={<TraineeHomePage />} />
								</Route>
								<Route path="/" element={<PrivateRoute requiredRoles={["trainer", "admin"]} />} >
									<Route path="/trainers" element={<TrainerHomePage />} />
									<Route path="/trainee_info" element={<TraineeInfo />} />
								</Route>
								<Route path="/login" element={<LoginPage />} />
								<Route path="/permission_denied" element={<PermissionDeniend />} />
								<Route path="*" element={<NotFound />} />
							</Routes>
						</Box>
					</ThemeProvider >
				</DataProvider>
			</PodsProvider>
		</UserProvider >
	);
}

export default App;
