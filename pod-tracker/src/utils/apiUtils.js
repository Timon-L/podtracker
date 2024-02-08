import axios from "axios";

const API_URL = "http://localhost:8080/api";

export const fetchToken = async (username, password) => {
  const endpoint = `${API_URL}/auth/login`;
  const requestOptions = {
    auth: {
      username,
      password,
    },
  };

  try {
    const response = await axios.post(endpoint, {}, requestOptions);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 401) {
      throw new Error("Invalid username or password");
    } else {
      throw new Error("Network error");
    }
  }
};

export const fetchUserData = async (token, username) => {
  const endpoint = `${API_URL}/users/username?username=${username}`;

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  try {
    const response = await axios.get(endpoint, { headers });
    console.log(response.data);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("User not found");
    } else {
      throw new Error("Network error");
    }
  }
};

//client endpoints
export const fetchClients = async (token) => {
  const endpoint = `${API_URL}/clients`;

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  try {
    const response = await axios.get(endpoint, { headers });
    console.log(response.data);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to fetch client data");
    } else {
      throw new Error("Network error");
    }
  }
};

export const addNewClient = async (token, client) => {
  const endpoint = `${API_URL}/clients`;

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  try {
    const response = await axios.post(endpoint, client, { headers });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to add new client");
    } else {
      throw new Error("Network error");
    }
  }
};

export const deleteClient = async (token, clientId) => {
  const endpoint = `${API_URL}/clients/${clientId}`;

  const headers = {
    'Authorization': `Bearer ${token}`
  };

  try {
    const response = await axios.delete(endpoint, { headers });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to delete client");
    } else {
      throw new Error("Network error");
    }
  }
}
//account manager endpoints
export const fetchAccountManagers = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/accountmanagers`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch account manager data");
      } else {
        alert("Network error");
      }
    });
};

export const addAccountManager = (token, accountManager) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/accountmanagers`;

  axios
    .post(endpoint, accountManager, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add new account manager");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAccountManagersTwo = async (token) => {
  const endpoint = `${API_URL}/accountmanagers`;

  const headers = {
    'Authorization': `Bearer ${token}`
  };

  try {
    const response = await axios.get(endpoint, { headers });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to fetch account manager data");
    } else {
      throw new Error("Network error");
    }
  }
}

export const addNewAccountManagers = async (token, accountManager) => {
  const endpoint = `${API_URL}/accountmanagers`;

  const headers = {
    'Authorization': `Bearer ${token}`
  };

  try {
    const response = await axios.post(endpoint, accountManager, { headers });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to add new account manager");
    } else {
      throw new Error("Network error");
    }
  }
}

export const deleteAccountManager = async (token, accountManagerId) => {
  const endpoint = `${API_URL}/accountmanagers/${accountManagerId}`;

  const headers = {
    'Authorization': `Bearer ${token}`
  };

  try {
    const response = await axios.delete(endpoint, { headers });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      throw new Error("Failed to delete account manager");
    } else {
      throw new Error("Network error");
    }
  }
}

//pod endpoints
export const fetchPods = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch pods data");
      } else {
        alert("Network error");
      }
      //return [];
    });
};

export const addPod = (token, pod) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods`;

  axios
    .post(endpoint, pod, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add new pod");
      } else {
        alert("Network error");
      }
    });
};

export const editPod = (token, pod) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods/${pod.podId}`;

  axios
    .put(endpoint, pod, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to edit pod");
      } else {
        alert("Network error");
      }
    });
};

export const deletePod = (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods/${id}`;

  axios
    .delete(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to delete the pod");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAPod = (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods/${id}`;

  axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch pod data");
      } else {
        alert("Network error");
      }
    });
};

export const addUserToPod = (token, podId, userId) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods/addByIds?podId=${podId}&userId=${userId}`;

  axios
    .put(endpoint, {}, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to edit pod");
      } else {
        alert("Network error");
      }
    });
};

export const removeUserFromPod = (token, podId, userId) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/pods/removeByIds?podId=${podId}&userId=${userId}`;

  axios
    .put(endpoint, {}, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to edit pod");
      } else {
        alert("Network error");
      }
    });
};


//goal endpoints
export const addGoal = async (token, goal) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/goals`;

  return await axios
    .post(endpoint, goal, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add goal");
      } else {
        alert("Network error");
      }
    });
};

export const fetchGoals = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/goals`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch goals data");
      } else {
        alert("Network error");
      }
    });
};

export const fetchProjectsInGoal = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/goals/podProjectGoal?id=${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch projects in goal");
      } else {
        alert("Network error");
      }
    });
};

export const fetchUpskillingsInGoal = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/goals/upskillingGoal?id=${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
            return [];
      } else {
        alert("Network error");
      }
    });
};

export const fetchDiscussionsInGoal = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/goals/discussionGoal?id=${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch opportunity discussions in goal");
      } else {
        alert("Network error");
      }
    });
};

