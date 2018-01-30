package com.example.android.anothertest;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bobek on 12/11/2017.
 */

public class LogBookListAdapter extends ArrayAdapter<LogBookItem> {

    /** Resource ID for the background color for this list of words */
    private int mTrainingColorResourceId;
    private int mClimbingColorResourceId;

    /**
     * Create a new {@link LogBookListAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param logBookItems is the list of {@link LogBookItem}s to be displayed.
     */
    public LogBookListAdapter(Context context, ArrayList<LogBookItem> logBookItems) {
        super(context, 0, logBookItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.log_book_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        LogBookItem currentItem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.log_book_list_item_title);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        titleTextView.setText(currentItem.getLogBookTaskName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView gradeTextView = (TextView) listItemView.findViewById(R.id.log_book_list_item_icon);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        gradeTextView.setText(currentItem.getGrade());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info1TextView = (TextView) listItemView.findViewById(R.id.log_book_list_item_info1);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        info1TextView.setText(currentItem.getInfoLine1());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info2TextView = (TextView) listItemView.findViewById(R.id.log_book_list_item_info2);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        info2TextView.setText(currentItem.getInfoLine2());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info3TextView = (TextView) listItemView.findViewById(R.id.log_book_list_item_info3);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        info3TextView.setText(currentItem.getInfoLine3());

        //View textContainer = listItemView.findViewById(R.id.log_book_list_item_wrapper);
        TextView textContainer = (TextView) listItemView.findViewById(R.id.log_book_list_item_icon);
        boolean mTrainingTrigger = currentItem.getTrainingTrigger();
        if (mTrainingTrigger==true) {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), R.color.colorTrainingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        } else {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), R.color.colorClimbingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        }

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
