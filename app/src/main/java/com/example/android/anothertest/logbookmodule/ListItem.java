package com.example.android.anothertest.logbookmodule;

/**
 * Created by Bobek on 31/01/2018.
 */

public class ListItem {

    private int mListItemID;
    private String mListItemName;

    //Constructor
    public ListItem(int listItemID, String listItemName) {
        mListItemID = listItemID;
        mListItemName = listItemName;
    }

    public int getListItemID() {
        return mListItemID;
    }

    public String getListItemName() {
        return mListItemName;
    }

}
