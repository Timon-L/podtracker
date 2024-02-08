import { Outlet, Navigate } from 'react-router-dom';
import { useUser } from '../hooks/contexts/UserContext';

const PrivateRoute = ({requiredRoles}) => {
  const { user } = useUser();
  const userRole = (user.role === null || user.role === undefined) ? "" : user.role.toLowerCase();
  return (
    requiredRoles.includes(userRole) ? <Outlet /> : <Navigate to="/login" />
  );
};

export default PrivateRoute;