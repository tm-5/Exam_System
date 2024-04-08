package com.examBE.BackendExamSys.services.Impls;

import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.entities.QuestionEntity;
import com.examBE.BackendExamSys.entities.UserAnswerEntity;
import com.examBE.BackendExamSys.entities.ContestEntity;
import com.examBE.BackendExamSys.models.Dto.AnswerQuestionDto;
import com.examBE.BackendExamSys.models.Dto.UserAnswerDto;
import com.examBE.BackendExamSys.models.UserAnswerDetailModel;
import com.examBE.BackendExamSys.models.UserAnswerQuestionModel;
import com.examBE.BackendExamSys.repositories.ExamRep;
import com.examBE.BackendExamSys.repositories.QuestionRep;
import com.examBE.BackendExamSys.repositories.UserAnswerRep;
import com.examBE.BackendExamSys.repositories.ContestRep;
import com.examBE.BackendExamSys.services.UserAnswerService;
import com.examBE.BackendExamSys.utils.Constants;
import com.examBE.BackendExamSys.utils.DateNow;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRep userAnswerRep;
    private final QuestionRep questionRep;
    private final ContestRep contestRep;
    private final ExamRep examRep;


    private UserAnswerEntity ToUserAnswerEntity(AnswerQuestionDto userAnswerDto, int idContest){
        return UserAnswerEntity.create(
                0,
                idContest,
                userAnswerDto.getIdQuestion(),
                userAnswerDto.getAnswer()
        );
    }

    @Override
    public List<UserAnswerEntity> getAll() {
        return userAnswerRep.findAll();
    }

    @Override
    public UserAnswerEntity searchById(int id) throws ErrorException {
        return userAnswerRep.findFirstById(id);
    }

    @Override
    public UserAnswerDetailModel searchDetailByIdContest(int id) throws ErrorException{
        List<UserAnswerEntity> userAnswerList = userAnswerRep.findByIdContest(id);
        if(userAnswerList.isEmpty()) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        List<QuestionEntity> questionList = questionRep.findAll();
        List<UserAnswerQuestionModel> userAnswerQuestionList = new ArrayList<>();
        for(UserAnswerEntity userAnswer: userAnswerList){
            for(QuestionEntity question: questionList){
                if(userAnswer.getIdQuestion() == question.getId()){
                    userAnswerQuestionList.add(new UserAnswerQuestionModel(userAnswer.getAnswer(), question));
                    break;
                }
            }
        }
        return new UserAnswerDetailModel(userAnswerList.get(0).getId(), userAnswerList.get(0).getIdContest(), userAnswerQuestionList);
    }
    @Override
    public void start(UserAnswerEntity userAnswerEntity) throws ErrorException{
        int idContest = userAnswerEntity.getIdContest();
        ContestEntity contest = contestRep.findFirstById(idContest);
        if(contest == null) throw new ErrorException("You don't have this exam!");
        if(contest.getSubmitTime() != null) throw new ErrorException("You already finish this exam!");
        if(contest.getStartTime()!=null){
            ExamEntity exam = examRep.findFirstById(contest.getIdExam());
            if(contest.getStartTime().plusMinutes(exam.getTimeDuration()).isBefore(LocalDateTime.now())) throw new ErrorException("Time out!");
        }
        else{
            contest.setStartTime(DateNow.getTime());
            contestRep.save(contest);
        }
    }
    @Override
    public void submit(UserAnswerDto userAnswerDto) throws ErrorException{
        int idContest = userAnswerDto.getIdContest();
        ContestEntity contest = contestRep.findFirstById(idContest);
        if(contest == null) throw new ErrorException("You don't have this exam!");
        if(contest.getSubmitTime() != null) throw new ErrorException("You already finish this exam!");
        List<AnswerQuestionDto> answerQuestionDtoList = userAnswerDto.getAnswerQuestion();
        if(answerQuestionDtoList.isEmpty()) throw new ErrorException("Something went wrong");

        List<QuestionEntity> questionList = questionRep.findByIdExam(contest.getIdExam());
        int rightAnswer = 0;
        int wrongAnswer = 0;
        int blankAnswer = 0;

        List<UserAnswerEntity> userAnswerEntityList = new ArrayList<>();
        for(AnswerQuestionDto answerQuestion : answerQuestionDtoList){
            if(answerQuestion.getAnswer() == null) blankAnswer++;
            else{
                for(QuestionEntity question: questionList){
                    if(question.getId() == answerQuestion.getIdQuestion()){
                        if(question.getRightAnswer() == answerQuestion.getAnswer()) rightAnswer++;
                        else wrongAnswer++;
                        break;
                    }
                }
            }
            userAnswerEntityList.add(ToUserAnswerEntity(answerQuestion, idContest));
        }
        float score = rightAnswer * 10/questionList.size();
//        contest.setScore(score);
        contest.setSubmitTime(DateNow.getTime());
        contest.setRightAnswer(rightAnswer);
        contest.setWrongAnswer(wrongAnswer);
        contest.setBlankAnswer(blankAnswer);
        contestRep.save(contest);
        userAnswerRep.saveAll(userAnswerEntityList);
    }

    @Override
    public void edit(UserAnswerEntity newUserAnswer) throws ErrorException {
        if(Valids.isEmpty(newUserAnswer.getId())) throw new ErrorException("Id is Empty");
        UserAnswerEntity userAnswer = userAnswerRep.findFirstById(newUserAnswer.getId());
        if(!Valids.isEmpty(newUserAnswer.getIdContest())) {
            if(!contestRep.existsById(newUserAnswer.getIdContest())) throw new ErrorException("Id Contest not found");
            userAnswer.setIdContest(newUserAnswer.getIdContest());
        }
        if(!Valids.isEmpty(newUserAnswer.getIdQuestion())) {
            if(questionRep.existsById(newUserAnswer.getIdQuestion())) throw new ErrorException("Id Question not found");
            userAnswer.setIdQuestion(newUserAnswer.getIdQuestion());
        }
        if(!Valids.isEmpty(newUserAnswer.getAnswer())) {
            if(newUserAnswer.getAnswer()<1 || newUserAnswer.getAnswer()>4) throw new ErrorException("Answer is not valid");
            userAnswer.setAnswer(newUserAnswer.getAnswer());
        }
        userAnswerRep.save(userAnswer);
    }

    @Override
    public void delete(int id) throws ErrorException {
        if(!userAnswerRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        userAnswerRep.deleteById(id);
    }
}
