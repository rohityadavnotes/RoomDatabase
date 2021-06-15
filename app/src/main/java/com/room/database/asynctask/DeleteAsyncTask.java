package com.room.database.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import com.room.database.local.Student;
import com.room.database.local.StudentDao;

public class DeleteAsyncTask extends AsyncTask<Void, Void, Integer> {

    private Activity activity;
    private StudentDao studentDao;
    private Student student;

    public DeleteAsyncTask(Activity activity, StudentDao studentDao, Student student) {
        this.activity   = activity;
        this.studentDao = studentDao;
        this.student    = student;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return studentDao.delete(student);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(activity,"Delete Student "+integer,Toast.LENGTH_SHORT).show();
    }
}