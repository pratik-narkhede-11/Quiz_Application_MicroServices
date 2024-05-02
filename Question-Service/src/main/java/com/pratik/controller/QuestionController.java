package com.pratik.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pratik.service.QuestionService;
import com.pratik.model.Question;
import com.pratik.model.QuestionWrapper;
import com.pratik.model.Response;

@RestController
@RequestMapping("question")
public class QuestionController 
{
	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment en;
	
	@GetMapping("allQuestions")
	public List<Question> getAllQuestion() 
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public String addQuestion(@RequestBody Question question)
	{
		questionService.addQuestion(question);
		return "Added the Question";
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ)
	{
		return questionService.getQuestionForQuiz(category, numQ);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds)
	{
		System.out.println(en.getProperty("local.server.port"));           //to check load balancing
		return questionService.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getScore") 
	public  ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return questionService.getScore(responses);
	}
}











