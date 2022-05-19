$(document).ready(function () {
    getAllUsers()
})

function getAllUsers() {
    let temp = ''
    fetch('/api/users')
        .then(response => response.json())
        .then(users => {
            users.forEach(user => {
                let roleOfUser = ''
                for (let r of user.roles) {
                    roleOfUser += r.role.replace("ROLE_", "") + " "
                }
                temp += '<tr>'
                temp += '<td>' + user.id + '</td>'
                temp += '<td>' + user.firstName + '</td>'
                temp += '<td>' + user.lastName + '</td>'
                temp += '<td>' + user.age + '</td>'
                temp += '<td>' + user.email + '</td>'
                temp += '<td>' + roleOfUser + '</td>'
                temp += '<td>' +
                    '<button type="button" class="btn btn-info" data-toggle="modal" ' +
                    'onclick="edit(' + user.id + ')">' +
                    'Edit' +
                    '</button>' +
                    '</td>';
                temp += '<td>' +
                    '<button type="button" class="btn btn-danger" data-toggle="modal" ' +
                    'onclick="deleteUser(' + user.id + ')">' +
                    'Delete' +
                    '</button>' +
                    '</td>'
                temp += '</tr>'
            })

            document.querySelector('#allUsers tbody').innerHTML = temp
            temp = ''
        })
}


function edit(id) {
    let tempModal = ''
    fetch('/api/users/' + id)
        .then(result => result.json())
        .then(user => {
            tempModal = '<div class="modal fade" id="edituser" tabIndex="-1" role="dialog" ' +
                'aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                '<div class="modal-dialog" role="document">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<h5 class="modal-title" id="exampleModalLabel">Edit user</h5>' +
                '<button type="button" class="close" data-dismiss="modal" ' +
                'aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>' +
                '<div class="modal-body">' +
                '<div class="container-fluid">' +
                '<div class="row">' +
                '<div class="col-sm-3">' +
                '</div>' +
                '<div class="col-sm-6 text-center">' +
                '<form method="PUT">' +
                '<div class="form-group">' +
                '<label for="edit1">' +
                '<b>' +
                'ID' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit1" name="id" value="' + user.id + '" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit2">' +
                '<b>' +
                'First name' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'value="' + user.firstName + '" ' +
                'class="form-control" id="edit2" name="firstName" ' +
                'placeholder="First name">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit3">' +
                '<b>' +
                'Last name' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit3" value="' + user.lastName + '" name="lastName" ' +
                'placeholder="Last name">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit4">' +
                '<b>' +
                'Age' +
                '</b>' +
                '</label>' +
                '<input type="number" ' +
                'class="form-control" id="edit4" value="' + user.age + '" name="age" ' +
                'placeholder="Age">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit5">' +
                '<b>' +
                'Email' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit5" value="' + user.email + '" name="email" ' +
                'placeholder="Email">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit6">' +
                '<b>' +
                'Password' +
                '</b>' +
                '</label>' +
                '<input type="password" class="form-control" name="password" ' +
                'id="edit6" value="' + user.password + '">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="select2">' +
                '<b>' +
                'Role' +
                '</b>' +
                '</label>' +
                '<select multiple class="form-control" size="2" ' +
                'name="roles" id="select2">' +
                '<option value="ROLE_ADMIN">ADMIN</option>' +
                '<option value="ROLE_USER">USER</option>' +
                '</select>' +
                '</div>' +
                '</form>' +
                '</div>' +
                '<div class="col-sm-3">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary" ' +
                'data-dismiss="modal">Close' +
                '</button>' +
                '<button type="submit" class="btn btn-primary" onclick="editSubmit()" data-dismiss="modal">Edit</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'

            document.getElementById('modal1').innerHTML = tempModal
            $("#edituser").modal()
        })
}

