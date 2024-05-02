package com.pratik.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.pratik.model.QuestionWrapper;
import com.pratik.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface 
{
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore") 
	public  ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
