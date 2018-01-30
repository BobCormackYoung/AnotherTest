package com.example.android.anothertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bobek on 19/11/2017.
 */

public class ExpandableListHolder extends AppCompatActivity {

    private static final String TAG = "ExpListHold_Tag";

    // TODO: add way to receive information from the previous activity about what data set to read

    // Initialise objects:
    // Custom expandable list view
    ExpandableListView expandableListView;
    // Expandable list adapter
    ExpandableListAdapter expandableListAdapter;
    // List of title of the expandable list
    List<String> expandableListTitle;
    // Sub-lists assigned to the above titles
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent selectorIntent = getIntent();
        String inputType = selectorIntent.getStringExtra("inputType");
        Log.i(TAG,"input type = " + inputType);

        // Open the custom expandable list view
        setContentView(R.layout.expandable_list_holder);
        // Assign the expandable list view to an objects
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListHolderView);
        // Get the data that is to be displayed
        // TODO: push input to ExpandableListDataPump.getData() showing where to load data from
        expandableListDetail = ExpandableListDataPump.getData(inputType);
        // Get list of the titles of the data set
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        // Create new expandable list adapter with the data above
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        // Assign adapter to the expandable list view
        expandableListView.setAdapter(expandableListAdapter);

/*        // Listener for the expansion of a specific group in the list
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listener for the collapse of a specific group in the list
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });*/

        // Listener for what happens when clicking on a specific child data item
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO: Send data back to the previous activity with the selected item

                String outputData = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                Intent outputIntent = new Intent();
                outputIntent.putExtra("OutputData", outputData);
                setResult(RESULT_OK, outputIntent);
                finish();

//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();


                return false;
            }
        });
    }

}
