package com.sk.manage.service.issue;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.manage.domain.issue.Issue;
import com.sk.manage.domain.issue.IssueRepository;
import com.sk.manage.domain.issue.Tag;
import com.sk.manage.domain.issue.TagRepository;
import com.sk.manage.web.issue.IssueResponseDto;
import com.sk.manage.web.issue.IssueSaveRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
	
	private final IssueRepository issueRepository;
	private final TagRepository tagRepository;
	
	//1.이슈저장
	@Transactional
	public void issueSave(IssueSaveRequestDto requestDto) {
		String tagName = requestDto.getTagName();
		Tag tag = tagRepository.findByName(tagName).orElseGet(()->tagRepository.save(new Tag(tagName)));
		Issue issue = requestDto.toEntity();
		issue.setTag(tag);
		issueRepository.save(issue);
	}
	
	//2.이슈갱신
	public void issueUpdate() {
		
	}
	
	//3.이슈목록
	//TODO : 페이징으로 전환
	public List<IssueResponseDto> issueList() {
		List<Issue> findAllIssue = issueRepository.findAll();
		List<IssueResponseDto> results = findAllIssue.stream()
				.map(issue -> 
				IssueResponseDto.builder().tagName(issue.getTag().getName())
						.content(issue.getContent())
						.title(issue.getTitle())
						.createDate(issue.getCreateDate())
						.build()).collect(Collectors.toList());
		return results;
	}
	
	//4.이슈상세
	public void issueDetail() {
		
	}

}
