const url = 'http://localhost:8080/api/users/current';
const tableBody = document.getElementById("fill-me");
const currentEmail = document.getElementById("fill-mail");
let output = '';
let roles = '';
fetch(url, {method: 'GET'}).then(res => res.json()).then(user => {
        output += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>`;
        for (const key in user.authorities) {
                roles += user.authorities[key].roleName + ' ';
        }
        currentEmail.innerText = user.email + ' with roles: ' + roles;
        output += roles + `</td></tr>`;
        tableBody.innerText = '';
        tableBody.innerHTML = output;
});

