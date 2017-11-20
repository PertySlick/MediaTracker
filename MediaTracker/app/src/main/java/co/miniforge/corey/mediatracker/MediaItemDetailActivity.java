package co.miniforge.corey.mediatracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.json_helpers.JSONExceptionLogger;
import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaItemDetailActivity extends AppCompatActivity {


// FIELDS, OBJECTS, AND CONSTANTS


    String intentText;

    EditText title;
    EditText description;
    EditText url;
    Button save;

    JSONObject jsonIntent;
    MediaItem newMedia;


// METHODS - PRIMARY


    /**
     * Initiates critical methods upon reaching onCreate state for this activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        //locateViews();
        readIntents();
        //bindFunctionality();
        createFragmentForMediaItemType(newMedia);
    }


    /**
     * If data was sent from previous activity: retrieve it, compile it into a JSON Object, put it
     * into a MediaItem object, then use it to populate the EditText fields within this activtity.
     */
    private void readIntents() {
        if (getIntent().hasExtra(MyListActivity.mediaExtra)) {
            intentText = getIntent().getStringExtra(MyListActivity.mediaExtra);

            // Proceed if conversion to JSON Object successful
            if (toJsonIntent(intentText)) {
                newMedia = new MediaItem(jsonIntent);
                //populateFields();
            }
        }
    }

    private void createFragmentForMediaItemType(MediaItem mediaItem) {
        Fragment fragment = null;

        switch (mediaItem.type) {
            case Generic:
                fragment = MediaItemDetailFragment.create(mediaItem);
                break;
            case Movie:
                break;
            case TV:
                break;
        }

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


    /**
     * Locates views within activity and assigns them to their appropriate identifier for later use
     * REMOVED AS PART OF FRAGMENT UTILIZATION
     */
//    private void locateViews() {
//        title = (EditText) findViewById(R.id.detail_input_title);
//        description = (EditText) findViewById(R.id.detail_input_desc);
//        url = (EditText) findViewById(R.id.detail_input_url);
//        save = (Button) findViewById(R.id.detail_button_save);
//    }

    /**
     * Assigns activity functionality including onClickListeners.
     * REMOVED AS PART OF FRAGMENT UTILIZATION
     */
//    private void bindFunctionality() {
//        save.setOnClickListener(new View.OnClickListener() {
//
//
//            /**
//             * When "save" button is clicked, assemble EditText field values into a MediaItem and
//             * then convert to a JSON Object string value for use in an intent.  Send via intent to
//             * the MyListActivity activity.
//             * @param view The view that triggered the onClick event
//             */
//            @Override
//            public void onClick(View view) {
//                setMediaItem();
//                String jsonValue = newMedia.toJson().toString();
//
//                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
//                intent.putExtra(MyListActivity.mediaExtra, jsonValue);
//                startActivity(intent);
//            }
//
//        });
//    }


// METHODS - SUB-ROUTINES


    // TODO - Change to employ MediaItem getters
    // TODO - Implement validation (null?)
    /**
     * Populates EditText fields with values present in the current MediaItem object.
     */
    private void populateFields() {
        title.setText(newMedia.title);
        description.setText(newMedia.description);
        url.setText(newMedia.url);
    }

    // TODO - Change to employ MediaItem setters
    // TODO - Implement input validation
    /**
     * Sets current MediaItem object's property values to the values found in the EditText fields
     */
    private void setMediaItem() {
        newMedia.title = title.getText().toString();
        newMedia.description = description.getText().toString();
        newMedia.url = url.getText().toString();
    }


    /**
     * Reusable method for attempting to create a JSON object from a supplied string.  Returns true
     * if the operation is successful, false otherwise.
     * @param jsonString String value to process into a JSON object
     * @return boolean true if successful, false otherwise
     */
    private boolean toJsonIntent(String jsonString) {
        try {
            jsonIntent = new JSONObject(jsonString);
        } catch (JSONException e) {
            JSONExceptionLogger.logError(e);
            return false;
        }
        return true;
    }
}
