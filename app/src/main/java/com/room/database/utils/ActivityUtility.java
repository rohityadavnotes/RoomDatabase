package com.room.database.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ActivityUtility {

    public static void goToNextActivity(Activity currentActivity, Class nextActivity) {
        Intent goToNextActivity = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(goToNextActivity);
    }

    public static void goToNextActivityClearBackStack(Activity currentActivity, Class nextActivity) {
        Intent goToNextActivity = new Intent(currentActivity, nextActivity);
        goToNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        currentActivity.startActivity(goToNextActivity);
        currentActivity.finish();
    }

    public static void goToNextActivity(Activity currentActivity, Class nextActivity, Bundle bundle) {
        Intent goToNextActivity = new Intent(currentActivity, nextActivity);
        goToNextActivity.putExtra("BUNDLE", bundle);
        currentActivity.startActivity(goToNextActivity);
    }

    public static void goToNextActivityClearBackStack(Activity currentActivity, Class nextActivity, Bundle bundle) {
        Intent goToNextActivity = new Intent(currentActivity, nextActivity);
        goToNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        goToNextActivity.putExtra("BUNDLE", bundle);
        currentActivity.startActivity(goToNextActivity);
        currentActivity.finish();
    }

    public static Bundle getDataFromPreviousActivity(Activity currentActivity) {
        Intent data = currentActivity.getIntent();
        return data.getBundleExtra("BUNDLE");
    }

    /**
     * Hides the activity's action bar
     *
     * @param activity the activity
     */
    public static void hideActionBar(Activity activity) {
        /*
         * Call before calling setContentView();
         */
        if (activity != null) {
            activity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            if (activity.getActionBar() != null) {
                activity.getActionBar().hide();
            }
        }
    }

    /**
     * Sets the activity in Fullscreen mode
     *
     * @param activity the activity
     */
    public static void setFullScreen(Activity activity) {
        /*
         * Call before calling setContentView();
         */
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private ActivityUtility() {
        throw new UnsupportedOperationException("Should not create instance of Utility class. Please use as static..");
    }
}
