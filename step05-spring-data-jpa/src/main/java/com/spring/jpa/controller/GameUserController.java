package com.spring.jpa.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.entity.GameUser;
import com.spring.jpa.repository.GameUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GameUserController {
	
	private final GameUserRepository gameUserRepository;
	
	@PostMapping(value = "sign-up")
	@ResponseBody
	public GameUser signUp(@RequestBody GameUser gameUser) {
		
		return gameUserRepository.save(gameUser);
	}
	
	@PostMapping(value = "/users/{gid}")
	@ResponseBody
	@Transactional
	public GameUser updateProfile(@PathVariable Long gid,
								@RequestBody GameUser gameUser) {
		
		GameUser foundUser = gameUserRepository.findById(gid)
												.orElseThrow();
		
		foundUser.setGname(gameUser.getGname());
		
		return foundUser;
	}
	
	
}