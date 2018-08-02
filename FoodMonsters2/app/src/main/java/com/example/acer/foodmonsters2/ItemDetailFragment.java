package com.example.acer.foodmonsters2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    ArrayList<IngredientPojo> ingredientPojos;
    TextView textView;
    String description;
    String videourl;
    String thumbnail;
    Uri videoURI;
    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

    public ItemDetailFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (exoPlayer != null) {
            outState.putLong("pos", exoPlayer.getCurrentPosition());
            outState.putBoolean("play_back", exoPlayer.getPlayWhenReady());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("ingredients")) {
            ingredientPojos = getArguments().getParcelableArrayList("ingredients");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        }
        if (getArguments().containsKey("video")) {
            description = getArguments().getString("description");
            videourl = getArguments().getString("video");
            thumbnail = getArguments().getString("thumbnail");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        textView = (TextView) rootView.findViewById(R.id.item_detail);
        textView.setFocusable(false);
        if (getArguments().containsKey("ingredients")) {
            for (int i = 0; i < ingredientPojos.size(); i++) {
                textView.append("Ingredients:-" + ingredientPojos.get(i).getIngredient() + "\n");
                textView.append("Quantity:-" + ingredientPojos.get(i).getQuantity() + "\n");
                textView.append("Measure:-" + ingredientPojos.get(i).getMeasure() + "\n");
            }
        }
        if (getArguments().containsKey("video")) {
            textView.setText("description" + description);
            exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exo_player);
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
                exoPlayerView.setVisibility(View.VISIBLE);
                videoURI = Uri.parse(videourl);
                if (videourl.equals("")) {
                    videoURI = Uri.parse(thumbnail);
                }

                if (!(videourl.isEmpty() && thumbnail.isEmpty())) {
                    DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("likith");
                    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                    MediaSource mediasource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
                    exoPlayerView.setPlayer(exoPlayer);
                    exoPlayer.prepare(mediasource);
                    exoPlayer.setPlayWhenReady(false);
                    if (savedInstanceState != null) {
                        exoPlayer.setPlayWhenReady(savedInstanceState.getBoolean("play_back"));
                        ;
                        exoPlayer.seekTo(savedInstanceState.getLong("pos"));
                    }

                } else {
                    exoPlayerView.setVisibility(View.GONE);
                    textView.append("\nNo Video for this steps");
                }
            } catch (Exception e) {
                Log.e("Main Activity", "Error" + e.toString());
            }
        }
        return rootView;
    }

}
