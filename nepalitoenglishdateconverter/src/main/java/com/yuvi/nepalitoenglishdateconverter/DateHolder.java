package com.yuvi.nepalitoenglishdateconverter;

public class DateHolder {
	public int year;
	public int month;
	public int dayOfMonth;
	public int dayOfWeek;

	public DateHolder(int year, int month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
	}
	
	public DateHolder(int year, int month, int dayOfMonth, int dayOfWeek) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
				
	}
	
}
