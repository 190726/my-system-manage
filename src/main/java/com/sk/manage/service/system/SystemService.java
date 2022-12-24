package com.sk.manage.service.system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.web.system.SystemRequestDto;
import com.sk.manage.web.system.SystemResponseDto;
import com.sk.manage.domain.system.System;

@Service
public class SystemService {
	
	@Autowired
	SystemRepository systemRepository;
	
	public com.sk.manage.domain.system.System findById(Long id) {
		return systemRepository
				.findById(id)
				.orElseThrow(()->new IllegalStateException("NotFound at systemId: " + id));
	}
	
	public Long save(SystemRequestDto requestDto) {
		return systemRepository.save(requestDto.toEntity()).getSystemId();
	}
	
	public void delete(Long systemId) {
		systemRepository.deleteById(systemId);
	}
	
	public List<SystemResponseDto> findAll(){
		List<System> systems = systemRepository.findAll();
		return systems.stream().map(sys -> mappedDto(sys)).collect(Collectors.toList());
		
	}
	
	private SystemResponseDto mappedDto(System system) {
		return new SystemResponseDto(system.getSystemId(), system.getSystemName(), system.getSystemOpenDate());
	}
	
	public List<System> findByName(String name){
		return systemRepository.findByNameContains(name);
	}
	
	
}