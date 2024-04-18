package org.javaacademy.autonoticeboardapp.dto;

import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class NoticeDto {
	LocalDate postingDate;
	BigDecimal price;
	String brandName;
	String color;
	String model;
}
