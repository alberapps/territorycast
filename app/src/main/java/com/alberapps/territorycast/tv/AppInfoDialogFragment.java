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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.GuidanceStylist.Guidance;
import android.support.v17.leanback.widget.GuidedAction;

import com.alberapps.territorycast.R;

import java.util.List;


public class AppInfoDialogFragment extends android.support.v17.leanback.app.GuidedStepFragment {

    private static final int ACTION_ID_POSITIVE = 1;
    private static final int ACTION_ID_NEGATIVE = ACTION_ID_POSITIVE + 1;

    @NonNull
    @Override
    public Guidance onCreateGuidance(Bundle savedInstanceState) {
        String title = getString(R.string.app_name) + " " + getString(R.string.app_version) + "\n" + getString(R.string.app_autor);
        String description = getString(R.string.app_autor_mail) + "\n" + getString(R.string.app_autor_web)
                + "\n" + getString(R.string.app_licencia_desc) + " " + getString(R.string.app_licencia);

/*                "\n\n" + getString(R.string.referencias) + "\n" + getString(R.string.referencias_infoapp)
                + "\n\n" + getString(R.string.detalle_licencia)
                + "\n\n" + getString(R.string.legal_b)
                + "\n\n" + getString(R.string.aviso_legal_analytics);*/
        Guidance guidance = new Guidance(title,
                description,
                "", null);
        return guidance;
    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {
        GuidedAction action = new GuidedAction.Builder()
                .id(ACTION_ID_POSITIVE)
                .title(getString(android.R.string.ok)).build();
        actions.add(action);
        /*action = new GuidedAction.Builder()
                .id(ACTION_ID_NEGATIVE)
                .title(getString(R.string.dialog_example_button_negative)).build();
        actions.add(action);*/
    }

    @Override
    public void onGuidedActionClicked(GuidedAction action) {
        /*if (ACTION_ID_POSITIVE == action.getId()) {
            Toast.makeText(getActivity(), R.string.dialog_example_button_toast_positive_clicked,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.dialog_example_button_toast_negative_clicked,
                    Toast.LENGTH_SHORT).show();
        }*/
        getActivity().finish();
    }
}
