package com.example.model;

public class NewsModel {
	public int ID;
	public String title;
	public String image;
	public String content;
	public String author;
	
	public NewsModel() {
	}

	public NewsModel(int iD, String title, String image, String content, String author) {
		ID = iD;
		this.title = title;
		this.image = image;
		this.content = content;
		this.author = author;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
