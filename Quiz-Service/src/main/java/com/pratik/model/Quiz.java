package com.pratik.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Quiz 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ElementCollection
	private List<Integer> questionIds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<Integer> questions) {
		this.questionIds = questions;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + "]";
	}
	
}
