package com.iu.winter.bankBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.main.util.FileManager;
import com.iu.main.util.Pager;

//서비스 역할 
@Service //해당 클래스의 객체를 생성하세요 
public class BankBookService {
	@Autowired //이 타입으로 만든 객체를 찾아서 넣어주세요 
	private BankBookDAO bankBookDAO;
	@Autowired
	private FileManager fileManager;
	
	public List<BankBookDTO> getList(Pager pager) throws Exception {
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		//page	startRow	lastRow
//		//1		1			10
//		//2		11			20
//		//3		21			30
//		//...
//		int count = 5; //페이지당 10개
//		int startRow = (page-1)*count+1; //count가 바뀌더라도 계산식 그대로 사용 가능 
//		int lastRow = page*count;
		
		pager.makeRowNum();
		Long total = bankBookDAO.getTotal(pager); 
		pager.makePageNum(total);
		
//		map.put("startRow", startRow);
//		map.put("lastRow", lastRow);
		
		return bankBookDAO.getList(pager);
	}
	
	public BankBookDTO getDetail(BankBookDTO bankBookDTO) throws Exception {
		return bankBookDAO.getDetail(bankBookDTO);
	}
	
	//없는 것은 매개변수로 받아온다
	public int setAdd(BankBookDTO bankBookDTO, MultipartFile[] files, HttpSession session) throws Exception {
		// /resources/upload/bankbook
		String path = "/resources/upload/bankbook/";
		
//		long num = bankBookDAO.getSequence();
//		bankBookDTO.setBookNum(num);
		int result = bankBookDAO.setAdd(bankBookDTO);
		
		
		for(MultipartFile multipartFile: files) { //int i=0; i<files.length; i++
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(path, multipartFile, session);
			
			BankFileDTO bankFileDTO = new BankFileDTO();
			bankFileDTO.setBookNum(bankBookDTO.getBookNum());
			bankFileDTO.setOriginalName(multipartFile.getOriginalFilename());
			bankFileDTO.setFileName(fileName);
			result = bankBookDAO.setFileAdd(bankFileDTO);
		}
	
		
		return result;
	}
	
	public int setDelete(Long num) throws Exception {
		return bankBookDAO.setDelete(num);
	}
	
	public int setUpdate(BankBookDTO bankBookDTO) throws Exception {
		return bankBookDAO.setUpdate(bankBookDTO);
	}
}
