package com.wujay.fund.activity;

import com.wujay.fund.MainActivity;
import com.wujay.fund.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity implements OnClickListener {

	Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

	}

	@Override
	protected void initView() {
		//
		mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:

			Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(mIntent);
			finish();
			break;

		default:
			break;
		}

	}

}
