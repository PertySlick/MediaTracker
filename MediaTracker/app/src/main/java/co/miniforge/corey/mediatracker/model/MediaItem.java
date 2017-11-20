package co.miniforge.corey.mediatracker.model;

import android.util.Log;

import org.json.JSONObject;

import java.security.MessageDigest;

import co.miniforge.corey.mediatracker.media_store.Md5IdHelper;

/**
 * Created by corey on 10/20/17.
 */

public class MediaItem {


// FIELDS, OBJECTS, CONSTANTS


    public String id;
    public String title;
    public String description;
    public String url;

//    TODO:  ENUM to store the types we support
    public MediaItemType type = MediaItemType.Generic;

    public MediaItem(JSONObject jsonObject){
        try{
            //Generate id based on the object instance (should work :D)
            this.id = jsonObject.getString("id");
            this.title = jsonObject.getString("title");
            this.description = jsonObject.getString("description");
            this.url = jsonObject.getString("url");

            this.type = getTypeForString(jsonObject.getString( "type" ));
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }
    }

    public MediaItem(){
        this.id = Md5IdHelper.idForObject(this);
        this.title = "Default Title";
        this.description = "Default Description";
        this.url = "Default URL";
    }

    /**
     * Special constructor for setting item type during creation
     * @param type MediaItemType value
     */
    public MediaItem(MediaItemType type) {
        this();
        this.type = type;
        this.title = "Default " + getStringForType(type) + " Title";
    }

    public MediaItemType getTypeForString(String value) {
        switch (value) {
            case "TV":
                return MediaItemType.TV;
            case "Movie":
                return MediaItemType.Movie;
            default:
                return MediaItemType.Generic;
        }
    }

    public String getStringForType (MediaItemType type) {
        switch (type) {
            case Movie:
                return "Movie";
            case TV:
                return "TV";
            default:
                return "Generic";
        }
    }

    public JSONObject toJson(){
        JSONObject mediaItem = new JSONObject();

        try{
            mediaItem.put("id", this.id);
            mediaItem.put("title", this.title);
            mediaItem.put("description", this.description);
            mediaItem.put("url", this.url);

            mediaItem.put("type", this.type);
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }

        return mediaItem;
    }


    @Override
    public String toString() {
        return "Media Item: " + this.id + " (" + this.title + ")";
    }
}
