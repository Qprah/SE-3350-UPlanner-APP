package com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingActivities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uplanner.R;
import com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingMenuActivity;

public class Professor2RatingsActivity extends Activity {
    private Button sendProf2Review;
    private Button backSpaceProf2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_rating2_page);

        sendProf2Review = (Button) findViewById(R.id.send_review_p2_button);
        backSpaceProf2 = (Button) findViewById(R.id.back_space_professor_rating2);

        //back space
        backSpaceProf2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), ProfessorRatingMenuActivity.class);
                startActivity(nextScreen);
            }
        });

        //send review pop up
        sendProf2Review.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showPopUp();
            }
        });
    }

    private void showPopUp()
    {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.send_review_pop_up_window);

        Button sendReviewButton = dialog.findViewById(R.id.send_review_button);
        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Thank you! Your review will be evaluated and posted in 2-3 business days.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
