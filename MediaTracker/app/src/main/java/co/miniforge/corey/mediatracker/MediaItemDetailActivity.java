package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.media_recycler.MediaViewHolder;
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


// METHODS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        locateViews();
        readIntents();
        bindFunctionality();
    }


    /**
     *
     */
    private void readIntents() {
        if (getIntent().hasExtra(MyListActivity.mediaExtra)) {
            intentText = getIntent().getStringExtra(MyListActivity.mediaExtra);

            // TODO - Remove commented chunk if separate method is successful
//            try {
//                jsonIntent = new JSONObject(intentText);
//            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                return;
//            }

            if (toJsonIntent(intentText)) {
                newMedia = new MediaItem(jsonIntent);
                populateFields();
            }
        }
    }


    /**
     *
     */
    private void locateViews() {
        title = (EditText) findViewById(R.id.detail_input_title);
        description = (EditText) findViewById(R.id.detail_input_desc);
        url = (EditText) findViewById(R.id.detail_input_url);
        save = (Button) findViewById(R.id.detail_button_save);
    }

    /**
     *
     */
    private void bindFunctionality() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMediaItem();
                String jsonValue = newMedia.toJson().toString();

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                intent.putExtra(MyListActivity.mediaExtra, jsonValue);
                startActivity(intent);
            }
        });
    }


    // TODO - Change to employ MediaItem getters
    /**
     *
     */
    private void populateFields() {
        title.setText(newMedia.title);
        description.setText(newMedia.description);
        url.setText(newMedia.url);
    }

    // TODO - Change to employ MediaItem setters
    /**
     *
     */
    private void setMediaItem() {
        newMedia.title = title.getText().toString();
        newMedia.description = description.getText().toString();
        newMedia.url = url.getText().toString();
    }


    /**
     * Reusable method for attempting to create a JSON object from a supplied string.  Returns true
     * if the operation is successfyl, false otherwise.
     * @param jsonString String value to process into a JSON object
     * @return boolean true if successful, false otherwise
     */
    private boolean toJsonIntent(String jsonString) {
        try {
            jsonIntent = new JSONObject(jsonString);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
