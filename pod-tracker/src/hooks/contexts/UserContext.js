import { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const UserContext = createContext({
    user: null,
    bearToken: null,
    setUser: () => { },
    setBearToken: () => { },
    logout: () => { },
});

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(() => {
        const savedUser = sessionStorage.getItem('user');
        return savedUser ? JSON.parse(savedUser) : null;
    });
    const [bearToken, setBearToken] = useState(() => sessionStorage.getItem('bearToken') || null);
    const navigate = useNavigate();

    useEffect(() => {
        if (user) sessionStorage.setItem('user', JSON.stringify(user));
        else sessionStorage.removeItem('user');
    }, [user]);

    useEffect(() => {
        if (bearToken) sessionStorage.setItem('bearToken', bearToken);
        else sessionStorage.removeItem('bearToken');
    }, [bearToken]);

    const logout = () => {
        setUser("");
        setBearToken("");
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('bearToken');
        const message = {
            type: 'success',
            text: 'Logout successful',
            toPage: 'login'
        }
        sessionStorage.setItem('message', JSON.stringify(message));
        navigate('/login');
    }

    const contextValue = {
        user: {
            id: user?.userId,
            username: user?.username,
            name: user?.name,
            email: user?.email,
            role: user?.role,
        },
        bearToken,
        setUser,
        setBearToken,
        logout,
    };

    return (
        <UserContext.Provider value={contextValue}>
            {children}
        </UserContext.Provider>
    );
}

export const useUser = () => {
    const context = useContext(UserContext);
    if (!context) {
        throw new Error("useUser must be used within an UserProvider");
    }
    return context;
};

export default UserProvider;
