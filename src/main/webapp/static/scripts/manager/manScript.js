console.log(
  "%c Welcome to V's console",
  "background: #cc9900; color: white; display: block;"
);
const currentUser = JSON.parse(localStorage.getItem("session"));
const urlReimbursements = "http://localhost:8080/EmployMe-0.0/api/reimbursements";
const urlEmployees = "http://localhost:8080/EmployMe-0.0/api/employees";
let reimbursements;
let employees;

window.onload = () => {
  if (localStorage.getItem("session") === null) {
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
  }
    //  let currentUser = {
    //      firstname: "Vytautas",
    //      lastname: "Klimavicius",
    //      email: "vyklimavicius@gmail.com",
    //  };
  let name = document.querySelector("#user-name");
  name.innerText = "Hello, " + currentUser.firstname + "!";
  console.log(
    "%c Load success!",
    "background: green; color: white; display: block;"
  );
};

const getMethodEmployees = (url, callback) => {
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

const setEmployees = response => {
  employees = JSON.parse(response);
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
getMethodReimbursements(urlReimbursements, setReimbursements);

const showProfile = () => {
  // let currentUser = {
  //     firstname: "Vytautas",
  //     lastname: "Klimavicius",
  //     email: "vyklimavicius@gmail.com",
  // }
  let mainCard = document.querySelector("#main-card");
  mainCard.innerHTML = "";
  mainCard.innerHTML = `<div class="card" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Profile</h3>
            <div class="card-body">
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Firstname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.firstname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Lastname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.lastname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Email:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.email}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Role:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">Manager</h6>
                <div style="margin-top: 7vh; text-align: center;">
                    <button onclick="showUpdateForm()" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Update</button>
                    <button onclick="showProfile()" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Delete</button>
                </div>
            </div>
        </div>`;
};


const viewEmployees = () => {
      let mainCard = document.querySelector("#main-card");
        mainCard.innerHTML = "";
        mainCard.innerHTML = `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Employees</h3>
                <table class="table table-striped table-bordered table-sm" style="border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
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

const logOut = () => {
  localStorage.clear();
  window.location.href = "http://localhost:8080/EmployMe-0.0/login";
};

const showfilterReimbursements = () => {
    let mainCard = document.querySelector("#main-card");
    mainCard.innerHTML = "";
    mainCard.innerHTML += `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Reimbursements</h3>
                <form id="search-form" onsubmit="filterReimbursements(event)" style="margin-bottom: 3vh; margin-left: 20vw;" class="form-inline my-2 my-lg-0">
                <input id="searchValue" style="width: 20vw; color: #cc9900; border: 1px solid #cc9900" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search by Employee Id">
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
