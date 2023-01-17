package com.example.gestionfraimobileandroid.ui.AcceptedRequests;

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

import com.example.gestionfraimobileandroid.Adapter.AcceptedRequestAdapter;
import com.example.gestionfraimobileandroid.Adapter.AllRequestAdapter;
import com.example.gestionfraimobileandroid.DetailRequest;
import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.databinding.FragmentAcceptedRequestsBinding;
import com.example.gestionfraimobileandroid.databinding.FragmentAllRequestBinding;
import com.example.gestionfraimobileandroid.ui.AllRequest.AllRequestViewModel;

import java.util.List;

public class AcceptedRequestsFragment  extends Fragment {

    private static final String TAG = "AllCamion";

    private FragmentAcceptedRequestsBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAcceptedRequestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        AcceptedRequestsViewModel macceptedRequestsViewModel = new ViewModelProvider(this).get(AcceptedRequestsViewModel.class);

        // reception initial les données
        macceptedRequestsViewModel.init();
        // instantiate the adapter
        AcceptedRequestAdapter adapter = new AcceptedRequestAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.AcceptedList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  OpenDetailTask(mTachesViewModel.getTask().getValue().get(position));
                OpenDetailrequest(macceptedRequestsViewModel.getRequests().getValue().get(position));
            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        macceptedRequestsViewModel.getRequests().observe(getActivity(), new Observer<List<Request>>(){

            @Override
            public void onChanged(List<Request> requests) {
                Log.i(TAG, "onchanged in observer is called!");
                Log.i("data", requests.toString());
                adapter.setObjects(requests);
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