let studentId = getParams().id;
getMethods(`http://localhost:8080/contests/searchDetailByIdUser?id=${studentId}`).then(data => {
    xml = '';
    console.log(data);
    data.data.forEach(d => {
        xml += element(d);
    });
    document.getElementById('result-table').innerHTML = xml;
})

const element = (data) => {
    return `
        <tr>
            <td> ${data.submitTime!=null?formatTime(data.submitTime):""} </td>
            <td>KTX0${data.exam.id<10?'0'+data.exam.id:data.exam.id}</td>
            <td>${data.exam.name}</td>
            <td> ${data.submitTime!=null?((data.rightAnswer+data.wrongAnswer)/(data.blankAnswer+data.rightAnswer+data.wrongAnswer)*100 + "%"):""} </td>
            <td> ${data.submitTime!=null?(Math.round((data.rightAnswer/(data.blankAnswer+data.rightAnswer+data.wrongAnswer)*1000)))/100:""} </td>
            <td> ${data.submitTime!=null?"Hoàn thành":(data.startTime==null?"Chưa làm":(compareTime(data.startTime,data.exam.timeDurition)?"Đang làm":"Quá thời hạn"))} </td>
            <td> ${data.submitTime!=null?"<a href='chi-tiet-cau-hoi.html?id="+data.id+"'><button>Xem chi tiết</button></a>":""}</td>
        </tr>
    `
}

const formatTime = (time) => {
    return new Date(time).toLocaleDateString();
}

const compareTime = (time, m) => {
    let t1 = new Date(time).getTime() + Number(m)*60*1000;
    let t2 = new Date().getTime();
    return t1>t2;
}


