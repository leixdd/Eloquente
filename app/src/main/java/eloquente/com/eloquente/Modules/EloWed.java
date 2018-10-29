package eloquente.com.eloquente.Modules;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Adapters.CommentsRec;
import eloquente.com.eloquente.Adapters.PackagesRec;
import eloquente.com.eloquente.Models.Inclusion;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.Packages;
import eloquente.com.eloquente.Models.Services;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EloWed extends AppCompatActivity {

    private static final String TAG = "EloWed";

    String service_id;

    List<Packages> packages;
    List<Inclusion> inclusions;

    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_wedpack);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                generatePackage();
            }
        });

        service_id = getIntent().getStringExtra("service_id");
        recyclerView = findViewById(R.id.recycleView);
        generatePackage();

    }

    public void generatePackage() {

        Log.d(TAG, "generatePackage: Generating" );

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Services> call = service.getPackage("getPackage", service_id);

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Services services = response.body();

                packages = services.getPackages();
                inclusions = services.getInclusions();

                Log.d(TAG, "onResponse: Checking Existinces -> " + services.getPackages());

                OpenAdapter();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                progressDialog.dismiss();
            }

        });

        onItemsLoadComplete();
    }

    public void OpenAdapter(){
        adapter = new PackagesRec(this, packages, inclusions );
        Log.d(TAG, "OpenAdapter: Set Adapter" + adapter);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
