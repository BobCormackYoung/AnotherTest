package com.example.android.anothertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddWorkout extends AppCompatActivity {

    final int PICK_TRAINING_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        LinearLayout workoutTextWrapper2 = findViewById(R.id.workoutTextWrapper2);
        workoutTextWrapper2.setVisibility(View.GONE);
        LinearLayout workoutTextWrapper3 = findViewById(R.id.workoutTextWrapper3);
        workoutTextWrapper3.setVisibility(View.GONE);
        LinearLayout workoutTextWrapper4 = findViewById(R.id.workoutTextWrapper4);
        workoutTextWrapper4.setVisibility(View.GONE);
        LinearLayout workoutTextWrapper5 = findViewById(R.id.workoutTextWrapper5);
        workoutTextWrapper5.setVisibility(View.GONE);
        LinearLayout workoutTextWrapper6 = findViewById(R.id.workoutTextWrapper6);
        workoutTextWrapper6.setVisibility(View.GONE);
        LinearLayout workoutTextWrapper7 = findViewById(R.id.workoutTextWrapper7);
        workoutTextWrapper7.setVisibility(View.GONE);

        // Listener for the training selection
        EditText gradeView = (EditText) findViewById(R.id.workoutEditText1);
        gradeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTraining();
            }
        });

        // Listener for the save button
        Button saveButton = (Button) findViewById(R.id.log_training_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add functionality to save to database
            }
        });

        // Listener for the cancel button
        Button cancelButton = (Button) findViewById(R.id.log_training_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_TRAINING_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a grade,
                String outputString = data.getStringExtra("OutputData");
                // Find the view & change the displayed result
                EditText gradeView = (EditText) findViewById(R.id.workoutEditText1);
                gradeView.setText(outputString);
            }
            // TODO: Error handler for nothing passed back
        }
    }

    // method for launching the activity for picking the grade
    private void pickTraining() {
        // Create new intent
        Intent selectorIntent = new Intent(this, ExpandableListHolder.class);
        // Add extra information to intent so that subsequent activity knows that we're requesting to generate list of grades
        selectorIntent.putExtra("inputType","training_type");
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_TRAINING_REQUEST);
    }

    private void getTrainingInputFields() {
        // TODO: Method for getting the required input fields
    }

    private void hiddenViewUpdater () {
        // TODO: Method for showing & modifying required input fields
    }

}
