package kr.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
