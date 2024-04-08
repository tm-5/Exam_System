let idExam = null;
if(getParams().id!=undefined){
    getMethods(`http://localhost:8080/exams/searchDetailById?id=${getParams().id}`).then(data => {
        idExam = data.data.id
        changeContentExam(data.data.name, data.data.description, data.data.type?"Thời gian cụ thể":"Tự do", formatDate(data.data.startTime), formatDate(data.data.endTime), data.data.timeDuration)
        data.data.question.forEach(e => {
            addQuestion(e.content, e.answer1, e.answer2, e.answer3, e.answer4, e.rightAnswer, e.id)
        });
    })
}

function toggleDateTimeFields() {
    if (document.getElementById('examType') == null) {
        return;
    }
    const examTypeSelect = document.getElementById('examType');
    const dateTimeFields = document.getElementById('dateTimeFields');

    if (examTypeSelect.value === 'Thời gian cụ thể') {
        dateTimeFields.style.display = 'block';
    } else {
        dateTimeFields.style.display = 'none';
    }
}

if (document.getElementById('examType') != null) {
    document.getElementById('examType').addEventListener('change', toggleDateTimeFields);
}

if (document.getElementById('submit') != null) {
    document.getElementById('submit').addEventListener('click', function() {
        submit();
    });
}

if (document.getElementById('uploadExcelBtn') != null) {
    document.getElementById('uploadExcelBtn').addEventListener('click', function() {
        excelInput.click(); // Mở hộp thoại chọn file khi người dùng nhấn vào nút
    });
}

if (document.getElementById('excelInput') != null) {
    const excelInput = document.getElementById('excelInput');
    excelInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const data = new Uint8Array(e.target.result);
                const workbook = XLSX.read(data, { type: 'array' });
                const sheet = workbook.Sheets[workbook.SheetNames[0]];

                changeContentExam(sheet['B1'].v, sheet['B2'].v, sheet['B3'].v, formatDate(sheet["B4"].w), formatDate(sheet["B5"].w), Number(sheet['B6'].v.split(' ')[0]))

                const numberOfQuestions = Number(sheet['!ref'].split(':')[1].substring(1));
                for (let i = 8; i <= numberOfQuestions; i++) {
                    addQuestion(sheet[`B${i}`].v, sheet[`C${i}`].v, sheet[`D${i}`].v, sheet[`E${i}`].v, sheet[`F${i}`].v, sheet[`G${i}`].v);
                }
            };
            reader.readAsArrayBuffer(file);
        }
    });
}

const changeContentExam = (name,description,type,startDate,endDate,timeOfContest) => {
    document.getElementById('examName').value = name
    document.getElementById('examDescription').value = description
    document.getElementById('examType').value = type
    toggleDateTimeFields();
    document.getElementById('startDate').value = startDate
    document.getElementById('endDate').value = endDate
    document.getElementById('timeOfContest').value = timeOfContest
}

if (document.getElementById('addQuestionBtn') != null) {
    const addQuestionBtn = document.getElementById('addQuestionBtn');
    addQuestionBtn.addEventListener('click', function() {
        const numberOfQuestions = parseInt(document.getElementById('numberOfQuestions').value);
        const errorElement = document.getElementById('numberOfQuestionsError');

        if (numberOfQuestions < 0) {
            errorElement.style.display = 'block'; // Hiển thị thông báo lỗi
        } else {
            errorElement.style.display = 'none'; // Ẩn thông báo lỗi nếu không có lỗi
            for (let i = 0; i < numberOfQuestions; i++) {
                addQuestion();
            }
        }
    });
}

