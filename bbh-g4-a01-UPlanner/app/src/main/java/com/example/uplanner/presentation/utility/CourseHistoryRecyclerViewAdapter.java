package com.example.uplanner.presentation.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uplanner.R;
import com.example.uplanner.objects.Course;

import java.util.List;

public class CourseHistoryRecyclerViewAdapter extends RecyclerView.Adapter<CourseHistoryRecyclerViewAdapter.MyViewHolder> {
    Context context;
    private List<Course> courses;
    public CourseHistoryRecyclerViewAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }
    @NonNull
    @Override
    public CourseHistoryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Giving a look to the row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new CourseHistoryRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHistoryRecyclerViewAdapter.MyViewHolder holder, int position) {
        //binding process
        holder.name.setText(courses.get(position).getCourseName());
        holder.code.setText(courses.get(position).getCourseId());
        holder.grade.setText(courses.get(position).getLetterGrade());
        holder.description.setText(courses.get(position).getCourseDescription());

        boolean isExpanded = courses.get(position).isExpanded();
        holder.constraintLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        //how many items in total
        return courses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //kinda like onCreate method
        ConstraintLayout constraintLayout;
        TextView name, code, grade, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //grabs the view from xml files, in this case courses_history
            name = itemView.findViewById(R.id.course_name_history);
            code = itemView.findViewById(R.id.textViewCode);
            grade = itemView.findViewById(R.id.textViewGrade);
            description = itemView.findViewById(R.id.description);

            //collapsable view
            constraintLayout = itemView.findViewById(R.id.expandable);

            name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Course course = courses.get(getAdapterPosition());
                    course.setExpanded(!course.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }

            });
        }
    }
}
