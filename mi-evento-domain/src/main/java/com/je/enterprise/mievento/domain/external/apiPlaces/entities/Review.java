package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Review {

	String text;
	@JsonProperty("author_name")
	String author;
	String time;
	List<Calification> aspects;
	
	public Review() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Calification> getAspects() {
		return aspects;
	}

	public void setAspects(List<Calification> aspects) {
		this.aspects = aspects;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
}
