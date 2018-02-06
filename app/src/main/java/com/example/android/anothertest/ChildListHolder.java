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

public class ChildListHolder extends AppCompatActivity {

    private static final String TAG = "NestListHold_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_list);

        Intent selectorIntent = getIntent();
        int selectorID = selectorIntent.getIntExtra("selector", 0);

        final ArrayList<ListItem> childListItems = new ArrayList<ListItem>();

        if (selectorID == 1) {
            childListItems.add(new ListItem(1, "Test1a"));
            childListItems.add(new ListItem(2, "Test1b"));
            childListItems.add(new ListItem(3, "Test1c"));
        } else if (selectorID == 2) {
            childListItems.add(new ListItem(4, "Test2a"));
            childListItems.add(new ListItem(5, "Test2b"));
            childListItems.add(new ListItem(6, "Test2c"));
        } else if (selectorID == 3) {
            childListItems.add(new ListItem(7, "Test3a"));
            childListItems.add(new ListItem(8, "Test3b"));
            childListItems.add(new ListItem(9, "Test3c"));
        }

        ChildListAdapter childAdapter = new ChildListAdapter(this, childListItems);

        ListView childListView = (ListView) findViewById(R.id.child_list);

        childListView.setAdapter(childAdapter);

        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                ListItem childListItem = childListItems.get(position);

                int output = childListItem.getListItemID();

                Intent outputIntent = new Intent();
                outputIntent.putExtra("OutputData", output);
                setResult(RESULT_OK, outputIntent);
                finish();

            }
        });

    }
}
