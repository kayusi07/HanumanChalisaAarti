package com.hanumanJi.jai;

import com.hanumanJi.hanumanChalisa.HanumanChalisaActivity;

import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity {
	ViewPager viewPager;
	MyPagerAdapter myPagerAdapter;

	Button fast;
	Button aarti;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fast = (Button) findViewById(R.id.fast);
		aarti = (Button) findViewById(R.id.aarti);

		viewPager = (ViewPager) findViewById(R.id.pager);
		myPagerAdapter = new MyPagerAdapter();
		viewPager.setAdapter(myPagerAdapter);

		fast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this,
						HanumanChalisaActivity.class);
				startActivity(i);
			}
		});

		aarti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this, Aarti.class);
				startActivity(i);
			}
		});

	}

	private class MyPagerAdapter extends PagerAdapter {

		int NumberOfPages = 12;

		String[] res = {
				"Tulsi Das Wrote Hanuman Chalisa When He was Imprisoned by Akbar!",
				"Hanuman Chalisa Tells Us The Distance Between Earth & Sun!",
				"Hanuman was Lord Shiva's Incarnation!",
				"The name Hanuman means Disfigured Jaw!",
				"Hanuman Once Applied Sindoor All Over His Body for Lord Rama's Long Life",
				"Hanuman was born to a woman cursed to be a female monkey!",
				"His greatest feat is when he moved an entire mountain to heal Rama's brother.",
				"When Rama's time of death was at hand, Hanuman barred Yama (the god of death) from claiming him. It wasn't until Rama purposefully dropped a ring to distract Hanuman did Rama pass.",
				" His last promise to Rama is that he would stay on the earth in secret as long as the name of Rama was remembered and worshiped.",
				"Hanuman  is  a  patron  of  wrestlers.  His  name  is  chanted  before  starting  a  bout.",
				"Hanuman  is  known  as  Chiranjeevi(eternal  living).",
				"His  birthday  is  celeberated  twice.One  come  before  Diwali  and  other  in  the  month  of  april.",
				"" };
		int[] backgroundcolor = { 0xFFCC0010, 0xFFFFCC00, 0xFF669900,
				0xFF000099, 0xFF996633, 0xFFCC0010, 0xFFFFCC00, 0xFF669900,
				0xFF000099, 0xFF996633, 0xFFCC0010, 0xFFFFCC00 };

		@Override
		public int getCount() {
			return NumberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			TextView textView = new TextView(MainActivity.this);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(10);

			textView.setText("Do you know? #" + String.valueOf(position));

			TextView factsText = new TextView(MainActivity.this);
			factsText.setSingleLine(false);
			factsText.setText(res[position]);
			LayoutParams factsTextParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			factsText.setLayoutParams(factsTextParams);
			factsText.setTextColor(Color.WHITE);
			factsText.setTextSize(20);
			factsText.setGravity(Gravity.CENTER);
			factsText.setTypeface(Typeface.DEFAULT_BOLD);
			LinearLayout layout = new LinearLayout(MainActivity.this);
			layout.setOrientation(LinearLayout.VERTICAL);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			layout.setBackgroundColor(backgroundcolor[position]);

			layout.setLayoutParams(layoutParams);

			layout.addView(textView);
			layout.addView(factsText);
			// layout.addView(fast);
			fast.setBackgroundColor(backgroundcolor[position]);
			aarti.setBackgroundColor(backgroundcolor[position]);
			final int page = position;
			layout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(MainActivity.this,
							"Please swipe right or left!", Toast.LENGTH_LONG)
							.show();
				}
			});

			container.addView(layout);
			return layout;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
		}

	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}

}
