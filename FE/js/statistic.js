getMethods('http://localhost:8080/contests/constestUserStatistic').then(data =>{
    console.log(data);
    changeContent(data.data);
    let xml = ""
    data.data.forEach((e)=>{
        xml += element(e);
    });
    document.getElementById('result-table').innerHTML = xml;
});

const changeContent = (data) => {
    let completionRate = 0;
    let scoreRate = 0;
    let totalFinishExam = 0;
    let totalExam = 0;

    data.forEach(e => {
        if(e.rightAnswer!=null&&e.wrongAnswer!=null){
            completionRate += (e.rightAnswer + e.wrongAnswer)/(e.rightAnswer + e.wrongAnswer + e.blankAnswer);
        }
        totalFinishExam += e.finishExam;
        totalExam += e.totalExam;
        if(e.rightAnswer!=null&&e.wrongAnswer!=null)
            scoreRate += (e.rightAnswer/(e.rightAnswer + e.wrongAnswer + e.blankAnswer))*10;
    });

    completionRate = (completionRate/data.length*100).toFixed(2);
    scoreRate = (scoreRate/data.length).toFixed(2);
    document.getElementById('finishExam').innerHTML = totalFinishExam;
    document.getElementById('unFinishExam').innerHTML = totalExam - totalFinishExam;
    document.getElementById('completionRate').innerHTML = completionRate + "%";
    document.getElementById('scoreRate').innerHTML = scoreRate;
}

const element = (data)=>{
    return `
        <tr>
            <td>${data.name}</td>
            <td>SV0${data.idUser<10?"0"+data.idUser:data.idUser}</td>
            <td>${data.finishExam}</td>
            <td>${data.totalExam - data.finishExam}</td>
            <td>${data.rightAnswer!=null&&data.wrongAnswer!=null?(data.rightAnswer + data.wrongAnswer)/(data.rightAnswer + data.wrongAnswer + data.blankAnswer)*100 +"%":""}</td>
            <td>${data.rightAnswer!=null&&data.wrongAnswer!=null?(Math.round(data.rightAnswer/(data.rightAnswer + data.wrongAnswer + data.blankAnswer)*1000)/100):""}</td>
        </tr>
    `
}

getMethods('http://localhost:8080/contests/constestExamStatistic').then(data =>{
    let dataChart = {
        labels: [],
        datasets: [{
            label: 'Điểm trung bình các bài kiểm tra',
            data: [],
            borderWidth: 1
        }]
    }
    let start = 0;
    if(data.data.length - 5 > 0) start = data.data.length - 5;

    for(let i = start; i < data.data.length; i++){
        let name ="KT0" + (data.data[i].idExam<10?"0"+data.data[i].idExam:data.data[i].idExam);
        dataChart.labels.push(name);
        dataChart.datasets[0].data.push((data.data[i].rightAnswer/(data.data[i].rightAnswer + data.data[i].wrongAnswer + data.data[i].blankAnswer))*10);
    }

    var ctx = document.getElementById("myChart");

    new Chart(ctx, {
        type: 'bar',
        data: dataChart,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

