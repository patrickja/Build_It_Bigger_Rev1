package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.jokedisplayactivity.DisplayJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  public String loadedJoke = null;
  public boolean testFlag = false;
  ProgressBar progressBar = null;

  public MainActivityFragment() {
  }

  @Override
  public View onCreateView(
          LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_main, container, false);

    AdView mAdView = (AdView) root.findViewById(R.id.adView);

    // Set onClickListener for the button
    Button button = (Button) root.findViewById(R.id.joke_button);
    button.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
              }
        });

    progressBar = (ProgressBar) root.findViewById(R.id.joke_progressbar);
    progressBar.setVisibility(View.GONE);

    AdRequest adRequest =
            new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
    mAdView.loadAd(adRequest);
    return root;
  }

  public void getJoke() {
    new EndpointAsyncTask().execute(this);
  }

  public void launchJokeDisplayActivity() {
    if (!testFlag) {
      Context context = getActivity();
      Intent intent = new Intent(context, DisplayJokeActivity.class);
      intent.putExtra(context.getString(R.string.jokeEnvelope), loadedJoke);
      // Toast.makeText(context, loadedJoke, Toast.LENGTH_LONG).show();
      context.startActivity(intent);
      progressBar.setVisibility(View.GONE);
    }
  }
}
