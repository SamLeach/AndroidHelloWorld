package Business;

import java.util.ArrayList;
import java.util.List;

public class Friend {

	private String name;
	private double moneyOwed;
	private double moneyOwes;
	private List<Friend> friends;

	public Friend()
	{
		friends = new ArrayList<Friend>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addFriend(Friend friend) {
		this.friends.add(friend);
	}
	
	public void removeFriend(Friend friend) {
		this.friends.remove(friend);
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

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}	
}
