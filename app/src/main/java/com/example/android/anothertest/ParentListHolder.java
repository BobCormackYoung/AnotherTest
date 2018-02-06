package com.example.android.anothertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Bobek on 01/02/2018.
 */

public class ParentListHolder extends AppCompatActivity {

    private static final String TAG = "NestListHold_Tag";
    final int REQUEST_CODE_NUMBER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_list);

        final ArrayList<ListItem> parentListItems = new ArrayList<ListItem>();
        parentListItems.add(new ListItem(1, "Test1"));
        parentListItems.add(new ListItem(2, "Test2"));
        parentListItems.add(new ListItem(3, "Test3"));

        ParentListAdapter parentAdapter = new ParentListAdapter(this, parentListItems);

        ListView parentListView = (ListView) findViewById(R.id.parent_listview);

        parentListView.setAdapter(parentAdapter);

        parentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                ListItem parentListItem = parentListItems.get(position);

                int output = parentListItem.getListItemID();

                // Create new intent
                Intent selectorIntent = new Intent(ParentListHolder.this, ChildListHolder.class);
                // Add extra information to intent so that subsequent activity knows that we're requesting to generate list of grades
                selectorIntent.putExtra("selector", output);
                // Start activity for getting result
                startActivityForResult(selectorIntent, REQUEST_CODE_NUMBER);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CODE_NUMBER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a grade,
                int output = data.getIntExtra("OutputData", 0);

                Intent outputIntent = new Intent();
                outputIntent.putExtra("OutputData", output);
                setResult(RESULT_OK, outputIntent);
                finish();

            }
            // TODO: Error handler for nothing passed back
        }
    }
}
