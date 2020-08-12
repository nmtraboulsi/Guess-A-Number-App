package com.nicoletraboulsi.guessanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // private variables which can only be used in this class
    private TextView mNumberOfTries;
    private EditText mEditText;
    private Button mButton;

    private int mRandomNumber = 0;
    private int mNumOfTries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create variables for UI elements
        mNumberOfTries = findViewById(R.id.numOfTries);
        mEditText = findViewById(R.id.edittext);
        mButton = findViewById(R.id.button);

        // Generates random number when app opens
        generateRandomNumber();

        // SUBMIT Button Functionality
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check button text
                if(mButton.getText().toString().equals("PlAY AGAIN")) {
                    // Reset the game so the user can play again
                    playAgain();
                }else {
                    // Submit new guess and check it's correct
                    submit();
                }
            }

        });
    }

    private void submit() {

        String CORRECT = "Congrats! You guessed the right answer!";
        String TOO_LOW = "Try again. Your guess was too low!";
        String TOO_HIGH = "Try again. Your guess was too high!";

        String editTextString = mEditText.getText().toString();

        // Check if edittext is empty and return
        if(editTextString.isEmpty()) {
            return;
        }

        int currentNum = Integer.parseInt(editTextString);
        String message = "";

        if(currentNum == mRandomNumber) {
            // Acknowledge that the user guessed the number correct
            message = CORRECT;
            // Change the text of the button to Play Again
            mButton.setText("PLAY AGAIN");

        }else if (currentNum > mRandomNumber) {
            // Acknowledge that the user guessed a number too high
            message = TOO_HIGH;
            // Increment number of tries by 1
            setmNumOfTries(mNumOfTries + 1);
        }else {
            message = TOO_LOW;
            // Increment number of tries by 1
            setmNumOfTries(mNumOfTries + 1);
        }

        // Show the user a message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Clear out text from editText
        mEditText.setText("");

    }

    private void setmNumOfTries(int number) {
        mNumOfTries = number;
        String numberString = String.valueOf(number);
        mNumberOfTries.setText("# of tries: " + numberString);

    }

    private void playAgain() {
        // Generate new random number
        generateRandomNumber();

        // Change button text back to Submit
        mButton.setText("SUBMIT");

        // Reset tries back to zero
        setmNumOfTries(0);
    }

    private void generateRandomNumber() {
        mRandomNumber = new Random().nextInt(100) + 1;
    }
}