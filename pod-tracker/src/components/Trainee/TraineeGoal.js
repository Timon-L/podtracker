import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  Paper,
  Dialog,
  DialogContent,
  DialogActions,
  Select,
  MenuItem,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Collapse,
  Checkbox,
  Hidden,
  FormControlLabel,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import ExpandLessIcon from "@mui/icons-material/ExpandLess";
import TraineeComment from "./TraineeComment";
import TraineeInterview from "./TraineeInterviews";
import TraineeOpportunities from "./TraineeOpportunities";
import TraineeUpSkilling from "./TraineeUpSkilling";
import TraineePodProject from "./TraineePodProject";
import { addGoal } from "../../utils/apiUtils";
import { addPodProjectGoal } from "../../utils/apiUtils";
import { addProject } from "../../utils/apiUtils";
import { createUpskilling } from "../../utils/apiUtils";
import { createUpskillingGoal } from "../../utils/apiUtils";
import { useUser } from "../../hooks/contexts/UserContext";

export default function TraineeGoal({ setOpenModal, isAdding, nextGoal }) {
  const [openAddActivityUpdateModal, setOpenAddActivityUpdateModal] =
    useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [selectedActivity, setSelectedActivity] = useState("");
  const [isSkillsOpen, setIsSkillsOpen] = useState(false);
  const [isProjectsOpen, setIsProjectsOpen] = useState(false);
  const [isInterviewsOpen, setIsInterviewsOpen] = useState(false);
  const [isOpportunitiesOpen, setIsOpportunitiesOpen] = useState(false);
  const [isTrainerFeedbackOpen, setIsTrainerFeedbackOpen] = useState(false);
  const [isCommentOpen, setIsCommentOpen] = useState(false);
  const [isAbsent, setIsAbsent] = useState(false);
  const { bearToken } = useUser();
  const [upskilling, setUpskilling] = useState([]);
  const [interviews, setInterviews] = useState([]);
  const [opportunities, setOpportunities] = useState([]);
  const [projects, setProjects] = useState([]);
  const [comment, setComment] = useState("");
  const [goal, setGoal] = useState(nextGoal);

  const [nextProject, setNextProject] = useState([]);
  const [nextUpskilling, setNextUpskilling] = useState([]);
  const [nextInterview, setNextInterview] = useState([]);
  const [nextOpportunity, setNextOpportunity] = useState([]);
  const [goalDate, setGoalDate] = useState(new Date());

  // useEffect(() => {
  // 	setOpenModal(selectedComponent === "Trainee Goal");
  // }, [selectedComponent]);

  useEffect(() => {
	if (nextGoal != null)
	{
		if (nextGoal.projects != null)
		{
			setProjects(nextGoal.projects)
		}
		if (nextGoal.upskilling != null)
		{
			setUpskilling(nextGoal.upskilling)
		}
		if (nextGoal.opportunities != null)
		{
			setOpportunities(nextGoal.opportunities)
		}
		if (nextGoal.interviews != null)
		{
		setInterviews(nextGoal.interviews)
		}
		if (nextGoal.comment != null)
		{
		setComment(nextGoal.comment)
		}
	}



}, [])
  

  const toggleSkills = () => {
    setIsSkillsOpen((prevState) => !prevState);
  };
  const toggleProjects = () => {
    setIsProjectsOpen((prevState) => !prevState);
  };
  const toggleInterviews = () => {
    setIsInterviewsOpen((prevState) => !prevState);
  };
  const toggleOpportunities = () => {
    setIsOpportunitiesOpen((prevState) => !prevState);
  };
  const toggleComment = () => {
    setIsCommentOpen((prevState) => !prevState);
  };
  const toggleTrainerFeedback = () => {
    setIsTrainerFeedbackOpen((prevState) => !prevState);
  };

  const addUpskillingActivity = (goal) => {
    upskilling.forEach((skill) => {
      const skillBody = {
        topic: skill.skill,
        completionETA: skill.targetDate,
        objective: skill.reason,
      };
      createUpskilling(bearToken, skillBody).then((data) => {
        const skillGoalBody = {
          upskilling: data,
          goal: goal,
          allocationHours: skill.hours,
        };
        createUpskillingGoal(bearToken, skillGoalBody);
      });
    });
  };

  const addPodProjectActivity = (goal) => {
    projects.forEach((p) => {
      const projectGoal = {
        podProject: p.project,
        goal: goal,
        allocationHours: p.hours,
        skills: p.skills,
        taskCompleted: p.tasks,
        isAbsence: isAbsent,
      };
      addPodProjectGoal(bearToken, projectGoal);
    });
  };

  const tryAddGoal = () => {
    const goalBody = {
      isAbsense: isAbsent,
      date: goalDate,
      comment: comment,
    };
    //if (confirm("Are you sure you want to submit this goal?"))
    //{
    addGoal(bearToken, goalBody).then((data) => {
      addPodProjectActivity(data);
      addUpskillingActivity(data);
    });
  };

  return (
    <>
      <Container
        component="main"
        sx={{
          marginTop: 4,
          display: "flex",
          flexDirection: "column",
          fontFamily: "monospace",
          marginBottom: 6,
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
          }}
        >
          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <Typography variant="h4">Goal</Typography>
            <Box sx={{ display: "flex", gap: 1 }}>
              <FormControlLabel
                control={
                  <Checkbox
                    value={isAbsent}
                    onChange={(e) => setIsAbsent(isAbsent)}
                  />
                }
                label="Absent"
              />
              <Button
                variant="outlined"
                size="small"
                onClick={() => {
                  setSelectedActivity("");
                  setOpenModal(false);
                }}
              >
                Cancel
              </Button>
              {/* {isAbsent === true && (
				<Hidden>)} */}
              {isAdding === true && (
                <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  onClick={() => {
                    setOpenAddActivityUpdateModal(true);
                    setSelectedActivity("Pod Project");
                  }}
                >
                  Add Activity
                </Button>
              )}
              {isAdding === true && (
                <Button
                  onClick={tryAddGoal}
                  variant="contained"
                  color="primary"
                  size="small"
                >
                  Submit Goal
                </Button>
              )}
            </Box>
          </Box>
          {isAdding === true && (
            <TextField
              type="date"
              value={goalDate}
              onChange={(e) => setGoalDate(e.target.value)}
            />
          )}
          {isAdding === false && (
            <TextField type="date" value={goalDate} disabled />
          )}
        </Box>

        <Box sx={{ mb: 3 }}>
          <TableContainer component={Paper} sx={{ mt: 3 }}>
            <Table>
              <TableBody>
                <TableCell>Name: Harry Potter</TableCell>
                <TableCell>Stream: Dev</TableCell>
                <TableCell>City: Melbourne</TableCell>
              </TableBody>
              <TableBody>
                <TableCell>Pod: banks</TableCell>
                <TableCell>Pod Project: banking app</TableCell>
                <TableCell>Trainer(s): John Smith, Jane Smith</TableCell>
              </TableBody>
            </Table>
          </TableContainer>
        </Box>

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            mb: 3,
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleProjects}
            >
              Projects
              {isProjectsOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isProjectsOpen} timeout="auto" unmountOnExit>
            <TableContainer component={Paper} sx={{ mt: 1 }}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Project
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Start date
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Estimated completion date
                      </Typography>
                    </TableCell>
                  </TableRow>
                </TableHead>
                {projects.map((project) => (
                  <TableBody>
                    <TableCell>{project.name}</TableCell>
                    <TableCell>{project.startDate}</TableCell>
                    <TableCell>{project.etaCompletionDate}</TableCell>
                  </TableBody>
                ))}
              </Table>
            </TableContainer>
          </Collapse>
        </Box>

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            mb: 3,
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleOpportunities}
            >
              Opportunities
              {isOpportunitiesOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isOpportunitiesOpen} timeout="auto" unmountOnExit>
            <TableContainer component={Paper} sx={{ mt: 1 }}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Role
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Account Manager
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Client
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Discussion result
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Hours
                      </Typography>
                    </TableCell>
                  </TableRow>
                </TableHead>
                {opportunities.map((Opportunity) => (
                  <TableBody>
                    <TableCell>{Opportunity.role}</TableCell>
                    <TableCell>{Opportunity.accountManager.name}</TableCell>
                    <TableCell>{Opportunity.client.name}</TableCell>
                    <TableCell>{Opportunity.discussion}</TableCell>
                    <TableCell>{Opportunity.hours}</TableCell>
                  </TableBody>
                ))}
              </Table>
            </TableContainer>
          </Collapse>
        </Box>

        {/* Feedback */}
        {isAdding === false && (
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              mb: 3,
              mt: 3,
            }}
          >
            <Paper>
              <Typography
                variant="h6"
                sx={{
                  backgroundColor: "#D3D3D3",
                  textAlign: "center",
                  cursor: "pointer",
                  display: "flex",
                  justifyContent: "space-between",
                  padding: "8px",
                }}
                onClick={toggleTrainerFeedback}
              >
                Trainer's Feedback
                {isTrainerFeedbackOpen ? (
                  <ExpandLessIcon />
                ) : (
                  <ExpandMoreIcon />
                )}
              </Typography>
            </Paper>
            <Collapse in={isTrainerFeedbackOpen} timeout="auto" unmountOnExit>
              <Box sx={{ mt: 1 }}>Some feedback here for the trainee.</Box>
            </Collapse>
          </Box>
        )}

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            mb: 3,
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleSkills}
            >
              Skills
              {isSkillsOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isSkillsOpen} timeout="auto" unmountOnExit>
            <TableContainer component={Paper} sx={{ mt: 1 }}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Upskilling
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Plan
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Status
                      </Typography>
                    </TableCell>
                  </TableRow>
                </TableHead>
                {upskilling.map((skill) => (
                  <TableBody>
                    <TableCell>{skill.skill}</TableCell>
                    <TableCell>{skill.reason}</TableCell>
                    <TableCell>{skill.achievement}</TableCell>
                  </TableBody>
                ))}
              </Table>
            </TableContainer>
          </Collapse>
        </Box>

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleInterviews}
            >
              Interviews
              {isInterviewsOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isInterviewsOpen} timeout="auto" unmountOnExit>
            <TableContainer component={Paper} sx={{ mt: 1 }}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Date
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Client
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Role
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Outcome
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Feedback
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Account Manager
                      </Typography>
                    </TableCell>
                  </TableRow>
                </TableHead>
                {interviews.map((interview) => (
                  <TableBody>
                    <TableCell>{interview.date}</TableCell>
                    <TableCell>{interview.client}</TableCell>
                    <TableCell>{interview.outcome}</TableCell>
                    <TableCell>{interview.feedback}</TableCell>
                    <TableCell>{interview.accountManager}</TableCell>
                  </TableBody>
                ))}
              </Table>
            </TableContainer>
          </Collapse>
        </Box>

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            mt: 3,
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleOpportunities}
            >
              Opportunities
              {isOpportunitiesOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isOpportunitiesOpen} timeout="auto" unmountOnExit>
            <TableContainer component={Paper} sx={{ mt: 1 }}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Role
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Account Manager
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Client
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Discussion result
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Typography variant="body1" sx={{ fontWeight: "700" }}>
                        Hours
                      </Typography>
                    </TableCell>
                  </TableRow>
                </TableHead>
                {opportunities.map((Opportunity) => (
                  <TableBody>
                    <TableCell>{Opportunity.role}</TableCell>
                    <TableCell>{Opportunity.accountManager}</TableCell>
                    <TableCell>{Opportunity.client}</TableCell>
                    <TableCell>{Opportunity.discussion}</TableCell>
                    <TableCell>{Opportunity.hours}</TableCell>
                  </TableBody>
                ))}
              </Table>
            </TableContainer>
          </Collapse>
        </Box>
        {/* Comments */}
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            mt: 3,
          }}
        >
          <Paper>
            <Typography
              variant="h6"
              sx={{
                backgroundColor: "#D3D3D3",
                textAlign: "center",
                cursor: "pointer",
                display: "flex",
                justifyContent: "space-between",
                padding: "8px",
              }}
              onClick={toggleComment}
            >
              Comment
              {isCommentOpen ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </Typography>
          </Paper>
          <Collapse in={isCommentOpen} timeout="auto" unmountOnExit>
            <Box sx={{ mt: 1 }}>{comment}</Box>
          </Collapse>
        </Box>
        {/* Feedback */}
        {isAdding === false && (
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              mb: 3,
              mt: 3,
            }}
          >
            <Paper>
              <Typography
                variant="h6"
                sx={{
                  backgroundColor: "#D3D3D3",
                  textAlign: "center",
                  cursor: "pointer",
                  display: "flex",
                  justifyContent: "space-between",
                  padding: "8px",
                }}
                onClick={toggleTrainerFeedback}
              >
                Trainer's Feedback
                {isTrainerFeedbackOpen ? (
                  <ExpandLessIcon />
                ) : (
                  <ExpandMoreIcon />
                )}
              </Typography>
            </Paper>
            <Collapse in={isTrainerFeedbackOpen} timeout="auto" unmountOnExit>
              <Box sx={{ mt: 1 }}>Some feedback here for the trainee.</Box>
            </Collapse>
          </Box>
        )}

        <Dialog open={openAddActivityUpdateModal}>
          <DialogContent>
            <Box sx={{ display: "flex", flexDirection: "column", gap: 3 }}>
              <Box sx={{ display: "flex" }}>
                <Typography variant="h6" sx={{ minWidth: 100 }}>
                  Activity
                </Typography>
                <Select
                  sx={{
                    width: 250,
                    height: 30,
                  }}
                  defaultValue={1}
                >
                  <MenuItem
                    value={1}
                    onClick={() => {
                      setSelectedActivity("Pod Project");
                    }}
                  >
                    Pod Project
                  </MenuItem>
                  <MenuItem
                    value={2}
                    onClick={() => {
                      setSelectedActivity("UpSkilling");
                    }}
                  >
                    Upskilling
                  </MenuItem>
                  <MenuItem
                    value={3}
                    onClick={() => {
                      setSelectedActivity("Opportunities");
                    }}
                  >
                    Opportunities
                  </MenuItem>
                  <MenuItem
                    value={4}
                    onClick={() => {
                      setSelectedActivity("Interviews");
                    }}
                  >
                    Interviews
                  </MenuItem>
                  <MenuItem
                    value={5}
                    onClick={() => {
                      setSelectedActivity("Comment");
                    }}
                  >
                    Comment
                  </MenuItem>
                </Select>
              </Box>

              <TraineePodProject
                values={nextProject}
                selectedActivity={selectedActivity}
              />
              <TraineeUpSkilling
                values={nextUpskilling}
                selectedActivity={selectedActivity}
              />
              <TraineeOpportunities
                values={nextOpportunity}
                selectedActivity={selectedActivity}
              />
              <TraineeInterview
                values={nextInterview}
                selectedActivity={selectedActivity}
              />
              <TraineeComment
                value={[comment, setComment]}
                selectedActivity={selectedActivity}
              />
            </Box>
          </DialogContent>
          <DialogActions>
            {selectedActivity && (
              <>
                <Button
                  onClick={() => {
                    setOpenAddActivityUpdateModal(false);
                    setSelectedActivity("");
                  }}
                >
                  Cancel
                </Button>
                {isAdding === true && (
                  <Button
                    onClick={() => {
                      setOpenAddActivityUpdateModal(false);

                      if (selectedActivity == "UpSkilling") {
                        upskilling.push(nextUpskilling);
                        setNextUpskilling([]);
                      } else if (selectedActivity == "Interviews") {
                        interviews.push(nextInterview);
                        setNextInterview([]);
                      } else if (selectedActivity == "Pod Project") {
                        projects.push(nextProject);
                        setNextProject([]);
                      } else if (selectedActivity == "Opportunities") {
                        opportunities.push(nextOpportunity);
                        setNextOpportunity([]);
                      }

                      setSelectedActivity("");
                    }}
                  >
                    Add Activity
                  </Button>
                )}
              </>
            )}
          </DialogActions>
        </Dialog>
      </Container>
    </>
  );
}
