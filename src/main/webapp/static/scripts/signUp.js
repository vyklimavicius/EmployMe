console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');

const urlEmployees = "http://localhost:8080/EmployMe-0.0/api/employees";
const urlManagers = "http://localhost:8080/EmployMe-0.0/api/managers";
let respStatus;

const onSubmit = (e) => {
    e.preventDefault();
    let firstName, lastName, email, password;
    firstName = document.querySelector('#firstName').value;
    lastName = document.querySelector("#lastName").value;
    email = document.querySelector('#email').value.toLowerCase();
    password = document.querySelector('#password').value
    document.querySelector('#sign-up-form').reset();
    let body = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
    };
    let user = document.getElementById('toggle-user').checked;
    user ? user = "manager" : user = "employee";
    if (user == "manager"){
        sendMethod(urlManagers, body);
    } else {
        sendMethod(urlEmployees, body);
    }
};

const sendMethod = (url, body) => {
    
    let request = new XMLHttpRequest();
    
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 201) {
            console.log(request.responseText);
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;');
            setTimeout(() => {
              window.location.href = "http://localhost:8080/EmployMe-0.0/login";
            }, 2000);
        } else if (request.status == 422){
            console.error("Form was not submitted succesfully..."); 
            let modalBody = document.querySelector("#modal-message");
            modalBody.innerHTML = `<h2 style="color: #cc9900; font-size: 1vw">That email already exists!</h2>`;
            $("#exampleModal").modal("toggle");
        }
    }
    request.open("POST", url);
    request.setRequestHeader('content-type', 'application/json');
    request.send(JSON.stringify(body));
}