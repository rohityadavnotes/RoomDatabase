package com.room.database.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import com.room.database.activity.StudentListActivity;
import com.room.database.local.Student;
import com.room.database.local.StudentDao;
import com.room.database.utils.ActivityUtility;

public class InsertAsyncTask extends AsyncTask<Void, Void, Long> {

    private Activity activity;
    private StudentDao studentDao;
    private Student student;

    public InsertAsyncTask(Activity activity, StudentDao studentDao, Student student) {
        this.activity   = activity;
        this.studentDao = studentDao;
        this.student    = student;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        return studentDao.insert(student);
    }

    @Override
    protected void onPostExecute(Long longs) {
        super.onPostExecute(longs);

        Toast.makeText(activity,"Insert Student "+longs,Toast.LENGTH_SHORT).show();
        ActivityUtility.goToNextActivity(activity, StudentListActivity.class);
    }
}