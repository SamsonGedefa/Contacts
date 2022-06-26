package com.ebookfrenzy.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebookfrenzy.contacts.databinding.AddContactFormBinding;
import com.ebookfrenzy.contacts.databinding.ContactListItemBinding;
import com.ebookfrenzy.contacts.databinding.MainFragmentBinding;
import com.ebookfrenzy.contacts.ui.main.ContactListAdapter;
import com.ebookfrenzy.contacts.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private ContactListItemBinding binding2;
    private ContactListAdapter adapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = MainFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        //Floating Action Button
        button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddContactActivity();
            }
        });
        observerSetup();
        recyclerSetup();
    }



    //Opens update screen and pass the values from the recycler to update screen to populate it's text view
    public void openUpdateActivity(View v){
        TextView name  = v.findViewById(R.id.name);
        TextView phone = v.findViewById(R.id.phone);
        TextView email = v.findViewById(R.id.email);
        TextView address = v.findViewById(R.id.address);


        Intent i = new Intent(this, UpdateContactActivity.class);
        i.putExtra("name", name.getText().toString());
        i.putExtra("phone", phone.getText().toString());
        i.putExtra("email", email.getText().toString());
        i.putExtra("address", address.getText().toString());
        startActivity(i);


    }

    //Opens add contact screen
    public void openAddContactActivity(){
        Intent i = new Intent(this, AddContactActivity.class);
        startActivity(i);
    }


    //Updates adapter
    private void observerSetup() {
        mViewModel.getAllContacts().observe(this,
                new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(@Nullable final List<Contact> contacts) {
                        adapter.setContactList(contacts);
                    }
                });
    }

    //Initiate adapter and pass the layout
    private void recyclerSetup() {
        adapter = new ContactListAdapter(R.layout.contact_list_item);
        binding.productRecycler.setLayoutManager(
                new LinearLayoutManager(this));
        binding.productRecycler.setAdapter(adapter);
    }
}