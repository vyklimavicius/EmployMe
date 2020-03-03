console.log(
  "%c Welcome to V's console",
  "background: #cc9900; color: white; display: block;"
);

const currentUser = JSON.parse(localStorage.getItem("session"));
const urlReimbursements = "http://localhost:8080/EmployMe-0.0/api/reimbursements";
const urlEmployees = "http://localhost:8080/EmployMe-0.0/api/employees";
const urlManagers = "http://localhost:8080/EmployMe-0.0/api/managers";
let reimbursements, employees, managers;

// Check token

window.onload = () => {
  if (localStorage.getItem("session") === null) {
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
  }
  let name = document.querySelector("#user-name");
  name.innerText = "Hello, " + currentUser.firstname + "!";
  console.log(
    "%c Load success!",
    "background: green; color: white; display: block;"
  );
};

// Data fetching

const getMethodEmployees = (url, callback) => {
  let request = new XMLHttpRequest();

  request.onreadystatechange = function() {
    if (request.status == 200) {
      callback(request.response);
      console.info(
        "%c Employees were loaded!!",
        "background: green; color: white; display: block;"
      );
    } else {
      console.error("Employees were not loaded...");
    }
  };
  request.open("GET", url);
  request.send();
};

const setEmployees = response => {
  employees = JSON.parse(response);
};

const getMethodManagers = (url, callback) => {
  let request = new XMLHttpRequest();

  request.onreadystatechange = function() {
    if (request.status == 200) {
      callback(request.response);
      console.info(
        "%c  Managers were loaded!!",
        "background: green; color: white; display: block;"
      );
    } else {
      console.error("Managers were not loaded...");
    }
  };
  request.open("GET", url);
  request.send();
};

const setManagers = response => {
  managers = JSON.parse(response);
};

const getMethodReimbursements = (url, callback) => {
  let request = new XMLHttpRequest();

  request.onreadystatechange = function() {
    if (request.status == 200) {
      callback(request.response);
      console.info(
        "%c Reimbursements were loaded!!",
        "background: green; color: white; display: block;"
      );
    } else {
      console.error("Reimbursements were not loaded...");
    }
  };
  request.open("GET", url);
  request.send();
};

const setReimbursements = response => {
  reimbursements = JSON.parse(response);
};

getMethodEmployees(urlEmployees, setEmployees);
getMethodManagers(urlManagers, setManagers);
getMethodReimbursements(urlReimbursements, setReimbursements);


// Handler functions

const showProfile = () => {
  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML = `<div class="card" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Profile</h3>
            <div style="margin-top: 5vh" class="card-body">
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Firstname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.firstname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Lastname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.lastname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Email:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.email}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Role:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">Manager</h6>
            </div>
        </div>`;
};

const viewManagers = () => {

  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML = `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Managers</h3>
                <table  style="margin-top: 3vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;" class="table table-striped table-bordered table-sm">
                    <thead>
                        <tr>
                            <th style="color: #cc9900; scope="col">#</th>
                            <th style="color: #cc9900; scope="col">firstname</th>
                            <th style="color: #cc9900; scope="col">lastname</th>
                            <th style="color: #cc9900; scope="col">email</th>
                        </tr>
                    </thead>
                    <tbody id="managers-table">
                    </tbody>
                </table>
            </div>`;
  let managersTable = document.querySelector("#managers-table");
  managers.forEach(m => {
    return (managersTable.innerHTML += `<tr>
                                <th scope="row">${m.managerId}</th>
                                <td>${m.firstName}</td>
                                <td>${m.lastName}</td>
                                <td>${m.email}</td>
                            </tr>`);
  });

};

