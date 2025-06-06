package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.service.QuestionService;
import br.com.cesarschool.presentation.dto.question.QuestionChatRequestDTO;
import br.com.cesarschool.presentation.dto.question.QuestionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/to-monitor")
    public ResponseEntity<String> makeQuestionToMonitor(@RequestBody QuestionRequestDTO request) {
        questionService.makeQuestionToMonitor(
                request.getStudentId(),
                request.getQuestion(),
                request.getDisciplineId(),
                request.getMonitorId()
        );
        return ResponseEntity.ok("Question to monitor created successfully.");
    }

    @PostMapping("/chat/answer")
    public ResponseEntity<String> answerQuestionInChat(@RequestBody QuestionChatRequestDTO request) {

        questionService.sendAnswerToQuestion(
                request.getQuestionId(),
                request.getUserId(),
                request.getAnswer()
        );

        return ResponseEntity.ok("Question to monitor created successfully.");
    }
}
