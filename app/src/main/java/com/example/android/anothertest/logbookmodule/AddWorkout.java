package com.example.android.anothertest.logbookmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseReadWrite;
import com.example.android.anothertest.logbookmodule.workoutpicker.ParentWorkoutHolder;
import com.example.android.anothertest.util.TimeUtils;

// TODO: Add the location for adding information about the movecount

public class AddWorkout extends AppCompatActivity {

    final int PICK_TRAINING_REQUEST = 1;
    final int ADD_WORKOUT_NEW = 0;
    final int ADD_WORKOUT_EDIT = 1;
    final int INCREMENT_TIME_SHORT = 5000; // 5 seconds in milliseconds
    final int INCREMENT_TIME_LONG = 60000; // 1 minute in milliseconds
    final double INCREMENT_WEIGHT_SMALL = 0.5; // 0.5 KG
    final double INCREMENT_WEIGHT_LARGE = 5; // 5 KG
    final int INCREMENT_COUNT_SMALL = 1; // set count
    final int INCREMENT_COUNT_LARGE = 5; // set count
    int outputWorkoutNumber; // Workout number
    int outputWorkoutName; // Workout name
    long outputDate = -1;
    int inputIntentCode = -1;
    int inputRowID = -1;

    // Initialise counters
    double counterWeight = 0;
    int counterRestTime = 0;
    int counterRepCount = 1;
    int counterRepTime = 0;
    int counterSetCount = 1;
    int counterMoveCount = 0;

    TextView titleWrapper; // Title wrapper

    // Chronometer
    LinearLayout chronometerWrapper;
    Chronometer chronometerTimer;
    Button chronometerStart;
    Button chronometerStop;
    Button chronometerSave;

    LinearLayout workoutDateWrapper; // Date wrapper
    EditText workoutDateEditText; // Date EditText

    LinearLayout workoutTrainingWrapper; // Training type/name wrapper
    EditText workoutTrainingEditText; // Training type/name EditText

    LinearLayout workoutWeightWrapper; // Weight wrapper
    EditText workoutWeightEditText; // Weight EditText
    Button workoutWeightButtonMinus; // Weight MinusButton
    Button workoutWeightButtonPlus; // Weight PlusButton
    Button workoutWeightButtonMinusBig;  // Weight MinusButton
    Button workoutWeightButtonPlusBig;  // Weight PlusButton

    LinearLayout workoutSetCountWrapper; // Set Count  Wrapper
    EditText workoutSetCountEditText; // Set Count EditText
    Button workoutSetCountButtonMinus; // Set Count MinusButton
    Button workoutSetCountButtonPlus; // Set Count PlusButton
    Button workoutSetCountButtonMinusBig; // Set Count MinusButton
    Button workoutSetCountButtonPlusBig; // Set Count PlusButton

    LinearLayout workoutRepCountWrapper; // Rep Count Wrapper
    EditText workoutRepCountEditText; // Set Count EditText
    Button workoutRepCountButtonMinus; // Set Count MinusButton
    Button workoutRepCountButtonPlus; // Set Count PlusButton
    Button workoutRepCountButtonMinusBig; // Set Count MinusButton
    Button workoutRepCountButtonPlusBig; // Set Count PlusButton

    LinearLayout workoutRepDurationWrapper; // Rep Duration Wrapper
    EditText workoutRepDurationEditText; // Rep Duration EditText
    Button workoutRepDurationButtonMinus; // Rep Duration MinusButton
    Button workoutRepDurationButtonPlus; // Rep Duration PlusButton
    Button workoutRepDurationButtonMinusBig; // Rep Duration MinusButton
    Button workoutRepDurationButtonPlusBig; // Rep Duration PlusButton

    LinearLayout workoutRestWrapper; // Rest per set wrapper
    EditText workoutRestEditText; // Rest per set EditText
    Button workoutRestButtonMinus; // Rest per set MinusButton
    Button workoutRestButtonPlus; // Rest per set PlusButton
    Button workoutRestButtonMinusBig; // Rest per set MinusButton
    Button workoutRestButtonPlusBig; // Rest per set PlusButton

    LinearLayout workoutGradeWrapper; // Grade Wrapper
    EditText workoutGradeEditText; // Grade EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        Intent inputIntent = getIntent();
        inputIntentCode = inputIntent.getIntExtra("EditOrNewFlag", 0);
        inputRowID = inputIntent.getIntExtra("RowID", 0);
        outputDate = inputIntent.getLongExtra("Date", 0);

