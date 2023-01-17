package com.example.gestionfraimobileandroid.ui.getUsers;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gestionfraimobileandroid.Adapter.getUsersAdpater;
import com.example.gestionfraimobileandroid.DetailOfUser;
import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.User;
import com.example.gestionfraimobileandroid.databinding.FragmentAllUsersBinding;

import java.util.List;

public class AllUsersFragment extends Fragment {

    private static final String TAG = "AAAAAllUsers";

    private FragmentAllUsersBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAllUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        AllUsersViewModel mallUsersViewModel = new ViewModelProvider(this).get(AllUsersViewModel.class);

        // reception initial les données
        mallUsersViewModel.init();
        // instantiate the adapter
        getUsersAdpater adapter = new getUsersAdpater((MainActivity) getActivity());
        list = root.findViewById(R.id.ListUsers);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  OpenDetailTask(mTachesViewModel.getTask().getValue().get(position));
                OpenDetailuser(mallUsersViewModel.getUser().getValue().get(position));
            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mallUsersViewModel.getUser().observe(getActivity(), new Observer<List<User>>() {

            @Override
            public void onChanged(List<User> users) {
             //   Log.i(TAG, "onchanged in observer is called!");
              //  Log.i("data", users.toString());
                adapter.setObjects(users);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }


     void OpenDetailuser(User user) {
        Log.i(TAG, "OpenDetailuser: id = " + user.getId());
        Intent intent = new Intent(getActivity(), DetailOfUser.class);
        intent.putExtra("user_id", user.getId());

       //  intent.putExtra("user_id", user.getFirst_name());
       //  intent.putExtra("user_id", user.getPassword());
        startActivity(intent);
    }
}