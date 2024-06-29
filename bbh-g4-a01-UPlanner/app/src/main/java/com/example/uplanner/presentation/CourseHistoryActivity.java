package com.example.uplanner.presentation;

import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uplanner.R;
import com.example.uplanner.objects.Course;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.stuAndCourseUtility.CourseUtility;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePool;
import com.example.uplanner.presentation.utility.ActiveStudentManager;
import com.example.uplanner.presentation.utility.CourseHistoryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CourseHistoryActivity extends AppCompatActivity {
    private final Student student = ActiveStudentManager.getInstance().getActiveStudent();
    private final List<Course> courseObjects = new ArrayList<>();
    private final ArrayList<String> toSearch = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_history);

        TextView noCourses = (TextView) findViewById(R.id.no_courses);
        noCourses.setVisibility(View.INVISIBLE);

        if(student.getCompletedCourses().isEmpty()){
            noCourses.setVisibility(View.VISIBLE);
        }else{
            RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
            setUpCourses();

            CourseHistoryRecyclerViewAdapter adapter = new CourseHistoryRecyclerViewAdapter(this, courseObjects);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            showProgress();
        }
        Button back_space_course_history = (Button) findViewById(R.id.back_space_course_history);

        back_space_course_history.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(nextScreen);
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private void showProgress(){
        ProgressBar pb = findViewById(R.id.progressBar);

        TextView numeric = findViewById(R.id.progress_numeric);

        int total = 60;
        int creditHour = 3;
        int validCreditHours = student.getCompletedCourses().size()*creditHour;
        int remainingCreditHours = total - validCreditHours;

        // Calculate progress as a percentage
        int progress = (int)(((double)validCreditHours / total) * 100);

        // Set progress to ProgressBar
        pb.setProgress(progress);

        //progress in text
        numeric.setText(format("%d/%d", validCreditHours / 3, total / 3));

    }

    private void setUpCourses(){
        toSearch.addAll(student.getCompletedCourses());
        courseObjects.addAll(searchCoursesPool(toSearch));
    }

    private List<Course> searchCoursesPool(ArrayList<String> toSearch){
        CoursePool cp = CourseUtility.getCp();
        List<Course> foundCourses = new ArrayList<>(); //a new list to store found courses
        List<Course> allCourses = cp.getCourses();
        for(String code: toSearch){
            for (Course course : allCourses) {
                if (course.getCourseId().equals(code)) {
                    // Add the course object to the list if its name matches
                    foundCourses.add(course);
                    break; // Break the loop since the course is found
                }
            }
        }

        // Sort the courseObjects list by course ID
        foundCourses.sort(new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                // Compare the course IDs
                return c1.getCourseId().compareTo(c2.getCourseId());
            }
        });
        return foundCourses;
    }
}
