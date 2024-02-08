# Trainee and Pod Tracking Management Tool
## Introduction

This application is designed to streamline the management of trainees and pod tracking within an educational or training environment. It provides an intuitive user interface for administrators, trainers, and trainees to manage and track progress, schedules, and various other activities.

## Features

    User Authentication: Secure login system with role-based access control.
    Dashboard: Customizable views for different user roles.
    Tracking System: Real-time tracking of trainee progress and pod activities.
    Reporting: Comprehensive reporting tools with export options.
    Notification System: Automated alerts and reminders for upcoming tasks and deadlines.

## Usage

Upon logging in, users can navigate through the dashboard to access various functions based on their role. Administrators can manage user accounts, trainers can create and assign tasks, and trainees can view their schedules and update their progress.

### User login page <br/>
![login page](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/loginPage.png) <br/>

### Admin: <br/>
When an Admin logs in, they will be directed to the admin dashboard. <br/>
![admin dashboard](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/adminDashboard.png) <br/>
Admin will be able to see a table of trainees currently not in a pond. <br/>
![trainee not in pod table](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/tableOfTraineeNotInPod.png) <br/>
There will also be a side bar with individual pods, and trainees currently in them. <br/>
![pod overview](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/podOverViewSidebar.png) <br/>
Admin can move the trainees from the table into the pods, in this image admin moves all the trainee into shrek pod. <br/>
![train into pods](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/movingAllTraineeInToShrekPod.png) <br/>
Now when admin view the pods, all the trainee can be found in shrek pod, and the table is empty. <br/>
![all in shrek](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/TraineeNowInShrekPod.png) <br/>
Admin also has other pages for viewing and editing user details, here is a general user details for editing password, emails, and names. <br/>
![user details](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/UserDetailsAndManagement.png) <br/>
Clicking on the edit button, toggles a new page for editing user details. <br/>
![edit details](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/UserDetailsAndManagement.png) <br/>
Admin can also have a finer details on each pod on the pods page. This shows the admin which trainers are assigned to that pod, their current capacity and what project the trainees has been doing. <br/> 
![pods page](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/PodDetails.png) <br/>

### Trainer: <br/>
When a trainer logs in they are directed to the trainer's dashboard. <br/>
![trainer dashboard](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/TrainerDashBoard.png) <br/>
Trainer has an overview of the trainees, and also they are able to view reports written by the trainees on their current progression. <br/>
![trainer view trainee](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/TrainerViewingAdditionalTraineeDetails.png) <br/>

### Trainee: <br/>
When a trainee logs in they are directed to the trainee's dashboard. <br/>
![trainee dashboard](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/TraineeDashboard.png) <br/>
They can add reports on their current details on the dashboard as well. <br/>
![adding report](https://github.com/Timon-L/podtracker/blob/main/PodtrackerImages/TraineeReporting.png) <br/>

## Testing
Testing are done with Mockito, Junit, Selenium and Cucumber.
