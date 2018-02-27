package com.hanumanJi.hanumanChalisa;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.hanumanJi.jai.MainActivity;
import com.hanumanJi.jai.R;

public class HClyrics extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, NetworkStateReceiver.NetworkStateReceiverListener {
    /**
     * A handler object, used for deferring UI operations.
     */
    private Handler mHandler = new Handler();
    private NetworkStateReceiver networkStateReceiver;

    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);



        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

// Monitor back stack changes to ensure the action bar shows the appropriate
// button (either "photo" or "info").
        getFragmentManager().addOnBackStackChangedListener(this);


        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

// Add either a "photo" or "finish" button to the action bar, depending on which page
// is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                mShowingBack
                        ? "English"
                        : "Hindi");
        item.setTitle(mShowingBack
                ? "English"
                : "Hindi");
//item.setIcon(mShowingBack
//        ? R.drawable.ic_launcher
//        : R.drawable.icon);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_flip:
                flipCard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

// Flip to the back.

        mShowingBack = true;

// Create and commit a new fragment transaction that adds the fragment for the back of
// the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();

// Defer an invalidation of the options menu (on modern devices, the action bar). This
// can't be done immediately because the transaction may not yet be committed. Commits
// are asynchronous in that they are posted to the main thread's message loop.
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

// When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }

    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });
        return intAd;
    }

    private void loadIntAdd() {
        // Disable the  level two button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onDestroy() {

        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);

        super.onDestroy();
    }

    @Override
    public void networkAvailable() {

        interstitialAd = createNewIntAd();
        loadIntAdd();
    }

    @Override
    public void networkUnavailable() {

    }

}
