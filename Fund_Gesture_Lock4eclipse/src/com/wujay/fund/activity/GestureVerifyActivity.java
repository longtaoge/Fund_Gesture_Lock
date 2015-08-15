package com.wujay.fund.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wujay.fund.Config;
import com.wujay.fund.R;
import com.wujay.fund.activity.interfaces.GestureVerifyActivityInterface;
import com.wujay.fund.common.BeanFactory;
import com.wujay.fund.widget.GestureContentView;
import com.wujay.fund.widget.GestureDrawline.GestureCallBack;

/**
 * 
 * 手势绘制/校验界面
 * 
 */
public class GestureVerifyActivity extends Activity implements
		GestureVerifyActivityInterface, android.view.View.OnClickListener {
	/** 手机号码 */
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private TextView mTextCancel;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	private TextView mTextOther;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);
	
		setUpViews();
		setUpListeners();
	}



	private void setUpViews() {
		
		mTextCancel = (TextView) findViewById(R.id.text_cancel);

		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextOther = (TextView) findViewById(R.id.text_other_account);

		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this, true,
				Config.defultPass, new GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {

					}

					@Override
					public void checkedSuccess() {
						mGestureContentView.clearDrawlineState(0L);
						Toast.makeText(GestureVerifyActivity.this, "密码正确", 1000)
								.show();
						GestureVerifyActivity.this.finish();
					}

					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						mTextTip.setText(Html
								.fromHtml("<font color='#c70c1e'>密码错误</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils
								.loadAnimation(GestureVerifyActivity.this,
										R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}

	private void setUpListeners() {
		mTextCancel.setOnClickListener(this);
		mTextForget.setOnClickListener(this);
		mTextOther.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_cancel:
			this.finish();
			break;
		case R.id.text_forget_gesture:
			//  跳转到登录页  工厂解耦，以便在其它项目中使用 
			Intent mIntent = new Intent();
			mIntent.setClass(GestureVerifyActivity.this,
					BeanFactory.getImpl(Config.TEXT_FORGET_GESTURE));
			startActivity(mIntent);
			GestureVerifyActivity.this.finish();

			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// 屏蔽后退键
		 //super.onBackPressed();
	}

}
