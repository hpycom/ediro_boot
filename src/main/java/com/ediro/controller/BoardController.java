package com.ediro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ediro.domain.board.BasicBoard;
import com.ediro.persistence.BasicBoardRepository;
import com.ediro.vo.PageMaker;
import com.ediro.vo.PageVO;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/boards/")
public class BoardController {

	@Autowired
	private BasicBoardRepository bbRepo;
	
	@GetMapping("/basiclist")
	public void BasicList(PageVO vo,Model model) {
		Pageable page = vo.makePageable(0, "bno");
		log.info(""+page);
		
		Page<BasicBoard> result = bbRepo.findAll(bbRepo.makePredicate(null, null),page);
		
		model.addAttribute("result",new PageMaker<BasicBoard>(result));
		
	}
	
}
