package com.incapp.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;

@Repository
public class BookRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String addBook(Book b,MultipartFile image1){
		try {
			String query="insert into book_info values(?,?,?,?,?)";
			jdbcTemplate.update(query,new Object[] {b.getName(),b.getPrice(),b.getAname(),b.getPname(),image1.getInputStream()});
			
			return "success";
		}catch(Exception ex) {
			return "failed";
		}
	}
	
	public List<Book> getAllBook(){
		class DataMapper implements RowMapper{
			public Book mapRow(ResultSet rs,int rowNum)throws SQLException{
				Book b=new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		final String query ="select name,price,aname,pname from book_info";
		List<Book> b=jdbcTemplate.query(query,new DataMapper());
		return b;
	}
	
	public byte[] getPhoto(String name){
		class DataMapper implements RowMapper{
			public byte[] mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getBytes("image1");
			}
		}
		try {
			final String query ="select image1 from book_info where name=?";
			byte[] r=(byte[]) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {name});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
}
