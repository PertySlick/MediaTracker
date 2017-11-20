package co.miniforge.corey.mediatracker;

import android.view.MenuItem;
import android.widget.PopupMenu;

import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;

/**
 * Assignment 6 - Select Type on creation
 * @author Tim Roush
 * @version 1.0
 */

public class AddPopUpMenuHelper implements PopupMenu.OnMenuItemClickListener {


// FIELDS, OBJECTS, and CONSTANTS


    private MyListActivity activity;


// CONSTRUCTOR(S)


    public AddPopUpMenuHelper(MyListActivity activity) {
        this.activity = activity;
    }


// METHODS


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.generic:
                activity.addMediaItem(new MediaItem(MediaItemType.Generic));
                return true;
            case R.id.movie:
                activity.addMediaItem(new MediaItem(MediaItemType.Movie));
                return true;
            case R.id.tv:
                activity.addMediaItem(new MediaItem(MediaItemType.TV));
                return true;
        }
        return false;
    }
}
