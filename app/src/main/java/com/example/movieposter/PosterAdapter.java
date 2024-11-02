package com.example.movieposter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
/**
 * Adapter class for displaying a list of Poster objects in a RecyclerView.
 * Handles the binding of poster data to the RecyclerView items and selection functionality.
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private final List<Poster> posterList;
    private final PostersListener posterListener;
    /**
     * Constructor for PosterAdapter.
     * @param posterList     List of Poster objects to be displayed.
     * @param posterListener Listener interface to handle poster selection events.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener posterListener) {
        this.posterList = posterList;
        this.posterListener = posterListener;
    }
    /**
     * Inflates the layout for each item in the RecyclerView.
     * @param parent   The parent ViewGroup.
     * @param viewType The type of the new View.
     * @return A new instance of PosterViewHolder.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false);
        return new PosterViewHolder(view);
    }
    /**
     * Binds the data of a Poster object to the item at the given position.
     * @param holder   The PosterViewHolder containing views to be updated.
     * @param position The position of the item in the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));
    }
    /**
     * Returns the total number of items in the data set.
     * @return The number of items in posterList.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }
    /**
     * Retrieves a list of posters that are currently selected.
     * @return A list of selected Poster objects.
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }
    /**
     * ViewHolder class to represent each item in the RecyclerView.
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout layoutPoster;
        private final View viewBackground;
        private final RoundedImageView imagePoster;
        private final TextView textName, textCreatedBy, textStory;
        private final RatingBar ratingBar;
        private final ImageView imageSelected;
        /**
         * Constructor for PosterViewHolder.
         * @param itemView The view representing a single item in the RecyclerView.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPoster = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.TextName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }
        /**
         * Binds a Poster object to the view, updating UI components to reflect poster data.
         * @param poster The Poster object to bind to the view.
         */
        void bindPoster(final Poster poster) {
            // Set poster image, title, creator, description, and rating
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            // Update selection visuals based on poster's selection state
            updateSelectionVisuals(poster.isSelected);
            // Toggle selection state and visuals on click
            layoutPoster.setOnClickListener(v -> {
                poster.isSelected = !poster.isSelected; // Toggle selection
                updateSelectionVisuals(poster.isSelected);
                // Notify listener of the selection status
                posterListener.onPosterAction(!getSelectedPosters().isEmpty());
            });
        }
        /**
         * Updates the visual appearance of the view to indicate selection status.
         * @param isSelected true if the poster is selected, false otherwise.
         */
        private void updateSelectionVisuals(boolean isSelected) {
            if (isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
        }
    }
}
