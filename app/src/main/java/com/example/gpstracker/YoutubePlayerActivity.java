package com.example.gpstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity implements View.OnClickListener {
  YouTubePlayerView playerView;
  Button goToMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        goToMap =findViewById(R.id.got_to_map);
        goToMap.setOnClickListener(this);
        playerView=findViewById(R.id.youtube_view);
        playerView.initialize(getString(R.string.google_maps_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored){
                    youTubePlayer.cueVideo("u2-Jg_PlSVY");
                  //  youTubePlayer.cuePlaylist("PLHnOjIp2R4CbpePeNEALNe-LNNorqRDaC");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(YoutubePlayerActivity.this,MainActivity.class);
        startActivity(intent);
    }
}