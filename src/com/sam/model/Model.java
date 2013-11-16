package com.sam.model;
public abstract class Model {
	
	protected String name;
	protected int id;
	protected String createdDate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCreatedAt(String date)
	{
		this.createdDate = date;
	}
	
	public String getCreatedAt()
	{
		return this.createdDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
