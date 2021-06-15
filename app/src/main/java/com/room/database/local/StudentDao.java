package com.room.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.room.database.Constants;
import java.util.List;

@Dao
public interface StudentDao {

    /*
     * Insert the object in database
     * @param students, object to be inserted
     * return, how many rows successfully inserted. return insert rows size.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Student students);

    /*
     * update the object in database
     * @param students, object to be updated
     * return, how many rows affected by the transaction. return updated rows size.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Student students);

    /*
     * delete list of objects from database
     * @param students, array of objects to be deleted
     * return, how many rows affected by the transaction. return delete rows size.
     */
    @Delete
    int delete(Student students);

    @Query("DELETE FROM "+Constants.TABLE_NAME)
    int deleteAllNotes();

    /*
     * get list of objects from database
     */
    @Query("SELECT * FROM "+Constants.TABLE_NAME)
    public List<Student> getStudent();

    /*
     * get single object from database
     * @param students roll number
     */
    @Query("SELECT * FROM "+Constants.TABLE_NAME+" WHERE id = :id")
    public Student getStudent(Long id);

}
