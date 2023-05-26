package com.KinaraCapital.demo;

public class Student {
	private int id;
    private String name;
    private int totalMarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", totalMarks=" + totalMarks + "]";
	}
    

}
