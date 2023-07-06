package com.java.batchapi;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class BookFieldSetMapper implements FieldSetMapper {

	   @Override
	    public Book mapFieldSet(FieldSet fieldSet) {
	        Book Book = new Book();
	        Book.setName(fieldSet.readString("name"));
	        Book.setAuthor(fieldSet.readString("author"));
	        return Book;
	    }
}
