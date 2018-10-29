package eloquente.com.eloquente.Modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Adapters.MenuRecycleAdapter;
import eloquente.com.eloquente.Adapters.ReservationAdapter;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.Reservations;
import eloquente.com.eloquente.Models.Reserve;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ARIELLECIAS on 10/29/2018.
 */

public class EloReservations extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<Reservations> DataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.elo_reservation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        context = view.getContext();

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllReserves();
            }
        });

        recyclerView = view.findViewById(R.id.recycleView);
        getAllReserves();



        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Your Reservations");
    }

    public void getAllReserves() {

        SharedPreferences prefs = context.getSharedPreferences(AppConstants.SHARED_PREFS_NAME, context.MODE_PRIVATE);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Reservations>> call = service.getAllReserves("getReservations", prefs.getString("account_id", null));

        call.enqueue(new Callback<List<Reservations>>() {
            @Override
            public void onResponse(Call<List<Reservations>> call, Response<List<Reservations>> response) {
                DataList = response.body();
                adapter = new ReservationAdapter(getContext(), DataList);
                recyclerView.setAdapter(adapter);
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Reservations>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
        onItemsLoadComplete();
    }


    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
