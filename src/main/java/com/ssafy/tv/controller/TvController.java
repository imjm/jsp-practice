package com.ssafy.tv.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.tv.model.dto.TvDto;
import com.ssafy.tv.model.service.TvService;
import com.ssafy.tv.model.service.TvServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tv")
public class TvController extends HttpServlet {
	
	private TvService service;
	
	@Override
	public void init() throws ServletException {
		service = TvServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";
		if(action.equals("list")) {
			path = list(request, response);
			forward(request, response, path);
		} else if(action.equals("mvregist")) {
			path = "/tv/regist.jsp";
			redirect(request, response, path);
		} else if(action.equals("regist")) {
			path = regist(request, response);
			redirect(request, response, path);
		} else if(action.equals("detail")) {
			path = detail(request, response);
			forward(request, response, path);
		} else if(action.equals("mvupdate")) {
			path = mvupdate(request, response);
			forward(request, response, path);
		} else if(action.equals("update")) {
			path = update(request, response);
			forward(request, response, path);
		} else if(action.equals("delete")) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			path = "";
			redirect(request, response, path);
		}
//		forward(request, response, path);
//		redirect(request, response, path);
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		Integer pn = Integer.valueOf(request.getParameter("pn"));
		try {
			service.delete(pn);
			return "/tv?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		Integer pn = Integer.valueOf(request.getParameter("pn"));
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		Integer price = Integer.valueOf(request.getParameter("price"));
		TvDto dto = new TvDto(pn, name, brand, price);
		try {
			service.update(dto);
			return "/tv?action=detail&pn=" + pn;
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String mvupdate(HttpServletRequest request, HttpServletResponse response) {
		Integer pn = Integer.valueOf(request.getParameter("pn"));
		try {
			TvDto dto = service.detail(pn);
			request.setAttribute("dto", dto);
			return "/tv/update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		Integer pn = Integer.valueOf(request.getParameter("pn"));
		try {
			TvDto dto = service.detail(pn);
			request.setAttribute("dto", dto);
			return "tv/detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String regist(HttpServletRequest request, HttpServletResponse response) {
		Integer pn = Integer.valueOf(request.getParameter("pn"));
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		Integer price = Integer.valueOf(request.getParameter("price"));
		TvDto dto = new TvDto(pn, name, brand, price);
		try {
			service.regist(dto);
			return "/tv?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			 List<TvDto> list = service.list();
			 request.setAttribute("list", list);
			 return "/tv/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
		request.getContextPath();
		response.sendRedirect(request.getContextPath() + path);
		
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
