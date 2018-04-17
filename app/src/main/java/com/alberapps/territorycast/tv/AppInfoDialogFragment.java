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
