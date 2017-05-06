package com.projeman.activities;

public class Books {
	
	private String bookTitle;
	private String bookDate;
	private String bookImageURL;
	private String bookPublisher;
	private boolean rate;
	private String id;
	
	public static final String URL = "http://androidtuts.gigfa.com/library/androidindex.php";	
		
	public String getBookTitle() {
		return bookTitle;
	}
	
	public String getBookDate() {
		return bookDate;
	}
	
	public String getBookImageURL() {
		return bookImageURL;
	}
	
	public String getBookPublisher() {
		return bookPublisher;
	}
	
	public boolean getRate() {
		return rate;
	}
	
	public String getId() {
		return id;
	}
			
	public void setBookTitle(String BookTitle) {
		this.bookTitle = BookTitle;
	}
	
	public void setBookDate(String BookDate) {
		this.bookDate = BookDate;
	}
	
	public void setBookImageURL(String BookImageURL) {
		this.bookImageURL = BookImageURL;
	}
	
	public void setBookPublisher(String BookPublisher) {
		this.bookPublisher = BookPublisher;
	}
	
	public void setRate(boolean rate) {
		this.rate = rate;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book id : "+ id +",Book Name: " + bookTitle + ", Date: " + bookDate + ", Book author : " + bookPublisher ;
	}
}