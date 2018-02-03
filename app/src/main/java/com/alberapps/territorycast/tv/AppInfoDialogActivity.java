package com.alberapps.territorycast.tv;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v17.leanback.app.GuidedStepFragment;


public class AppInfoDialogActivity extends Activity{

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#21272A")));

        if (savedInstanceState == null) {
            GuidedStepFragment fragment = new AppInfoDialogFragment();
            GuidedStepFragment.addAsRoot(this, fragment, android.R.id.content);
        }
    }


}
