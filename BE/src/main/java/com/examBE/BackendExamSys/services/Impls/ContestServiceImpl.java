package com.examBE.BackendExamSys.services.Impls;

import com.examBE.BackendExamSys.entities.ContestUserExamEntity;
import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.entities.ContestEntity;
import com.examBE.BackendExamSys.models.ContestExamModel;
import com.examBE.BackendExamSys.models.ContestUserModel;
import com.examBE.BackendExamSys.models.Dto.ContestDto;
import com.examBE.BackendExamSys.models.ContestDetailModel;
import com.examBE.BackendExamSys.repositories.ContestRep;
import com.examBE.BackendExamSys.repositories.ContestUserExamRep;
import com.examBE.BackendExamSys.repositories.UserRep;
import com.examBE.BackendExamSys.services.ContestService;
import com.examBE.BackendExamSys.repositories.ExamRep;
import com.examBE.BackendExamSys.utils.Constants;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {
    private final ExamRep examRep;
    private final ContestRep contestRep;
    private final UserRep userRep;
    private final ContestUserExamRep contestUserRep;
    private ContestEntity ToContestEntity(ContestDto contestDto){
        return ContestEntity.create(
                0,
                contestDto.getIdUser(),
                contestDto.getIdExam(),
//                contestDto.getScore(),
                contestDto.getStartTime(),
                contestDto.getSubmitTime(),
                contestDto.getRightAnswer(),
                contestDto.getWrongAnswer(),
                contestDto.getBlankAnswer()
        );
    }

    @Override
    public List<ContestEntity> getAll() {
        return contestRep.findAll();
    }

    @Override
    public ContestEntity searchById(int id) throws ErrorException {
        ContestEntity contest = contestRep.findFirstById(id);
        if(contest == null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        return contest;
    }

    @Override
    public ContestDetailModel searchDetailById(int id) throws ErrorException {
        ContestEntity contest = contestRep.findFirstById(id);
        if(contest == null) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        ExamEntity exam = examRep.findFirstById(contest.getIdExam());
        return new ContestDetailModel(contest, exam);
    }

    @Override
    public List<ContestDetailModel> searchDetailByIdUser(int id) throws ErrorException {
        List<ContestEntity> contestList = contestRep.findByIdUser(id);
        if(contestList.isEmpty()) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        List<ExamEntity> examList = examRep.findAll();
        List<ContestDetailModel> contestDetailList = new ArrayList<>();
        for(ContestEntity contest: contestList){
            for(ExamEntity exam: examList){
                if(contest.getIdExam() == exam.getId()){
                    contestDetailList.add(new ContestDetailModel(contest, exam));
                    break;
                }
            }
        }
        return contestDetailList;
    }

    @Override
    public List<ContestEntity> searchByIdExam(int id) throws ErrorException {
        List<ContestEntity> contestList = contestRep.findByIdExam(id);
        if(contestList.isEmpty()) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        return contestList;
    }

    @Override
    public void create(ContestDto newContest) throws ErrorException{
        if(Valids.isEmpty(newContest.getIdExam())) throw new ErrorException("Id Exam is empty");
        else{
            if(!examRep.existsById(newContest.getIdExam())) throw new ErrorException("Exam is not exist");
        }
        if(Valids.isEmpty(newContest.getIdUser())) throw new ErrorException("Id Exam is empty");
        else{
            if(!userRep.existsById(newContest.getIdExam())) throw new ErrorException("User is not exist");
        }
        contestRep.save(ToContestEntity(newContest));
    }

    @Override
    public void edit(ContestEntity newContest) throws ErrorException {
        if(Valids.isEmpty(newContest.getId())) throw new ErrorException(Constants.SEARCH_DATA_EMPTY);
        ContestEntity preContest = contestRep.findFirstById(newContest.getId());
        if(!Valids.isEmpty(newContest.getIdExam())){
            if(!examRep.existsById(newContest.getIdExam())) throw new ErrorException("Exam not found");
            else preContest.setIdExam(newContest.getIdExam());
        }
        if(!Valids.isEmpty(newContest.getIdUser())){
            if(!userRep.existsById(newContest.getIdUser())) throw new ErrorException("User not found");
            else preContest.setIdUser(newContest.getIdUser());
        }
//        preContest.setScore(newContest.getScore());
        preContest.setStartTime(newContest.getStartTime());
        preContest.setSubmitTime(newContest.getSubmitTime());
        preContest.setRightAnswer(newContest.getRightAnswer());
        preContest.setRightAnswer(newContest.getRightAnswer());
        preContest.setWrongAnswer(newContest.getWrongAnswer());
        preContest.setBlankAnswer(newContest.getBlankAnswer());

        contestRep.save(preContest);
    }

    @Override
    public void delete(int id) throws ErrorException {
        if(!contestRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        contestRep.deleteById(id);
    }

    @Override
    public List<ContestUserModel> findWithUser(){
        List<Object[]> ls = contestUserRep.statisticByUser();
        List<ContestUserModel> rs = new ArrayList<>();
        for(Object[] c:ls){
            rs.add(new ContestUserModel(c));
        }
        return rs;
    }

    @Override
    public List<ContestExamModel> findWithExam(){
        List<Object[]> ls = contestUserRep.statisticByExam();
        List<ContestExamModel> rs = new ArrayList<>();
        for(Object[] c:ls){
            rs.add(new ContestExamModel(c));
        }
        return rs;
    }
}
