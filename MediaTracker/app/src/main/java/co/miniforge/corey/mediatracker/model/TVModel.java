package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 */

public class TVModel extends MediaItem {


//    FIELDS


    public int currentEpisodesWatched;
    public int totalEpisodes;


//    CONSTRUCTOR


    public TVModel(JSONObject jsonObject) {
        super(jsonObject);

//            currentEpisodesWatched = jsonObject.getInt("currentEpisodesWatched");
//            totalEpisodes = jsonObject.getInt("totalEpisodes");
    }


    public JSONObject toJson() {
        JSONObject result = super.toJson();

        try {
            result.put("currentEpisodesWatched", currentEpisodesWatched);
            result.put("totalEpisodes", totalEpisodes);
        } catch (Exception e) {
//            TODO:
        }

        return result;
    }
}
