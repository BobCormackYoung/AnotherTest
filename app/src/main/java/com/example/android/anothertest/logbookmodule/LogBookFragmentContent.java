package com.example.android.anothertest.logbookmodule;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseHelper;

import static com.example.android.anothertest.util.TimeUtils.millisToStartOfDay;

/**
 * Created by Bobek on 26/02/2018.
 */

public class LogBookFragmentContent extends Fragment {

    private static final String KEY_DATE = "date";
    final long DAYPERIOD = 86400000;
    final int ADD_CLIMB_NEW = 0;
    final int ADD_CLIMB_EDIT = 1;
    long fragmentDate;
    LogBookListAdapter adapter;
    Cursor cursor;
    DatabaseHelper handler;
    SQLiteDatabase database;
    private TextView tvContent;

    public static LogBookFragmentContent newInstance(long date) {
        LogBookFragmentContent fragmentFirst = new LogBookFragmentContent();
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final long millis = getArguments().getLong(KEY_DATE);
        if (millis > 0) {
            final Context context = getActivity();
            if (context != null) {
                //tvContentValue = "This is the content for the date " + TimeUtils.getFormattedDate(context, millis);
                fragmentDate = millis;
                return;
            }
        }

        fragmentDate = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_book_viewpager_fragment, container, false);

        final Context context = getActivity();
        handler = new DatabaseHelper(context);
        database = handler.getWritableDatabase();
        long dayStart = fragmentDate - millisToStartOfDay();
        long dayEnd = dayStart + DAYPERIOD;
        cursor = getCursorBetweenDates(dayStart, dayEnd, database);
        TextView columnLine = view.findViewById(R.id.log_book_list_column);
        TextView noDataInfo = view.findViewById(R.id.log_book_list_blank);

        try {
            if (cursor.getCount() == 0) {
                columnLine.setVisibility(View.GONE);
                noDataInfo.setVisibility(View.VISIBLE);

            } else {
                columnLine.setVisibility(View.VISIBLE);
                noDataInfo.setVisibility(View.GONE);
            }

            adapter = new LogBookListAdapter(context, cursor);
            ListView listView = (ListView) view.findViewById(R.id.log_book_list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // Create a new intent to open the {@link FamilyActivity}
                    Intent editClimbIntent = new Intent(context, AddClimb.class);
                    editClimbIntent.putExtra("EditOrNewFlag", ADD_CLIMB_EDIT);
                    editClimbIntent.putExtra("RowID", (int) id);
                    editClimbIntent.putExtra("Date", (long) fragmentDate);
                    // Start the new activity
                    Log.i("TAG ME UP", "OnItemClick " + (int) id + " " + ADD_CLIMB_EDIT + " " + fragmentDate);
                    startActivity(editClimbIntent);
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

                    AlertDialog.Builder deleteAlert = deleteDialog(id);

                    deleteAlert.show();

                    return true;
                }
            });

            return view;
        } finally {
            Log.i("LogBookFragmentContent", "in finally clause");
            cursor.close();
            if (cursor.isClosed()) {
                Log.i("LogBookFragmentContent", "in finally clause, is closed");
            } else {
                Log.i("LogBookFragmentContent", "in finally clause, is open");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCursor();
    }

    @Override
    public void onDestroy() {
        Log.i("LogBookFragmentContent", "fragment columns before" + cursor.getColumnCount());
        cursor.close();
        Log.i("LogBookFragmentContent", "fragment columns after" + cursor.getColumnCount());
        Log.i("LogBookFragmentContent", "fragment destroyed");
        super.onDestroy();
    }

    public Cursor getCursorBetweenDates(long dateStart, long dateEnd, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.ClimbLogEntry.TABLE_NAME + " where " + DatabaseContract.ClimbLogEntry.COLUMN_DATE + " BETWEEN '" + dateStart + "' AND '" + dateEnd + "' ORDER BY Date ASC", null);
        return cursor;
    }

    public AlertDialog.Builder deleteDialog(final long id) {

        final Context context = getActivity();
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        //alert.setTitle("Alert!!");
        alert.setMessage("Are you sure you wish to delete this ");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String table = DatabaseContract.ClimbLogEntry.TABLE_NAME;
                String whereClause = "_id=?";
                String[] whereArgs = new String[]{String.valueOf(id)};
                database.delete(table, whereClause, whereArgs);

                Toast.makeText(context, "Clicked Yes, deleting " + id, Toast.LENGTH_LONG).show();

                refreshCursor();

                dialog.dismiss();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Clicked No", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        return (alert);

    }

    public void refreshCursor() {
        long dayStart = fragmentDate - millisToStartOfDay();
        long dayEnd = dayStart + DAYPERIOD;
        cursor.close();
        cursor = getCursorBetweenDates(dayStart, dayEnd, database);
        adapter.changeCursor(cursor);
    }

}