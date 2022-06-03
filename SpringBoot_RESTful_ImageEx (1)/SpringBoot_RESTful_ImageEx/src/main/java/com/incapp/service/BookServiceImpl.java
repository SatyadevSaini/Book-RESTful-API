package com.incapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;
import com.incapp.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepo;
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepo.getAllBook();
	}

	@Override
	public String setBook(Book book,MultipartFile image1) {
		return bookRepo.addBook(book,image1);
	}

	@Override
	public byte[] getPhoto(String name) {
		return bookRepo.getPhoto(name);
	}
	
	
}
