package eloquente.com.eloquente.Modules;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Adapters.MenuRecycleAdapter;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class EloMenu extends Fragment {

    private static final String TAG = "EloMenu";


    private ArrayList<String> listImages = new ArrayList<>();
    private ArrayList<String> listNames = new ArrayList<>();
    private ArrayList<String> listRatings = new ArrayList<>();
    private ArrayList<String> listID = new ArrayList<>();
    private ArrayList<String> listReviews = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Menus> DataSet = new ArrayList<>();

    private ArrayAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.elo_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllMenu();
            }
        });

        recyclerView = view.findViewById(R.id.recycleView);
        getAllMenu();

        Spinner spinnerName = view.findViewById(R.id.spinner);

        mAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, R.array.Categories) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                TextView txt = (TextView) super.getView(position, convertView, parent);

                txt.setTextColor(Color.parseColor("#FFFFFF"));
                return txt;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView txt = (TextView) super.getView(position, convertView, parent);

                txt.setTextColor(Color.parseColor("#FFFFFF"));

                return txt;
            }
        };

        spinnerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosenOption = (String) parent.getItemAtPosition(position);
                getCatMenu(chosenOption);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Eloquente Menus");
        //recyclerView.setHasFixedSize(true);

    }

    public void getCatMenu(String cat){
        Log.d(TAG, "getting menu");

        listImages = new ArrayList<>();
        listNames = new ArrayList<>();
        listRatings = new ArrayList<>();
        listID = new ArrayList<>();
        listReviews = new ArrayList<>();

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Menus>> call = service.getMenuCat("getMenu", cat);

        call.enqueue(new Callback<List<Menus>>() {

            @Override
            public void onResponse(Call<List<Menus>> call, Response<List<Menus>> response) {
                DataSet = response.body();

                for (Menus m : DataSet) {
                    listImages.add(ApiClient.BASE_URL + "img/menu/" + m.getImage());
                    listNames.add(m.getName());
                    listRatings.add(m.getRatings());
                    listID.add(m.getId());
                    listReviews.add(m.getReviews());
                }

                adapter = new MenuRecycleAdapter(getContext(), listNames, listImages, listRatings, listID, listReviews);
                recyclerView.setAdapter(adapter);
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Menus>> call, Throwable t) {
                progressDialog.dismiss();
            }

        });


        onItemsLoadComplete();
    }

    public void getAllMenu(){
        Log.d(TAG, "getting menu");

         listImages = new ArrayList<>();
         listNames = new ArrayList<>();
         listRatings = new ArrayList<>();
         listID = new ArrayList<>();
         listReviews = new ArrayList<>();


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Menus>> call = service.getAllMenus("getAllMenu");

        call.enqueue(new Callback<List<Menus>>() {

            @Override
            public void onResponse(Call<List<Menus>> call, Response<List<Menus>> response) {
                DataSet = response.body();

                for (Menus m : DataSet) {
                    listImages.add(ApiClient.BASE_URL + "img/menu/" + m.getImage());
                    listNames.add(m.getName());
                    listRatings.add(m.getRatings());
                    listID.add(m.getId());
                    listReviews.add(m.getReviews());
                }

                adapter = new MenuRecycleAdapter(getContext(), listNames, listImages, listRatings, listID, listReviews);
                recyclerView.setAdapter(adapter);
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

            }

            @Override
            public void onFailure(Call<List<Menus>> call, Throwable t) {
            }

        });

        onItemsLoadComplete();
        progressDialog.dismiss();
    }

    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
