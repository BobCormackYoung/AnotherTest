package com.example.android.anothertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogClimb extends AppCompatActivity {

    final int PICK_GRADE_REQUEST = 1;
    final int PICK_ASCENT_TYPE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_climb);

        // Listener for the grade selection
        EditText gradeView = (EditText) findViewById(R.id.editText4);
        gradeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickGrade();
            }
        });

        // Listener for the ascent-type selection
        EditText ascentTypeView = (EditText) findViewById(R.id.editText3);
        ascentTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickAscentType();
            }
        });

        // Listener for the save button
        Button saveButton = (Button) findViewById(R.id.log_climb_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add functionality to save to database
            }
        });

        // Listener for the cancel button
        Button cancelButton = (Button) findViewById(R.id.log_climb_cancel);
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
        if (requestCode == PICK_GRADE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a grade,
                //String outputString = data.getStringExtra("OutputData");
                // Find the view & change the displayed result
                //EditText gradeView = (EditText) findViewById(R.id.editText4);
                //gradeView.setText(outputString);
                int output = data.getIntExtra("OutputData", 0);
                String outputString = Integer.toString(output);
                EditText gradeView = (EditText) findViewById(R.id.editText4);
                gradeView.setText(outputString);
            }
            // TODO: Error handler for nothing passed back
        } else if (requestCode == PICK_ASCENT_TYPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                // The user picked an ascent type,
                String outputString = data.getStringExtra("OutputData");
                // Find the view & change the displayed result
                EditText ascentTypeView = (EditText) findViewById(R.id.editText3);
                ascentTypeView.setText(outputString);
            }
            // TODO: Error handler for nothing passed back
        }
    }

    // method for launching the activity for picking the grade
    private void pickGrade() {
        // Create new intent
        //Intent selectorIntent = new Intent(this, ExpandableListHolder.class);
        Intent selectorIntent = new Intent(this, ParentListHolder.class);
        // Add extra information to intent so that subsequent activity knows that we're requesting to generate list of grades
        //selectorIntent.putExtra("inputType","grade_type");
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_GRADE_REQUEST);
    }

    // method for launching the activity for picking the ascent type
    private void pickAscentType() {
        // Create new intent
        Intent selectorIntent = new Intent(this, ExpandableListHolder.class);
        // Add extra information to intent so that subsequent activity knows that we're requesting to generate list of grades
        selectorIntent.putExtra("inputType","ascent_type");
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_ASCENT_TYPE_REQUEST);
    }

}
