console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');
const currentUser = JSON.parse(localStorage.getItem("session"));
const url = "http://localhost:8080/EmployMe-0.0/api/reimbursements";
let reimbursements;
// let reimbursements = [
//   {
//     reimbursementId: 1,
//     employeeId: 1,
//     managerId: 1,
//     status: "pending",
//     reimbursement: 2000,
//     createdAt: 1582524000000,
//     updatedAt: 1582524000000
//   },
//   {
//     reimbursementId: 2,
//     employeeId: 2,
//     managerId: 1,
//     status: "pending",
//     reimbursement: 2000,
//     createdAt: 1582524000000,
//     updatedAt: 1582524000000
//   },
//   {
//     reimbursementId: 3,
//     employeeId: 3,
//     managerId: 1,
//     status: "pending",
//     reimbursement: 2000,
//     createdAt: 1582524000000,
//     updatedAt: 1582524000000
//   },
//   {
//     reimbursementId: 4,
//     employeeId: 4,
//     managerId: 1,
//     status: "pending",
//     reimbursement: 2000,
//     createdAt: 1582524000000,
//     updatedAt: 1582524000000
//   }];

// const currentUser = { 
//     id: 1,
//     firstname: "Vytautas",
//     lastname: "Klimavicius",
//     email: "vyklimavicius@gmail.com",
// }

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

// getMethod(url, setReimbursements);


// window.onload = () => {
//    if (localStorage.getItem("session") === null){
//         window.location.href = "http://localhost:8080/EmployMe-0.0/login";
//    }
//    let currentUser = JSON.parse(localStorage.getItem("session"));
//    let name = document.querySelector('#user-name');
//    name.innerText = "Hello, " + currentUser.firstname + "!";
//    console.log('%c Load success!', 'background: green; color: white; display: block;');
// }

window.onload = () => {
if (localStorage.getItem("session") === null){
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
}
//    let currentUser = {
//        firstname: "Vytautas",
//        lastname: "Klimavicius",
//        email: "vyklimavicius@gmail.com",
//    };
   let name = document.querySelector('#user-name');
   name.innerText = "Hello, " + currentUser.firstname + "!";
   console.log('%c Load success!', 'background: green; color: white; display: block;');
};

const showProfile = () => {
    // let currentUser = {
    //     firstname: "Vytautas",
    //     lastname: "Klimavicius",
    //     email: "vyklimavicius@gmail.com",
    // }
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
                    <button onclick="showProfile()" type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Delete</button>
                </div>
            </div>
        </div>`;

};

const addReimbursement = () => {
    let mainCard = document.querySelector('#main-card');
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<form id="reimbursement-form" onsubmit="onSubmit(event)" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Add reimbursement</h3>
            <br>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="margin-top: 6vh; width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;" for="reimbursement">Please type Amount $:</label>
                <br>
                <input style="margin-top: 3vh; width: 20vw; height: 4vh; font-size: 1vw; border-color: antiquewhite;" class="form-control-sm"
                    id="reimbursement" type="number" name="reimbursement" step="0.01" placeholder="two decimal places expected" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <input
                    style="margin-top: 10px; width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;"
                    class="btn btn-primary" type="submit">
            </div>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="margin-top: 6vh; width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;"
                    for="reimbursement">Status:</label>
                <br>
                <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Pending</h3>
            </div>
        </form>`
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
                            <th scope="col">#</th>
                            <th scope="col">reimbursement</th>
                            <th scope="col">status</th>
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

const onSubmit = (e) => {
    e.preventDefault();
    let reimbursement = document.querySelector('#reimbursement').value;
    let body = {
        employeeId: currentUser.id,
        reimbursement: reimbursement,
        status: "pending",
        managerId: 1
    }
    document.querySelector('#reimbursement-form').reset();
    sendMethod(url, body);
};

const sendMethod = (url, body) => {
    
    let request = new XMLHttpRequest();
    
    request.onreadystatechange = function () {
        if (request.status == 201) {
            console.log(request.responseText);
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;');
        } else {
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
