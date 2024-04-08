package com.examBE.BackendExamSys.services.Impls;

import com.examBE.BackendExamSys.entities.ContestEntity;
import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.entities.QuestionEntity;
import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.models.Dto.ContestDto;
import com.examBE.BackendExamSys.models.Dto.ExamDto;
import com.examBE.BackendExamSys.models.Dto.ExamQuestionDto;
import com.examBE.BackendExamSys.models.Dto.QuestionDto;
import com.examBE.BackendExamSys.models.ExamDetailModel;
import com.examBE.BackendExamSys.repositories.ContestRep;
import com.examBE.BackendExamSys.repositories.ExamRep;
import com.examBE.BackendExamSys.repositories.QuestionRep;
import com.examBE.BackendExamSys.repositories.UserRep;
import com.examBE.BackendExamSys.services.ExamService;
import com.examBE.BackendExamSys.utils.Constants;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRep examRep;
    private final QuestionRep questionRep;
    private final UserRep userRep;
    private final ContestRep contestRep;


    private ExamEntity ToExamEntity(ExamDto examDto){
        return ExamEntity.create(
                0,
                examDto.getName(),
                examDto.getDescription(),
                examDto.getType(),
                examDto.getStartTime(),
                examDto.getEndTime(),
                examDto.getTimeDuration()
        );
    }

    private ExamEntity ToExamEntity(ExamQuestionDto examQuestionDto){
        return ExamEntity.create(
                0,
                examQuestionDto.getName(),
                examQuestionDto.getDescription(),
                examQuestionDto.getType(),
                examQuestionDto.getStartTime(),
                examQuestionDto.getEndTime(),
                examQuestionDto.getTimeDuration()
        );
    }

    private QuestionEntity ToQuestionEntity(QuestionDto questionDto, int idExam){
        return QuestionEntity.create(
                0,
                idExam,
                questionDto.getContent(),
                questionDto.getAnswer1(),
                questionDto.getAnswer2(),
                questionDto.getAnswer3(),
                questionDto.getAnswer4(),
                questionDto.getRightAnswer(),
                questionDto.getExplain()
        );
    }

    @Override
    public List<ExamEntity> getAll() {
        return examRep.findAll();
    }

    @Override
    public List<ExamDetailModel> getAllDetail(){
        List<ExamEntity> examList = examRep.findAll();
        List<QuestionEntity> questionList = questionRep.findAll();
        List<ExamDetailModel> examDetail = new ArrayList<>();
        for (ExamEntity e : examList) {
            List<QuestionEntity> question = new ArrayList<>();
            for(QuestionEntity q: questionList){
                if(q.getIdExam() == e.getId()){
                    question.add(q);
                }
            }
            examDetail.add(new ExamDetailModel(e, question));
        }
        return examDetail;
    }

    @Override
    public ExamEntity searchById(int id) throws ErrorException {
        ExamEntity exam = examRep.findFirstById(id);
        if(exam == null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        return exam;
    }
    @Override
    public ExamDetailModel searchDetailById(int id) throws ErrorException {
        ExamEntity exam = examRep.findFirstById(id);
        if(exam == null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        List<QuestionEntity> question = questionRep.findByIdExam(id);
        return new ExamDetailModel(exam, question);
    }

    @Override
    public void create(ExamDto examDto) throws ErrorException {
        if(Valids.isEmpty(examDto.getName())) throw new ErrorException("Ten trong");
        if(Valids.isEmpty(examDto.getDescription())) throw new ErrorException("Mo ta trong");
        if(Valids.isEmpty(examDto.getType())) throw new ErrorException("Loai trong");
        ExamEntity newExam = ToExamEntity(examDto);
        newExam = examRep.save(newExam);
        System.out.println(newExam.getId());
    }
    @Override
    public void createAll(ExamQuestionDto examQuestionDto) throws ErrorException {
        if(Valids.isEmpty(examQuestionDto.getName())) throw new ErrorException("Ten trong");
        if(Valids.isEmpty(examQuestionDto.getDescription())) throw new ErrorException("Mo ta trong");
        if(Valids.isEmpty(examQuestionDto.getType())) throw new ErrorException("Loai trong");
        if(examQuestionDto.getQuestion().isEmpty()) throw new ErrorException("Phai co it nhat cau hoi");
        List<QuestionDto> questionDtoList = examQuestionDto.getQuestion();
        for(int i=0; i < questionDtoList.size(); i++){
            if(Valids.isEmpty(questionDtoList.get(i).getContent())) throw new ErrorException(MessageFormat.format("Cau hoi {0} trong",i+1));
            if(Valids.isEmpty(questionDtoList.get(i).getAnswer1())) throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 1",i+1));
            if(Valids.isEmpty(questionDtoList.get(i).getAnswer2())) throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 2",i+1));
            if(Valids.isEmpty(questionDtoList.get(i).getAnswer3())) throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 3",i+1));
            if(Valids.isEmpty(questionDtoList.get(i).getAnswer4())) throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 4",i+1));
            if(Valids.isEmpty(questionDtoList.get(i).getRightAnswer())) throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an dung",i+1));
            if(questionDtoList.get(i).getRightAnswer() < 1 || questionDtoList.get(i).getRightAnswer() > 4 ) throw new ErrorException(MessageFormat.format("Cau hoi {0} dap an dung chi duoc phep tu 1 den 4",i+1));
        }
        ExamEntity newExam = ToExamEntity(examQuestionDto);
        newExam = examRep.save(newExam);
        int examId = newExam.getId();
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for(QuestionDto questionDto:questionDtoList){
            questionEntityList.add(ToQuestionEntity(questionDto,examId));
        }
        questionRep.saveAll(questionEntityList);
        List<UserEntity> listStudent = userRep.findAllByRoles("student");
        List<ContestEntity> listContest = new ArrayList<>();
        for(UserEntity st: listStudent){
            ContestEntity nc = new ContestEntity();
            nc.setIdExam(examId);
            nc.setIdUser(st.getId());
            listContest.add(nc);
        }
        contestRep.saveAll(listContest);
    }

    @Override
    public void edit(ExamEntity newExamEntity) throws ErrorException {
        if(Valids.isEmpty(newExamEntity.getId())) throw new ErrorException("Id Exam is Empty");
        ExamEntity examEntity = examRep.findFirstById(newExamEntity.getId());
        if(!Valids.isEmpty(newExamEntity.getName())) examEntity.setName(newExamEntity.getName());
        if(!Valids.isEmpty(newExamEntity.getDescription())) examEntity.setDescription(newExamEntity.getDescription());
        if(!Valids.isEmpty(newExamEntity.getType())) examEntity.setType(newExamEntity.getType());
        if(!Valids.isEmpty(newExamEntity.getStartTime())) examEntity.setStartTime(newExamEntity.getStartTime());
        if(!Valids.isEmpty(newExamEntity.getEndTime())) examEntity.setEndTime(newExamEntity.getEndTime());
        if(!Valids.isEmpty(newExamEntity.getTimeDuration())) examEntity.setTimeDuration(newExamEntity.getTimeDuration());
        examRep.save(examEntity);
    }

    @Override
    public void editAll(ExamDetailModel examDetailModel) throws ErrorException {
        if(Valids.isEmpty(examDetailModel.getId())) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        ExamEntity preExam = examRep.findFirstById(examDetailModel.getId());
        if(preExam==null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        if(!Valids.isEmpty(examDetailModel.getName())) preExam.setName(examDetailModel.getName());
        if(!Valids.isEmpty(examDetailModel.getDescription())) preExam.setDescription(examDetailModel.getDescription());
        if(!Valids.isEmpty(examDetailModel.getType())) preExam.setType(examDetailModel.getType());

        if(examDetailModel.getQuestion().isEmpty()) throw new ErrorException("Phai co it nhat cau hoi");
        List<QuestionEntity> newQuestionList = examDetailModel.getQuestion();
        List<QuestionEntity> questionList = questionRep.findByIdExam(examDetailModel.getId());
        List<QuestionEntity> deleteQuestionList = new ArrayList<>();
        List<QuestionEntity> saveQuestionList = new ArrayList<>();
        for(QuestionEntity question: questionList){
            boolean check = false;
            for(QuestionEntity newQuestion: newQuestionList){
                if(question.getId() == newQuestion.getId()){
                    check = true;
                    if (!Valids.isEmpty(newQuestion.getContent())) question.setContent(newQuestion.getContent());
                    if (!Valids.isEmpty(newQuestion.getAnswer1())) question.setAnswer1(newQuestion.getAnswer1());
                    if (!Valids.isEmpty(newQuestion.getAnswer2())) question.setAnswer2(newQuestion.getAnswer2());
                    if (!Valids.isEmpty(newQuestion.getAnswer3())) question.setAnswer3(newQuestion.getAnswer3());
                    if (!Valids.isEmpty(newQuestion.getAnswer4())) question.setAnswer4(newQuestion.getAnswer4());
                    if (!Valids.isEmpty(newQuestion.getRightAnswer()) && newQuestion.getRightAnswer() >= 1 && newQuestion.getRightAnswer() <= 4) question.setRightAnswer(newQuestion.getRightAnswer());
                    saveQuestionList.add(question);
                    break;
                }
            }
            if(!check){
                deleteQuestionList.add(question);
            }
        }
        for(QuestionEntity newQuestion: newQuestionList){
            if(Valids.isEmpty(newQuestion.getId())){
                saveQuestionList.add(newQuestion);
            }
        }
        examRep.save(preExam);
        questionRep.saveAll(saveQuestionList);
        questionRep.deleteAll(deleteQuestionList);
    }

    @Override
    public void delete(int id) throws ErrorException {
        if(!examRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        examRep.deleteById(id);
    }
}
