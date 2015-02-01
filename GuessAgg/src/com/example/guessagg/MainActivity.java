package com.example.guessagg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button initbutt;// 重置按钮
	private Button setbutt;// 设置按钮
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private TextView restext;// 显示游戏结果
	private int[] imagetitle = new int[] { R.drawable.shoe_default,
			R.drawable.shoe_ok, R.drawable.shoe_sorry };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 获取相关组件
		imageView1 = (ImageView) findViewById(R.id.image1);
		imageView2 = (ImageView) findViewById(R.id.image2);
		imageView3 = (ImageView) findViewById(R.id.image3);
		restext = (TextView) findViewById(R.id.welcometextview);
		initbutt = (Button) findViewById(R.id.initbutt);
		setbutt = (Button) findViewById(R.id.setbutt);

		// 初始化
		initGame();

		// 给图片添加监听事件。
		imageView1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				enableonclick();
				GameStart(arg0, 0);
			}
		});
		imageView2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				enableonclick();
				GameStart(arg0, 1);
			}

			
		});

		imageView3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				enableonclick();
				GameStart(arg0, 2);
			}
		});
		// 为重置按钮添加事件 一个按钮对应一个监听事件
		initbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				initGame();
				ableonclick();
				restext.setText("欢迎进入，才鸡蛋游戏");
				imageView1.setAlpha(255);
				imageView2.setAlpha(255);
				imageView3.setAlpha(255);

				// 图片恢复为默认
				imageView1.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
				imageView2.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
				imageView3.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
			}
		});

		// 关于游戏事件，弹出关于游戏的对话框
		setbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog alertDialog = new AlertDialog.Builder(
						MainActivity.this).create();
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setTitle("猜鸡蛋游戏");
				alertDialog
						.setMessage("此款游戏是基于android开发实战一书中的例子来改写的，增加了开场动画和关于游戏的按钮，复习了相关知识。");
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								// 无响应事件。
							}
						});
				alertDialog.show();
			}
		});

	}

	// 初始化程序,让imagetitle中的图片随机放在默认的鞋子中
	// 掉换方法。将图片打乱顺序
	private void initGame() {
		for (int i = 0; i < 3; i++) {
			int tmp = imagetitle[i];// 将其中的图片传递给tmp
			int index = (int) (Math.random() * 2);// 生成2以内的随机数
			imagetitle[i] = imagetitle[index];// 生成的数为图片
			imagetitle[index] = tmp;// 将原来的图片给生成的图片
		}
	}

	// 编写游戏主逻辑
	private void GameStart(View arg0, int index) {
		// 将初始化的数组图片设置为imageviww
		imageView1.setImageDrawable(getResources().getDrawable(imagetitle[0]));
		imageView2.setImageDrawable(getResources().getDrawable(imagetitle[1]));
		imageView3.setImageDrawable(getResources().getDrawable(imagetitle[2]));

		imageView1.setAlpha(100);
		imageView2.setAlpha(100);
		imageView3.setAlpha(100);

		ImageView v = (ImageView) arg0; // 接受用户点击的是哪张图片，并接受事件。
		v.setAlpha(255);
		if (imagetitle[index] == R.drawable.shoe_ok) {
			restext.setText("恭喜您，猜对了");
		} else {
			restext.setText("猜错了，点击按钮可重!");
		}
	}
	
	//按钮实效，点击重置按钮后才会继续游戏
	private void enableonclick() {
		// TODO Auto-generated method stub
		imageView1.setEnabled(false);
		imageView2.setEnabled(false);
		imageView3.setEnabled(false);
	}
	private void ableonclick() {
		// TODO Auto-generated method stub
		imageView1.setEnabled(true);
		imageView2.setEnabled(true);
		imageView3.setEnabled(true);
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
