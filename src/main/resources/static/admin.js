function getRoles(user) {
    let roles = '';
    for (const key in user.authorities) {
        roles += user.authorities[key].roleName + ' ';
    }
    return roles;
}

function renderTable() {
    const urlAllUsers = 'http://localhost:8080/api/users';
    const tableBody = document.getElementById("fill-table");
    fetch(urlAllUsers, {method: 'GET'}).then(res => res.json()).then(data => {
        let output = '';
        data.forEach(user => {
            output += `<tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>`;
            output += '<td>' + getRoles(user) + '</td>';
            output += `<td><button role="button" class="btn btn-info" data-toggle="modal">Edit</button></td>`;
            output += `<td><button role="button" class="btn btn-danger" data-toggle="modal">Delete</button></td>`;
            output += '</tr>';
        });
        tableBody.innerHTML = output;
    })
}

const urlUserLogin = 'http://localhost:8080/api/users/current';
const currentEmail = document.getElementById("fill-mail");
fetch(urlUserLogin, {method: 'GET'}).then(res => res.json()).then(user => {
    currentEmail.innerText = user.email + ' with roles: ' + getRoles(user);
});

renderTable();

const urlAllRoles = 'http://localhost:8080/api/roles';
const createCheckbox = document.getElementById("createCheckboxes");
let rolesArray = [];
fetch(urlAllRoles, {method: 'GET'}).then(res => res.json()).then(data => {
    let output = '';
    data.forEach(role => {
        rolesArray.push(role);
        output += `<div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" 
                                name="checked-create" value="${role.id}" id="'switch' + ${role.id}">
                        <label class="custom-control-label" for="'switch' + ${role.id}">${role.roleName}</label>
                   </div>`;
    })
    createCheckbox.innerHTML = output;
})

function getRolesForJSON(rolesSRC) {
    let rolesDST = [];
    let checkboxes = document.getElementsByName("checked-create");
    checkboxes.forEach(box => {
        if (box.checked) {
            let temp = rolesSRC.find(role => role.id == box.value);
            if (temp != null) {
                rolesDST.push(temp);
            }
        }
    })
    console.log(rolesDST);
    return rolesDST;
}
const urlCreate = 'http://localhost:8080/api/users'
const createPostForm = document.MyCreateForm;
const createFirstName = document.getElementById("firstName");
const createLastName = document.getElementById("lastName");
const createAge = document.getElementById("age");
const createEmail = document.getElementById("email");
const createPassword = document.getElementById("password");
createPostForm.addEventListener('submit', (e) => {
    e.preventDefault();

    fetch(urlCreate, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: createFirstName.value,
            lastName: createLastName.value,
            age: createAge.value,
            email: createEmail.value,
            password: createPassword.value,
            authorities: getRolesForJSON(rolesArray)
        })
    }).then(renderTable);
})


