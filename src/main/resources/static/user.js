const url = '/one'
let userInput = ''
fetch(url)
    .then(response => {
        response.json()
            .then(user => {

                    userInput += '<tr>'
                    userInput += '<td>' + user.id + '</td>'
                    userInput += '<td>' + user.firstName + '</td>'
                    userInput += '<td>' + user.lastName + '</td>'
                    userInput += '<td>' + user.age + '</td>'
                    userInput += '<td>' + user.email + '</td>'
                    userInput += '<td>'
                    user.roles.forEach((r) => userInput += r.role.replace('ROLE_', '') + ' ')
                    userInput += '<td>'
                    userInput += '</tr>'
                    document.getElementById('oneUser').innerHTML = userInput


                }
            )
    })
