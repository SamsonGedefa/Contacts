package com.ebookfrenzy.contacts.ui.main;
import androidx.lifecycle.LiveData;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.ebookfrenzy.contacts.ContactRepository;
import com.ebookfrenzy.contacts.Contact;

import java.util.List;

//Class to interact with repository methods
public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ContactRepository repository;
    private LiveData<List<Contact>> allContacts;
    private int id;

    //When class in initiated in main fragment, a repository instance is created to access it's methods
    //the application context is passed to repository class
    public MainViewModel (Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();

    }

    //Returns all todo to the main fragment ui
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }
    //method Initiated in main fragment that calls repository method to insert todo to database
    public void insertContact(Contact contact) {
        repository.insertContacts(contact);
    }
    public void updateContact(int id, String name, String phone, String email, String address) {
        repository.updateContacts(id,name, phone, email, address);
    }

}