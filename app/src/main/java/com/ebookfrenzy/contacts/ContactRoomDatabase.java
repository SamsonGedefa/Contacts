package com.ebookfrenzy.contacts;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Class to create the database when initiated in the repository
@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDoa contactDoa();
    private static ContactRoomDatabase INSTANCE;
    static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    ContactRoomDatabase.class,
                                    "new_contact_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
