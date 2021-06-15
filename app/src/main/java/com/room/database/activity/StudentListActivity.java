package com.room.database.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.room.database.R;
import com.room.database.asynctask.StudentRepository;
import com.room.database.local.Student;
import com.room.database.utils.ActivityUtility;
import java.util.ArrayList;

public class StudentListActivity extends BaseActivity {

    private static final String TAG = StudentListActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Student> studentArrayList;
    private StudentAdapter studentAdapter;

    private Button addNewStudentButton,refreshButton;

    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_student_list);
    }

    @Override
    protected void initializeView() {
        recyclerView        =   findViewById(R.id.recycler_view);
        addNewStudentButton =   findViewById(R.id.add_new_student_button);
        refreshButton       =   findViewById(R.id.refresh_button);
    }

    @Override
    protected void initializeObject() {
        studentArrayList = new ArrayList<>();
        studentRepository = new StudentRepository(this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        studentAdapter =new StudentAdapter(StudentListActivity.this,studentRepository);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setAdapterListData(studentArrayList);
        studentAdapter.notifyDataSetChanged();

        studentRepository.retrieveTask(studentAdapter);
    }

    @Override
    protected void onTextChangedListener() {

    }

    @Override
    protected void initializeEvent() {

        addNewStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.goToNextActivity(StudentListActivity.this,AddStudentActivity.class);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentRepository.retrieveTask(studentAdapter);
                //studentRepository.deleteAllTask();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentRepository.retrieveTask(studentAdapter);
    }
}