        mapViews();

        if (inputIntentCode == ADD_WORKOUT_NEW) {

            workoutDateEditText.setText(TimeUtils.convertDate(outputDate, "yyyy-MM-dd"));

            // Weight Data Input Wrapper
            workoutWeightWrapper.setVisibility(View.GONE);
            // Set Count Input Wrapper
            workoutSetCountWrapper.setVisibility(View.GONE);
            // Rep Count Input Wrapper
            workoutRepCountWrapper.setVisibility(View.GONE);
            // Rep Duration Input Wrapper
            workoutRepDurationWrapper.setVisibility(View.GONE);
            // Rest Per Set Input Wrapper
            workoutRestWrapper.setVisibility(View.GONE);
            // Grade or Difficulty Input Wrapper
            workoutGradeWrapper.setVisibility(View.GONE);

        } else if (inputIntentCode == ADD_WORKOUT_EDIT) {

            // Get the workout entry information, update the view fields
            Bundle workoutEntryBundle = DatabaseReadWrite.EditWorkoutLoadEntry(inputRowID, this);
            outputWorkoutNumber = workoutEntryBundle.getInt("outputWorkoutCode");
            outputWorkoutName = workoutEntryBundle.getInt("outputWorkoutTypeCode");
            counterWeight = workoutEntryBundle.getDouble("outputWeight");
            counterRestTime = workoutEntryBundle.getInt("outputRestDuration");
            counterRepCount = workoutEntryBundle.getInt("outputRepCount");
            counterRepTime = workoutEntryBundle.getInt("outputRepDuration");
            counterSetCount = workoutEntryBundle.getInt("outputSetCount");
            counterMoveCount = workoutEntryBundle.getInt("outputMoveCount");

            // Get which fields should be shown
            Bundle workoutFieldsBundle = DatabaseReadWrite.workoutLoadFields(outputWorkoutNumber, this);

            // Display the data
            workoutDateEditText.setText(TimeUtils.convertDate(outputDate, "yyyy-MM-dd"));
            String outputStringWorkoutName = DatabaseReadWrite.getWorkoutTextClimb(outputWorkoutNumber, this);
            String outputStringWorkoutType = DatabaseReadWrite.getWorkoutTypeClimb(outputWorkoutName, this);
            workoutTrainingEditText.setText(outputStringWorkoutType + " | " + outputStringWorkoutName);
            getTrainingInputFields(workoutFieldsBundle);
        }

