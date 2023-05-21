package com.questionpro.assessment.VO;

import java.util.ArrayList;
import java.util.List;

public class Item {

	String by;
	
	String descendants;
	
	int id;
	
	List<Integer> kids;
	
	long score;
	
	long time;
	
	String title;
	
	String type;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	String url;
	
	String text;

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getDescendants() {
		return descendants;
	}

	public void setDescendants(String descendants) {
		this.descendants = descendants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getKids() {
		if (kids == null) {
			kids = new ArrayList<>();
		}
		return kids;
	}

	public void setKids(List<Integer> kids) {
		this.kids = kids;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
