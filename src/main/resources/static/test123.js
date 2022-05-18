const test = document.getElementById('test21')
let output = ''
// console.log(test);
// test.textContent = 'Проверка'

const url = 'http://localhost:8080/api/users'


// fetch(url)
//     .then(res => res.json())
//     .then(data => {
//         data.forEach(post => {
//             output += `
//             Имя: ${post.name}
//             Фамилия: ${post.lastName}
//             Емаил: ${post.email}
//             Роли: ${post.role}
//
//             `
//         })
//         test.innerText = output
//     })
getAllUsers()

function getAllUsers() {
    let temp = ''
    fetch(url)
        .then(response => response.json())
        .then(users => {
            users.forEach(user => {
                let roleOfUser = ''
                // for (let r of user.roles) {
                //     roleOfUser += r.role.replace("ROLE_", "") + " "
                // }
                temp += '<tr>'
                temp += '<td>' + user.id + '</td>'
                temp += '<td>' + user.name + '</td>'
                temp += '<td>' + user.lastName + '</td>'
                temp += '<td>' + user.age + '</td>'
                temp += '<td>' + user.email + '</td>'
                // temp += '<td>' + roleOfUser + '</td>'
                temp += '<td>'
                //     '<button type="button" class="btn btn-info" data-toggle="modal" ' +
                //     'onclick="edit(' + user.id + ')">' +
                //     'Edit' +
                //     '</button>' +
                //     '</td>';
                // temp += '<td>' +
                //     '<button type="button" class="btn btn-danger" data-toggle="modal" ' +
                //     'onclick="deleteUser(' + user.id + ')">' +
                //     'Delete' +
                //     '</button>' +
                //     '</td>'
                // temp += '</tr>'
            })

            document.querySelector('#allUsers tbody').innerHTML = temp
            temp = ''
        })
}