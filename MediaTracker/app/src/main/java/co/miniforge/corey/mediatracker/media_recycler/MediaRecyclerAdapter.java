package co.miniforge.corey.mediatracker.media_recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * Created by corey on 10/15/17.
 */

public class MediaRecyclerAdapter extends RecyclerView.Adapter {
    private List<MediaItem> mediaItems = new LinkedList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new MediaViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((MediaViewHolder)holder).bindData(mediaItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public void updateList(List<MediaItem> mediaItems){
        this.mediaItems = mediaItems;
        notifyDataSetChanged();
    }
}