function editSubmit() {
    let id = document.getElementById('edit1').value
    let firstName = document.getElementById('edit2').value
    let lastName = document.getElementById('edit3').value
    let age = document.getElementById('edit4').value
    let email = document.getElementById('edit5').value
    let password = document.getElementById('edit6').value
    let roles = $('[name="roles"]').val()

    for (let i = 0; i < roles.length; i++) {
        if (roles[i] === 'ROLE_ADMIN') {
            roles[i] = {
                'id': 2,
                'role': 'ROLE_ADMIN'
            }
        }
        if (roles[i] === 'ROLE_USER') {
            roles[i] = {
                'id': 1,
                'role': 'ROLE_USER'
            }
        }
    }

    fetch('/api/users', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            id,
            firstName,
            lastName,
            age,
            email,
            password,
            'roles': roles
        })
    })
        .then(() => {
            getAllUsers()
        })

}

function deleteUser(id) {
    let tempModal = ''
    fetch('/api/users/' + id)
        .then(result => result.json())
        .then(user => {
            tempModal = '<div class="modal fade" id="deleteuser" tabIndex="-1" role="dialog" ' +
                'aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                '<div class="modal-dialog" role="document">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<h5 class="modal-title" id="exampleModalLabel">Delete user</h5>' +
                '<button type="button" class="close" data-dismiss="modal" ' +
                'aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>' +
                '<div class="modal-body">' +
                '<div class="container-fluid">' +
                '<div class="row">' +
                '<div class="col-sm-3">' +
                '</div>' +
                '<div class="col-sm-6 text-center">' +
                '<form method="DELETE">' +
                '<div class="form-group">' +
                '<label for="edit1">' +
                '<b>' +
                'ID' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit1" name="id" value="' + user.id + '" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit2">' +
                '<b>' +
                'First name' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'value="' + user.firstName + '" ' +
                'class="form-control" id="edit2" name="firstName" ' +
                'placeholder="First name" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit3">' +
                '<b>' +
                'Last name' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit3" value="' + user.lastName + '" name="lastName" ' +
                'placeholder="Last name" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit4">' +
                '<b>' +
                'Age' +
                '</b>' +
                '</label>' +
                '<input type="number" ' +
                'class="form-control" id="edit4" value="' + user.age + '" name="age" ' +
                'placeholder="Age" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit5">' +
                '<b>' +
                'Email' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit5" value="' + user.email + '" name="email" ' +
                'placeholder="Email" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="select2">' +
                '<b>' +
                'Role' +
                '</b>' +
                '</label>' +
                '<select multiple class="form-control" size="2" ' +
                'name="roles" id="select2" disabled>' +
                '<option value="ROLE_ADMIN">ADMIN</option>' +
                '<option value="ROLE_USER">USER</option>' +
                '</select>' +
                '</div>' +
                '</form>' +
                '</div>' +
                '<div class="col-sm-3">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary" ' +
                'data-dismiss="modal">Close' +
                '</button>' +
                '<button type="submit" class="btn btn-danger" onclick="deleteSubmit(' + id + ')" data-dismiss="modal">Delete</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'

            document.getElementById('modal1').innerHTML = tempModal
            $("#deleteuser").modal()
        })

}

function deleteSubmit(id) {
    fetch('/api/users/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
        .then(() => {
            getAllUsers()
        })
}

function addNewUser() {
    let firstName = document.getElementById('add1').value
    let lastName = document.getElementById('add2').value
    let age = document.getElementById('add3').value
    let email = document.getElementById('add4').value
    let password = document.getElementById('add5').value
    let roles = $('[id="select1"]').val()

    for (let i = 0; i < roles.length; i++) {
        if (roles[i] === 'ROLE_ADMIN') {
            roles[i] = {
                'id': 2,
                'role': 'ROLE_ADMIN'
            }
        }
        if (roles[i] === 'ROLE_USER') {
            roles[i] = {
                'id': 1,
                'role': 'ROLE_USER'
            }
        }
    }

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            firstName,
            lastName,
            age,
            email,
            password,
            'roles': roles
        })
    })
        .then(() => {
            document.getElementById('home-tab').click()
            getAllUsers()
            document.newUser.reset()
        })
}