// app.js
async function handleRegister(evt) {
    evt.preventDefault();
    const form = evt.target;
    const data = Object.fromEntries(new FormData(form));
    const res = await fetch('/api/register', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(data)
    });
    if (res.ok) {
        window.location = 'login.html?registered';
    } else {
        const err = await res.text();
        form.querySelector('#error').textContent = err;
    }
}

async function handleLogin(evt) {
    evt.preventDefault();
    const form = evt.target;
    const data = new URLSearchParams(new FormData(form));
    const res = await fetch('/login', {
        method: 'POST',
        body: data,
    });
    if (res.ok) {
        window.location = '/';
    } else {
        form.querySelector('#loginError').textContent = 'Invalid credentials';
    }
}

async function fetchUsers() {
    const res = await fetch('/api/users');
    if (res.ok) {
        const list = document.getElementById('users');
        (await res.json()).forEach(u => {
            const li = document.createElement('li');
            li.textContent = `${u.name} (${u.email}), age ${u.age}`;
            list.append(li);
        });
    }
}

function handleLogout() {
    fetch('/logout', {method:'POST'}).then(() => window.location='login.html');
}

document.addEventListener('DOMContentLoaded', () => {
    const path = location.pathname;
    if (path.endsWith('register.html')) {
        document.getElementById('registerForm').addEventListener('submit', handleRegister);
    } else if (path.endsWith('login.html')) {
        document.getElementById('loginForm').addEventListener('submit', handleLogin);
    } else if (path.endsWith('index.html') || path==='/' ) {
        fetchUsers();
        document.getElementById('logout').addEventListener('click', handleLogout);
    }
});
