package com.libertymutual.goforcode.timeless.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

public class TimeEntryItem {

	private String date;
	private double monday;
	private double tuesday;
	private double wednesday;
	private double thursday;
	private double friday;
	private double total;
	private String status;
	private boolean isSubmitted;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMonday() {
		return monday;
	}

	public void setMonday(double monday) {
		this.monday = monday;
	}

	public double getTuesday() {
		return tuesday;
	}

	public void setTuesday(double tuesday) {
		this.tuesday = tuesday;
	}

	public double getWednesday() {
		return wednesday;
	}

	public void setWednesday(double wednesday) {
		this.wednesday = wednesday;
	}

	public double getThursday() {
		return thursday;
	}

	public void setThursday(double thursday) {
		this.thursday = thursday;
	}

	public double getFriday() {
		return friday;
	}

	public void setFriday(double friday) {
		this.friday = friday;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return this.monday + this.tuesday + this.wednesday + this.thursday + this.friday;
	}

}