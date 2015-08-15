package com.wujay.fund.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.wujay.fund.Config;
import com.wujay.fund.activity.interfaces.GestureVerifyActivityInterface;
import com.wujay.fund.common.BeanFactory;
import com.wujay.fund.common.LogUtil;

public abstract class BaseActivity extends Activity {

	protected abstract void initView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// onCreate()方法中注册
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		registerReceiver(mBatInfoReceiver, filter);
		filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		registerReceiver(mBatInfoReceiver, filter);
		final IntentFilter homeFilter = new IntentFilter(
				Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		registerReceiver(homePressReceiver, homeFilter);
	}

	// Intent.ACTION_SCREEN_OFF;
	// Intent.ACTION_SCREEN_ON;
	private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, final Intent intent) {
			final String action = intent.getAction();
			if (Intent.ACTION_SCREEN_OFF.equals(action)) {

				LogUtil.i(BaseActivity.this.getClass().getSimpleName(), "关闭屏幕");
				if (Config.show) {
					startPassWordActivity();
				}

			} else if (Intent.ACTION_SCREEN_ON.equals(action)) {
				LogUtil.i(BaseActivity.this.getClass().getSimpleName(), "开启屏幕");
			}

		}
	};

	private final BroadcastReceiver homePressReceiver = new BroadcastReceiver() {
		final String SYSTEM_DIALOG_REASON_KEY = "reason";
		final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
				if (reason != null
						&& reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
					// 自己随意控制程序，关闭...
					LogUtil.i(BaseActivity.this.getClass().getSimpleName(),
							"home键");
					if (Config.show) {
						startPassWordActivity();
					}

				}
			}
		}

	};

	/**
	 * 开启密码锁
	 */
	private void startPassWordActivity() {
		GestureVerifyActivityInterface mClass = (BeanFactory
				.getImpl(GestureVerifyActivityInterface.class));
		Intent mIntent = new Intent(BaseActivity.this, mClass.getClass());
		startActivity(mIntent);
	}

	@Override
	protected void onDestroy() {

		// onDestory()方法中解除注册
		if (mBatInfoReceiver != null) {
			try {
				unregisterReceiver(mBatInfoReceiver);
			} catch (Exception e) {
			}
		}

		if (homePressReceiver != null) {
			try {
				unregisterReceiver(homePressReceiver);
			} catch (Exception e) {
			}
		}

		super.onDestroy();

	}

}
