package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity class that displays a list of movie posters in a RecyclerView.
 * Allows users to select posters and manage a watchlist.
 */
public class MainActivity extends AppCompatActivity implements PostersListener {
    private Button buttonAddToWatchlist;

    /**
     * Called when the activity is first created.
     * Initializes UI components, sets up the RecyclerView with movie posters,
     * and manages the watchlist button visibility.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the "Add to Watchlist" button
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);
        // Set the color for the status bar and button background
        getWindow().setStatusBarColor(getResources().getColor(R.color.primaryColor));
        buttonAddToWatchlist.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        // Adjust layout for system bars like the status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize the RecyclerView and set up the adapter with poster data
        RecyclerView posterRecyclerView = findViewById(R.id.posterRecyclerView);
        List<Poster> posterList = createPosterList(); // Get a list of poster objects
        PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        posterRecyclerView.setAdapter(posterAdapter);

        // Set up click listener for the "Add to Watchlist" button
        buttonAddToWatchlist.setOnClickListener(v -> {
            List<Poster> selectedPosters = posterAdapter.getSelectedPosters();
            displaySelectedPosters(selectedPosters);
        });
    }
    /**
     * Creates and returns a list of Poster objects with predefined data.
     * @return A list of initialized Poster objects.
     */
    private List<Poster> createPosterList() {
        List<Poster> posterList = new ArrayList<>();

        // Adding individual Poster objects to the list
        Poster the100 = new Poster();
        the100.image = R.drawable.archer;
        the100.name = "Archer";
        the100.createdBy = "Adam Reed";
        the100.rating = 4.8f;
        the100.story = "A self-involved spy, Sterling Archer, navigates espionage with his eccentric colleagues.";
        posterList.add(the100);

        Poster the101 = new Poster();
        the101.image = R.drawable.avengers;
        the101.name = "Avengers";
        the101.createdBy = "Joss Whedon";
        the101.rating = 4.5f;
        the101.story = "Earth's mightiest heroes team up to fight the threat of Loki and his alien army.";
        posterList.add(the101);

        Poster the102 = new Poster();
        the102.image = R.drawable.hypnotic;
        the102.name = "Hypnotic";
        the102.createdBy = "Matt Angel and Suzanne Coote";
        the102.rating = 2.5f;
        the102.story = "A young woman seeking self-improvement enlists a hypnotist for help, only to find herself in danger.";
        posterList.add(the102);

        Poster the103 = new Poster();
        the103.image = R.drawable.advocate;
        the103.name = "The Devil's Advocate";
        the103.createdBy = "Taylor Hackford";
        the103.rating = 4.2f;
        the103.story = "An ambitious lawyer is drawn into a world of darkness under a charismatic boss.";
        posterList.add(the103);

        Poster the104 = new Poster();
        the104.image = R.drawable.fourteighthrs;
        the104.name = "48 Hours";
        the104.createdBy = "Walter Hill";
        the104.rating = 4.0f;
        the104.story = "A tough cop teams up with a wise-cracking criminal to catch a pair of killers.";
        posterList.add(the104);

        Poster the105 = new Poster();
        the105.image = R.drawable.openh2o;
        the105.name = "Open Water";
        the105.createdBy = "Chris Kentis";
        the105.rating = 3.6f;
        the105.story = "Based on a true story, a couple is accidentally left behind on a scuba diving trip in shark-infested waters.";
        posterList.add(the105);

        Poster the106 = new Poster();
        the106.image = R.drawable.spidey;
        the106.name = "Spider-Man";
        the106.createdBy = "Sam Raimi";
        the106.rating = 4.4f;
        the106.story = "Peter Parker, a high school student, gains spider-like abilities and becomes the hero known as Spider-Man.";
        posterList.add(the106);

        Poster the107 = new Poster();
        the107.image = R.drawable.sourcecode;
        the107.name = "Source Code";
        the107.createdBy = "Duncan Jones";
        the107.rating = 4.1f;
        the107.story = "A soldier wakes up in another man's body and discovers he's part of a mission to find a bomber.";
        posterList.add(the107);


        Poster the108 = new Poster();
        the108.image = R.drawable.thenorthman;
        the108.name = "The Northman";
        the108.createdBy = "Robert Eggers";
        the108.rating = 4.3f;
        the108.story = "A Viking prince sets out on a journey to avenge his father's murder.";
        posterList.add(the108);

        Poster the109 = new Poster();
        the109.image = R.drawable.indianajones;
        the109.name = "Indiana Jones";
        the109.createdBy = "Steven Spielberg";
        the109.rating = 4.7f;
        the109.story = "An adventurous archaeologist goes on a quest for a historical artifact.";
        posterList.add(the109);

        Poster the110 = new Poster();
        the110.image = R.drawable.timebandits;
        the110.name = "Time Bandits";
        the110.createdBy = "Terry Gilliam";
        the110.rating = 4.0f;
        the110.story = "A young boy embarks on a time-traveling adventure with a band of misfit thieves.";
        posterList.add(the110);

        return posterList;
    }
    /**
     * Displays the names of selected posters in a Toast message.
     * @param selectedPosters List of selected Poster objects to display.
     */
    private void displaySelectedPosters(List<Poster> selectedPosters) {
        StringBuilder posterNames = new StringBuilder();
        for (int i = 0; i < selectedPosters.size(); i++) {
            if (i > 0) {
                posterNames.append("\n");
            }
            posterNames.append(selectedPosters.get(i).name);
        }
        Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
    }
    /**
     * Called by PosterAdapter when there is a change in poster selection.
     * Controls the visibility of the "Add to Watchlist" button.
     * @param isSelected true if at least one poster is selected, false otherwise.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        buttonAddToWatchlist.setVisibility(isSelected ? View.VISIBLE : View.GONE);
    }
}
