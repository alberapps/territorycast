/**
 *  TerritoryCast
 *  Copyright (C) 2018 Alberto Montiel
 *
 *  based on Android Samples Copyright (C) 2014 The Android Open Source Project
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alberapps.territorycast.tv;

import android.os.Bundle;
import android.support.v17.leanback.app.VideoSupportFragment;
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerGlue;
import android.support.v17.leanback.media.PlaybackGlue;

/**
 * Handles video playback with media controls.
 */
public class PlaybackVideoFragment extends VideoSupportFragment {
    private static final String TAG = "PlaybackVideoFragment";

    private MediaPlayerGlue mTransportControlGlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Programa programa = (Programa) getActivity()
                .getIntent().getSerializableExtra(TVDetailsActivity.MOVIE);

        VideoSupportFragmentGlueHost glueHost =
                new VideoSupportFragmentGlueHost(PlaybackVideoFragment.this);

        mTransportControlGlue = new MediaPlayerGlue(getActivity());
        mTransportControlGlue.setMode(MediaPlayerGlue.NO_REPEAT);
        mTransportControlGlue.setHost(glueHost);
        mTransportControlGlue.setTitle(programa.getTitle());
        mTransportControlGlue.setArtist(programa.getDescription());
        mTransportControlGlue.addPlayerCallback(
                new PlaybackGlue.PlayerCallback() {
                    @Override
                    public void onPreparedStateChanged(PlaybackGlue glue) {
                        if (glue.isPrepared()) {
                            glue.play();
                        }
                    }
                });
        mTransportControlGlue.setVideoUrl(programa.getVideoUrl());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTransportControlGlue != null) {
            mTransportControlGlue.pause();
        }
    }
}