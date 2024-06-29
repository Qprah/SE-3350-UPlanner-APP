package com.example.uplanner.presentation.ProfessorRatingActivityMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uplanner.R;
import com.example.uplanner.presentation.MainPageActivity;
import com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingActivities.Professor1RatingsActivity;
import com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingActivities.Professor2RatingsActivity;
import com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingActivities.Professor3RatingsActivity;

public class ProfessorRatingMenuActivity extends Activity
{
    private Button backSpaceInProfessorMenu;
    private Button viewProfessor1;
    private Button viewProfessor2;
    private Button viewProfessor3;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_rating_menu_page);

        backSpaceInProfessorMenu = (Button) findViewById(R.id.back_space_professor_rating_interface);
        viewProfessor1 = (Button) findViewById(R.id.view_professor1_button);
        viewProfessor2 = (Button) findViewById(R.id.view_professor2_button);
        viewProfessor3 = (Button) findViewById(R.id.view_professor3_button);


        //back space
        backSpaceInProfessorMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(nextScreen);
            }
        });

        //view professor 1's ratings
        viewProfessor1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Professor1RatingsActivity.class);
                startActivity(nextScreen);
            }
        });

        //view professor 2's ratings
        viewProfessor2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Professor2RatingsActivity.class);
                startActivity(nextScreen);
            }
        });

        //view professor 3's ratings
        viewProfessor3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Professor3RatingsActivity.class);
                startActivity(nextScreen);
            }
        });
    }
}
