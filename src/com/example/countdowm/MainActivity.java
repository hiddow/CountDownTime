package com.example.countdowm;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button mGetTime,startTime,stopTime;
	private EditText mInputTime;
	private TextView mTime;
	private int i = 0;
	private Timer timer = null;
	private TimerTask task = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	public void init(){
		mGetTime = (Button)findViewById(R.id.getTime);
		startTime = (Button)findViewById(R.id.startCDTime);
		stopTime = (Button)findViewById(R.id.stopCDTime);
		mInputTime = (EditText)findViewById(R.id.inputTime);
		mTime = (TextView)findViewById(R.id.time);
		mGetTime.setOnClickListener(this);
		startTime.setOnClickListener(this);
		stopTime.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.getTime:
			mTime.setText(mInputTime.getText().toString());
			i = Integer.parseInt(mInputTime.getText().toString());
			break;
		case R.id.startCDTime:
			startCDTime();
			break;
		case R.id.stopCDTime:
			stopCDTime();
			break;

		default:
			break;
		}
		
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			mTime.setText(msg.arg1 + " ");
			startCDTime();
		};
	};
	
	public void startCDTime(){
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
				i --;
				Message message = handler.obtainMessage();
				message.arg1 = i;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, 1000);
	}
	
	public void stopCDTime(){
		timer.cancel();
	}
	
}
