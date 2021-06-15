package com.room.database.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.room.database.Constants;

@Database(entities = {Student.class}, version = Constants.DATABASE_VERSION, exportSchema = true)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static volatile MyRoomDatabase INSTANCE;

    public static MyRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null)
        {
            synchronized (MyRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDatabase.class,
                            Constants.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract StudentDao studentDao();

    public static void destroyInstance() {
        INSTANCE = null;
    }
}


