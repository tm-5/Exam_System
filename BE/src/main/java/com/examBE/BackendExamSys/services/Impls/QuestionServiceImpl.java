package com.examBE.BackendExamSys.services.Impls;

import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.entities.QuestionEntity;
import com.examBE.BackendExamSys.models.Dto.QuestionDto;
import com.examBE.BackendExamSys.repositories.ExamRep;
import com.examBE.BackendExamSys.services.QuestionService;
import com.examBE.BackendExamSys.utils.Constants;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.examBE.BackendExamSys.repositories.QuestionRep;

import java.util.List;
@Component
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final ExamRep examRep;
    private final QuestionRep questionRep;

    private QuestionEntity ToQuestionEntity(QuestionDto questionDto){
        return QuestionEntity.create(
                0,
                questionDto.getIdExam(),
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
    public List<QuestionEntity> getAll() {
        return questionRep.findAll();
    }

    @Override
    public QuestionEntity searchById(int id) throws ErrorException {
        QuestionEntity question = questionRep.findFirstById(id);
        if(question == null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        return question;
    }

    @Override
    public void create(QuestionDto newQuestion) throws ErrorException {
        if(Valids.isEmpty(newQuestion.getIdExam())) throw new ErrorException("Id Exam trong");
        if(Valids.isEmpty(newQuestion.getContent())) throw new ErrorException("Cau hoi trong");
        if(Valids.isEmpty(newQuestion.getAnswer1())) throw new ErrorException("Dap an 1 trong");
        if(Valids.isEmpty(newQuestion.getAnswer2())) throw new ErrorException("Dap an 2 trong");
        if(Valids.isEmpty(newQuestion.getAnswer3())) throw new ErrorException("Dap an 3 trong");
        if(Valids.isEmpty(newQuestion.getAnswer4())) throw new ErrorException("Dap an 4 trong");
        if(Valids.isEmpty(newQuestion.getRightAnswer())) throw new ErrorException("Dap an dung trong");
        ExamEntity exam = examRep.findFirstById(newQuestion.getIdExam());
        if(exam==null) throw new ErrorException("Khong ton tai Exam co ID "+ newQuestion.getIdExam());
        questionRep.save(ToQuestionEntity(newQuestion));
    }

    @Override
    public void edit(QuestionEntity newQuestion) throws ErrorException {
        if(Valids.isEmpty(newQuestion.getId())) throw new ErrorException("Something Went Worng");
        QuestionEntity question = questionRep.findFirstById(newQuestion.getId());
        if(question==null) throw new ErrorException("Something Went Worng");

        if(!Valids.isEmpty(newQuestion.getIdExam())) {
            ExamEntity exam = examRep.findFirstById(newQuestion.getIdExam());
            if(exam==null) throw new ErrorException("Khong ton tai Exam co ID "+ newQuestion.getIdExam());
            question.setIdExam(newQuestion.getIdExam());
        }
        if(!Valids.isEmpty(newQuestion.getContent())) question.setContent(newQuestion.getContent());
        if(!Valids.isEmpty(newQuestion.getAnswer1())) question.setAnswer1(newQuestion.getAnswer1());
        if(!Valids.isEmpty(newQuestion.getAnswer2())) question.setAnswer2(newQuestion.getAnswer2());
        if(!Valids.isEmpty(newQuestion.getAnswer3())) question.setAnswer3(newQuestion.getAnswer3());
        if(!Valids.isEmpty(newQuestion.getAnswer4())) question.setAnswer4(newQuestion.getAnswer4());
        if(!Valids.isEmpty(newQuestion.getRightAnswer())) question.setRightAnswer(newQuestion.getIdExam());
        if(!Valids.isEmpty(newQuestion.getExplain())) question.setExplain(newQuestion.getExplain());

        questionRep.save(question);
    }

    @Override
    public void delete(int id) throws ErrorException {
        if(!questionRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        questionRep.deleteById(id);
    }
}
