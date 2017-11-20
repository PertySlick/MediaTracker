package co.miniforge.corey.mediatracker;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.model.MediaItem;


/**
 * Assignment 6 - Utilizing Fragments
 */
public class MediaItemDetailFragment extends Fragment {


// FIELDS, OBJECTS, and CONSTANTS


    private MediaItem mediaItem;

    private TextView titleText, descText, urlText;
    private EditText titleEdit, descEdit, urlEdit;
    private Button save;


// CONSTRUCTOR(S)


    public MediaItemDetailFragment() {
        // Required empty public constructor
    }



// METHODS - PRIMARY


    /**
     * TODO: What does this do...?
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.fragment_media_item_detail, container, false);
        locateViews(inflated);
        return inflated;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindData();
    }

    private void locateViews(View view) {
        titleText = (TextView) view.findViewById(R.id.detail_text_title);
        descText  = (TextView) view.findViewById(R.id.detail_text_desc);
        urlText = (TextView) view.findViewById(R.id.detail_text_url);
        titleEdit = (EditText) view.findViewById(R.id.detail_input_title);
        descEdit = (EditText) view.findViewById(R.id.detail_input_desc);
        urlEdit = (EditText) view.findViewById(R.id.detail_input_url);
        save = (Button) view.findViewById(R.id.detail_button_save);
    }

    private void bindData() {
        populateFields(mediaItem);

        save.setOnClickListener(new View.OnClickListener() {


            /**
             * When "save" button is clicked, assemble EditText field values into a MediaItem and
             * then convert to a JSON Object string value for use in an intent.  Send via intent to
             * the MyListActivity activity.
             * @param view The view that triggered the onClick event
             */
            @Override
            public void onClick(View view) {
                setMediaItem();
                String jsonValue = mediaItem.toJson().toString();

                Intent intent = new Intent(getActivity().getApplicationContext(), MyListActivity.class);
                intent.putExtra(MyListActivity.mediaExtra, jsonValue);
                startActivity(intent);
            }

        });
    }


// METHODS - SUB-ROUTINES


    /**
     * Returns an instance of this fragment...?
     * @param item
     * @return fragment Instance of this fragment
     */
    public static MediaItemDetailFragment create(MediaItem item) {
        MediaItemDetailFragment fragment = new MediaItemDetailFragment();
        fragment.mediaItem = item;
        return fragment;
    }

    /**
     * Populates EditText fields with values present in the current MediaItem object.
     */
    private void populateFields(MediaItem newMedia) {
        titleEdit.setText(newMedia.title);
        descEdit.setText(newMedia.description);
        urlEdit.setText(newMedia.url);
    }

    /**
     * Sets current MediaItem object's property values to the values found in the EditText fields
     */
    private void setMediaItem() {
        mediaItem.title = titleEdit.getText().toString();
        mediaItem.description = descEdit.getText().toString();
        mediaItem.url = urlEdit.getText().toString();
    }

}
