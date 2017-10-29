package co.miniforge.corey.mediatracker.json_helpers;

import android.util.Log;

/**
 * Created by Perty on 10/26/2017.
 */

public class JSONExceptionLogger {

    public static void logError(Exception e) {
        Log.e("JsonError", "There wa an error that occured: \n" + e.getMessage());
        e.printStackTrace();
    }



}
