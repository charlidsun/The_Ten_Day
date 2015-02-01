package com.example.guessagg;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/*
 * 程序的开场动画，5秒事件，有个跳过按钮
 */

public class startactivity extends Activity {

	private Button passbutt;// 跳过按钮
	private boolean flag = true;// 判断是否点击button按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏现实
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.start_main);

		passbutt = (Button) findViewById(R.id.passbutt);

		passbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				flag = false;
				Intent intent = new Intent(startactivity.this,
						MainActivity.class);
				startActivity(intent);
				startactivity.this.finish();
			}
		});

		// handler 运行5s中
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (flag) {
					Intent intent = new Intent(startactivity.this,
							MainActivity.class);
					startActivity(intent);
					startactivity.this.finish();
				}

			}
		}, 5000);// 5s
	}

}
