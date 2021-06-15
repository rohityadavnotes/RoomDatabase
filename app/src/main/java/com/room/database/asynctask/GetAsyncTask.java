package com.room.database.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import com.room.database.activity.StudentAdapter;
import com.room.database.local.Student;
import com.room.database.local.StudentDao;
import java.util.List;

public class GetAsyncTask extends AsyncTask<Void, Void, List<Student>> {

    private Activity activity;
    private StudentDao studentDao;
    private StudentAdapter studentAdapter;

    public GetAsyncTask(Activity activity, StudentDao studentDao, StudentAdapter studentAdapter) {
        this.activity       = activity;
        this.studentDao     = studentDao;
        this.studentAdapter = studentAdapter;
    }

    @Override
    protected List<Student> doInBackground(Void... voids) {
        return studentDao.getStudent();
    }

    @Override
    protected void onPostExecute(List<Student> studentList) {
        super.onPostExecute(studentList);
        studentAdapter.setAdapterListData(studentList);
        Toast.makeText(activity,"Get Student "+studentList.size(),Toast.LENGTH_SHORT).show();
    }
}