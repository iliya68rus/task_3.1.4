const url = '/one'
let userInput = ''
fetch(url)
    .then(response => {
        response.json()
            .then(user => {
                userInput +=`
                <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.roleName}</td> 
                </tr>
                `
                document.getElementById('oneUser').innerHTML = userInput
                }
            )
    })
