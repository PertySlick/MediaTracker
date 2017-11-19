package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import co.miniforge.corey.mediatracker.R;

/**
 * Assignment 6 - UI Themes
 * @author Tim Roush
 * @version 1.0
 */

public class ThemeHelper {


// FIELDS, OBJECTS, and CONSTANTS


    private SharedPreferences sharedPref;
    private int themeDarkText;
    private int themeDarkContainer;
    private int themeDarkBg;
    private int themeLightText;
    private int themeLightContainer;
    private int themeLightBg;


// CONSTRUCTOR(S)


    public ThemeHelper(Context context) {
        // Retrieve and store color values
        try {
            themeDarkText = ContextCompat.getColor(context, R.color.themeDarkText);
            themeDarkContainer = ContextCompat.getColor(context, R.color.themeDarkContainer);
            themeDarkBg = ContextCompat.getColor(context, R.color.themeDarkBg);
            themeLightText = ContextCompat.getColor(context, R.color.themeLightText);
            themeLightContainer = ContextCompat.getColor(context, R.color.themeLightContainer);
            themeLightBg = ContextCompat.getColor(context, R.color.themeLightBg);
        } catch (Exception e) {
            Log.e("Color Error", "There was an error setting colors:\n" + e.getMessage());
        }

        // TODO: Retrieve theme preferences...?
        sharedPref = context.getSharedPreferences("theme", 0);
    }


// METHODS


    /**
     * Determine if the dark theme is currently enabled or not.
     * @return true
     */
    public boolean darkThemeEnabled() {
        //TODO - How does this work???
        return sharedPref.getBoolean("darkTheme", false);
    }

    /**
     * Enable or disable the dark theme
     * @param enabled boolean toggle for enabling or disabling the dark theme
     */
    public void enableDarkTheme(boolean enabled) {
        sharedPref.edit().putBoolean("darkTheme", enabled).apply();
    }

    /**
     * Set each TextView element to the light or dark theme based on the
     * boolean "dark".
     * @param textViews Array or sequence of TextView elements
     */
    public void themeTextView(List<TextView> textViews) {
        boolean dark = darkThemeEnabled();

        // Apply appropriate theme to each TextView element
        for (TextView textView : textViews) {
            textView.setTextColor(dark ? themeDarkText : themeLightText);
        }
    }

    /**
     * Set each View element to the light or dark background based on the
     * boolean "dark".
     * @param containers Array or sequence of TextView elements
     */
    public void themeImageContainer(View... containers) {
        boolean dark = darkThemeEnabled();

        // Apply appropriate theme to each View element
        for (View view : containers) {
            view.setBackgroundColor(dark ? themeDarkContainer : themeLightContainer);
        }
    }

    // Set main background color based on the boolean "dark".
    public void themeBackground(View rootView) {
        boolean dark = darkThemeEnabled();

        rootView.setBackgroundColor(dark ? themeDarkBg : themeLightBg);
    }
}