package com.echo.iamnot250;

import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	private String[] expressions;
	private Random random;
	private int wrongExpressionIndex;
	private int level;
	
	private static final int EXPRESSION_COUNT_PER_LEVEL = 5;
	private static final int TIME_LEN_PER_QUESTION = 10 * 1000;
	private static final int MAX_LEVEL = 7;
	private int current_count;
	
	private MyAdapter myAdapter;
	
	private CountDownTimer countDownTimer;
	private Context context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;

		expressions = new String[4];
		random = new Random();
		wrongExpressionIndex = random.nextInt(4);
		level = 4;
		countDownTimer = new MyCountDownTimer(TIME_LEN_PER_QUESTION, 1000);
		resetExpressions();

		setContentView(R.layout.fragment_main);

		ListView listView = (ListView) findViewById(R.id.listView);
		myAdapter = new MyAdapter(this);
		listView.setAdapter(myAdapter);
		listView.setOnItemClickListener(this);
		
		
	}

	
	public class MyAdapter extends ArrayAdapter<String>{

		public MyAdapter(Context context){
			//super(context, android.R.layout.simple_list_item_1);
			super(context, R.layout.list_item);
		}
		public MyAdapter(Context context, int resource) {
			super(context, resource);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public String getItem(int position) {

			return expressions[position];
		}
		
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == wrongExpressionIndex) {
			Toast.makeText(this, "250", Toast.LENGTH_SHORT).show();
			
		}else {
			resetExpressions();
			myAdapter.notifyDataSetChanged();
		}
	}
	
	private void resetExpressions(){
		current_count ++;
		if (current_count == EXPRESSION_COUNT_PER_LEVEL) {
			if (level == MAX_LEVEL) {
				Toast.makeText(this, "pass all levels", Toast.LENGTH_SHORT).show();
				return;
			}
			level ++;
			current_count = 0;
			Toast.makeText(this, "pass one level " + level , Toast.LENGTH_SHORT).show();
		}

		for (int i = 0; i < expressions.length; i++) {
			if(i == wrongExpressionIndex){
				expressions[i] = Util.getWrongExpression(level);
			}else {
				expressions[i] = Util.getRightExpression(level);
			}
			
		}
		
		countDownTimer.cancel();
		countDownTimer.start();
		
	}
	
	private class MyCountDownTimer extends CountDownTimer{

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			Toast.makeText(context, "Game Over!", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			
		}
		
	}

}