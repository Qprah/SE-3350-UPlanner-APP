package com.example.uplanner.presentation.ProfessorRatingActivityMain;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uplanner.R;

public class SendReviewPopUpActivity extends Activity
{
    private Button sendReview;
    private EditText professorName;
    private EditText rating;
    private EditText teachingStyle;
    private EditText review;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_review_pop_up_window);

        sendReview    = (Button) findViewById(R.id.send_review_button);
        professorName = (EditText) findViewById(R.id.input_box_professor);
        rating        = (EditText) findViewById(R.id.input_box_rating);
        teachingStyle = (EditText) findViewById(R.id.input_box_teaching);
        review        = (EditText) findViewById(R.id.input_box_main_review);

        sendReview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(Integer.parseInt(rating.toString()) < 0 || Integer.parseInt(rating.toString()) > 5) //if rating is invalid
                {
                    Toast.makeText(SendReviewPopUpActivity.this, "Please enter a rating between 0 to 5.", Toast.LENGTH_SHORT).show();
                }
                if(professorName.length()== 0 ||  rating.length()== 0 || teachingStyle.length()== 0 || review.length()== 0) //if any of the fields have not been filled
                {
                    Toast.makeText(SendReviewPopUpActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SendReviewPopUpActivity.this, "Thank you! You're review will evaluated and posted in 2-3 business days.!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
