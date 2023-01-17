package com.example.gestionfraimobileandroid.ui.RefusedRequest;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.gestionfraimobileandroid.Adapter.RefusedRequestAdapter;
import com.example.gestionfraimobileandroid.DetailRequest;
import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.Request;

import com.example.gestionfraimobileandroid.databinding.FragmentRefusedRequestBinding;


import java.util.List;

public class RefusedRequestFragment extends Fragment {

    private static final String TAG = "RefusedRequest";

    private FragmentRefusedRequestBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRefusedRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
       RefusedRequestViewModel mRefusedRequestViewModel = new ViewModelProvider(this).get(RefusedRequestViewModel.class);

        // reception initial les données
        mRefusedRequestViewModel.init();
        // instantiate the adapter
        RefusedRequestAdapter adapter = new RefusedRequestAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.RefusedList);///////////////
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  OpenDetailTask(mTachesViewModel.getTask().getValue().get(position));
                OpenDetailrequest(mRefusedRequestViewModel.getRequests().getValue().get(position));
            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mRefusedRequestViewModel.getRequests().observe(getActivity(), new Observer<List<Request>>(){

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