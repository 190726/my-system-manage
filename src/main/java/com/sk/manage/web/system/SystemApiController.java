package com.sk.manage.web.system;

import java.util.List;

import com.sk.manage.domain.system.System;
import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.SystemUser;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import com.sk.manage.service.system.SystemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SystemApiController {

	private final SystemService systemService;
	
	@GetMapping("/systems")
	public List<SystemResponseDto> findAll(){
		return systemService.findAll();
	}
	
	@PostMapping("/system/save")
	public Long save(@RequestBody SystemRequestDto requestDto) {
		log.debug("saving system data is {}", requestDto);
		return systemService.save(requestDto);
	}
	
	@PostMapping("/system/user/add")
	public Long addSystemUser(@RequestBody SystemUserDto systemUserDto) {
		systemService.enrolledSystemUser(systemUserDto.getSystemId(), systemUserDto.getSno());
		return 1L;
	}
	
	@PostMapping("/system/user/delete")
	public Long deleteSystemUser(@RequestBody SystemUserDto systemUserDto) {
		log.info("delete systemUserId is {}", systemUserDto.getSystemUserId());
		systemService.deleteSystemUser(systemUserDto.getSystemUserId());
		return 1L;
	}
	
	@PostMapping("/system/detail/save")
	public Long systemDetailSave(@RequestBody SystemDetailDto systemDetailDto) {
		log.info("System Detail Save Date is {}", systemDetailDto);
		systemService.detailSave(systemDetailDto);
		return 1L;
	}
	
}