package com.example.BookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/bookmanagement";
	private String jdbcUsername = "root";
	private String jdbcPassword = "chutamdinh0365";
	
	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String SELECT_BOOK_BY_ID = "select * from book where BookCode=?";
	private static final String INSERT_BOOKS_SQL = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BOOKS_SQL = "UPDATE book SET Name=?,Author=?,Title=?,ReleaseDate=?,NumberOfPages=?,Type=?,Painted=?,Img=? where BookCode=?";
	private static final String DELETE_BOOKS_SQL = "DELETE from book where BookCode=?";
	
	public BookDAO() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Book> selectAllBooks(){
		List<Book> books = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);) {
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int BookCode = Integer.valueOf(rs.getInt("BookCode"));
				String Name = rs.getString("Name");
				String Author = rs.getString("Author");
				String Title = rs.getString("Title");
				String ReleaseDate = rs.getString("ReleaseDate");
				int NumberOfPages = Integer.valueOf(rs.getString("NumberOfPages"));
				String Type = rs.getString("Type");
				String Decribe= rs.getString("Painted");
				String Img = rs.getString("Img");
				books.add(new Book(BookCode,Name,Author,Title,ReleaseDate,NumberOfPages,Type,Decribe,Img));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public Book selectBook(int BookCode) {
		Book book = new Book();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);){
			preparedStatement.setInt(1, BookCode);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				book.setBookCode(rs.getInt("BookCode"));
				book.setName(rs.getString("Name"));
				book.setAuthor(rs.getString("Author"));
				book.setTitle(rs.getString("Title"));
				book.setReleaseDate(rs.getString("ReleaseDate"));
				book.setNumberOfPages(rs.getInt("NumberOfPages"));
				book.setType(rs.getString("Type"));
				book.setDecribe(rs.getString("Painted"));
				book.setImg(rs.getString("Img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public void insertBook(Book book) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_BOOKS_SQL);) {
			ps.setInt(1,Integer.valueOf(book.getBookCode()));
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			ps.setString(4,book.getTitle());
			ps.setString(5,book.getReleaseDate());
			ps.setInt(6, Integer.valueOf(book.getNumberOfPages()));
			ps.setString(7,book.getType());
			ps.setString(8,book.getDecribe());
			ps.setString(9,book.getImg());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(Book book) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_SQL);) {
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3,book.getTitle());
			ps.setString(4,book.getReleaseDate());
			ps.setInt(5, Integer.valueOf(book.getNumberOfPages()));
			ps.setString(6,book.getType());
			ps.setString(7,book.getDecribe());	
			ps.setString(8,book.getImg());
			ps.setInt(9,book.getBookCode());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteBook(String BookCode) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_BOOKS_SQL);) {
			ps.setInt(1, Integer.valueOf(BookCode));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