function submit() {
    const nameContest = document.getElementById('examName').value;
    const disCribe = document.getElementById('examDescription').value;
    const type = document.getElementById('examType').value;

    if (nameContest.trim() === "") {
        alert("Vui lòng nhập tên kỳ thi!");
        return;
    }

    if (type == 'Thời gian cụ thể') {
        const timeBegin = document.getElementById('startDate').value;
        const timeEnd = document.getElementById('endDate').value;
        const timeContest = document.getElementById('timeOfContest').value;
        if (!timeBegin) {
            alert("Vui lòng chọn Thời gian bắt đầu!");
            return; // Dừng hàm submit nếu ngày bắt đầu không được chọn
        }
        if (!timeEnd) {
            alert("Vui lòng chọn Thời gian kết thúc!");
            return; // Dừng hàm submit nếu ngày bắt đầu không được chọn
        }
        // Kiểm tra điều kiện về thời gian
        if (new Date(timeBegin) >  new Date(timeEnd)) {
            alert("Lỗi thời gian kỳ thi!");
            return;
        } 
        if(!timeContest) {
            alert("Vui lòng chọn Thời gian lam bai!");
            return;
        }
    }
    
    let data = {
        name: nameContest,
        description: disCribe,
        type: type == 'Thời gian cụ thể' ? true : false,
        startTime: type == 'Thời gian cụ thể' ? document.getElementById('startDate').value : null,
        endTime: type == 'Thời gian cụ thể' ? document.getElementById('endDate').value : null,
        timeDuration: type == 'Thời gian cụ thể' ? document.getElementById('timeOfContest').value : null,
        question: []
    }
   
    if(idExam!=null){
        data.id = idExam
    }

    let listQuestionEle = document.getElementsByClassName('question')
    for(let i=0;i<listQuestionEle.length;i++) {
        let q = listQuestionEle[i].querySelectorAll('input[type="text"]')[0].value
        let a1 = listQuestionEle[i].querySelectorAll('input[type="text"]')[1].value
        let a2 = listQuestionEle[i].querySelectorAll('input[type="text"]')[2].value
        let a3 = listQuestionEle[i].querySelectorAll('input[type="text"]')[3].value
        let a4 = listQuestionEle[i].querySelectorAll('input[type="text"]')[4].value
        let correct = listQuestionEle[i].querySelector('select').value
        if(q == ""){alert("Cau hoi so "+(i+1)+" chua nhap noi dung"); return}
        if(a1 == ""){alert("Cau hoi so "+(i+1)+" chua nhap dap an 1"); return}
        if(a2 == ""){alert("Cau hoi so "+(i+1)+" chua nhap dap an 2"); return}
        if(a3 == ""){alert("Cau hoi so "+(i+1)+" chua nhap dap an 3"); return}
        if(a4 == ""){alert("Cau hoi so "+(i+1)+" chua nhap dap an 4"); return}
        if(correct == "" || (correct!=1&&correct!=2&&correct!=3&&correct!=4)){alert("Cau hoi so "+(i+1)+" chua chon dap an dung"); return}

        data.question.push({
            content: q,
            answer1: a1,
            answer2: a2,
            answer3: a3,
            answer4: a4,
            rightAnswer: correct
        })
        if(idExam!=null){
            id = listQuestionEle[i].querySelector('input[type="hidden"]').value
            data.question[i].id = id==""?null:id
            data.question[i].idExam = idExam
        }
    }
    if(idExam==null){
        postMethods('http://localhost:8080/exams/createAll', data).then(data => {
            if (data.status == 'OK') {
                alert('Tạo kỳ thi thành công');
                window.location.href = "./trang-bai-thi.html";
            } else {
                alert(data.message);
            }
        })
    }
    else{
        putMethods(`http://localhost:8080/exams/updateAll?id=${idExam}`, data).then(data => {   
            if (data.status == 'OK') {
                alert('Sửa kỳ thi thành công');
                window.location.href = "./trang-bai-thi.html";
            } else {
                alert(data.message);
            }
        })
    }
}

function updateQuestionNumbers() {
    const questionElements = document.getElementsByClassName('question');
    for (let i = 0; i < questionElements.length; i++) {
        questionElements[i].querySelector('span').innerText = `Câu hỏi ${i + 1}: `;
    }
}

