package com.pratik.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pratik.dao.QuestionDao;
import com.pratik.model.Question;
import com.pratik.model.QuestionWrapper;
import com.pratik.model.Response;

@Service
public class QuestionService 
{
	@Autowired
	QuestionDao questionDao;
	
	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}
	
	public void addQuestion(Question question) {
		questionDao.save(question);
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, int numQ) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) 
	{
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer id : questionIds)
		{
			questions.add(questionDao.findById(id).get());
		}
		for(Question question : questions)
		{
			QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption2(), 
															question.getOption1(), question.getOption3(), question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) 
	{
		int right = 0;
		for(Response r : responses)
		{
			Question question = questionDao.findById(r.getId()).get();
;			if(r.getResponse().equals(question.getRightAnswer()))
				right++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
}
