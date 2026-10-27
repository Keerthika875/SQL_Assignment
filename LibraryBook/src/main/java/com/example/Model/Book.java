package com.example.Model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private Long bookId;
	
	@NotBlank(message="Book name is required")
	private String bookName;
	
	@NotBlank(message="Author name is required")
	private String author;
	
	@Positive(message="price must be positive")
	private BigDecimal price;
	
	private boolean available;

}
