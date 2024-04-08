const idAnswer = getParams().id;
getMethods(`http://localhost:8080/userAnswers/searchDetailByIdContest?id=${idAnswer}`).then(data => {
    let xml = '';
    console.log(data);
    let marks = Math.round(1000/data.data.answerQuestion.length)/100;
    let i = 0;
    data.data.answerQuestion.forEach(e => {
        xml += element(i, e, marks);
        i++;
    });
    document.getElementById('result-table').innerHTML = xml;
})


const element = (i, data, marks) => {
    return `
        <tr>
            <td> ${i+1} </td>
            <td> Câu hỏi ${i+1} </td>
            <td> ${answer(data.question.rightAnswer)} </td>
            <td> ${answer(data.userAnswer)} </td>
            <td> ${marks} </td>
            <td> ${data.question.explain} </td>
        </tr>
    `
}

const answer = (i) => {
    if(i==0) return 'Đáp án A'
    if(i==1) return 'Đáp án B'
    if(i==2) return 'Đáp án C'
    if(i==3) return 'Đáp án D'
    return ''
}

