import { createContext, useContext, useState } from "react";

const PodsContext = createContext({
    pods: {},
    setPods: () => { },
    addToPod: () => { },
    removeFromPod: () => { },
});

export const PodsProvider = ({ children }) => {
    const [pods, setPods] = useState({
        ToyStoryPod: {
            trainers: {
                primary: {
                    name: 'Andy Davis'
                },
                secondary: {
                    name: 'Sid Phillips'
                }
            },
            trainees: [
                {
                    id: 1,
                    name: 'Woody',
                    email: 'woody@toystory.com',
                    pondStartDate: new Date('01-01-2022'),
                },
                {
                    id: 2,
                    name: 'Buzz Lightyear',
                    email: 'buzz_lightyear@toystory.com',
                    pondStartDate: new Date('02-01-2022'),
                },
                {
                    id: 3,
                    name: 'Jessie',
                    email: 'jessie@toystory.com',
                    pondStartDate: new Date('03-01-2022'),
                }
            ],
            project: {
                name: 'Toy Story',
                description: 'A project about toys coming to life',
                startDate: new Date('10-06-2023'),
                completionDate: null
            },
            status: 'In Progress',
        },
        SimpsonsPod: {
            trainers: {
                primary: {
                    name: 'Ned Flanders'
                },
                secondary: {
                    name: 'Mr. Burns'
                }
            },
            trainees: [
                {
                    id: 4,
                    name: 'Homer Simpson',
                    email: 'homer_simpson@simpsons.com',
                    pondStartDate: new Date('04-01-2022'),
                },
                {
                    id: 5,
                    name: 'Marge Simpson',
                    email: 'marge_simpson@simpsons.com',
                    pondStartDate: new Date('05-01-2022'),
                },
                {
                    id: 6,
                    name: 'Bart Simpson',
                    email: 'bart_simpson@simpsons.com',
                    pondStartDate: new Date('06-01-2022'),
                }
            ],
            project: {
                name: 'Simpsons',
                description: 'A project about a yellow family in Springfield',
                startDate: new Date('10-07-2023'),
                completionDate: null
            },
            status: 'Planning',
        },
        AvatarPod: {
            trainers: {
                primary: {
                    name: 'Fire Lord Ozai'
                },
                secondary: {
                    name: 'Uncle Iroh'
                }
            },
            trainees: [
                {
                    id: 7,
                    name: 'Aang',
                    email: 'aang@avatar.com',
                    pondStartDate: new Date('07-01-2022'),
                },
                {
                    id: 8,
                    name: 'Katara',
                    email: 'katara@avatar.com',
                    pondStartDate: new Date('08-01-2022'),
                },
                {
                    id: 9,
                    name: 'Sokka',
                    email: 'sokka@avatar.com',
                    pondStartDate: new Date('09-01-2022'),
                }
            ],
            project: {
                name: 'Avatar',
                description: 'A project about the last Airbender',
                startDate: new Date('10-08-2023'),
                completionDate: new Date('10-10-2023')
            },
            status: 'Completed',
        },
        SpongeBobPod: {
            trainers: {
                primary: {
                    name: 'Mr. Krabs'
                },
                secondary: {
                    name: 'Sandy Cheeks'
                }
            },
            trainees: [
                {
                    id: 10,
                    name: 'SpongeBob SquarePants',
                    email: 'spongebob@bikinibottom.com',
                    pondStartDate: new Date('10-01-2022'),
                },
                {
                    id: 11,
                    name: 'Patrick Star',
                    email: 'patrick_star@bikinibottom.com',
                    pondStartDate: new Date('11-01-2022'),
                },
                {
                    id: 12,
                    name: 'Squidward Tentacles',
                    email: 'squidward@bikinibottom.com',
                    pondStartDate: new Date('12-01-2022'),
                }
            ],
            project: {
                name: 'SpongeBob',
                description: 'A project about underwater adventures',
                startDate: new Date('10-09-2023'),
                completionDate: null
            },
            status: 'Planning',
        },
        ShrekPod: {
            trainers: {
                primary: {
                    name: 'Lord Farquaad'
                },
                secondary: {
                    name: 'Dragon'
                }
            },
            trainees: [
                {
                    id: 13,
                    name: 'Shrek',
                    email: 'shrek@farfaraway.com',
                    pondStartDate: new Date('01-02-2022'),
                },
                {
                    id: 14,
                    name: 'Donkey',
                    email: 'donkey@farfaraway.com',
                    pondStartDate: new Date('02-02-2022'),
                },
                {
                    id: 15,
                    name: 'Princess Fiona',
                    email: 'princess_fiona@farfaraway.com',
                    pondStartDate: new Date('03-02-2022'),
                }
            ],
            project: {
                name: 'Shrek',
                description: 'A project about an ogre’s adventures',
                startDate: new Date('10-11-2023'),
                completionDate: new Date('10-12-2023')
            },
            status: 'Completed',
        },
    });

    const addToPod = (podName, users) => {
        setPods((prevPods) => {
            const newPods = { ...prevPods };
            newPods[podName].trainees = [...newPods[podName].trainees, ...users];
            return newPods;
        });
    };

    const removeFromPod = (podName, targetTrainee) => {
        console.log(podName, targetTrainee)
        setPods((prevPods) => {
            const newPods = { ...prevPods };
            newPods[podName].trainees = newPods[podName].trainees.filter((trainee) => trainee.id !== targetTrainee.id);
            return newPods;
        });
    };

    const contextValue = {
        pods,
        setPods,
        addToPod,
        removeFromPod,
    };

    return (
        <PodsContext.Provider value={contextValue}>
            {children}
        </PodsContext.Provider>
    );
}

export const usePods = () => {
    const context = useContext(PodsContext);
    if (!context) {
        throw new Error("usePod must be used within a PodProvider");
    }
    return context;
}

export default PodsProvider;