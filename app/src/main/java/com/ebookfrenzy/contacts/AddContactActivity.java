package com.ebookfrenzy.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ebookfrenzy.contacts.databinding.AddContactFormBinding;
import com.ebookfrenzy.contacts.ui.main.ContactListAdapter;
import com.ebookfrenzy.contacts.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class AddContactActivity extends AppCompatActivity {
    private AddContactFormBinding binding;
    private MainViewModel mViewModel;
    private Button button;
    private ContactListAdapter adapter;
    String name;
    String phone;
    String email;
    String address;
    int id;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        super.onCreate(saveInstanceState);
        binding = AddContactFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //Save Button
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.addName.getText().toString();
                phone = binding.addPhone.getText().toString();
                email = binding.addEmail.getText().toString();
                address = binding.addAddress.getText().toString();

                //Insets new record to database
                if (!name.equals("") && !phone.equals("") && !email.equals("")
                        && !address.equals("")) {
                    Contact contact = new Contact(name, phone, email, address);
                    mViewModel.insertContact(contact);
                    goBackToMain();
                } else {
                    binding.addName.setHint("Field Required!");
                    binding.addPhone.setHint("Field Required!");
                    binding.addEmail.setHint("Field Required!");
                    binding.addAddress.setHint("Field Required!");
                }

            }
        });

        //Cancel Button
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToMain();
            }
        });
    }


    public void goBackToMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}

