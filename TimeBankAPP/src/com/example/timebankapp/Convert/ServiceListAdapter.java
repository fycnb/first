package com.example.timebankapp.Convert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.timebankapp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceListAdapter extends BaseAdapter {

	private List<ServiceList> list = new ArrayList<>();
	private LayoutInflater inflater;
	String imagepath;

	public ServiceListAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
	}

	public void setList(List<ServiceList> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int i) {
		return list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;

		if (view == null) {
			view = inflater.inflate(R.layout.serviceinfolistview, null);
			holder = new ViewHolder();

			holder.image = (ImageView) view.findViewById(R.id.serviceimage_ImageView);
			holder.name = (TextView) view.findViewById(R.id.peopleorplace_TextView);
			holder.phone = (TextView) view.findViewById(R.id.phone_TextView);
			holder.money = (TextView) view.findViewById(R.id.servicemoney_TextView);
			holder.fame = (TextView) view.findViewById(R.id.fame_TextView);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		DownloadBitmap(list.get(i).getimage(), holder.image);
		// holder.image.setImageBitmap(getImage("https://img1.doubanio.com//mpic//s28023953.jpg"));
		holder.name.setText(list.get(i).getname());
		holder.phone.setText(list.get(i).getphone());
		holder.money.setText(list.get(i).getmoney());
		holder.fame.setText(list.get(i).getfame());

		return view;
	}

	public class ViewHolder {
		ImageView image;
		TextView name;
		TextView phone;
		TextView money;
		TextView fame;
	}

	// public static Bitmap getImage(String path){
	//
	// try {
	// HttpURLConnection conn = (HttpURLConnection) new
	// URL(path).openConnection();
	// conn.setConnectTimeout(5000);
	// conn.setRequestMethod("GET");
	// if(conn.getResponseCode() == 200){
	// InputStream inputStream = conn.getInputStream();
	// Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
	// return bitmap;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

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
