package com.iu.winter.bankBook;

import java.util.List;

public class BankBookDTO {
	private Long bookNum;
	private String bookName;
	private String bookContents;
	private Double bookRate;
	private Integer bookSale;
	
	// 1:N 관계
	private List<BankFileDTO> fileDTOs;
	
	
	public List<BankFileDTO> getFileDTOs() {
		return fileDTOs;
	}
	public void setFileDTOs(List<BankFileDTO> fileDTOs) {
		this.fileDTOs = fileDTOs;
	}
	
	public Long getBookNum() {
		return bookNum;
	}
	public void setBookNum(Long bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookContents() {
		return bookContents;
	}
	public void setBookContents(String bookContents) {
		this.bookContents = bookContents;
	}
	public Double getBookRate() {
		return bookRate;
	}
	public void setBookRate(Double bookRate) {
		this.bookRate = bookRate;
	}
	public Integer getBookSale() {
		return bookSale;
	}
	public void setBookSale(Integer bookSale) {
		this.bookSale = bookSale;
	}
	
}
