package com.ssafy.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ssafy.mem.model.dto.MemDto;
import com.ssafy.mem.model.service.MemService;
import com.ssafy.mem.model.service.MemServiceImpl;
import com.ssafy.tv.model.dto.TvDto;
import com.ssafy.tv.model.service.TvService;
import com.ssafy.tv.model.service.TvServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member")
public class MemController extends HttpServlet{

private MemService service;
	
	@Override
	public void init() throws ServletException {
		service = MemServiceImpl.getInstance();
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
		if(action.equals("mvlogin")) {
			path = "/login.jsp";
			redirect(request, response, path);
		} else if(action.equals("login")) {
			path = login(request, response);
			forward(request, response, path);
		} else if(action.equals("logout")) {
			path = logout(request, response);
			redirect(request, response, path);
		} else if(action.equals("mvjoin")) {
			path = "/join.jsp";
			redirect(request, response, path);
		} else if(action.equals("join")) {
			path = join(request, response);
			redirect(request, response, path);
		} else if(action.equals("idcheck")) {
			idcheck(request, response);
		} else {
			path = "";
			redirect(request, response, path);
		}
//		forward(request, response, path);
//		redirect(request, response, path);
	}


	private void idcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			boolean flag = service.check(id);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			if(flag) { // id중복
				writer.write("중복된 아이디");
			} else { // 중복 없음
				writer.write("가능힌 아이디");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.sendRedirect(request.getContextPath() + "/join.jsp");
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		Integer age = Integer.valueOf(request.getParameter("age"));
		MemDto dto = new MemDto(id, pw, name, age);
		try {
			service.join(dto);
			return "/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			MemDto dto = service.login(id, pw);
			if(dto != null) { // 로그인 성공
				HttpSession session = request.getSession();
				session.setAttribute("memberDto", dto);
				String remember = request.getParameter("remember");
				if(remember != null) {
					Cookie cookie1 = new Cookie("rememberID", dto.getId());
					cookie1.setPath(request.getContextPath());
					cookie1.setMaxAge(60*60*24*365);
					response.addCookie(cookie1);
					
					Cookie cookie2 = new Cookie("check", "checked");
					cookie2.setPath(request.getContextPath());
					cookie2.setMaxAge(60*60*24*365);
					response.addCookie(cookie2);
				} else {
					Cookie cookie1 = new Cookie("rememberID", dto.getId()); // 아이디 저장
					cookie1.setPath(request.getContextPath());
					cookie1.setMaxAge(0);
					response.addCookie(cookie1);
					
					Cookie cookie2 = new Cookie("check", "checked"); // 아이디 기억
					cookie2.setPath(request.getContextPath());
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
				}
				return "/index.jsp";
			} else {
				return "/login.jsp";
			}
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
