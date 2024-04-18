package org.javaacademy.autonoticeboardapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaacademy.autonoticeboardapp.entity.AutoFilter;
import org.javaacademy.autonoticeboardapp.entity.Notice;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class NoticeBoardRepository {
	private final Set<Notice> noticeRepo = new HashSet<>();

	public void createNotice(Notice notice) {
		noticeRepo.add(notice);
	}

	public Set<Notice> getAllNotice() {
		return noticeRepo;
	}

	public Set<Notice> getNoticeByDate(LocalDate date) {
		return noticeRepo.stream()
				.filter(e -> e.getPostingDate().equals(date))
				.collect(Collectors.toSet());
	}

	public Notice getNoticeByUUID(String uuid) {
		return findNoticeByUUID(uuid);
	}

	public void deleteNoticeByUUID(String uuid) {
		Notice notice = findNoticeByUUID(uuid);
		if (notice != null) {
			noticeRepo.remove(notice);
		}
	}

	public Set<Notice> getNoticeByFilter(AutoFilter autoFilter) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> mapFilter = objectMapper
				.convertValue(autoFilter, new TypeReference<Map<String, Object>>() {});
		Set<Notice> filteredAutos = new HashSet<>(noticeRepo);
		for (Map.Entry<String, Object> filter : mapFilter.entrySet()) {
			filteredAutos = findByMapFilter(filter.getKey(), filter.getValue(), filteredAutos);
		}
		return filteredAutos;
	}

	private Notice findNoticeByUUID(String uuid) {
		return noticeRepo.stream()
				.filter(e -> e.getUniqueID().equals(uuid))
				.findFirst().orElseGet(() -> null);
	}

	private Set<Notice> findByMapFilter(String key, Object value, Set<Notice> filteredAutos) {
		if (key.equals("brandName") && value != null) {
			return filteredAutos.stream()
					.filter(e -> e.getAuto().getBrandName().equals(value))
					.collect(Collectors.toSet());
		} else if (key.equals("color") && value != null) {
			return filteredAutos.stream()
					.filter(e -> e.getAuto().getColor().equals(value))
					.collect(Collectors.toSet());
		} else if (key.equals("model") && value != null) {
			return filteredAutos.stream()
					.filter(e -> e.getAuto().getModel().equals(value))
					.collect(Collectors.toSet());
		} else if (key.equals("price") && value != null) {
			return filteredAutos.stream()
					.filter(e -> e.getAuto().getPrice().equals(value))
					.collect(Collectors.toSet());
		}
		return filteredAutos;
	}
}
