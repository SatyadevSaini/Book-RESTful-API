package com.incapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;
import com.incapp.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping(value = "/books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping(value = "/book")
	public ResponseEntity<String> addBook(@RequestPart("book") Book b,@RequestPart("image1") MultipartFile image1) {
		String r=bookService.setBook(b,image1);
		if( r.equalsIgnoreCase("success") )
			return new ResponseEntity<String>(r, HttpStatus.OK);
		else
			return new ResponseEntity<String>(r, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value = "/getPhoto/{name}")
	public byte[] getPhoto(@PathVariable String name){
		byte[] b=bookService.getPhoto(name);
		if(b!=null) {
			return b;
		}else {
			return null;
		}
	}
}
