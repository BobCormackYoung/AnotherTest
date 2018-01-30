package com.example.android.anothertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class LogBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book);

        //TODO: read in database for today and display

        //TEMPORARY: Create a list of trainings and climbs
        final ArrayList<LogBookItem> logBook = new ArrayList<LogBookItem>();
        logBook.add(new LogBookItem("Climb 1", "6a", "dummy1", " ", " ", false));
        logBook.add(new LogBookItem("Training 1", "6b", "dummy1", "dummy2", "dummy3", true));
        logBook.add(new LogBookItem("Climb 2", "6c", "dummy1", " ", " ", false));
        logBook.add(new LogBookItem("Climb 3", "7a", "dummy1", " ", " ", false));
        logBook.add(new LogBookItem("Training 2", "7b", "dummy1", "dummy2", "dummy3", true));
        logBook.add(new LogBookItem("Climb 4", "7c", "dummy1", " ", " ", false));
        logBook.add(new LogBookItem("Training 3", "8a", "dummy1", "dummy2", "dummy3", true));
        logBook.add(new LogBookItem("Training 4", "8b", "dummy1", "dummy2", "dummy3", true));
        logBook.add(new LogBookItem("Climb 6", "8c", "dummy1", " ", " ", false));
        logBook.add(new LogBookItem("Climb 7", "9a", "dummy1", " ", " ", false));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        LogBookListAdapter adapter = new LogBookListAdapter(this, logBook);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.log_book_list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //TODO: add functionality to modify selected list item

                    // Get the {@link Word} object at the given position the user clicked on
                    LogBookItem item = logBook.get(position);
                }
            });


        //TODO: onclicklistener for scrolling between days

        View button_previous_day = findViewById(R.id.button_previous_day);
        View button_next_day = findViewById(R.id.button_next_day);
        View button_add_workout = findViewById(R.id.button_add_workout);
        View button_log_climb = findViewById(R.id.button_log_climb);

        button_previous_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                // ...Intent TrainingLogIntent = new Intent(LogBook.this, LogBook.class);
                // Start the new activity
                // ...startActivity(TrainingLogIntent);
            }
        });

        button_next_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                // ...Intent TrainingLogIntent = new Intent(LogBook.this, LogBook.class);
                // Start the new activity
                // ...startActivity(TrainingLogIntent);
            }
        });

        button_add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent AddWorkoutIntent = new Intent(LogBook.this, AddWorkout.class);
                // Start the new activity
                startActivity(AddWorkoutIntent);
            }
        });

        button_log_climb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent LogClimbIntent = new Intent(LogBook.this, LogClimb.class);
                // Start the new activity
                startActivity(LogClimbIntent);
            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted

        //TODO: add refresh ad reload

    }


}
