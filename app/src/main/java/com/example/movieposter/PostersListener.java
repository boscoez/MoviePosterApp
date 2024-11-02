package com.example.movieposter;
/**
 * Listener interface for handling actions on Poster items in the RecyclerView.
 * Used to notify when there is a change in poster selection status.
 */
public interface PostersListener {
    /**
     * Called when there is a change in the selection status of a poster.
     * @param isSelected true if at least one poster is selected, false otherwise.
     */
    void onPosterAction(Boolean isSelected);
}
