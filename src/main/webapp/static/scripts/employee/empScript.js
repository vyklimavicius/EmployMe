console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');
const currentUser = JSON.parse(localStorage.getItem("session"));
const url = "http://localhost:8080/EmployMe-0.0/api/reimbursements";
let reimbursements;
let selection;

// Check token

window.onload = () => {
if (localStorage.getItem("session") === null){
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
}

let name = document.querySelector('#user-name');
name.innerText = "Hello, " + currentUser.firstname + "!";
console.log('%c Load success!', 'background: green; color: white; display: block;');
};

// Data fetching 

const getMethod = (url, callback) => {

    let request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.status == 200) {
            callback(request.response);
            console.info('%c Reimbursements were loaded!!', 'background: green; color: white; display: block;');
        } else {
            console.error("Reimbursements were not loaded...");
        }
    }
    request.open("GET", url);
    request.send();
};

const setReimbursements = (response) => {
    reimbursements = JSON.parse(response);
};

// Handler functions

const showProfile = () => {
    let mainCard = document.querySelector('#main-card');
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
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">Employee</h6>
                <div style="margin-top: 7vh; text-align: center;">
                    <button onclick="showUpdateForm()" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Update</button>
                </div>
            </div>
        </div>`;

};

const addReimbursement = () => {
    let mainCard = document.querySelector('#main-card');
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<form id="reimbursement-form" onsubmit="onSubmit(event)" style="height: 85vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <div class="card-title" style="text-align: center;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Add reimbursement</h3>
            <label class="card-title" style="width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;"
                    for="reimbursement">Type:</label>
                <br>
                <h3 id="type-status" style="margin-top: 2vh; text-align: center; font-size: 2vw; color:#cc9900;"></h3>
            </div>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="margin-top: 6vh; width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;" for="reimbursement">Please type Amount $:</label>
                <br>
                <input style="margin-top: 3vh; width: 20vw; height: 4vh; font-size: 1vw; border-color: antiquewhite;" class="form-control-sm"
                    id="reimbursement" type="number" min="0" name="reimbursement" step="0.01" placeholder="two decimal places expected" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <input
                    style="margin-top: 10px; width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;"
                    class="btn btn-primary" type="submit">
            </div>
            <div class="btn-group">
              <button style="margin-left: 45vw; margin-top: 12px; width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Type
              </button>
              <div style="background-color: white" id="type" class="dropdown-menu" required>
              <a onclick="handleDropDown(event)" style="color: #cc9900; font-size: 1vw; background-color: white" class="dropdown-item" href="#">Relocation</a>
              <a onclick="handleDropDown(event)" style="color: #cc9900; font-size: 1vw; background-color: white" class="dropdown-item" href="#">Traveling</a>
              <a onclick="handleDropDown(event)" style="color: #cc9900; font-size: 1vw; background-color: white" class="dropdown-item" href="#">Miscellaneous</a>
            </div>
           </div>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;"
                    for="reimbursement">Status:</label>
                <br>
                <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Pending</h3>
            </div>
        </form>`;
};

