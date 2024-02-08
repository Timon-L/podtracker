import { createContext, useContext, useState } from "react";

const DataContext = createContext({
    selectedUser: null,
    selectedUsers: [],
    interviews: [],
    traineesNotInPod: [],
    setSelectedUser: () => { },
    setSelectedUsers: () => { },
    setInterviews: () => { },
    setTraineesNotInPod: () => { },
});

export const DataProvider = ({ children }) => {
    const [selectedUser, setSelectedUser] = useState(null);
    const [selectedUsers, setSelectedUsers] = useState([]);
    const [interviews, setInterviews] = useState([]);
    const [traineesNotInPod, setTraineesNotInPod] = useState([
        {
            id: 16,
            name: 'Hank Hill',
            email: 'hank_hill@strickland_propane.com',
            pondStartDate: new Date('01-01-2021'),
        },
        {
            id: 17,
            name: 'Dale Gribble',
            email: 'dgribble@altavista.com',
            pondStartDate: new Date('01-09-2021'),
        },
        {
            id: 18,
            name: 'Bill Dauterive',
            email: 'B.D@usbarber.com',
            pondStartDate: new Date('01-15-2023'),
        },
        {
            id: 19,
            name: 'Jeff Boomhauer',
            email: '-.-.-.-@mail.com',
            pondStartDate: new Date('01-01-2023'),
        },
        {
            id: 20,
            name: 'Bobby Hill',
            email: 'bobby_hill@strickland_propane.com',
            pondStartDate: new Date('01-01-2023'),
        },
        {
            id: 21,
            name: 'Luanne Platter',
            email: 'luanne_platter@mangerbabies.com',
            pondStartDate: new Date('01-12-2023'),
        },
        {
            id: 22,
            name: 'Kahn Souphanousinphone',
            email: 'kahn_souphanousinphone@laotian_embassy.com',
            pondStartDate: new Date('12-12-2023'),
        },
        {
            id: 23,
            name: 'Connie Souphanousinphone',
            email: 'connie_souphanousinphone@laotian_embassy.com',
            pondStartDate: new Date('12-12-2023'),
        },
        {
            id: 24,
            name: 'Joseph Gribble',
            email: 'xxx_xxxx@mailg.com',
            pondStartDate: new Date('12-12-2023'),
        },
    ]);

    const contextValue = {
        selectedUser: {
            id: selectedUser?.id,
            username: selectedUser?.username,
            email: selectedUser?.email,
            role: selectedUser?.role,
        },
        selectedUsers: selectedUsers,
        interviews: interviews,
        traineesNotInPod: traineesNotInPod,
        setSelectedUser,
        setSelectedUsers,
        setInterviews,
        setTraineesNotInPod,
    };

    return (
        <DataContext.Provider value={contextValue}>
            {children}
        </DataContext.Provider>
    );
}

export const useData = () => {
    const context = useContext(DataContext);
    if (!context) {
        throw new Error("useSelectedUser must be used within an SelectedUserProvider");
    }
    return context;
}

export default DataProvider;
