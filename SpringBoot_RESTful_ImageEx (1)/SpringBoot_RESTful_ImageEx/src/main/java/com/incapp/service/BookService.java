package com.incapp.service;

import java.util.List;

import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;

public interface BookService {
	List<Book> getAllBooks() ;
	String setBook(Book book,MultipartFile image1);
	byte[] getPhoto(String name);
}
