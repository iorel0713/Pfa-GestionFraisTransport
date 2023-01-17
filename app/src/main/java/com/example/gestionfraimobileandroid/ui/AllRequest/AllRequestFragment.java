package com.example.gestionfraimobileandroid.ui.AllRequest;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.RecoverySystem;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gestionfraimobileandroid.Adapter.AllRequestAdapter;
import com.example.gestionfraimobileandroid.DetailRequest;
import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.databinding.FragmentAllRequestBinding;

import java.util.List;
import java.util.Observable;


public class AllRequestFragment extends Fragment {

    private static final String TAG = "AllCamion";

    private FragmentAllRequestBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAllRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        AllRequestViewModel mAllRequestViewModel = new ViewModelProvider(this).get(AllRequestViewModel.class);

        // reception initial les données
        mAllRequestViewModel.init();
        // instantiate the adapter
        AllRequestAdapter adapter = new AllRequestAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.AllRequestList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              //  OpenDetailTask(mTachesViewModel.getTask().getValue().get(position));
                OpenDetailrequest(mAllRequestViewModel.getRequests().getValue().get(position));
            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mAllRequestViewModel.getRequests().observe(getActivity(), new Observer<List<Request>>(){

            @Override
            public void onChanged(List<Request> camions) {
                Log.i(TAG, "onchanged in observer is called!");
                Log.i("data", camions.toString());
                adapter.setObjects(camions);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void OpenDetailrequest(Request request) {
        Log.i(TAG, "OpenDetailrequest: id = " + request.getId());
        Intent intent = new Intent(getActivity(), DetailRequest.class);

        intent.putExtra("request_id", request.getId());

        startActivity(intent);
    }


}