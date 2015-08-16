package com.wujay.fund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.wujay.fund.activity.BaseActivity;
import com.wujay.fund.activity.GestureEditActivity;
import com.wujay.fund.activity.GestureVerifyActivity;

public class MainActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener {
	private Button mBtnSetLock;
	private Button mBtnVerifyLock;
    private CheckBox mBheckBox;
	private TextView password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    initView();
		setUpListener();
	}


	
	private void setUpListener() {
		mBtnSetLock.setOnClickListener(this);
		mBtnVerifyLock.setOnClickListener(this);
		mBheckBox.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_set_lockpattern:
			startSetLockPattern();
			break;
		case R.id.btn_verify_lockpattern:
			startVerifyLockPattern();
			break;
		default:
			break;
		}
	}

	private void startSetLockPattern() {
		Intent intent = new Intent(MainActivity.this, GestureEditActivity.class);
		startActivity(intent);
	}
	
	private void startVerifyLockPattern() {
		Intent intent = new Intent(MainActivity.this, GestureVerifyActivity.class);
		startActivity(intent);
	}

	@Override
	protected void initView() {
		mBtnSetLock = (Button) findViewById(R.id.btn_set_lockpattern);
		mBtnVerifyLock = (Button) findViewById(R.id.btn_verify_lockpattern);
		mBheckBox=(CheckBox)findViewById(R.id.checkbox);
		mBheckBox.setChecked(Config.show);
		password=(TextView) findViewById(R.id.password);
		password.setText("密码是"+Config.defultPass);
		
	}



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (mBheckBox.isChecked()) {
			Config.show=true;
		}else {
			Config.show=false;
		}
	}
}
