package org.javaacademy.autonoticeboardapp.dto;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class AutoFilterDto {
	String brandName;
	String color;
	String model;
	BigDecimal price;
}
