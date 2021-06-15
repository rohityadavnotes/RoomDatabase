package com.room.database.asynctask;

import android.app.Activity;
import com.room.database.activity.StudentAdapter;
import com.room.database.local.MyRoomDatabase;
import com.room.database.local.Student;

public class StudentRepository {

    private MyRoomDatabase myRoomDatabase;
    private Activity activity;

    public StudentRepository(Activity activity) {
        myRoomDatabase = MyRoomDatabase.getInstance(activity);
        this.activity = activity;
    }

    public void insertTask(Student student){
        new InsertAsyncTask(activity,myRoomDatabase.studentDao(),student).execute();
    }

    public void updateTask(Student student){
        new UpdateAsyncTask(activity,myRoomDatabase.studentDao(),student).execute();
    }

    public void retrieveTask(StudentAdapter studentAdapter) {
        new GetAsyncTask(activity, myRoomDatabase.studentDao(),studentAdapter).execute();
    }

    public void deleteTask(Student student){
        new DeleteAsyncTask(activity,myRoomDatabase.studentDao(),student).execute();
    }

    public void deleteAllTask(){
        new DeleteTableAsyncTask(activity,myRoomDatabase.studentDao()).execute();
    }
}













