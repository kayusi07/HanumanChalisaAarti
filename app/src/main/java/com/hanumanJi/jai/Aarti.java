package com.hanumanJi.jai;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.hanumanJi.jai.R.id.coordinatorLayout;

@SuppressLint("NewApi")
public class Aarti extends AppCompatActivity {
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
	private CoordinatorLayout coordinatorLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_play_aarti);

		startTimeField = (TextView) findViewById(R.id.textView1A);
		endTimeField = (TextView) findViewById(R.id.textView2A);
		seekbar = (SeekBar) findViewById(R.id.seekBar1A);
		playButton = (ImageButton) findViewById(R.id.imageButton1A);
		pauseButton = (ImageButton) findViewById(R.id.imageButton2A);
		mediaPlayer = MediaPlayer.create(this, R.raw.hanuman_ji_aarti);
		seekbar.setClickable(false);
		pauseButton.setEnabled(false);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

	}

	@SuppressLint("NewApi")
	public void play(View view) {

		Snackbar snackbar = Snackbar
				.make(coordinatorLayout, "Playing Hanuman Aarti", Snackbar.LENGTH_LONG);

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
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
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
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
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
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
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
			textView.setTextColor(Color.YELLOW);
			snackbar.show();
		}

	}

	@Override
	protected void onDestroy() {
		try {
			mediaPlayer.release();
			mediaPlayer = null;
		} catch (Exception e) {

		}
		super.onDestroy();
	}
}