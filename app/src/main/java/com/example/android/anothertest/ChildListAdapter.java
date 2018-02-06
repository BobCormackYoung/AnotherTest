package com.example.android.anothertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bobek on 01/02/2018.
 */

public class ChildListAdapter extends ArrayAdapter<ListItem> {

    public ChildListAdapter(Context context, ArrayList<ListItem> parentListItems) {
        super(context, 0, parentListItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup child) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, child, false);
        }

        ListItem currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.list_item_text);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        nameTextView.setText(currentWord.getListItemName());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}