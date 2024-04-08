getMethods("http://localhost:8080/exams/listDetail").then(data => {
    console.log(data);
    let xml = '';
    data.data.forEach(e => {
        xml += element(e);
    });
    document.getElementById('result-table').innerHTML = xml;
})

const element = (data) => {
    return `
        <tr> 
            <td> ${data.name} </td>
            <td> ${data.description} </td>
            <td> ${data.type?"Có thời hạn":"Không thời hạn"} </td>
            <td> ${data.type&&data.startTime!=null?new Date(data.startTime).toLocaleDateString():""} </td>
            <td> ${data.type&&data.endTime!=null?new Date(data.endTime).toLocaleDateString():""} </td>
            <td> ${data.type?data.timeDuration:""} </td>
            <td> ${data.question.length} </td>
            <td>
                <button class="btn-edit-exam" onclick="window.location.href='/tao-ky-thi.html?id=${data.id}'"> <i class="fa-solid fa-pen" style="color: #63E6BE; font-size: 20px; margin-right: 5px;"></i> </button>
                <button class="btn-delete-exam" onclick="deleteExam(${data.id})"><i class="fa-solid fa-trash" style="font-size: 20px; color: rgb(255, 70, 70);"></i></button>
            </td>
        </tr>
    `
}

const deleteExam = (id) => {
    if(confirm('Are you sure to Delete this Exam?')) {
        deleteMethods(`http://localhost:8080/exams/delete?id=${Number(id)}`).then(data => {
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

