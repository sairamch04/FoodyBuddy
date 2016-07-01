package com.foodybuddy.utils;

public enum OrderStatusEnum {

	INTIATED(0), CANCELLED(1), READY(2), COMPLETED(3);

	private int value;

	private OrderStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}