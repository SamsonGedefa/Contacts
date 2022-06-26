package com.ebookfrenzy.contacts.ui.main;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ebookfrenzy.contacts.Contact;
import com.ebookfrenzy.contacts.MainActivity;
import com.ebookfrenzy.contacts.R;
import com.ebookfrenzy.contacts.Contact;
import java.util.List;

//Adapter class to provide contacts to the recycler when initiated in MainActivity
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private final int contactItemLayout;
    private List<Contact> contactList;


    public ContactListAdapter(int layoutId) {
        contactItemLayout = layoutId;
    }
    public void setContactList(List<Contact> contacts) {
        contactList = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(contactItemLayout, parent, false);
        return new ViewHolder(view);
    }

    //Method to add a new row to recycler when the user scrolls
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.setName;
        TextView item2 = holder.setPhone;
        TextView item3 = holder.setEmail;
        TextView item4 = holder.setAddress;


        //Creates new row as user scroll
        item.setText(contactList.get(listPosition).getName());
        item2.setText(contactList.get(listPosition).getPhone());
        item3.setText(contactList.get(listPosition).getEmail());
        item4.setText(contactList.get(listPosition).getAddress());
    }



    //Find the element in the recycler view
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView setName;
        TextView setPhone;
        TextView setEmail;
        TextView setAddress;
        ViewHolder(View itemView) {
            super(itemView);
            setName = itemView.findViewById(R.id.name);
            setPhone = itemView.findViewById(R.id.phone);
            setEmail = itemView.findViewById(R.id.email);
            setAddress = itemView.findViewById(R.id.address);

        }
    }
}
