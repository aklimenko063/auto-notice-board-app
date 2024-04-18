package org.javaacademy.autonoticeboardapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.autonoticeboardapp.dto.NoticeDto;
import org.javaacademy.autonoticeboardapp.entity.Auto;
import org.javaacademy.autonoticeboardapp.entity.AutoFilter;
import org.javaacademy.autonoticeboardapp.entity.Notice;
import org.javaacademy.autonoticeboardapp.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeBoardService {
	private final NoticeBoardRepository noticeBoardRepository;

	public int createNotice(NoticeDto noticeDto) {
		int status = checkDto(noticeDto);
		if (status == 400) {
			log.warn("Ошибка в передаваемых параметрах!");
			return status;
		}
		Auto auto = new Auto(noticeDto.getBrandName(),
				noticeDto.getColor(),
				noticeDto.getModel(),
				noticeDto.getPrice());
		Notice notice = new Notice(UUID.randomUUID().toString(),
				noticeDto.getPostingDate(),
				auto);
		noticeBoardRepository.createNotice(notice);
		log.info("Добавлено объявление: {}", notice);
		return 201;
	}

	private int checkDto(NoticeDto noticeDto) {
		if (noticeDto.getPostingDate() == null) {
			return 400;
		} else if (noticeDto.getBrandName() == null) {
			return 400;
		} else if (noticeDto.getModel() == null) {
			return 400;
		} else if (noticeDto.getColor() == null) {
			return 400;
		} else if (noticeDto.getPrice() == null) {
			return 400;
		} else if (noticeDto.getPrice().compareTo(BigDecimal.ZERO) == -1) {
			return 400;
		}
		return 200;
	}

	public Set<Notice> getAllNotice() {
		return noticeBoardRepository.getAllNotice();
	}

	public Set<Notice> getNoticeByDate(LocalDate date) {
		return noticeBoardRepository.getNoticeByDate(date);
	}

	public Notice getNoticeByUUID(String uuid) {
		return noticeBoardRepository.getNoticeByUUID(uuid);
	}

	public void deleteNoticeByUUID(String uuid) {
		noticeBoardRepository.deleteNoticeByUUID(uuid);
	}

	public Set<Notice> getNoticeByFilter(AutoFilter autoFilter) {
		return noticeBoardRepository.getNoticeByFilter(autoFilter);
	}
}
