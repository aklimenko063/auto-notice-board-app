package org.javaacademy.autonoticeboardapp.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.autonoticeboardapp.dto.NoticeDto;
import org.javaacademy.autonoticeboardapp.entity.AutoFilter;
import org.javaacademy.autonoticeboardapp.entity.Notice;
import org.javaacademy.autonoticeboardapp.service.NoticeBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;

	@PostMapping("/create-notice")
	public ResponseEntity createNotice(@RequestBody NoticeDto noticeDto) {
		int status = noticeBoardService.createNotice(noticeDto);
		return ResponseEntity.status(status).build();
	}

	@GetMapping
	public Set<Notice> getAllNotice() {
		return noticeBoardService.getAllNotice();
	}

	@GetMapping("/date={date}")
	public Set<Notice> getNoticeByDate(@PathVariable LocalDate date) {
		return noticeBoardService.getNoticeByDate(date);
	}

	@GetMapping("/id={uuid}")
	public Notice getNoticeByUUID(@PathVariable String uuid) {
		return noticeBoardService.getNoticeByUUID(uuid);
	}

	@GetMapping("/filter")
	public Set<Notice> getByFilter(@RequestParam(required = false) String brandName,
	                               @RequestParam(required = false) String color,
	                               @RequestParam(required = false) String model,
	                               @RequestParam(required = false) BigDecimal price) {
		AutoFilter autoFilter = new AutoFilter(brandName, color, model, price);
		return noticeBoardService.getNoticeByFilter(autoFilter);
	}

	@DeleteMapping("/delete={uuid}")
	public void deleteNoticeByUUID(@PathVariable String uuid) {
		noticeBoardService.deleteNoticeByUUID(uuid);
	}

}
