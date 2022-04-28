package br.com.app.store.Store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.app.store.Store.dto.UserDTO;
import br.com.app.store.Store.model.User;
import br.com.app.store.Store.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/users/entrar")
	public ModelAndView entrar(UserDTO userDTO) {
		return new ModelAndView("users/entrar");
	}

	@PostMapping("/users/entrar")
	public ModelAndView entrar(@Valid UserDTO userDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("users/entrar");
		}
		
		List<User> users = userRepository.findUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
		System.out.println("user" + users.toString());

		if (users.isEmpty()) {
			return new ModelAndView("users/entrar");
		}
		
		return new ModelAndView("redirect:/home?username="+ users.get(0).getUsername());
	}

	@GetMapping("/users/criar")
	public ModelAndView criar(UserDTO userDTO) {
		return new ModelAndView("users/criar");
	}

	@PostMapping("/users/criar")
	public ModelAndView criar(@Valid UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("users/criar");
		}
		
		List<User> users = userRepository.findUserByUsername(userDTO.getUsername());
		System.out.println("users" + users.toString());

		if (!users.isEmpty()) {
			return new ModelAndView("users/criar");
		}

		User user = modelMapper.map(userDTO, User.class);
		userRepository.save(user);
		return new ModelAndView("redirect:/users/entrar");
	}

}
