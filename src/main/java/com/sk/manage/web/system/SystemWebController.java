package com.sk.manage.web.system;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.manage.service.system.SystemService;
import com.sk.manage.service.user.UserService;
import com.sk.manage.web.user.UserResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/web/system")
public class SystemWebController {
	
	private final SystemService systemService;
	private final UserService userService;
	
	@GetMapping("/save")
	public String saveForm() {
		return "system/save";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<SystemResponseDto> systems = systemService.findAll();
		model.addAttribute("systems", systems);
		return "system/list";
	}

	@GetMapping("/new-user/{sysId}")
	public String newUser(@PathVariable Long sysId, Model model){
		log.info("new system user, system id is {}", sysId);
		model.addAttribute("system", systemService.findById(sysId));
		model.addAttribute("users", userService.allUsers());
		return "system/new-user";
	}
	
	@GetMapping("/detail/{sysId}")
	public String detail(@PathVariable Long sysId, Model model) {
		log.info("system detail, system id is {}", sysId);
		model.addAttribute("detailDto", systemService.systemDetailDto(sysId));
		return "system/detail";
	}
}