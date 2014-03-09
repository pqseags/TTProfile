package com.example.datainput;


public class GeneralDate {
	public int month;
	public int year;
	
	GeneralDate(int mon, int yea){
		this.year = yea;
		if (mon>0 && mon<13)
			this.month = mon;
		else this.month = 0;
		
	}
}
