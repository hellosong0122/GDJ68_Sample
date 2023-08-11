package com.iu.winter.bankBook;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.main.util.Pager;

//컨트롤러 역할
@Controller
@RequestMapping("/bankbook") // /밑에 bankbook으로 시작되는 모든 얘들은 이곳으로 
public class BankBookController {
	@Autowired
	private BankBookService bankBookService;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String getList(Pager pager, Model model) throws Exception { //파라미터 이름과 동일한 
		List<BankBookDTO> ar = bankBookService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		return "bankbook/list";
	}
	
	@RequestMapping(value="detail") //method 생략하면 GET
	public ModelAndView getDetail(BankBookDTO bankBookDTO, ModelAndView mv) throws Exception {
		bankBookDTO = bankBookService.getDetail(bankBookDTO);
		
		System.out.println(bankBookDTO.getBookName());
		mv.addObject("dto", bankBookDTO);
		mv.setViewName("bankbook/detail");
		
		return mv;
	}
	
	//폼으로 이동
	@RequestMapping(value="add", method=RequestMethod.GET)
	public void setAdd() throws Exception {
		//생략하면 자동으로 이동됨
	}
	
	//DB insert
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String setAdd(BankBookDTO bankBookDTO, MultipartFile[] photos, HttpSession session) throws Exception {
		int result = bankBookService.setAdd(bankBookDTO, photos, session);
		return "redirect:./list";
	}
	
	//DB delete 
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String setDelete(Long bookNum) throws Exception {
		int result = bankBookService.setDelete(bookNum);
		return "redirect:./list";
	}
	
	//수정 폼으로 이동
	@RequestMapping(value="update", method = RequestMethod.GET)
	public void setUpdate(BankBookDTO bankBookDTO, Model model) throws Exception {
		bankBookDTO = bankBookService.getDetail(bankBookDTO);
		model.addAttribute("dto", bankBookDTO);
	}
	
	//DB update
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String setUpdate(BankBookDTO bankBookDTO) throws Exception {
		int result = bankBookService.setUpdate(bankBookDTO);
		//return "redirect:./list";
		return "redirect:./detail?bookNum="+bankBookDTO.getBookNum();
	}
}
