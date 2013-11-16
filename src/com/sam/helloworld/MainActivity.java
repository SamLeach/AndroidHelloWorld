package com.sam.helloworld;

import java.util.List;

import com.sam.dataAccess.DataAccess;
import com.sam.model.Person;

import Business.Friend;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	DataAccess db;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	db = new DataAccess(getApplicationContext());
    	
    	Person person = new Person("Sam");
    	person.setId(12345);
    	
    	long personId = db.createPerson(person);
   
    	Person p = db.getPerson(personId);
    	
    	db.closeDB();
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendMessage(View view) {
    	//Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText editText = (EditText) findViewById(R.id.textView1);
    	//String message = editText.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	//startActivity(intent);
    	TextView text = (TextView) findViewById(R.id.textView1);
    	double moneyOwed = getSamsMoneyOwed();    	
    	String s = Double.toString(moneyOwed);
    	text.setText(s);
    }
    
    public double getSamsMoneyOwed()
    {
		Friend friend = new Friend();
		Friend me = new Friend();
		me.setMoneyOwed(10);
		
		friend.setName("Eric");
		friend.addFriend(me);
		friend.setMoneyOwed(10);
			
		me.setName("Sam");
		friend.addFriend(me);
		List<Friend> friends = friend.getFriends();
		Friend eric = friends.get(0);
		double moneyOwed = eric.getMoneyOwed();
		return moneyOwed;
    }
    
}
