import mysql.connector
import random
import re
import json

# Replace the following with your own details
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="123456",
    database="backendexamsys"
)

lastName = ["Nguyễn","Đỗ","Trần","Lê","Phạm","Vũ","Võ","Phan","Trương","Bùi","Ngô","Hồ","Hoàng","Đặng","Đào"]
middleNameMale = ["Văn","Anh","Duy","Minh","Hữu","Hải","Tuấn","Ngọc","Trọng","Thanh","Mạnh"]
middleNameFemale = ["Thị","Xuân","Thu","Hồng","Thúy","Diễm","Ngọc","Hương","Thùy","Quỳnh","Hải"]
firstNameMale = ["An","Anh","Bình","Bảo","Công","Cường","Duy","Dũng","Đạt","Đăng","Hiếu","Hùng","Kiên","Khánh","Long","Minh","Nam","Nghĩa","Phong","Phú","Quân","Quang","Quốc","Sơn","Thành","Thái","Thắng","Thịnh","Trung","Tuấn","Tùng","Việt","Vinh","Vũ","Vương","Văn","Vĩnh","Xuân","Đức"]
firstNameFemale = ["Anh","Dương","Chi","Châu","Hân","Hoài","Ngọc","Ngân","Thu","Trang","Thùy","Linh","Tuyết","Giang","Hương","Hạnh","Hà","Hằng","Hồng","Huyền","Hải","Hậu"]
listNganhhoc = ["Công Nghệ Thông Tin","An Toàn Thông Tin","Kế Toán","Quản Trị Kinh Doanh","Điện Tử","Tài Chính","Báo Chí"]
listAddress = ["Hà Nội","Hưng Yên","Hải Dương", "Hải Phòng", "Quảng Ninh", "Lào Cai", "Hà Giang", "Yên Bái","Thanh Hóa", "Nghệ An", "Hà Tĩnh", "Ninh Bình","Hòa Bình"]

cursor = db.cursor()

def insertStudent(data):
    sql = "INSERT INTO users( name,gender, email, password, roles) VALUES ( %s, %s, %s, %s, %s)"
    val = (data[0], data[1], data[2], data[3], data[4])
    cursor.execute(sql, val)
    db.commit()
    
def noneSign(s):
    signa = r"[àáảãạâầấẩẫậăằắẳẵặ]"
    signA = r"[ÂẦẤẨẪẬĂẰẮẲẴẶÁÀẢÃẠ]"
    signd = r"[đ]"
    signD = r"[Đ]"
    signe = r"[èéẻẽẹêềếểễệ]"
    signE = r"[ÊỀẾỂỄỆÉÈẺẼẸ]"
    signi = r"[ìíỉĩị]"
    signI = r"[ÌÍỈĨỊ]"
    signo = r"[òóỏõọôồốổỗộơờớởỡợ]"
    signO = r"[ÔỒỐỔỖỘƠỜỚỞỠỢÓÒỎÕỌ]"
    signu = r"[ùúủũụưừứửữự]"
    signU = r"[ƯỪỨỬỮỰÚÙỦŨỤ]"
    signy = r"[ỳýỷỹỵ]"
    signY = r"[ỲÝỶỸỴ]"

    s = re.sub(signa,"a",s)
    s = re.sub(signA,"A",s)
    s = re.sub(signd,"d",s)
    s = re.sub(signD,"D",s)
    s = re.sub(signe,"e",s)
    s = re.sub(signE,"E",s)
    s = re.sub(signi,"i",s)
    s = re.sub(signI,"I",s)
    s = re.sub(signo,"o",s)
    s = re.sub(signO,"O",s)
    s = re.sub(signu,"u",s)
    s = re.sub(signU,"U",s)
    s = re.sub(signy,"y",s)
    s = re.sub(signY,"Y",s)

    return s

def fakeName(gender):
    if gender:
        name = random.choice(lastName) + " " + random.choice(middleNameMale) + " " + random.choice(firstNameMale)
    else:
        name = random.choice(lastName) + " " + random.choice(middleNameFemale) + " " + random.choice(firstNameFemale)
    return name

def fakeEmail(l,name):
    name=noneSign(name)
    n = name.split()
    email = n[n.__len__()-1]
    for i in range(0, n.__len__()-1):
        email += n[i][0].upper()
    email += l + "@gmail.com"
    return email

def createStudent():
    for i in range(0, 100):
        gender = random.randint(0, 1)
        name = fakeName(gender)
        email = fakeEmail(str(i),name)
        password = "123456"
        role = "student"
        data = ( name, gender, email, password, role)
        insertStudent(data)

def insertExam(data):
    sql = "INSERT INTO exams(name,description,type, start_time, end_time,time_duration) VALUES (%s, %s, %s, %s, %s, %s)"
    val = (data[0], data[1], data[2], data[3], data[4], data[5])
    cursor.execute(sql, val)
    db.commit()

def insertQuestion(data):
    sql = "INSERT INTO questions(id_exam,constent,answer1,answer2,answer3,answer4,right_answer, explains) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
    val = (data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7])
    cursor.execute(sql, val)
    db.commit()

file = open("1.json", "r", encoding="utf-8")
data = json.load(file)
print(data)

cursor.close()
db.close()
