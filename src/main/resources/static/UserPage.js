$(async function () {
    await thisUser();
});

async function thisUser() {
    fetch("http://localhost:8080/api/viewUser")
        .then(res => res.json())
        .then(data => {
            $('#headerUsername').append(data.username);
            let roles = data.roles.map(role => " " + role.name.substring(5));
            $('#headerRoles').append(roles);


            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.userName}</td>
                <td>${data.lastName}</td>
                <td>${data.email}</td>
                <td>${data.age}</td>
                <td>${data.password}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        });
}
