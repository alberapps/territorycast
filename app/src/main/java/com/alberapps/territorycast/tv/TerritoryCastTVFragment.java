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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alberapps.territorycast.R;
import com.alberapps.territorycast.tasks.LoadProgramasAsyncTask;
import com.alberapps.territorycast.uamp.ui.tv.TvBrowseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TerritoryCastTVFragment extends BrowseFragment {
    private static final String TAG = "TerritoryCastTVFragment";

    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private static final int GRID_ITEM_WIDTH = 200;
    private static final int GRID_INFO_WIDTH = 500;
    private static final int GRID_ITEM_HEIGHT = 200;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 15;

    private final Handler mHandler = new Handler();
    private ArrayObjectAdapter mRowsAdapter;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private String mBackgroundUri;
    private BackgroundManager mBackgroundManager;
    public List<com.alberapps.territorycast.programas.Programa> programasList = null;


    private boolean limite = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        prepareBackgroundManager();

        setupUIElements();

        consultarProgramas();
        //loadRows();

        setupEventListeners();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mBackgroundTimer) {
            Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString());
            mBackgroundTimer.cancel();
        }
    }

    /**
     * Cargar el listado de programas almacenados
     *

     */
    @SuppressLint("NewApi")
    public void consultarProgramas() {

        //TODO provisional con carga sin BD

        LoadProgramasAsyncTask.LoadProgramasAsyncTaskResponder loadProgramasAsyncTaskResponder = new LoadProgramasAsyncTask.LoadProgramasAsyncTaskResponder() {
            public void ProgramasLoaded(List<com.alberapps.territorycast.programas.Programa> programas) {

                if (programas != null && !programas.isEmpty()) {

                    limite = true;

                    programasList = programas;

                    loadRows();

                } else {

                    //noticiasRss = null;
                    // Error al recuperar datos
                    //cargarListadoRss();

                    loadRows();

                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.sinprogramas), Toast.LENGTH_LONG).show();


                }


            }
        };


        new LoadProgramasAsyncTask(loadProgramasAsyncTaskResponder).execute(getContext());


    }


    private void loadRows() {

        List<Programa> list = null;

        if(programasList != null && !programasList.isEmpty()) {
            list = ProgramaList.setupProgramas(programasList);
        }

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        CardPresenter cardPresenter = new CardPresenter();

        int i;

        //int rows = NUM_ROWS;
        //int cols = NUM_COLS;
        int rows = 1;
        int cols = 1;

        HeaderItem gridHeader1 = new HeaderItem(0, getString(R.string.app_name));

        GridInfoPresenter mGridPresenter1 = new GridInfoPresenter();
        ArrayObjectAdapter gridRowAdapter1 = new ArrayObjectAdapter(mGridPresenter1);
        //gridRowAdapterPref.add(getResources().getString(R.string.grid_view));
        //gridRowAdapterPref.add(getString(R.string.error_fragment));
        gridRowAdapter1.add(getString(R.string.en_desarrollo_1) + "\n" + getString(R.string.en_desarrollo_2));
        //gridRowAdapter1.add(getString(R.string.nuevo_rss));
        mRowsAdapter.add(new ListRow(gridHeader1, gridRowAdapter1));


        if(list != null && !list.isEmpty()) {

            for (i = 0; i < rows; i++) {
                if (i != 0) {
                    Collections.shuffle(list);
                }
                ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
                for (int j = 0; j < cols; j++) {
                    listRowAdapter.add(list.get(j));
                }
                //HeaderItem header = new HeaderItem(i+1, ProgramaList.MOVIE_CATEGORY[i]);
                HeaderItem header = new HeaderItem(i + 1, getString(R.string.menu_programas));
                mRowsAdapter.add(new ListRow(header, listRowAdapter));
            }

        } else {
            i=1;
        }

        /*int i = 0;

        HeaderItem gridHeader = new HeaderItem(i, "PREFERENCES");

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        //gridRowAdapter.add(getResources().getString(R.string.grid_view));
        gridRowAdapter.add(getString(R.string.error_fragment));

        //gridRowAdapter.add(getResources().getString(R.string.personal_settings));
        mRowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));
*/
        //int i = 0;

        HeaderItem gridHeaderPref = new HeaderItem(i+1, getString(R.string.aplicacion));

        GridItemPresenter mGridPresenterPref = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapterPref = new ArrayObjectAdapter(mGridPresenterPref);
        //gridRowAdapterPref.add(getResources().getString(R.string.grid_view));
        //gridRowAdapterPref.add(getString(R.string.error_fragment));
        gridRowAdapterPref.add(getResources().getString(R.string.nuevo_programa));
        gridRowAdapterPref.add(getResources().getString(R.string.appinfo));

        mRowsAdapter.add(new ListRow(gridHeaderPref, gridRowAdapterPref));

        setAdapter(mRowsAdapter);
    }

    private void prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Implement your own in-app search", Toast.LENGTH_LONG)
                        .show();
            }
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    protected void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(getActivity())
                .load(uri)
                .centerCrop()
                .error(mDefaultBackground)
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable>
                                                        glideAnimation) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Programa) {
                Programa programa = (Programa) item;
                Log.d(TAG, "Item: " + item.toString());
                //Intent intent = new Intent(getActivity(), TVDetailsActivity.class);
                //intent.putExtra(TVDetailsActivity.MOVIE, programa);

                Intent intent = new Intent(getActivity(), TvBrowseActivity.class);
                //intent.putExtra(TVDetailsActivity.MOVIE, programa);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
                        TVDetailsActivity.SHARED_ELEMENT_NAME).toBundle();
                getActivity().startActivity(intent, bundle);
            } else if (item instanceof String) {

                if (((String) item).contains(getString(R.string.appinfo))) {
                    Intent intent = new Intent(getActivity(), AppInfoDialogActivity.class);
                    startActivity(intent);
                } else if (((String) item).contains(getString(R.string.nuevo_programa))) {
                    //Intent intent = new Intent(getActivity(), AppInfoDialogActivity.class);
                    //startActivity(intent);

                    if(!limite) {
                        //NuevoPrograma nuevoPrograma = new NuevoPrograma(getActivity());
                        //nuevoPrograma.cargarModalNuevoPrograma();

                        Intent intent = new Intent(getActivity(), NuevoDialogActivity.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.en_desarrollo), Toast.LENGTH_LONG).show();
                    }

                }



                /*if (((String) item).contains(getString(R.string.error_fragment))) {
                    Intent intent = new Intent(getActivity(), BrowseErrorActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT)
                            .show();
                }*/

            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Programa) {
                mBackgroundUri = ((Programa) item).getBackgroundImageUrl();
                startBackgroundTimer();
            }
        }
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updateBackground(mBackgroundUri);
                }
            });
        }
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

    private class GridInfoPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_INFO_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}
