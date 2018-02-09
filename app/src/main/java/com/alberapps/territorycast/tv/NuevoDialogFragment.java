/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.alberapps.territorycast.tv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.GuidanceStylist.Guidance;
import android.support.v17.leanback.widget.GuidedAction;

import com.alberapps.territorycast.R;
import com.alberapps.territorycast.programas.*;

import java.util.List;


public class NuevoDialogFragment extends android.support.v17.leanback.app.GuidedStepFragment {


    private static final int ACTION_ID_POSITIVE = 1;
    private static final int ACTION_ID_NEGATIVE = ACTION_ID_POSITIVE + 1;
    private static final int ACTION_ID_TITULO = 3;
    private static final int ACTION_ID_URLRSS = 4;
    private static final int ACTION_ID_URLPODCAST = 5;

    @NonNull
    @Override
    public Guidance onCreateGuidance(Bundle savedInstanceState) {
        String title = getString(R.string.nuevo_programa);
        String description = getString(R.string.nuevo_programa_desc);

        Guidance guidance = new Guidance(title,
                description,
                "", null);
        return guidance;
    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {

        actions.add(new GuidedAction.Builder(getActivity())
                .id(ACTION_ID_TITULO)
                .title(R.string.titulo)
                .editTitle("")
                .description(R.string.titulo)
                .editDescription(R.string.titulo)
                .editable(true)
                .build()
        );

        actions.add(new GuidedAction.Builder(getActivity())
                .id(ACTION_ID_URLPODCAST)
                .title(R.string.urlPodcast)
                .editTitle("")
                .description(R.string.urlPodcast)
                .editDescription(R.string.urlPodcast)
                .editable(true)
                .build()
        );


        GuidedAction action = new GuidedAction.Builder()
                .id(ACTION_ID_POSITIVE)
                .title(getString(android.R.string.ok)).build();


        actions.add(action);
        /*action = new GuidedAction.Builder()
                .id(ACTION_ID_NEGATIVE)
                .title(getString(android.R.string.cancel)).build();
        actions.add(action);*/
    }

    //@Override
    /*public void onCreateButtonActions(@NonNull List<GuidedAction> actions,
                                      Bundle savedInstanceState) {
        actions.add(new GuidedAction.Builder()
                .clickAction(GuidedAction.ACTION_ID_OK)
                .build()
        );
        actions.get(actions.size() - 1).setEnabled(false);
    }*/

    @Override
    @SuppressLint("NewApi")
    public void onGuidedActionClicked(GuidedAction action) {
        if (ACTION_ID_POSITIVE == action.getId()) {

            CharSequence titulo = findActionById(ACTION_ID_TITULO).getDescription();
            CharSequence urlPodcast = findActionById(ACTION_ID_URLPODCAST).getDescription();

            ProgramasManager programasManager = new ProgramasManager(getContext());

            com.alberapps.territorycast.programas.Programa programa = new com.alberapps.territorycast.programas.Programa();

            programa.setNombre(titulo.toString());
            programa.setUrlRssNoticias("");
            programa.setUrlRssPodcast(urlPodcast.toString());

            programa.setDescripcion("");
            programa.setImg("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Orion_Watching_Over_ALMA.jpg/1024px-Orion_Watching_Over_ALMA.jpg");

            programasManager.savePrograma(programa);

            getActivity().finish();

        } else {
            //Toast.makeText(getActivity(), R.string.dialog_example_button_toast_negative_clicked,
              //      Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public long onGuidedActionEditedAndProceed(GuidedAction action) {


        if (action.getId() == ACTION_ID_TITULO) {
            CharSequence titulo = action.getEditTitle();
            action.setDescription(titulo);
        } else if (action.getId() == ACTION_ID_URLPODCAST) {
            CharSequence podcast = action.getEditTitle();
            action.setDescription(podcast);
        }


        return GuidedAction.ACTION_ID_CURRENT;
    }

}
