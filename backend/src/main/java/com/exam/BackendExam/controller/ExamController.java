package com.exam.BackendExam.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exam.BackendExam.entities.ContestEntity;
import com.exam.BackendExam.entities.ExamEntity;
import com.exam.BackendExam.entities.QuestionEntity;
import com.exam.BackendExam.entities.UserAnswerEntity;
import com.exam.BackendExam.repository.*;

@Controller
public class ExamController {
	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
	private ExamRepository er;
	@Autowired
	private UserAnswerRepository ur;
	@Autowired
	private ContestRepository contestRepository;
	
	public LocalDateTime start;
	
    @GetMapping("/exam_detail/{examId}")
    public String showExamDetail(@PathVariable("examId") int examId, Model model) {
    	start=LocalDateTime.now();
        List<QuestionEntity> questions = questionRepository.findByIdExam(examId);
        Optional<ExamEntity> e=er.findById(examId);
        ExamEntity exam=e.get();
        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);
        return "exam_detail";
    }
    
    @PostMapping("/submitExam")
    public String submitExam(@RequestParam Map<String, String> userAnswers, Model model) {
        int correct = 0, wrong = 0;
        int examid = 0;
        List<Integer> ans=new ArrayList<>();
        ans.add(-1);
        for (String questionId : userAnswers.keySet()) {
            String idString = questionId.replace("question_", "");
            int id = Integer.parseInt(idString);
            int selectedOptionIndex = Integer.parseInt(userAnswers.get(questionId));
            ans.add(selectedOptionIndex);
            Optional<QuestionEntity> qe = questionRepository.findById(id);
            if (qe.isPresent()) {
                QuestionEntity q = qe.get();
                examid=q.getIdExam();
                if (q.getRightAnswer() == selectedOptionIndex) {
                    correct++;
                } else {
                    wrong++;
                }
                ContestEntity contest = new ContestEntity();

             contest.setIdExam(examid); 
             contest.setStartTime(start);

             contest.setSubmitTime(LocalDateTime.now()); 
             contest.setRightAnswer(correct);
             contest.setWrongAnswer(wrong);
             contest.setBlankAnswer(userAnswers.size() - correct - wrong);
                
                UserAnswerEntity e= new UserAnswerEntity();
                
                //ur.save();
            } else {
            }
        }
        
        List<QuestionEntity> questions = questionRepository.findByIdExam(examid);
        
        model.addAttribute("userAnswers", ans);
        model.addAttribute("questions", questions);
        model.addAttribute("correctAnswers", correct);
        model.addAttribute("wrongAnswers", wrong);
        return "result";
    }

}