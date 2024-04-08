getMethods('http://localhost:8080/users/listStudent').then(data => {
    console.log(data);
    xml = '';
    data.data.forEach(d => {
        xml += element(d);
    });
    document.getElementById('result-table').innerHTML = xml;
})

const element = (data) => {
    return `
        <tr>
            <td> SV0${data.id<10?'0'+data.id:data.id} </td>
            <td> ${data.name} </td>
            <td> ${data.email} </td>
            <td> ${data.gender?'Male':'Female'} </td>
            <td> 
                <a href="chinh-sua-sinh-vien.html?id=${data.id}"><svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 256 256" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M160,112a24,24,0,1,1-24-24A24,24,0,0,1,160,112Zm64-72V216a16,16,0,0,1-16,16H64a16,16,0,0,1-16-16V192H32a8,8,0,0,1,0-16H48V136H32a8,8,0,0,1,0-16H48V80H32a8,8,0,0,1,0-16H48V40A16,16,0,0,1,64,24H208A16,16,0,0,1,224,40ZM190.4,163.2A67.88,67.88,0,0,0,163,141.51a40,40,0,1,0-53.94,0A67.88,67.88,0,0,0,81.6,163.2a8,8,0,1,0,12.8,9.6,52,52,0,0,1,83.2,0,8,8,0,1,0,12.8-9.6Z"></path></svg></a>
            </td>
            <td>
                <button onclick="EditForm(${data.id},'${data.name}','${data.email}',${data.gender})"><i class="fa-solid fa-pen" style="color: #63E6BE; font-size: 20px; margin-right: 5px;"></i></button>
                <button onclick="deleteStudent(${data.id})"><i class="fa-solid fa-trash" style="font-size: 20px; color: rgb(255, 70, 70);"></i></button>
            </td>
        </tr>
    `
}

const closeForm = () => {
    if(document.getElementsByClassName('form-container').length == 0) return;
    document.getElementsByClassName('form-container')[0].remove();
}

const EditForm = (id,name,email,gender) => {
    closeForm();
    document.body.innerHTML += `
        <div class="form-container">
            <div class="form-input"> 
                <h2>Update Student</h2>
                <input type="hidden" id="id" value="${id}">
                <label for="name">Name</label>
                <input type="text" id="name" placeholder="Name" value="${name}">
                <label for="email">Email</label>
                <input type="text" id="email" placeholder="Email" value="${email}">
                
                <div class="form-group">
                    <label for="gender"> Gender </label>
                    <span><input type="radio" id="gender-male" name="gender" value="1" ${gender?"checked":""}> Male</span>
                    <span><input type="radio" id="gender-female" name="gender" value="0" ${!gender?"checked":""}> Female</span>
                </div>
                <button onclick="updateStudent()" class="btn-submit">Update</button>
                <button onclick="closeForm()" class="btn-close"><svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 512 512" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="m289.94 256 95-95A24 24 0 0 0 351 127l-95 95-95-95a24 24 0 0 0-34 34l95 95-95 95a24 24 0 1 0 34 34l95-95 95 95a24 24 0 0 0 34-34z"></path></svg></button> 
            </div>
        </div>
        
    `
}

const updateStudent = () => {
    const idInput = document.getElementById('id').value;
    const nameInput = document.getElementById('name').value;
    const emailInput = document.getElementById('email').value;
    const genderInput = document.querySelector('input[name="gender"]:checked').value;
    const data = {
        id: Number(idInput),
        name: nameInput,
        email: emailInput,
        gender: genderInput=="1"?true:false
    }

    putMethods('http://localhost:8080/users/update',data).then(data => {
        if(data.status == 'OK') {
            alert('Update success');
            location.reload();
        }
        else{
            alert(data.message);
        }
    })
    closeForm();
}

const deleteStudent = (id) => {
    if(confirm('Are you sure to Delete this Student?')) {
        deleteMethods(`http://localhost:8080/users/delete?id=${Number(id)}`).then(data => {
            if(data.status == 'OK') {
                alert('Delete success');
                location.reload();
            } 
            else{
                alert(data.message);
            }
        })
    }
}

const AddStudentForm = () => {
    closeForm();
    document.body.innerHTML += `
        <div class="form-container">
            <div class="form-input"> 
                <h2>Update Student</h2>
                <input type="hidden" id="id">
                <label for="name">Name</label>
                <input type="text" id="name" placeholder="Name">
                <label for="email">Email</label>
                <input type="text" id="email" placeholder="Email">
                
                <div class="form-group">
                    <label for="gender"> Gender </label>
                    <span><input type="radio" id="gender-male" name="gender" value="1"> Male</span>
                    <span><input type="radio" id="gender-female" name="gender" value="0"> Female</span>
                </div>

                <label for="password">Password</label>
                <input type="text" id="password" placeholder="Password">

                <button onclick="AddStudent()" class="btn-submit">Add</button>
                <button onclick="closeForm()" class="btn-close"><svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 512 512" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="m289.94 256 95-95A24 24 0 0 0 351 127l-95 95-95-95a24 24 0 0 0-34 34l95 95-95 95a24 24 0 1 0 34 34l95-95 95 95a24 24 0 0 0 34-34z"></path></svg></button> 
            </div>
        </div>
        
    `
}

const AddStudent = () => {
    const nameInput = document.getElementById('name').value;
    const emailInput = document.getElementById('email').value;
    if(document.querySelector('input[name="gender"]:checked') == null) {
        alert('Please fill all the field');
        return;
    }
    const genderInput = document.querySelector('input[name="gender"]:checked').value;
    const passwordInput = document.getElementById('password').value;
    if(nameInput == "" || emailInput == "" || passwordInput == "") {
        alert('Please fill all the field');
        return;
    }
    const data = {
        name: nameInput,
        email: emailInput,
        gender: genderInput=="1"?true:false,
        password: passwordInput,
    }
    postMethods('http://localhost:8080/users/registerStudent',data).then(data => {
        if(data.status == 'OK') {
            alert('Add success');
            location.reload();
        }
        else{
            alert(data.message);
        }
    })
}