package com.ebookfrenzy.contacts;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Dao;
import com.ebookfrenzy.contacts.databinding.ContactListItemBinding;
import com.ebookfrenzy.contacts.databinding.UpdateContactFormBinding;
import com.ebookfrenzy.contacts.ui.main.MainViewModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;






public class UpdateContactActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private UpdateContactFormBinding binding;

    //To hold data that's sent from MainActivity
    String name;
    String phone;
    String email;
    String address;

    String updateName;
    String updatePhone;
    String updateEmail;
    String updateAddress;
    private ContactDoa contactDoa; //To access the database


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = UpdateContactFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Receives the data from MainActivity that's associated with particular view(Row)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            phone = extras.getString("phone");
            email = extras.getString("email");
            address = extras.getString("address");

            //Bind the values to text fields in update screen
            binding.updateName.setText(name);
            binding.updatePhone.setText(phone);
            binding.updateEmail.setText(email);
            binding.updateAddress.setText(address);
        }

        //Update button
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToMain();
                getId();
            }
        });

        //Call button
        binding.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCall();
            }
        });


        //Email Button
        binding.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEmail();
            }
        });
    }

    //Return user to home screen after updating contact
    public void goBackToMain(){

        //Get the values of update view to be used later
        updateName = binding.updateName.getText().toString();
        updatePhone = binding.updatePhone.getText().toString();
        updateEmail = binding.updateEmail.getText().toString();
        updateAddress = binding.updateAddress.getText().toString();

        //To go back to home screen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //Return an Id of contact from database
    //Use id to update the database record
    public void getId() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            //Get the id of the contact to be used to update the row given the name and phone value
            ContactRoomDatabase db;
            db = ContactRoomDatabase.getDatabase(this.getApplicationContext());
            contactDoa = db.contactDoa();
            int id = contactDoa.getContactId(name, phone);

            //Calls Dao method to update the contact
            mViewModel.updateContact(id, updateName, updatePhone, updateEmail, updateAddress);
        });

        executor.shutdown();
    }

    public void startCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phone));
        startActivity(intent);
    }

    public void startEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        startActivity(Intent.createChooser(emailIntent, "Choose"));
    }
}
