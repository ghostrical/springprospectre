package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;

	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
//		
//		Board vo = new Board();
//		vo.setIdx(1);
//		vo.setTitle("게시판");
//		vo.setContent("게내");
//		vo.setWriter("박");
//		vo.setIndate("22-2");
//		vo.setCount(0);
//		
//		List<Board> list = new ArrayList<Board>();
//		list.add(vo);
//		
//		model.addAttribute("list",list);
//		
//		return "boardList";
		
		List<Board> list = mapper.getLists();
		model.addAttribute("list",list);
//		
		return "boardList";
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		
		mapper.boardInsert(vo);
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		
		Board vo = mapper.boardContent(idx);
		
		mapper.boardCount(idx);
		
		model.addAttribute("vo",vo);
		
		
		return "boardContent";
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		
		mapper.boardDelete(idx);
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		
		Board vo = mapper.boardContent(idx);
		
		model.addAttribute("vo",vo);
		
		return "boardUpdate";
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
}
