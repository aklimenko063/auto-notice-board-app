package org.javaacademy.autonoticeboardapp.entity;

import lombok.Data;
import lombok.NonNull;
import java.time.LocalDate;

@Data
public class Notice {
	@NonNull
	private String uniqueID;
	@NonNull
	private LocalDate postingDate;
	@NonNull
	private Auto auto;
}