const viewReimbursements = () => {
    getMethod(url, setReimbursements);
    let mainCard = document.querySelector("#main-card");
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<div style="margin-top: 30vh; margin-left: 32vw;" class="spinner-border text-warning" role="status">
    <span class="sr-only">Loading...</span>
    </div>`;
    setTimeout(() => {
        mainCard.innerHTML = "";
        mainCard.innerHTML = `<div> 
                <h3 style="text-align: center; font-size: 3vw; color:#cc9900;">Reimbursements</h3>
                <table class="table table-striped table-bordered table-sm" style="border: 2px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
                    <thead>
                        <tr>
                            <th style="color: #cc9900;" scope="col">#</th>
                            <th style="color: #cc9900;" scope="col">Reimbursement</th>
                            <th style="color: #cc9900;" scope="col">Type</th>
                            <th style="color: #cc9900;" scope="col">Status</th>
                        </tr>
                    </thead>
                    <tbody id="reimbursement-table">
                    </tbody>
                </table>
            </div>`;
        let maintable = document.querySelector('#reimbursement-table');
        let filteredReimbursements = reimbursements.filter((r) => {
            return r.employeeId === currentUser.id;
        });
        filteredReimbursements.forEach( (r) => {
            return (maintable.innerHTML += `<tr>
                                <th scope="row">${r.reimbursementId}</th>
                                <td>$${r.reimbursement}</td>
                                <td>${r.type}</td>
                                <td>${r.status}</td>
                            </tr>`);
        });
    }, 1000);
};

const logOut = () => {
    localStorage.clear();
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
};

// controller functions 
const handleDropDown = (e) => {
    selection = e.target.innerText;
    let typeStatus = document.querySelector('#type-status');
    typeStatus.innerText = selection;
};

const onSubmit = (e) => {
    e.preventDefault();
    let reimbursement = document.querySelector('#reimbursement').value;
    let body = {
        employeeId: currentUser.id,
        reimbursement: reimbursement,
        status: "Pending",
        type: selection,
        managerId: 1
    }
    document.querySelector('#reimbursement-form').reset();
    sendMethod(url, body);
    selection = null;
};

const sendMethod = (url, body) => {
    
    let request = new XMLHttpRequest();
    
    request.onreadystatechange = function () {
        if (request.status == 201) {
            console.log(request.responseText);
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;');
        } else if(request.status == 404){
            let modalBody = document.querySelector("#modal-message");
            modalBody.innerHTML = `<h2 style="color: #cc9900; font-size: 1vw">Please insert a Type!</h2>`;
            $("#exampleModal").modal("toggle");
            console.error("Form was not submitted succesfully...");
        }
    }
    request.open("POST", url);
    request.setRequestHeader('content-type', 'application/json');
    request.send(JSON.stringify(body));
};

const showUpdateForm = () => {
    let mainCard = document.querySelector("#main-card");
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<div class="card" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Update Profile</h3>
            <form id="sign-up-form" onsubmit="handleUpdate(event)" style="margin-top: 1vh; height: 70vh;">
            <br>
            <div class="form-group" style="text-align: center;">
                <label style="margin-top: 1vh; width: 20vw; height: 2vh; font-size: 1vw; color: #cc9900;" for="firstName">Please type your firstname:</label>
                <br>
                <input style="width: 10vw; height: 4vh; font-size: 1vw; border-color: #cc9900;" class="form-control-sm" id="firstName" type="text" name="firstName" placeholder="John" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <label style="width: 20vw; height: 2vh; font-size: 1vw; color: #cc9900;" for="firstName">Please type your lastname:</label>
                <br>
                <input style="width: 10vw; height: 4vh; font-size: 1vw; border-color: #cc9900;" class="form-control-sm" id="lastName" type="text" name="lastName" placeholder="Doe" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <label style="width: 20vw; height: 2vh; font-size: 1vw; color: #cc9900;" for="firstName">Please type your desired password:</label>
                <br>
                <input style="width: 10vw; height: 4vh; font-size: 1vw; border-color: #cc9900;" class="form-control-sm" id="password" type="password" name="password" placeholder="Type password" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <input style="margin-top: 3vh; width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;" class="btn btn-primary" type="submit" >
            </div>
            <h6 style="margin-top: 2vh; text-align: center; font-size: 1vw; color:red;">For security reasons this action will log you out!</h3>
        </form>
        </div>`;
};


const handleUpdate = (e) => {
    e.preventDefault();
    let firstName, lastName, password;
    firstName = document.querySelector("#firstName").value;
    lastName = document.querySelector("#lastName").value;
    password = document.querySelector("#password").value;
    let body = {
      employeeId: currentUser.id,
      firstName: firstName,
      lastName: lastName,
      password: password
    };
    // ajax
    let request = new XMLHttpRequest();

    request.onreadystatechange = function() {
      if (request.status == 200) {
        console.log(request.responseText);
        console.info(
          "%c Form was submitted succesfully!!",
          "background: green; color: white; display: block;"
        );
      } else {
        console.error("Form was not submitted succesfully...");
      }
    };
    request.open("PUT", "http://localhost:8080/EmployMe-0.0/api/employees");
    request.setRequestHeader("content-type", "application/json");
    request.send(JSON.stringify(body));
    logOut();
};