const viewEmployees = () => {
      let mainCard = document.querySelector("#main-card");
        mainCard.innerHTML = "";
        mainCard.innerHTML = `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Employees</h3>
                <table  style="margin-top: 3vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;" class="table table-striped table-bordered table-sm">
                    <thead>
                        <tr>
                            <th style="color: #cc9900; scope="col">#</th>
                            <th style="color: #cc9900; scope="col">firstname</th>
                            <th style="color: #cc9900; scope="col">lastname</th>
                            <th style="color: #cc9900; scope="col">email</th>
                        </tr>
                    </thead>
                    <tbody id="employees-table">
                    </tbody>
                </table>
            </div>`;
        let employeesTable = document.querySelector("#employees-table");
        employees.forEach(e => {
          return (employeesTable.innerHTML += `<tr>
                                <th scope="row">${e.employeeId}</th>
                                <td>${e.firstName}</td>
                                <td>${e.lastName}</td>
                                <td>${e.email}</td>
                            </tr>`);
        });
};

const showfilterReimbursements = () => {
    
    let mainCard = document.querySelector("#main-card");
    mainCard.innerHTML = "";
    mainCard.innerHTML += `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Reimbursements</h3>
                <form id="search-form" onsubmit="filterReimbursements(event)" style="margin-bottom: 3vh; margin-left: 20vw;" class="form-inline my-2 my-lg-0">
                <input id="searchValue" style="width: 20vw; color: #cc9900; border: 1px solid #cc9900; background-color: antiquewhite; border-color: antiquewhite;" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search by Employee Id">
                <button style="width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
                <table class="table table-striped table-bordered table-sm" style="margin-top: 4vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
                    <thead>
                        <tr>
                            <th style="color: #cc9900;" scope="col">#</th>
                            <th style="color: #cc9900;" scope="col">reimbursement</th>
                            <th style="color: #cc9900;" scope="col">status</th>
                        </tr>
                    </thead>
                    <tbody id="reimbursement-table">
                    </tbody>
                </table>
                </div>`;
};

const viewReimbursements = () => {
  
  setTimeout(() => {
    getMethodReimbursements(urlReimbursements, setReimbursements);
  }, 2000);
  
  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML += `<h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Pending Reimbursements</h3>
  <table class="table table-striped table-bordered table-sm" style="margin-top: 4vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
  <thead>
  <tr>
  <th style="color: #cc9900;" scope="col">#</th>
  <th style="color: #cc9900;" scope="col">EmployeeId</th>
  <th style="color: #cc9900;" scope="col">reimbursement</th>
  <th style="color: #cc9900;" scope="col">status</th>
  <th style="color: #cc9900;" scope="col">Approve</th>
  <th style="color: #cc9900;" scope="col">Deny</th>
  </tr>
  </thead>
  <tbody id="reimbursement-table">
  </tbody>
  </table>`;
  let maintable = document.querySelector("#reimbursement-table");
  let filteredReimbursements = reimbursements.filter(r => {
    return r.status == "pending";
  });
  
  filteredReimbursements.forEach(r => {
    return (maintable.innerHTML += `<tr id="${r.reimbursementId}">
    <th scope="row">${r.reimbursementId}</th>
    <th scope="row">${r.employeeId}</th>
    <td>$${r.reimbursement}</td>
    <td>${r.status}</td>
    <td><button onclick="clickReimbursement(event)" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 1vw; background-color: antiquewhite; border-color: antiquewhite;">Approved</button></td>
    <td><button onclick="clickReimbursement(event)" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 1vw; background-color: antiquewhite; border-color: antiquewhite;">Deny</button></td>
    </tr>`);
  });
};

const viewApproved = () => {

  setTimeout(() => {
    getMethodReimbursements(urlReimbursements, setReimbursements);
  }, 2000);

  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML += `<h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Approved Reimbursements</h3>
  <table class="table table-striped table-bordered table-sm" style="margin-top: 4vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
    <thead>
      <tr>
        <th style="color: #cc9900;" scope="col">#</th>
        <th style="color: #cc9900;" scope="col">EmployeeId</th>
        <th style="color: #cc9900;" scope="col">reimbursement</th>
        <th style="color: #cc9900;" scope="col">status</th>
        <th style="color: #cc9900;" scope="col">Manager</th>
      </tr>
    </thead>
    <tbody id="reimbursement-table">
    </tbody>
  </table>`;
  let maintable = document.querySelector("#reimbursement-table");
  let filteredReimbursements = reimbursements.filter(r => {
    return r.status == "Approved";
  });

  filteredReimbursements.forEach(r => {
    return (maintable.innerHTML += `<tr id="${r.reimbursementId}">
                                    <th scope="row">${r.reimbursementId}</th>
                                    <th scope="row">${r.employeeId}</th>
                                    <td>$${r.reimbursement}</td>
                                    <td>${r.status}</td>
                                    <td>${r.managerId}</td>
                                </tr>`);
  });


};

