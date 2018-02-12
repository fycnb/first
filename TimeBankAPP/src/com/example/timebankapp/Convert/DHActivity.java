package com.example.timebankapp.Convert;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.timebankapp.PublishActivity;
import com.example.timebankapp.R;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class DHActivity extends ActivityGroup {

	private String tab = "tab0";
	private TabHost mTabHost;

	private Button screenb;
	private Button seekgoods;
	private EditText goodsname;
	private LinearLayout screenl;
	private boolean goodsclass = false;
	private boolean serviceclass = false;

	private ImageView myimage;
	private TextView moneynumber;
	private String id = "1";
	private Button refresh;

	private Spinner mySpinner1;
	private Spinner mySpinner2;
	private Spinner mySpinner3;
	private ListView goodslistview;
	private TextView emptygoods;

	private String style = "全部";
	private String money = "全部";
	private String mymoney = "全部";
	private String whatservice = "全部";
	private int i = -3;

	private Button screenb2;
	private Button publish;
	private Button serviceseekbt;
	private LinearLayout screenl2;
	private ListView servicename;
	private ListView serviceinfo;
	private EditText serviceseeket;
	private TextView emptyservice;
	private TextView emptyservicename;
	private String serviceid;

	private EditText bankcard1;
	private EditText bankcard2;
	private EditText apppassword;
	private EditText cashmoneynumber;
	private CheckBox surecb;
	private Button surebt;
	private TextView bc1;
	private TextView bc2;
	private TextView ap;
	private TextView cn;

	private TextView cashgoodsname;
	private TextView cashgoodsmoney;
	private TextView cashgoodsallmoney;
	private TextView hint;
	private TextView refreshmoney;
	private Button surecash;
	private Spinner cashgoodsnumber;
	private CheckBox yesbuy;
	private CheckBox nobuy;
	private String str;
	private int number;
	private String goodsid;
	private String goodsmoney;

	private TextView cashservicename;
	private TextView cashservicemoney;
	private Spinner cashservicetime;
	private Button refreshservice;
	private Button reserveservice;
	private TextView servicehint;
	private TextView refreshmoney2;
	private String servicetime;
	private String servicemoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dh);

		mTabHost = (TabHost) findViewById(R.id.Convert_TabHost);
		mTabHost.setup();
		TabWidget tabWidget = mTabHost.getTabWidget();

		mTabHost.addTab(mTabHost.newTabSpec("商品列表").setIndicator("商品列表").setContent(R.id.goodspage_LinearLayout));
		mTabHost.addTab(mTabHost.newTabSpec("服务列表").setIndicator("服务列表").setContent(R.id.servicepage_LinearLayout));
		mTabHost.addTab(mTabHost.newTabSpec("提现").setIndicator("提现").setContent(R.id.cashpage_LinearLayout));
		if (tab.equalsIgnoreCase("tab0")) {
			mTabHost.setCurrentTabByTag("tab0");
		}

		updateTab(mTabHost);

		mTabHost.setOnTabChangedListener(new OnTabChangedListener());

		myimage = (ImageView) findViewById(R.id.myimage_ImageView);
		moneynumber = (TextView) findViewById(R.id.timemoneynumber_TextView);
		refresh = (Button) findViewById(R.id.refresh_Button);

		moneynumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Intent intent = new Intent();
								intent.setClass(DHActivity.this, MoneyDetailsActivity.class);
								startActivity(intent);
							}
						});
					}
				}.start();
			}
		});

		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updatemyinfo();
			}
		});

		goodslistview = (ListView) findViewById(R.id.goodsinfo_ListView);
		emptygoods = (TextView) findViewById(R.id.emptygoods_TextView);
		mySpinner1 = (Spinner) findViewById(R.id.type_Spinner);
		mySpinner2 = (Spinner) findViewById(R.id.money_Spinner);
		mySpinner3 = (Spinner) findViewById(R.id.cash_Spinner);
		screenb = (Button) findViewById(R.id.class_Button);
		screenl = (LinearLayout) findViewById(R.id.goodsclass_LinearLayout);
		seekgoods = (Button) findViewById(R.id.goodsseek_Button);
		goodsname = (EditText) findViewById(R.id.goodsseek_EditText);

		mySpinner1.setOnItemSelectedListener(new SpinnerSelectedListener(1));
		mySpinner2.setOnItemSelectedListener(new SpinnerSelectedListener(2));
		mySpinner3.setOnItemSelectedListener(new SpinnerSelectedListener(3));

		screenb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (screenl.getVisibility() == View.GONE) {
					if (!goodsclass) {
						updategoodsclass();
					}
					screenl.setVisibility(View.VISIBLE);
				} else {
					screenl.setVisibility(View.GONE);
				}
			}
		});

		seekgoods.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = goodsname.getText().toString();
				String regex = "\\s+";
				String str1 = str.replaceAll(regex, "");
				if (!str1.equals("")) {
					updategoodsbutton(str1);
				}
			}
		});

		goodslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				number = 0;
				str = "否";
				goodsid = ((GoodsList) adapterView.getAdapter().getItem(i)).getid();
				final String name = ((GoodsList) adapterView.getAdapter().getItem(i)).getname();
				goodsmoney = ((GoodsList) adapterView.getAdapter().getItem(i)).getmoney();
				final AlertDialog.Builder builder = new AlertDialog.Builder(DHActivity.this, R.style.logstyle);
				final View logview = View.inflate(DHActivity.this, R.layout.goodscashwindow, null);
				builder.setView(logview);
				final AlertDialog dialog = builder.show();

				cashgoodsname = (TextView) logview.findViewById(R.id.cashgoodsname_TextView);
				cashgoodsmoney = (TextView) logview.findViewById(R.id.cashgoodsmoney_TextView);
				hint = (TextView) logview.findViewById(R.id.surehint_TextView);
				cashgoodsnumber = (Spinner) logview.findViewById(R.id.cashgoodsnumber_Spinner);
				cashgoodsallmoney = (TextView) logview.findViewById(R.id.cashgoodstotalmoney_TextView);
				surecash = (Button) logview.findViewById(R.id.surecash_Button);
				yesbuy = (CheckBox) logview.findViewById(R.id.yesbuysurplus_CheckBox);
				nobuy = (CheckBox) logview.findViewById(R.id.nobuysurplus_CheckBox);
				refreshmoney = (TextView) logview.findViewById(R.id.refreshmoney_TextView);

				// TODO Auto-generated method stub

				cashgoodsname.setText(name);
				cashgoodsmoney.setText(goodsmoney);
				List<String> list = new ArrayList<String>();
				for (int q = 0; q < 11; q++) {
					list.add("" + q);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<>(DHActivity.this, R.layout.spinnerstyle, list);
				adapter.setDropDownViewResource(R.layout.spinnerstyle);
				cashgoodsnumber.setAdapter(adapter);
				cashgoodsnumber.setOnItemSelectedListener(
						new SpinnerSelectedListener(4, goodsmoney, cashgoodsallmoney, hint, surecash));
				if (moneynumber.getText().toString().equals("xxxx")) {
					cashgoodsnumber.setEnabled(false);
					hint.setText("请先刷新一下您的时间币");
				}
				refreshmoney.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						refreshmoney("goods", goodsid, cashgoodsmoney);
					}
				});
				yesbuy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							nobuy.setChecked(false);
							str = "是";
						} else {
							nobuy.setChecked(true);
							str = "否";
						}
					}
				});

				nobuy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							yesbuy.setChecked(false);
							str = "否";
						} else {
							yesbuy.setChecked(true);
							str = "是";
						}
					}
				});
				surecash.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new Thread() {
							public void run() {
								final String result = CashGoodsService.CashGoodsByPost(id, goodsid, number, goodsmoney,
										str);
								String hintinfo;
								if (result != null) {
									if (result.equals("兑换成功")) {
										hintinfo = "全部兑换成功!";
										updatemyinfo();
										dialog.dismiss();
									} else if (result.equals("数量不足")) {
										hintinfo = "兑换失败,剩余商品不足!";
									} else if (result.equals("价钱变更")) {
										hintinfo = "兑换失败,商品价格变更(变贵!)";
										hint.setText("请点击单价,刷新价格");
									} else if (result.equals("余额不足")) {
										hintinfo = "兑换失败,余额不足,请刷新时间币!";
									} else if (result.equals("兑换数已满")) {
										hintinfo = "兑换失败,兑换数已满!";
									} else if (result.length() > 4) {
										hintinfo = "兑换失败," + result + "个!";
									} else {
										hintinfo = "剩余商品不足,成功兑换" + result + "个!";
										updatemyinfo();
										dialog.dismiss();
									}
								} else {
									hintinfo = "兑换失败,请检查网络!";
									dialog.dismiss();
								}
								final String str = hintinfo;
								runOnUiThread(new Runnable() {
									public void run() {
										Toast t3 = Toast.makeText(DHActivity.this, str, Toast.LENGTH_SHORT);
										t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 10);
										t3.setMargin(0f, 0.5f);
										t3.show();
									}
								});
							}
						}.start();
					}
				});
			}
		});

		screenb2 = (Button) findViewById(R.id.class2_Button);
		publish = (Button) findViewById(R.id.publish_Button);
		serviceseekbt = (Button) findViewById(R.id.serviceseek_Button);
		serviceseeket = (EditText) findViewById(R.id.serviceseek_EditText);
		screenl2 = (LinearLayout) findViewById(R.id.serviceclass_LinearLayout);
		servicename = (ListView) findViewById(R.id.serviceclass_ListView);
		serviceinfo = (ListView) findViewById(R.id.serviceinfo_ListView);
		emptyservice = (TextView) findViewById(R.id.emptyservice_TextView);
		emptyservicename = (TextView) findViewById(R.id.emptyservicename_TextView);

		screenb2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (screenl2.getVisibility() == View.GONE) {
					if (!serviceclass) {
						updateserviceclass();
					}
					screenl2.setVisibility(View.VISIBLE);
				} else {
					screenl2.setVisibility(View.GONE);
				}
			}
		});

		servicename.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				whatservice = adapterView.getAdapter().getItem(i).toString();

				updateservice(whatservice);

			}
		});

		serviceseekbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = serviceseeket.getText().toString();
				String regex = "\\s+";
				String str1 = str.replaceAll(regex, "");
				if (!str1.equals("")) {
					updateservicebutton(str1);
				}
			}
		});

		serviceinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				serviceid = ((ServiceList) adapterView.getAdapter().getItem(i)).getid();
				String servicename = ((ServiceList) adapterView.getAdapter().getItem(i)).getname();
				servicemoney = ((ServiceList) adapterView.getAdapter().getItem(i)).getmoney();

				final AlertDialog.Builder builder = new AlertDialog.Builder(DHActivity.this, R.style.logstyle);
				final View logview = View.inflate(DHActivity.this, R.layout.servicecashwindow, null);
				builder.setView(logview);
				final AlertDialog dialog = builder.show();

				cashservicename = (TextView) logview.findViewById(R.id.cashservicename_TextView);
				cashservicemoney = (TextView) logview.findViewById(R.id.cashservicemoney_TextView);
				cashservicetime = (Spinner) logview.findViewById(R.id.cashservicetime_Spinner);
				refreshservice = (Button) logview.findViewById(R.id.refreshtime_Button);
				reserveservice = (Button) logview.findViewById(R.id.reserveservice_Button);
				servicehint = (TextView) logview.findViewById(R.id.servicehint_TextView);
				refreshmoney2 = (TextView) logview.findViewById(R.id.refreshmoney2_TextView);

				cashservicename.setText(servicename);
				cashservicemoney.setText(servicemoney);

				updateservicetime(serviceid, servicehint);

				refreshmoney2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						refreshmoney("service", serviceid, cashservicemoney);
					}
				});
				cashservicetime.setOnItemSelectedListener(
						new SpinnerSelectedListener(5, servicemoney, servicehint, reserveservice));

				refreshservice.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						updateservicetime(serviceid, servicehint);
					}
				});

				reserveservice.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new Thread() {
							public void run() {
								final String result = CashServiceService.CashServiceByPost(id, serviceid, servicetime,
										servicemoney);
								String hintinfo;
								if (result != null) {
									if (result.equals("预订成功")) {
										hintinfo = "预订成功!";
										dialog.dismiss();
									} else if (result.equals("无时间")) {
										hintinfo = "预订失败,请重新选择其他时间!";
									} else if (result.equals("价钱变更")) {
										hintinfo = "预订失败,服务价格变更(变贵!)";
										servicehint.setText("请点击价格,刷新价格!");
									} else {
										hintinfo = "预订失败,余额不足,请刷新时间币!";
									}
								} else {
									hintinfo = "预订失败,请检查网络!";
									dialog.dismiss();
								}
								final String str = hintinfo;
								runOnUiThread(new Runnable() {
									public void run() {
										Toast t3 = Toast.makeText(DHActivity.this, str, Toast.LENGTH_SHORT);
										t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 10);
										t3.setMargin(0f, 0.5f);
										t3.show();
									}
								});

							}
						}.start();
					}
				});
			}
		});

		publish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Intent intent = new Intent();
								intent.setClass(DHActivity.this, PublishActivity.class);
								startActivity(intent);
							}
						});
					}
				}.start();
			}
		});

		bankcard1 = (EditText) findViewById(R.id.bankcardnumber1_EditText);
		bankcard2 = (EditText) findViewById(R.id.bankcardnumber2_EditText);
		apppassword = (EditText) findViewById(R.id.apppassword_EditText);
		cashmoneynumber = (EditText) findViewById(R.id.cashnumber_EditText);
		surecb = (CheckBox) findViewById(R.id.sure_CheckBox);
		surebt = (Button) findViewById(R.id.surecash_Button);
		bc1 = (TextView) findViewById(R.id.bankcardnumber1_TextView);
		bc2 = (TextView) findViewById(R.id.bankcardnumber2_TextView);
		ap = (TextView) findViewById(R.id.apppassword_TextView);
		cn = (TextView) findViewById(R.id.cashnumber_TextView);

		bankcard1.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {
					String str = bankcard1.getText().toString();
					if (!judgebankcard(str))
						bc1.setText("请输入正确的卡号");
					else
						bc1.setText("");
				} else
					bc1.setText("");
			}
		});

		bankcard2.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {
					String str1 = bankcard1.getText().toString();
					String str2 = bankcard2.getText().toString();
					if (!str1.equals(str2))
						bc2.setText("卡号不一致");
					else
						bc2.setText("");
				} else
					bc2.setText("");
			}
		});

		cashmoneynumber.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {
					String str1 = cashmoneynumber.getText().toString();
					String str2 = moneynumber.getText().toString();

					if (!str1.equals("")) {
						if (str2.equals("xxxx")) {
							cn.setText("请点击上面的刷新按钮");
						} else if (Integer.parseInt(str1) > Integer.parseInt(str2) || Integer.parseInt(str1) > 20) {
							cn.setText("请输入正确的时间币");
						} else {
							cn.setText("");
						}
					}
				} else
					cn.setText("");
			}
		});

		surecb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					surebt.setEnabled(true);
				} else {
					surebt.setEnabled(false);
				}
			}
		});

		updatemyinfo();
		updategoods(style, money, mymoney);
		updateservice(whatservice);

	}

	class OnTabChangedListener implements OnTabChangeListener {

		@Override
		public void onTabChanged(String tabId) {
			mTabHost.setCurrentTabByTag(tabId);
			updateTab(mTabHost);
		}
	}

	private void updateTab(final TabHost tabHost) {
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			View view = tabHost.getTabWidget().getChildAt(i);
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextSize(16);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			if (tabHost.getCurrentTab() == i) {
				view.setBackgroundDrawable(getResources().getDrawable(R.color.huise));
				tv.setTextColor(this.getResources().getColorStateList(R.color.lanse));
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);
			} else {
				view.setBackgroundDrawable(getResources().getDrawable(R.color.lanse));
				tv.setTextColor(this.getResources().getColorStateList(R.color.huise));
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
			}
		}
	}

	class SpinnerSelectedListener implements Spinner.OnItemSelectedListener {

		int a;
		String b;
		TextView c;
		TextView d;
		Button e;

		public SpinnerSelectedListener(int a) {
			this.a = a;
		}

		public SpinnerSelectedListener(int a, String b, TextView c, Button e) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.e = e;
		}

		public SpinnerSelectedListener(int a, String b, TextView c, TextView d, Button e) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long thisid) {
			// TODO Auto-generated method stub
			final String str = parent.getItemAtPosition(position).toString();
			int mymoney1 = (moneynumber.getText().toString().equals("xxxx")) ? -1
					: Integer.parseInt(moneynumber.getText().toString());

			if (i < 0) {
				i += 1;
			} else {
				switch (a) {
				case 1:
					style = str;
					updategoods(style, money, mymoney);
					break;
				case 2:
					money = str;
					updategoods(style, money, mymoney);
					break;
				case 3:
					if (str.equals("全部")) {
						mymoney = str;
					} else {
						mymoney = id;
					}

					updategoods(style, money, mymoney);
					break;

				}

			}
			switch (a) {
			case 5:
				servicetime = str;
				if (mymoney1 < Integer.parseInt(b)) {
					e.setEnabled(false);
					c.setText("余额不足");
				} else if (servicetime.equals("请选择")) {
					e.setEnabled(false);
					c.setText("请选择时间");
				} else {
					e.setEnabled(true);
					c.setText("");
				}
				break;
			case 4:
				number = Integer.parseInt(str);
				int money = Integer.parseInt(b);
				int allmoney = money * number;
				c.setText("" + allmoney);
				if (mymoney1 == -1) {
				} else if (allmoney > mymoney1) {
					e.setEnabled(false);
					d.setText("余额不足");
				} else if (allmoney == 0) {
					e.setEnabled(false);
					d.setText("请选择数量");
				} else {
					e.setEnabled(true);
					d.setText("");
				}
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	void updateservicetime(final String id, final TextView hint) {
		new Thread() {
			public void run() {
				final String result = UpdateServiceTimeService.UpdateServiceTimeByPost(id);

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						List<String> list = new ArrayList<String>();
						if (result != null) {

							try {
								JSONArray ja = new JSONArray(result);

								for (int i = 0; i < ja.length(); i++) {
									JSONObject j = (JSONObject) ja.get(i);
									String time = j.getString("time");
									if (i < 1) {
										list.add("请选择");
									}
									list.add(time);
								}

								ArrayAdapter<String> adapter = new ArrayAdapter<>(DHActivity.this,
										R.layout.spinnerstyle, list);
								adapter.setDropDownViewResource(R.layout.spinnerstyle);
								cashservicetime.setAdapter(adapter);
							} catch (Exception e) {

							}
						} else {
							hint.setText("时间获取失败,请检查网络,或点击刷新按钮!");
						}
					}
				});
			}
		}.start();
	}

	void updatemyinfo() {
		new Thread() {
			public void run() {
				final String result = UpdateInfoService.UpdateInfoByPost(id);

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (result != null) {
							try {

								JSONArray ja = new JSONArray(result);
								for (int i = 0; i < ja.length(); i++) {
									JSONObject j = (JSONObject) ja.get(i);
									String money = j.getString("money");
									String image = j.getString("image");
									DownloadBitmap(image, myimage);
									moneynumber.setText(money);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {

						}
					}
				});
			}
		}.start();
	}

	void refreshmoney(final String what, final String id, final TextView tv) {
		new Thread() {
			public void run() {
				final String result = RefreshMoneyService.RefreshMoneyByPost(what, id);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (result != null) {
							try {
								tv.setText(result);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast t3 = Toast.makeText(DHActivity.this, "请检查网络", Toast.LENGTH_SHORT);
							t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 10);
							t3.setMargin(0f, 0.5f);
							t3.show();
						}
					}
				});
			}
		}.start();
	}

	void updategoodsclass() {
		new Thread() {
			public void run() {
				final String result1 = InquireGoodsService.InquireGoodsClassByPost();

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final List<String> list1 = new ArrayList<String>();
						final List<String> list2 = new ArrayList<String>();
						final List<String> list3 = new ArrayList<String>();
						list2.add("全部");
						list2.add("1~10");
						list2.add("11~30");
						list2.add("30+");
						list3.add("全部");
						list3.add("可兑换");
						if (result1 != null) {
							try {
								ArrayAdapter<String> adapter1;
								JSONArray ja = new JSONArray(result1);
								list1.add("全部");
								for (int i = 0; i < ja.length(); i++) {
									JSONObject j = (JSONObject) ja.get(i);

									String name = j.getString("name");

									list1.add(name);
								}
								adapter1 = new ArrayAdapter<String>(DHActivity.this, R.layout.spinnerstyle, list1);
								adapter1.setDropDownViewResource(R.layout.spinnerstyle);
								mySpinner1.setAdapter(adapter1);
								adapter1 = new ArrayAdapter<String>(DHActivity.this, R.layout.spinnerstyle, list2);
								adapter1.setDropDownViewResource(R.layout.spinnerstyle);
								mySpinner2.setAdapter(adapter1);
								adapter1 = new ArrayAdapter<String>(DHActivity.this, R.layout.spinnerstyle, list3);
								adapter1.setDropDownViewResource(R.layout.spinnerstyle);
								mySpinner3.setAdapter(adapter1);
								goodsclass = true;
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast t3 = Toast.makeText(DHActivity.this, "请检查网络,并重新点击筛选按钮刷新!", Toast.LENGTH_SHORT);
							t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 10);
							t3.setMargin(0f, 0.5f);
							t3.show();
						}
					}
				});
			}
		}.start();
	}

	void goodslist(final String result) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				List<GoodsList> list = new ArrayList<GoodsList>();
				GoodsListAdapter adapter = new GoodsListAdapter(DHActivity.this);
				if (result != null) {
					try {

						JSONArray ja = new JSONArray(result);

						for (int i = 0; i < ja.length(); i++) {
							JSONObject j = (JSONObject) ja.get(i);

							String number = j.getString("number");
							String image = j.getString("image");
							String name = j.getString("name");
							String site = j.getString("site");
							String money = j.getString("money");
							String id = j.getString("id");

							list.add(new GoodsList(id, name, money, site, number, image));
						}
						adapter.setList(list);
						goodslistview.setAdapter(adapter);
						emptygoods.setText("未找到商品");
						goodslistview.setEmptyView(emptygoods);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					adapter.setList(list);
					goodslistview.setAdapter(adapter);
					emptygoods.setText("请检查网络");
					goodslistview.setEmptyView(emptygoods);
				}
			}
		});
	}

	void updategoods(final String mstyle, final String mmoney, final String mmymoney) {
		new Thread() {
			public void run() {
				final String result = InquireGoodsService.InquireGoodsByPost(mstyle, mmoney, mmymoney);

				goodslist(result);
			}
		}.start();
	}

	void updategoodsbutton(final String str) {
		new Thread() {
			public void run() {
				final String result = InquireGoodsService.SeekGoodsByPost(str);

				goodslist(result);
			}
		}.start();
	}

	void updateserviceclass() {
		new Thread() {
			public void run() {
				final String result1 = InquireServiceService.InquireServiceClassByPost();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						List<String> list = new ArrayList<>();
						ServiceNameListAdapter adapter = new ServiceNameListAdapter(DHActivity.this);
						if (result1 != null) {

							try {

								JSONArray ja = new JSONArray(result1);

								list.add("全部");
								for (int i = 0; i < ja.length(); i++) {
									JSONObject j = (JSONObject) ja.get(i);

									String name = j.getString("name");

									list.add(name);
								}
								adapter.setList(list);
								servicename.setAdapter(adapter);
								servicename.setEmptyView(emptyservicename);
								serviceclass = true;
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							adapter.setList(list);
							servicename.setAdapter(adapter);
							servicename.setEmptyView(emptyservicename);
						}
					}
				});
			}
		}.start();
	}

	void serviceslist(final String result) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				List<ServiceList> list = new ArrayList<ServiceList>();
				ServiceListAdapter adapter = new ServiceListAdapter(DHActivity.this);
				if (result != null) {
					try {

						JSONArray ja = new JSONArray(result);

						for (int i = 0; i < ja.length(); i++) {
							JSONObject j = (JSONObject) ja.get(i);

							String phone = j.getString("phone");
							String name = j.getString("name");
							String id = j.getString("id");
							String fame = j.getString("fame");
							String money = j.getString("money");
							String image = j.getString("money");

							list.add(new ServiceList(id, name, phone, money, fame, image));
						}
						adapter.setList(list);
						serviceinfo.setAdapter(adapter);
						emptyservice.setText("未找到服务");
						serviceinfo.setEmptyView(emptyservice);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					adapter.setList(list);
					serviceinfo.setAdapter(adapter);
					emptyservice.setText("请检查网络");
					serviceinfo.setEmptyView(emptyservice);
				}
			}
		});
	}

	void updateservicebutton(final String str) {
		new Thread() {
			public void run() {
				final String result = InquireServiceService.SeekServiceByPost(str);

				serviceslist(result);
			}
		}.start();
	}

	void updateservice(final String a) {
		new Thread() {
			public void run() {
				final String result = InquireServiceService.InquireServiceByPost(a);

				serviceslist(result);
			}
		}.start();
	}

	Boolean judgebankcard(String a) {
		int sumOdd = 0;
		int sumEven = 0;
		String number = a;
		int length = number.length();
		int[] wei = new int[length];
		for (int i = 0; i < number.length(); i++) {
			wei[i] = Integer.parseInt(number.substring(length - i - 1, length - i));
		}
		for (int i = 0; i < length / 2; i++) {
			sumOdd += wei[2 * i];
			if ((wei[2 * i + 1] * 2) > 9)
				wei[2 * i + 1] = wei[2 * i + 1] * 2 - 9;
			else
				wei[2 * i + 1] *= 2;
			sumEven += wei[2 * i + 1];
		}
		if ((sumOdd + sumEven) % 10 == 0)
			return true;
		else
			return false;

	}

	public void DownloadBitmap(String bmurl, final ImageView iv) {
		Bitmap bm = null;
		InputStream is = null;
		BufferedInputStream bis = null;
		try {
			URL url = new URL(bmurl);
			URLConnection connection = url.openConnection();
			bis = new BufferedInputStream(connection.getInputStream());
			bm = BitmapFactory.decodeStream(bis);
			final Bitmap bm1 = bm;

			iv.setImageBitmap(bm1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
