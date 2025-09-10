package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;

@Controller
public class AddrBookController {
	
	@Autowired
	AddrBookDAO dao;
	
	@RequestMapping("/insert.do")
	public String insert(AddrBookVO vo) throws Exception {
		System.out.print(vo);
		dao.insertDB(vo);
		return "redirect:addrbook_list.do";
	}
	
	@RequestMapping("/addrbook_form.do")
	public String form() {
		return "addrbook_form";
	}

	@RequestMapping("addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	
	@RequestMapping("edit.do")
	public String getDB(@RequestParam("abId") int abId, HttpServletRequest req) throws Exception {
		AddrBookVO vo = dao.getDB(abId);
		req.setAttribute("ab", vo);
		return "addrbook_edit_form";
	}
	
	@RequestMapping("update.do")
	public String updateDB(AddrBookVO vo) throws Exception {
		System.out.print("vo = "+ vo);
		dao.updateDB(vo);
		return "redirect:addrbook_list.do";
	}
	
//	@RequestMapping("delete.do")
//	public String deleteDB(AddrBookVO vo) throws Exception {
//		System.out.print(vo);
//		dao.insertDB(vo);
//		return "redirect:addrbook_list.do";
//	}
	
	
}