//podproject goal endpoints
export const addPodProjectGoal = async (token, projectGoal) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endPoint = `${API_URL}/podProject_goal`;

  return await axios
    .post(endPoint, projectGoal, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add Pod Project activity");
      } else {
        alert(error.message);
      }
    });
};

export const fetchAPodProjectGoal = async (token, goal, project) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endPoint = `${API_URL}/podProject_goal/search?goalId=${goal.id}&projectId=${project.projectId}`;

  return await axios
    .get(endPoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch Pod Project activity");
      } else {
        alert(error.message);
      }
    });
};

export const addProject = async (token, project) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/projects`;

  return await axios
    .post(endpoint, project, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add Pod Project");
      } else {
        alert("Network error");
      }
    });
};

export const fetchPodProjects = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/projects`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch projects data");
      } else {
        alert("Network error");
      }
    });
};

//upskilling endpoints
export const createUpskilling = async (token, skill) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/upskillings`;

  return await axios
    .post(endpoint, skill, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add upskilling");
      } else {
        alert(error.message);
      }
    });
};

export const fetchUpskillings = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/upskillings`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch skills");
      } else {
        alert(error.message);
      }
    });
};

export const fetchUpskillingById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/upskillings/search?id=${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch skill");
      } else {
        alert(error.message);
      }
    });
};

//upskilling goal endpoints
export const createUpskillingGoal = async (token, upskillingGoal) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/upskilling_goal`;

  return await axios
    .post(endpoint, upskillingGoal, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        console.log("Failed to add upskilling activity");
      } else {
        console.log(error.message);
      }
    });
};

export const fetchAUpskillingGoal = async (token, goal, skill) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endPoint = `${API_URL}/upskilling_goal/search?goalId=${goal.id}&skillId=${skill.id}`;

  return await axios
    .get(endPoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch upskilling activity");
      } else {
        alert(error.message);
      }
    });
};

//user endpoints
export const createUser = async (token, user) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/users/create`;

  return await axios
    .post(endpoint, user, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add user");
      } else {
        alert(error.message);
      }
    });
};

export const fetchUserById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/users/${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch user");
      } else {
        alert(error.message);
      }
    });
};

export const fetchUserByUsername = async (token, username) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/users/username?username=${username}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch user");
      } else {
        alert(error.message);
      }
    });
};

export const fetchAllUsers = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/users`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data);
        return response.data;
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch users");
      } else {
        alert(error.message);
      }
    });
};

export const updateUser = async (token, user) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/users/${user.userId}`;

  return await axios
    .put(endpoint, user, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to update user");
      } else {
        alert(error.message);
      }
    });
};

//interview endpoints
export const addInterview = async (token, interview) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviews`;

  return await axios
    .post(endpoint, interview, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add interview");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAnInterviewById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviews/${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch an interview");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAllInterview = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviews`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch interviews");
      } else {
        alert("Network error");
      }
    });
};

export const updateInterview = async (token, interview) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviews/${interview.id}`;

  return await axios
    .put(endpoint, interview, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to update interview");
      } else {
        alert("Network error");
      }
    });
};

//interview prep endpoints
export const addInterviewPrep = async (token, interviewPrep) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviewprep`;

  return await axios
    .post(endpoint, interviewPrep, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add interview prep");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAnInterviewPrepById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviewprep/${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch an interview prep");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAllInterviewPreps = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/interviewprep`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch interview preps");
      } else {
        alert("Network error");
      }
    });
};

//opportunity endpoints
export const addOpportunity = async (token, opportunity) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/opportunities`;

  return await axios
    .post(endpoint, opportunity, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add opportunity");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAnOpportunityById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/opportunities/${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch an opportunity");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAllOpportunities = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/opportunities`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch opportunities");
      } else {
        alert("Network error");
      }
    });
};

//opportunity discussion goal endpoints
export const addDiscussionGoal = async (token, discussionGoal) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussion_goal`;

  return await axios
    .post(endpoint, discussionGoal, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add opportunity discussion for a goal");
      } else {
        alert("Network error");
      }
    });
};

export const fetchADiscussionGoal = async (token, goal, discussion) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussion_goal/search?goalId=${goal.id}&discussionId=${discussion.id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch an opportunity discussion in a goal");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAllDiscussionGoals = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussion_goal`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch all opportunity discussions in all goals");
      } else {
        alert("Network error");
      }
    });
};

//opportunity discussion endpoints
export const addDiscussion = async (token, discussion) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussions`;

  return await axios
    .post(endpoint, discussion, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to add opportunity discussion");
      } else {
        alert("Network error");
      }
    });
};

export const fetchADiscussionById = async (token, id) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussions/search?id=${id}`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch an opportunity discussion");
      } else {
        alert("Network error");
      }
    });
};

export const fetchAllDiscussions = async (token) => {
  const requestOptions = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const endpoint = `${API_URL}/discussions`;

  return await axios
    .get(endpoint, requestOptions)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Failed to fetch opportunity discussions");
      } else {
        alert("Network error");
      }
    });
};
