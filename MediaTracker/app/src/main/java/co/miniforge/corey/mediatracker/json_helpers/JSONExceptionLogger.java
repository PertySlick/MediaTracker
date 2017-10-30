package co.miniforge.corey.mediatracker.json_helpers;

import android.util.Log;

/**
 * Utility class for providing a reusable exception logging method for JSON Object related errors
 *
 * @author Timothy Roush
 * @version 1.0
 */

public class JSONExceptionLogger {
    /**
     * Outputs an error message template to the debug log that includes the provided error message
     * @param e Error object provided by an Exception
     */
    public static void logError(Exception e) {
        Log.e("JsonError", "There wa an error that occured: \n" + e.getMessage());
        e.printStackTrace();
    }
}