const viewDenied = () => {

  setTimeout(() => {
    getMethodReimbursements(urlReimbursements, setReimbursements);
  }, 2000);

  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML += `<h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Denied Reimbursements</h3>
  <table class="table table-striped table-bordered table-sm" style="margin-top: 4vh; border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
    <thead>
      <tr>
        <th style="color: #cc9900;" scope="col">#</th>
        <th style="color: #cc9900;" scope="col">EmployeeId</th>
        <th style="color: #cc9900;" scope="col">reimbursement</th>
        <th style="color: #cc9900;" scope="col">status</th>
        <th style="color: #cc9900;" scope="col">Manager</th>
      </tr>
    </thead>
    <tbody id="reimbursement-table">
    </tbody>
  </table>`;
  let maintable = document.querySelector("#reimbursement-table");
  let filteredReimbursements = reimbursements.filter(r => {
    return r.status == "Denied";
  });

  filteredReimbursements.forEach(r => {
    return (maintable.innerHTML += `<tr id="${r.reimbursementId}">
                                    <th scope="row">${r.reimbursementId}</th>
                                    <th scope="row">${r.employeeId}</th>
                                    <td>$${r.reimbursement}</td>
                                    <td>${r.status}</td>
                                    <td>${r.managerId}</td>
                                </tr>`);
  });

};

const logOut = () => {
  localStorage.clear();
  window.location.href = "http://localhost:8080/EmployMe-0.0/login";
};

// controller functions

const filterReimbursements = (e) => {
    e.preventDefault();
    let searchedId = parseInt(document.querySelector('#searchValue').value);
    document.querySelector('#search-form').reset();
    let maintable = document.querySelector("#reimbursement-table");
    maintable.innerHTML = "";
    let filteredReimbursements = reimbursements.filter(r => {
      return r.employeeId === searchedId;
    });
    
    filteredReimbursements.forEach(r => {
        return (maintable.innerHTML += `<tr>
                                    <th scope="row">${r.reimbursementId}</th>
                                    <td>$${r.reimbursement}</td>
                                    <td>${r.status}</td>
                                </tr>`);
    });
};

const clickReimbursement = (e) => {
      let reimbuId = parseInt(e.target.parentNode.parentElement.id);
      let message = e.target.innerText;

      if (message == "Deny"){
        message = "Denied";
      }

      let update = {
        reimbursementId : reimbuId,
        managerId: currentUser.id,
        status: message
      };
      
      updateReimbursement(update);
      getMethodReimbursements(urlReimbursements, setReimbursements);
      let mainCard = document.querySelector("#main-card");
      mainCard.innerHTML = `<div style="margin-top: 30vh; margin-left: 32vw;" class="spinner-border text-warning" role="status">
            <span class="sr-only">Loading...</span>
        </div>`;
};

const updateReimbursement = (body) => {
  let request = new XMLHttpRequest();
  request.onreadystatechange = function () {
    if (request.status == 200) {
      console.info(request.response);
      console.info(
        "%c Reimbursement was updated!!",
        "background: green; color: white; display: block;"
      );
    } else {
      console.error("Reimbursement was not updated...");
    }
  };
  request.open("PUT", "http://localhost:8080/EmployMe-0.0/api/reimbursements");
  request.setRequestHeader("content-type", "application/json");
  request.send(JSON.stringify(body));
};









