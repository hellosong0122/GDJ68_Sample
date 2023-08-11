package com.iu.main.winter.notice;


import java.util.List;

import com.iu.main.board.BoardDTO;



public class NoticeDTO extends BoardDTO  {

	
	private List<NoticeFileDTO> dtos;

	public List<NoticeFileDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<NoticeFileDTO> dtos) {
		this.dtos = dtos;
	}

}
