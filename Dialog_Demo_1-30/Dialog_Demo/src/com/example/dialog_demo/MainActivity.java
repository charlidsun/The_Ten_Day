package com.example.dialog_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button exitbutt;// 退出对话框按钮
	private Button listbutt;// 列表对话框按钮
	private Button selebutt;// 单选对话框按钮 与列表和多选对话框相似
	private Button morebutt;// 多选对话框按钮
	private Button editbutt;// 编辑对话框按钮
	private Button returnbutt; // returnbutt无意义。点按返回键显示退出对话框，确定和退出事件。

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 为退出对话框按钮添加监听事件，实现对话框
		exitbutt = (Button) findViewById(R.id.exitbutt);
		exitbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 使用 AlertDialog 类
				AlertDialog aDialog = new AlertDialog.Builder(MainActivity.this)
						.create();// 实例化
				aDialog.setIcon(R.drawable.ic_launcher);// 图标
				aDialog.setTitle("退出");// 标题
				aDialog.setMessage("您确认退出吗？");// 内容
				// 添加按钮 取消
				aDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// 在此设置 取消 按钮的触发事件。例如跳转，取消，退出等。
								Toast.makeText(getApplicationContext(),
										"您点击取消按钮", Toast.LENGTH_SHORT).show();
							}
						});

				aDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// 在此设置 取消 按钮的触发事件。例如跳转，取消，退出等。
								Toast.makeText(getApplicationContext(),
										"您点击了取消按钮", Toast.LENGTH_SHORT).show();
							}
						});
				// 将对话框显示在屏幕上
				aDialog.show();

			}
		});
		// 列表对话框按钮的监听事件
		listbutt = (Button) findViewById(R.id.listbutt);
		listbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String[] list = new String[] { "诺基亚", "摩托罗拉", "苹果", "三星" };
				// 调用buulder 方法
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.ic_launcher);// 图标
				builder.setTitle("您喜欢的品牌?");// 标题
				// 设置 列表选项 list 为列表的源 后面为监听事件
				builder.setItems(list, new OnClickListener() {
					@Override
					// arg1 列表顺序
					public void onClick(DialogInterface arg0, int arg1) {
						// 在此添加事件。demo为toast显示
						Toast.makeText(getApplicationContext(),
								"您点击了" + list[arg1], Toast.LENGTH_SHORT).show();
					}
				});
				// 显示事件
				builder.create().show();
			}
		});

		// 单选对话框实现
		selebutt = (Button) findViewById(R.id.selebutt);
		selebutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String[] list = new String[] { "篮球", "足球", "排球", "台球",
						"手球" };
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("您最喜欢的球类运动？");
				// 单选列表选项 单选第二个参数为0 没有使用适配器显示。
				builder.setSingleChoiceItems(list, 0, new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// demo 为toast显示 开发中可以显示在编辑框中
						Toast.makeText(getApplicationContext(),
								"您点击了" + list[arg1], Toast.LENGTH_SHORT).show();
					}
				});
				builder.setPositiveButton("确定", null);
				builder.create().show();// 记得添加显示
			}
		});
		// 多选对话框的设置
		morebutt = (Button) findViewById(R.id.morebutt);
		morebutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 多选的事件有true和false决定
				final boolean[] checklist = new boolean[] { false, false,
						false, false, false };
				final String[] list = new String[] { "C/C++", "Java", "Go",
						"Ruby", "PHP" };
				Builder butt = new AlertDialog.Builder(MainActivity.this);
				butt.setIcon(R.drawable.ic_launcher);
				butt.setTitle("您擅长的语言?");
				// 多选事件列表方法
				butt.setMultiChoiceItems(list, checklist,
						new OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1,
									boolean arg2) {
								// 改变选中状态
								checklist[arg1] = arg2;
							}
						});
				// 确定按钮显示选择方法
				butt.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						String res = "";
						for (int i = 0; i < checklist.length; i++) {
							if (checklist[i]) {
								res += list[i] + ",";
							}
						}
						if (!"".equals(res)) {
							res = res.substring(0, res.length() - 1);
							Toast.makeText(getApplicationContext(),
									"您选择了 [" + res + "]", Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
				butt.create().show();
			}
		});
		// 编辑对话框的实现 应用较少，故没有添加监听事件。目前还没想好如何添加。mark，待续。
		editbutt = (Button) findViewById(R.id.editbutt);
		editbutt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("请您输入内容");
				builder.setView(new EditText(MainActivity.this));
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					}
				});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
		});

	}

	// 最后 测试 返回键。 点按返回键显示退出对话框，确定和退出事件。
	public boolean onKeyDown(int Key, KeyEvent event) {
		if (Key == KeyEvent.KEYCODE_BACK) {
			AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
					.create();
			alertDialog.setTitle("退出");
			alertDialog.setMessage("是否退出?");
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					//点击取消，退出按钮
				}
			});
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					//退出
					MainActivity.this.finish();
				}
			});
			alertDialog.show();
		}
		return false;

	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