        onClickListenerInitiation();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_TRAINING_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                putWorkout(data);
            }
            // TODO: Error handler for nothing passed back
        }
    }

    // method for launching the activity for picking the grade
    private void pickTraining() {
        // Create new intent
        Intent selectorIntent = new Intent(this, ParentWorkoutHolder.class);
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_TRAINING_REQUEST);
    }

    private void putWorkout(Intent inputData) {
        // The user picked a workout, get the workout name
        outputWorkoutNumber = inputData.getIntExtra("OutputWorkoutNumber", 0);
        String outputStringWorkoutName = DatabaseReadWrite.getWorkoutTextClimb(outputWorkoutNumber, this);

        // The user picked a workout, get the workout type name
        // Put workout text in the view
        outputWorkoutName = inputData.getIntExtra("OutputWorkoutName", 0);
        String outputStringWorkoutType = DatabaseReadWrite.getWorkoutTypeClimb(outputWorkoutName, this);
        workoutTrainingEditText.setText(outputStringWorkoutType + " | " + outputStringWorkoutName);

        // Display the missing fields
        Bundle bundle = DatabaseReadWrite.workoutLoadFields(outputWorkoutNumber, this);
        getTrainingInputFields(bundle);
    }

    private void getTrainingInputFields(Bundle bundle) {

        workoutRestEditText.setText(TimeUtils.convertDate(counterRestTime, "mm:ss"));
        workoutRepDurationEditText.setText(TimeUtils.convertDate(counterRepTime, "mm:ss"));
        workoutRepCountEditText.setText("" + counterRepCount);
        workoutSetCountEditText.setText("" + counterSetCount);
        workoutWeightEditText.setText("" + counterWeight + " Kg");

        int outputIsClimb = bundle.getInt("outputIsClimb");
        //if (outputIsClimb == DatabaseContract.WorkoutListEntry.IS_TRUE) {
        //    workoutGradeWrapper.setVisibility(View.VISIBLE);
        //}
        int outputIsGradeCode = bundle.getInt("outputIsGradeCode");
        if (outputIsGradeCode == DatabaseContract.IS_TRUE) {
            workoutGradeWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutGradeWrapper.setVisibility(View.GONE);
        }

        int outputIsRepCountPerSet = bundle.getInt("outputIsRepCountPerSet");
        if (outputIsRepCountPerSet == DatabaseContract.IS_TRUE) {
            workoutRepCountWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutRepCountWrapper.setVisibility(View.GONE);
        }

        int outputRepDurationPerSet = bundle.getInt("outputRepDurationPerSet");
        if (outputRepDurationPerSet == DatabaseContract.IS_TRUE) {
            workoutRepDurationWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutRepDurationWrapper.setVisibility(View.GONE);
        }

        int outputIsRestDuratonPerSet = bundle.getInt("outputIsRestDuratonPerSet");
        if (outputIsRestDuratonPerSet == DatabaseContract.IS_TRUE) {
            workoutRestWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutRestWrapper.setVisibility(View.GONE);
        }

        int outputIsSetCount = bundle.getInt("outputIsSetCount");
        if (outputIsSetCount == DatabaseContract.IS_TRUE) {
            workoutSetCountWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutSetCountWrapper.setVisibility(View.GONE);
        }

        int outputIsWeight = bundle.getInt("outputIsWeight");
        if (outputIsWeight == DatabaseContract.IS_TRUE) {
            workoutWeightWrapper.setVisibility(View.VISIBLE);
        } else {
            workoutWeightWrapper.setVisibility(View.GONE);
        }
    }

    private void mapViews() {
        // Title wrapper
        titleWrapper = findViewById(R.id.pageTitle);

        // Chronometer
        chronometerWrapper = findViewById(R.id.chronometerWrapper);
        chronometerTimer = findViewById(R.id.chronometer);
        chronometerStart = findViewById(R.id.buttonStartChrono);
        chronometerStop = findViewById(R.id.buttonStopChrono);
        chronometerSave = findViewById(R.id.buttonSaveChrono);

        // Date Wrapper
        workoutDateWrapper = findViewById(R.id.workoutTextWrapper1);
        workoutDateEditText = findViewById(R.id.workoutEditText1);

        // Workout Type Wrapper
        workoutTrainingWrapper = findViewById(R.id.workoutTextWrapper2);
        workoutTrainingEditText = findViewById(R.id.workoutEditText2);

        // Weight Data Input Wrapper
        workoutWeightWrapper = findViewById(R.id.workoutTextWrapper3);
        workoutWeightEditText = findViewById(R.id.workoutEditText3);
        workoutWeightButtonMinus = findViewById(R.id.workoutButtonMinus3);
        workoutWeightButtonPlus = findViewById(R.id.workoutButtonPlus3);
        workoutWeightButtonMinusBig = findViewById(R.id.workoutButtonMinus3a);
        workoutWeightButtonPlusBig = findViewById(R.id.workoutButtonPlus3a);

        // Set Count Input Wrapper
        workoutSetCountWrapper = findViewById(R.id.workoutTextWrapper4);
        workoutSetCountEditText = findViewById(R.id.workoutEditText4);
        workoutSetCountButtonMinus = findViewById(R.id.workoutButtonMinus4);
        workoutSetCountButtonPlus = findViewById(R.id.workoutButtonPlus4);
        workoutSetCountButtonMinusBig = findViewById(R.id.workoutButtonMinus4a);
        workoutSetCountButtonPlusBig = findViewById(R.id.workoutButtonPlus4a);

        // Rep Count Input Wrapper
        workoutRepCountWrapper = findViewById(R.id.workoutTextWrapper5);
        workoutRepCountEditText = findViewById(R.id.workoutEditText5);
        workoutRepCountButtonMinus = findViewById(R.id.workoutButtonMinus5);
        workoutRepCountButtonPlus = findViewById(R.id.workoutButtonPlus5);
        workoutRepCountButtonMinusBig = findViewById(R.id.workoutButtonMinus5a);
        workoutRepCountButtonPlusBig = findViewById(R.id.workoutButtonPlus5a);

        // Rep Duration Input Wrapper
        workoutRepDurationWrapper = findViewById(R.id.workoutTextWrapper6);
        workoutRepDurationEditText = findViewById(R.id.workoutEditText6);
        workoutRepDurationButtonMinus = findViewById(R.id.workoutButtonMinus6);
        workoutRepDurationButtonPlus = findViewById(R.id.workoutButtonPlus6);
        workoutRepDurationButtonMinusBig = findViewById(R.id.workoutButtonMinus6a);
        workoutRepDurationButtonPlusBig = findViewById(R.id.workoutButtonPlus6a);

        // Rest Per Set Input Wrapper
        workoutRestWrapper = findViewById(R.id.workoutTextWrapper7);
        workoutRestEditText = findViewById(R.id.workoutEditText7);
        workoutRestButtonMinus = findViewById(R.id.workoutButtonMinus7);
        workoutRestButtonPlus = findViewById(R.id.workoutButtonPlus7);
        workoutRestButtonMinusBig = findViewById(R.id.workoutButtonMinus7a);
        workoutRestButtonPlusBig = findViewById(R.id.workoutButtonPlus7a);

        // Grade or Difficulty Input Wrapper
        workoutGradeWrapper = findViewById(R.id.workoutTextWrapper8);
        workoutGradeEditText = findViewById(R.id.workoutEditText8);

    }

    private void onClickListenerInitiation() {
        // Listener for the training selection
        workoutTrainingEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputIntentCode == ADD_WORKOUT_NEW) {
                    // Only allow workout type to be changed if saving a new workout
                    pickTraining();

                    // Reset the counters when changing the workout
                    counterWeight = 0;
                    counterRestTime = 0;
                    counterRepCount = 1;
                    counterRepTime = 0;
                    counterSetCount = 1;
                    counterMoveCount = 0;
                }
            }
        });

        // Listener for the save button
        Button saveButton = (Button) findViewById(R.id.log_training_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputIntentCode == ADD_WORKOUT_NEW) {
                    if (outputWorkoutNumber != 0 && outputWorkoutName != 0) {
                        long outputRow = DatabaseReadWrite.writeWorkoutLogData(outputDate, outputWorkoutName, outputWorkoutNumber, counterWeight, counterSetCount,
                                counterRepCount, counterRepTime, counterRestTime, 0, 0, counterMoveCount, AddWorkout.this);
                        DatabaseReadWrite.writeCalendarUpdate(DatabaseContract.IS_WORKOUT, outputDate, outputRow, AddWorkout.this);
                        Toast.makeText(getApplicationContext(), "New Row ID: " + outputRow, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "No workout type selected", Toast.LENGTH_SHORT).show();
                    }
                } else if (inputIntentCode == ADD_WORKOUT_EDIT) {
                    long outputRow = DatabaseReadWrite.updateWorkoutLogData(outputDate, outputWorkoutName, outputWorkoutNumber, counterWeight, counterSetCount,
                            counterRepCount, counterRepTime, counterRestTime, 0, 0, counterMoveCount, inputRowID, AddWorkout.this);
                    Toast.makeText(getApplicationContext(), "New Row ID: " + outputRow, Toast.LENGTH_SHORT).show();
                    finish();
                }
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

        // Chronometer
        chronometerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerTimer.start();
            }
        });
        chronometerStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerTimer.stop();
            }
        });

        // Weight Button Listeners
        workoutWeightButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterWeight - INCREMENT_WEIGHT_SMALL >= 0) {
                    counterWeight = counterWeight - INCREMENT_WEIGHT_SMALL;
                    workoutWeightEditText.setText("" + counterWeight + " Kg");
                }
            }
        });
        workoutWeightButtonMinusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterWeight - INCREMENT_WEIGHT_LARGE >= 0) {
                    counterWeight = counterWeight - INCREMENT_WEIGHT_LARGE;
                    workoutWeightEditText.setText("" + counterWeight + " Kg");
                }
            }
        });
        workoutWeightButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterWeight = counterWeight + INCREMENT_WEIGHT_SMALL;
                workoutWeightEditText.setText("" + counterWeight + " Kg");
            }
        });
        workoutWeightButtonPlusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterWeight = counterWeight + INCREMENT_WEIGHT_LARGE;
                workoutWeightEditText.setText("" + counterWeight + " Kg");
            }
        });

        // Set Count Button Listeners
        workoutSetCountButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterSetCount - INCREMENT_COUNT_SMALL >= 0) {
                    counterSetCount = counterSetCount - INCREMENT_COUNT_SMALL;
                    workoutSetCountEditText.setText("" + counterSetCount);
                }
            }
        });
        workoutSetCountButtonMinusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterSetCount - INCREMENT_COUNT_LARGE >= 0) {
                    counterSetCount = counterSetCount - INCREMENT_COUNT_LARGE;
                    workoutSetCountEditText.setText("" + counterSetCount);
                }
            }
        });
        workoutSetCountButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterSetCount = counterSetCount + INCREMENT_COUNT_SMALL;
                workoutSetCountEditText.setText("" + counterSetCount);
            }
        });
        workoutSetCountButtonPlusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterSetCount = counterSetCount + INCREMENT_COUNT_LARGE;
                workoutSetCountEditText.setText("" + counterSetCount);
            }
        });

        // Rep Count Button Listeners
        workoutRepCountButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRepCount - INCREMENT_COUNT_SMALL >= 0) {
                    counterRepCount = counterRepCount - INCREMENT_COUNT_SMALL;
                    workoutRepCountEditText.setText("" + counterRepCount);
                }
            }
        });
        workoutRepCountButtonMinusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRepCount - INCREMENT_COUNT_LARGE >= 0) {
                    counterRepCount = counterRepCount - INCREMENT_COUNT_LARGE;
                    workoutRepCountEditText.setText("" + counterRepCount);
                }
            }
        });
        workoutRepCountButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRepCount = counterRepCount + INCREMENT_COUNT_SMALL;
                workoutRepCountEditText.setText("" + counterRepCount);
            }
        });
        workoutRepCountButtonPlusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRepCount = counterRepCount + INCREMENT_COUNT_LARGE;
                workoutRepCountEditText.setText("" + counterRepCount);
            }
        });

        // Rep Duration Button Listeners
        workoutRepDurationButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRepTime - INCREMENT_TIME_SHORT >= 0) {
                    counterRepTime = counterRepTime - INCREMENT_TIME_SHORT;
                    workoutRepDurationEditText.setText(TimeUtils.convertDate(counterRepTime, "mm:ss"));
                }
            }
        });
        workoutRepDurationButtonMinusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRepTime - INCREMENT_TIME_LONG >= 0) {
                    counterRepTime = counterRepTime - INCREMENT_TIME_LONG;
                    workoutRepDurationEditText.setText(TimeUtils.convertDate(counterRepTime, "mm:ss"));
                }
            }
        });
        workoutRepDurationButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRepTime = counterRepTime + INCREMENT_TIME_SHORT;
                workoutRepDurationEditText.setText(TimeUtils.convertDate(counterRepTime, "mm:ss"));
            }
        });
        workoutRepDurationButtonPlusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRepTime = counterRepTime + INCREMENT_TIME_LONG;
                workoutRepDurationEditText.setText(TimeUtils.convertDate(counterRepTime, "mm:ss"));
            }
        });

        // Rest Duration Button Listeners
        workoutRestButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRestTime - INCREMENT_TIME_SHORT >= 0) {
                    counterRestTime = counterRestTime - INCREMENT_TIME_SHORT;
                    workoutRestEditText.setText(TimeUtils.convertDate(counterRestTime, "mm:ss"));
                }
            }
        });
        workoutRestButtonMinusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterRestTime - INCREMENT_TIME_LONG >= 0) {
                    counterRestTime = counterRestTime - INCREMENT_TIME_LONG;
                    workoutRestEditText.setText(TimeUtils.convertDate(counterRestTime, "mm:ss"));
                }
            }
        });
        workoutRestButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRestTime = counterRestTime + INCREMENT_TIME_SHORT;
                workoutRestEditText.setText(TimeUtils.convertDate(counterRestTime, "mm:ss"));
            }
        });
        workoutRestButtonPlusBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterRestTime = counterRestTime + INCREMENT_TIME_LONG;
                workoutRestEditText.setText(TimeUtils.convertDate(counterRestTime, "mm:ss"));
            }
        });

    }

}