let questionIndex = 0;
function addQuestion(q = "", a1 = "", a2 = "", a3 = "", a4 = "", correct = "", id="") {
    let questData = []
    let questionElements = document.querySelectorAll('.question');
    if(questionElements!=null && questionElements.length>0) questionElements.forEach(e => {
        let q = e.querySelectorAll('input[type="text"]')[0].value
        let a1 = e.querySelectorAll('input[type="text"]')[1].value
        let a2 = e.querySelectorAll('input[type="text"]')[2].value
        let a3 = e.querySelectorAll('input[type="text"]')[3].value
        let a4 = e.querySelectorAll('input[type="text"]')[4].value
        let correct = e.querySelector('select').value
        let id = e.querySelector('input[type="hidden"]').value
        questData.push({q,a1,a2,a3,a4,correct,id})
    });

    if(questionElements!=null && questionElements.length>0) quesCount = questionElements.length + 1;
    else quesCount = 1;
    
    let xml = `
        <div class="question" id="question-${questionIndex}"> 
            <div class="question-header">
                <span> Câu hỏi ${quesCount}: </span> 
                <button onclick="removeQuestion(${questionIndex})" class="remove-question">Xóa câu hỏi</button> 
            </div>
            <input type="text" placeholder="Nhập câu hỏi" required="" value="${q}">
            <div class="answer-row">
                <label>Đáp án 1: </label>
                <input type="text" placeholder="Nhập đáp án 1" required="" value="${a1}">
                <label>Đáp án 2: </label>
                <input type="text" placeholder="Nhập đáp án 2" required="" value="${a2}">
                <label>Đáp án 3: </label>
                <input type="text" placeholder="Nhập đáp án 3" required="" value="${a3}">
                <label>Đáp án 4: </label>
                <input type="text" placeholder="Nhập đáp án 4" required="" value="${a4}">
            </div>
            <select required="">
                <option disabled="" ${correct==""?"selected":""}>Đáp án đúng là</option>
                <option value="1" ${correct==1?"selected":""}>Đáp án 1</option>
                <option value="2" ${correct==2?"selected":""}>Đáp án 2</option>
                <option value="3" ${correct==3?"selected":""}>Đáp án 3</option>
                <option value="4" ${correct==4?"selected":""}>Đáp án 4</option>
            </select>
            <input type="hidden" name="id" value="${id}">
        </div>
    `
    questionIndex++;
    document.getElementById('questionContainer').innerHTML += xml;
    questionElements = document.querySelectorAll('.question');
    if(questData.length>0) 
        questData.forEach((e,i) => {
            questionElements[i].querySelectorAll('input[type="text"]')[0].value = e.q
            questionElements[i].querySelectorAll('input[type="text"]')[1].value = e.a1
            questionElements[i].querySelectorAll('input[type="text"]')[2].value = e.a2
            questionElements[i].querySelectorAll('input[type="text"]')[3].value = e.a3
            questionElements[i].querySelectorAll('input[type="text"]')[4].value = e.a4
            questionElements[i].querySelector('select').value = e.correct
            questionElements[i].querySelector('input[type="hidden"]').value = e.id
        });
}

const removeQuestion = (id) => {
    let question = document.getElementById(`question-${id}`);
    question.remove();
    updateQuestionNumbers();
}

const formatDate = (time) => {
    let x = new Date(time)
    let y = x.getFullYear()
    let M = x.getMonth() + 1
    M = M < 10 ? '0' + M : M
    let d = x.getDate()
    d = d < 10 ? '0' + d : d
    let h = x.getHours()
    h = h < 10 ? '0' + h : h
    let m = x.getMinutes()
    m = m < 10 ? '0' + m : m
    return y+'-'+M+'-'+d+'T'+h+':'+m
}
