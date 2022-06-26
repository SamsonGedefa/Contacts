package com.ebookfrenzy.contacts;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


//Data access object class used to access the database
@Dao
public interface ContactDoa {
    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    //Returns id given name and phone
    @Query("SELECT * FROM contacts WHERE  name = :name OR phone = :phone")
    int getContactId(String name, String phone);

    //Updates record given id
    @Query("UPDATE contacts SET name = :name, phone=:phone, email=:email, address=:address  WHERE contactId =:id ")
    void updateContact(int id, String name, String phone, String email, String address);
}
