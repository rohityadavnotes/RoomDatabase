package com.room.database.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import com.room.database.local.StudentDao;

public class DeleteTableAsyncTask extends AsyncTask<Void, Void, Integer> {

    private Activity activity;
    private StudentDao studentDao;

    public DeleteTableAsyncTask(Activity activity, StudentDao studentDao) {
        this.activity   = activity;
        this.studentDao = studentDao;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return studentDao.deleteAllNotes();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(activity,"Delete All Student "+integer,Toast.LENGTH_SHORT).show();
    }
}