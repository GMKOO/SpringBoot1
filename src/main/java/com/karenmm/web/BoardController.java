package com.karenmm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board")
	public String board(Model model) {

		List<BoardDTO> list = boardService.boardList();

		model.addAttribute("list", list);
		// BoardDTO dto = new BoardDTO();
		// System.out.println(list);

		return "board";
	}

	@ResponseBody
	@PostMapping("/detail")
	public String detail(@RequestParam("bno") int bno) {
		// System.out.println(bno);
		BoardDTO dto = boardService.datail(bno);
		JSONObject json = new JSONObject();
		JSONObject e = new JSONObject();
		// e.put("content", dto.getBcontent());
		// e.put("uuid", dto.getBcontent());
		json.put("content", dto.getBcontent());
		json.put("uuid", dto.getUuid());
		json.put("ip", dto.getBip());
		System.out.println("제이슨출력문 detail" + json.toString());
		return json.toString();

		// return "{content : \"본문내용입니다\"}";
	}

	@GetMapping("/write")
	public String write() {

		return "write";
	}

	@PostMapping("/write")
	public String write(HttpServletRequest request, HttpSession session) {
		// 로그인 한사용자만 들어올 수 있습니다
		// System.out.println(request.getParameter("title"));
		// System.out.println(request.getParameter("content"));
		// System.out.println();
		// System.out.println(request.getParameter("content"));

		BoardDTO dto = new BoardDTO();
		dto.setBtitle(request.getParameter("title"));
		dto.setBcontent(request.getParameter("content"));
		// dto.setM_id((String) session.getAttribute("mid")); // 임시로 members에 있는 id를
		// 넣엉주세요.
		dto.setM_id(String.valueOf(session.getAttribute("mid"))); // 임시로 members에 있는 id를 넣엉주세요.

		int result = boardService.write(dto);

		return "redirect:/board";

	}

	@PostMapping("/delete")
	public String delete(BoardDTO dto) {

		System.out.println(dto.getBno());
		System.out.println(dto.getUuid());

		return "redirect:/board";
	}

}
