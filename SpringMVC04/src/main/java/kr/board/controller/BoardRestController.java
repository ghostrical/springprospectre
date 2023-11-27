package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@RequestMapping("/board")
@RestController
public class BoardRestController {
	
	@Autowired
	BoardMapper boardMapper;
	
	
	@GetMapping("/all")
	public List<Board> boardList(){
		
		List<Board> list = boardMapper.getLists();
		return list;
	}
	
	@PostMapping("/new")
	public  void boardInsert(Board vo) {
		boardMapper.boardInsert(vo);
		
		
	}
	
	@DeleteMapping("/{idx}")
	public  void boardDelete(@PathVariable("idx") int idx) {
		boardMapper.boardDelete(idx);
	}
	
	@PutMapping("/update")
	public  void boardUpdate(@RequestBody Board vo) {
		boardMapper.boardUpdate(vo);
	}
	
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		Board vo = boardMapper.boardContent(idx);
		return vo;
	}
	
	@PutMapping("/count/{idx}")
	public  Board boardCount(@PathVariable("idx") int idx) {
		boardMapper.boardCount(idx);
		Board vo = boardMapper.boardContent(idx);
		return vo;
	}

}
