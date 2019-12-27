package com.winitsolutions.todolist;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 2)
public abstract  class UserDataBase extends RoomDatabase {
   public abstract UserDeo userDeo();

   private static UserDataBase mInstance;

    public static UserDataBase getmInstance(Context context) {
        if(mInstance==null){
            mInstance= Room.databaseBuilder(context, UserDataBase.class, "UserDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigrationFrom()
                    .addMigrations(migration_1_2)
                    .build();

        }

        return mInstance;
    }



    static final Migration migration_1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table User add column phone_numbe TEXT");
//            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'age' INTEGER NOT NULL");
            Log.d("VROM","migration_1_2");

        }
    };
}
