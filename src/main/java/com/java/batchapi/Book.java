package com.java.batchapi;

public class Book {

	  private String name;
	    private String author;

	    // Constructors, getters, and setters

	    @Override
	    public String toString() {
	        return "Book{" +
	                "name='" + name + '\'' +
	                ", author='" + author + '\'' +
	                '}';
	    }

	    public void setName(String name) {
	    	this.name = name;

	    }

	    public String getName() {
	    	return name;
	    }

	    public void setAuthor(String author) {

	    	this.author = author;
	    }

	    public String getAuthor() {
	    	return author;
	    }
}
