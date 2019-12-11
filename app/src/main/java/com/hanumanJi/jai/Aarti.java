package com.hanumanJi.jai;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hanumanJi.hanumanChalisa.NetworkStateReceiver;

@SuppressLint("NewApi")
public class Aarti extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener{
	public TextView songName, startTimeField, endTimeField;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private ImageButton playButton, pauseButton;
	public static int oneTimeOnly = 0;
	AdView mAdView;
	private CoordinatorLayout coordinatorLayout;
	private NetworkStateReceiver networkStateReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_play_aarti);

		networkStateReceiver = new NetworkStateReceiver();
		networkStateReceiver.addListener(this);
		this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

		startTimeField = (TextView) findViewById(R.id.textView1A);
		endTimeField = (TextView) findViewById(R.id.textView2A);
		seekbar = (SeekBar) findViewById(R.id.seekBar1A);
		playButton = (ImageButton) findViewById(R.id.b_play);
		pauseButton = (ImageButton) findViewById(R.id.b_pause);
		mediaPlayer = MediaPlayer.create(this, R.raw.hanuman_ji_aarti);
		seekbar.setClickable(false);
		pauseButton.setEnabled(false);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
		Button alyrics = (Button) findViewById(R.id.aartiLyrics);
		alyrics.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Aarti.this, AartiLyrics.class);
				startActivity(i);
			}
		});

		mAdView = (AdView) findViewById(R.id.adView);

	}

	@SuppressLint("NewApi")
	public void play(View view) {

		Snackbar snackbar = Snackbar
				.make(coordinatorLayout, "Playing Hanuman Aarti", Snackbar.LENGTH_LONG);

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
		textView.setTextColor(Color.YELLOW);
		snackbar.show();


		mediaPlayer.start();
		finalTime = mediaPlayer.getDuration();
		startTime = mediaPlayer.getCurrentPosition();
		if (oneTimeOnly == 0) {
			seekbar.setMax((int) finalTime);
			oneTimeOnly = 1;
		}

		endTimeField.setText(String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
				TimeUnit.MILLISECONDS.toSeconds((long) finalTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes((long) finalTime))));
		startTimeField.setText(String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes((long) startTime),
				TimeUnit.MILLISECONDS.toSeconds((long) startTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes((long) startTime))));
		seekbar.setProgress((int) startTime);
		myHandler.postDelayed(UpdateSongTime, 100);
		pauseButton.setEnabled(true);
		playButton.setEnabled(false);
	}

	private Runnable UpdateSongTime = new Runnable() {
		public void run() {
			if (mediaPlayer != null) {
				startTime = mediaPlayer.getCurrentPosition();
				startTimeField.setText(String.format(
						"%d min, %d sec",
						TimeUnit.MILLISECONDS.toMinutes((long) startTime),
						TimeUnit.MILLISECONDS.toSeconds((long) startTime)
								- TimeUnit.MINUTES
										.toSeconds(TimeUnit.MILLISECONDS
												.toMinutes((long) startTime))));
				seekbar.setProgress((int) startTime);
				myHandler.postDelayed(this, 100);
			}
		}
	};

	public void pause(View view) {
		Snackbar snackbar = Snackbar
				.make(coordinatorLayout, "Pausing Hanuman Aarti", Snackbar.LENGTH_LONG);

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
		textView.setTextColor(Color.YELLOW);
		snackbar.show();


		mediaPlayer.pause();
		pauseButton.setEnabled(false);
		playButton.setEnabled(true);
	}

	public void forward(View view) {
		int temp = (int) startTime;
		if ((temp + forwardTime) <= finalTime) {
			startTime = startTime + forwardTime;
			mediaPlayer.seekTo((int) startTime);
		} else {
			Snackbar snackbar = Snackbar
					.make(coordinatorLayout, "Cannot jump forward 5 seconds", Snackbar.LENGTH_LONG);

			View sbView = snackbar.getView();
			TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
			textView.setTextColor(Color.YELLOW);
			snackbar.show();

		}

	}

	public void rewind(View view) {
		int temp = (int) startTime;
		if ((temp - backwardTime) > 0) {
			startTime = startTime - backwardTime;
			mediaPlayer.seekTo((int) startTime);
		} else {

			Snackbar snackbar = Snackbar
					.make(coordinatorLayout, "Cannot jump backward 5 seconds", Snackbar.LENGTH_LONG);

			View sbView = snackbar.getView();
			TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
			textView.setTextColor(Color.YELLOW);
			snackbar.show();
		}

	}

	@Override
	protected void onDestroy() {
		networkStateReceiver.removeListener(this);
		this.unregisterReceiver(networkStateReceiver);
		if (mAdView != null) {
			mAdView.destroy();
		}
		try {
			mediaPlayer.release();
			mediaPlayer = null;
		} catch (Exception e) {

		}
		super.onDestroy();
	}

	@Override
	public void networkAvailable() {

		mAdView.setVisibility(View.VISIBLE);
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.loadAd(adRequest);
	}

	@Override
	public void networkUnavailable() {
		mAdView.setVisibility(View.GONE);
	}

	@Override
	public void onPause() {
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

}