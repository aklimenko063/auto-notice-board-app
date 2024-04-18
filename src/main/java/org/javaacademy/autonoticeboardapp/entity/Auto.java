package org.javaacademy.autonoticeboardapp.entity;

import lombok.Data;
import lombok.NonNull;
import java.math.BigDecimal;

@Data
public class Auto {
	@NonNull
	private String brandName;
	@NonNull
	private String color;
	@NonNull
	private String model;
	@NonNull
	private BigDecimal price;
}
