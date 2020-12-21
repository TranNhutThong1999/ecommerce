package com.tmdt.util;

import org.springframework.stereotype.Component;

@Component
public class ConvertMoneyToXu {
	public int handle(int money) {
		switch (money) {
		case 10:
			return 10;
		case 20:
			return 25;
		case 50:
			return 60;
		case 200:
			return 225;
		default:
			return 0;
		}
	}
}
