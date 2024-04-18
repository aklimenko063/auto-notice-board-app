package org.javaacademy.autonoticeboardapp.entity;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class AutoFilter {
	String brandName;
	String color;
	String model;
	BigDecimal price;
}
