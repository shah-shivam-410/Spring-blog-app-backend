package com.example.demo;

import java.time.LocalDateTime;

public class TempTest {

	public static void main(String[] args) {
		String date = LocalDateTime.now().toString();
//				.replaceAll("[\\s:.]", "-");
		System.out.println(date);
	}
	
}
