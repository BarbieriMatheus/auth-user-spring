package br.com.app.store.Store.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/home")
	public ModelAndView index(HttpServletRequest request, @RequestParam String username) {
		request.setAttribute("username", username);
		return new ModelAndView("home");
	}
}
