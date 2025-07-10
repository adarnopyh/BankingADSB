window.onload = () => {
    fetch('/api/users') // assumes controller is @RequestMapping("api/users")
        .then(response => response.json())
        .then(users => {
            const userList = document.getElementById('users');
            users.forEach(user => {
                const li = document.createElement('li');
                li.textContent = `${user.name} (${user.email})`;
                userList.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
};
