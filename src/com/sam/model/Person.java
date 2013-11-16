package com.sam.model;

import java.util.ArrayList;
import java.util.List;

public class Person extends Model{

	private double moneyOwed;
	private double moneyOwes;
	private List<Person> people;
	
	public Person()
	{
		people = new ArrayList<Person>();
	}
	
	public Person(String name)
	{
		super.name = name;
		people = new ArrayList<Person>();
	}
	
	public Person(String name, List<Person> people)
	{
		super.name = name;
		this.people = new ArrayList<Person>();
		this.people = people;
		
	}
		
	public void addFriend(Person friend) {
		this.people.add(friend);
	}
	
	public void removeFriend(Person friend) {
		this.people.remove(friend);
	}

	public double getMoneyOwed() {
		return moneyOwed;
	}

	public void setMoneyOwed(double moneyOwed) {
		this.moneyOwed = moneyOwed;
	}

	public double getMoneyOwes() {
		return moneyOwes;
	}

	public void setMoneyOwes(double moneyOwes) {
		this.moneyOwes = moneyOwes;
	}

	public List<Person> getFriends() {
		return people;
	}

	public void setFriends(List<Person> people) {
		this.people = people;
	}
}
