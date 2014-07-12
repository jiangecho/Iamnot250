package com.echo.iamnot250;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, DialogInterface.OnKeyListener{

	private String[] expressions;
	private Random random;
	private int wrongExpressionIndex;
	private int level;
	
	private static final int EXPRESSION_COUNT_PER_LEVEL = 7;
	private static final int TIME_LEN_PER_QUESTION = 5 * 1000;
	private static final int MAX_LEVEL = 8;
	private int current_count;
	
	private MyAdapter myAdapter;
	
	private CountDownTimer countDownTimer;
	private Context context;
	
	private TextView levelTV;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;

		expressions = new String[4];
		random = new Random();
		level = 0;

		setContentView(R.layout.fragment_main);

		ListView listView = (ListView) findViewById(R.id.listView);
		myAdapter = new MyAdapter(this);
		listView.setAdapter(myAdapter);
		listView.setOnItemClickListener(this);
		levelTV = (TextView) findViewById(R.id.levelTV);
		updateLevelInfo();
		
		resetExpressions();
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		countDownTimer = new MyCountDownTimer(TIME_LEN_PER_QUESTION, 1000);
		countDownTimer.start();

	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		countDownTimer.cancel();
		countDownTimer = null;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
			resetExpressions();
		}else {
			if (countDownTimer != null) {
				countDownTimer.cancel();
			}

			Toast.makeText(this, "250", Toast.LENGTH_SHORT).show();
			Resources resources = getResources();
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle(resources.getString(R.string.fail_title));
			alertDialog.setMessage(resources.getString(R.string.level_0_fail_msg + level));
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, resources.getString(R.string.restart), 
					new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					level = 0;
					updateLevelInfo();
					current_count = 0;
					resetExpressions();
					
				}
			});
			alertDialog.setOnKeyListener(MainActivity.this);
			alertDialog.show();
		}
	}
	
	private void resetExpressions(){
		wrongExpressionIndex = random.nextInt(4);
		current_count ++;
		if (current_count == EXPRESSION_COUNT_PER_LEVEL) {
			if (level == MAX_LEVEL) {
				Toast.makeText(this, "pass all levels", Toast.LENGTH_SHORT).show();
				Resources resources = getResources();
				AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle(resources.getString(R.string.success_title));
				alertDialog.setMessage(resources.getString(R.string.success_msg));
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, resources.getString(R.string.restart), 
						new DialogInterface.OnClickListener() {
				
					@Override
					public void onClick(DialogInterface dialog, int which) {
						level = 0;
						updateLevelInfo();
						current_count = 0;
						resetExpressions();
					
					}
				});
				alertDialog.setOnKeyListener(MainActivity.this);
				alertDialog.show();
				return;
			}
			level ++;
			updateLevelInfo();
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
		myAdapter.notifyDataSetChanged();
		
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer.start();
			
		}
		
	}
	
	private class MyCountDownTimer extends CountDownTimer{

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			Resources resources = getResources();
			AlertDialog alertDialog = new AlertDialog.Builder(context).create();
			alertDialog.setTitle(resources.getString(R.string.fail_title));
			alertDialog.setMessage(resources.getString(R.string.time_up_msg));
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, resources.getString(R.string.restart), 
					new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					level = 0;
					updateLevelInfo();
					current_count = 0;
					resetExpressions();
					
				}
			});
			alertDialog.setOnKeyListener(MainActivity.this);
			alertDialog.show();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			
		}
		
	}
	
	private void updateLevelInfo(){
		levelTV.setText("ตฺ" + " " + level + " " + "นุ");
	}


	// block the back event when the dialog is shown
	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}

}