package com.ebookfrenzy.contacts;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class ContactRepository {
    private final MutableLiveData<List<Contact>> searchResults = new MutableLiveData<>();
    private final ContactDoa contactDoa;
    private List<Contact> results;
    private final LiveData<List<Contact>> allContacts;



    //Instantiate and pass context to room
    public ContactRepository(Application application) {
        ContactRoomDatabase db;
        db = ContactRoomDatabase.getDatabase(application);
        contactDoa = db.contactDoa();
        allContacts = contactDoa.getAllContacts();
    }

    //Inserts new contact
    //Used by Add contact activity class
    public void insertContacts(Contact newContact) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            contactDoa.insertContact(newContact);
        });
        executor.shutdown();
    }


    //To update database
    //Used by Update contact activity class
    public void updateContacts(int id, String name, String phone, String email, String address) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            contactDoa.updateContact(id,name, phone, email, address);
        });
        executor.shutdown();
    }
    //Returns all contact to be used in with the recycler
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

}
