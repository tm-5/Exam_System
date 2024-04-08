const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

function register() {
    var username = document.getElementById('usernameSignup').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('passwordSignup').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    if (password !== confirmPassword) {
        alert('Xác nhận mật khẩu không khớp!');
        return;
    }
    alert('Đăng ký thành công!');

}

function signIn() {
    var username = document.getElementById('usernameLogin').value;
    var password = document.getElementById('passwordLogin').value;
    if (username !== "" && password !== "") {
        const data = {
            username: username,
            password: password
        }
        console.log(data);
        postMethods('http://localhost:8080/users/loginAdmin',data).then(data => {
            if (data.status == 'OK') {
                saveUser(data.data);
                alert('Đăng nhập thành công!');
                window.location.href = "./quan-ly-nguoi-dung.html";
            } else {
                alert(data.message);
            }
        })
    } else {
        alert('Hãy nhập đủ thông tin');
    }
}

