package eloquente.com.eloquente.Modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Adapters.CommentsRec;
import eloquente.com.eloquente.Adapters.MenuRecycleAdapter;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.Reviews;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EloReviews extends AppCompatActivity {

    private static final String TAG = "EloReviews";

    private ArrayList<String> listComments = new ArrayList<>();
    private ArrayList<Float> listRatings = new ArrayList<>();
    private ArrayList<String> listNames = new ArrayList<>();
    private ArrayList<String> listDates  = new ArrayList<>();



    String id;

    private List<Reviews> DataSet = new ArrayList<>();
    private Button btnMake;

    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    CircleImageView imageView;
    RatingBar rate;
    TextView menuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elo_reviews);

        btnMake = findViewById(R.id.btnMakeReview);

         imageView = findViewById(R.id.image);
         rate = findViewById(R.id.ratingBar);
         menuName = findViewById(R.id.menuName);

        imageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra("menuImage"));
        menuName.setText(getIntent().getStringExtra("menuName"));
        rate.setRating(getIntent().getFloatExtra("menuRate", 0.0f));
        id = (getIntent().getStringExtra("menuID"));
        Log.d(TAG, "ID : " + id);



        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAlReviews();
            }
        });

        recyclerView = findViewById(R.id.recycleView);
        getAlReviews();


        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent wed = new Intent(view.getContext(), EloRate.class);
                wed.putExtra("menuName", menuName.getText());
                wed.putExtra("menuImage", ( (BitmapDrawable) imageView.getDrawable() ).getBitmap());
                wed.putExtra("menuID", id);
                startActivity(wed);
            }
        });
    }


    private void getAlReviews() {

        listComments = new ArrayList<>();
        listRatings = new ArrayList<>();
        listNames = new ArrayList<>();
        listDates  = new ArrayList<>();

        Log.d(TAG, "getAlReviews: getting Reviews");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Reviews>> call = service.getReviews("getAllComments", id);

        call.enqueue(new Callback<List<Reviews>>() {
            @Override
            public void onResponse(Call<List<Reviews>> call, Response<List<Reviews>> response) {
                DataSet = response.body();
                SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);

                for(Reviews r : DataSet) {

                    Log.d(TAG, "onResponse: " + r.getComments());
                    listComments.add(r.getComments());
                    listRatings.add(Float.parseFloat(r.getRatings()));
                    listDates.add(r.getDate());
                    listNames.add(r.getName());

                    Log.d(TAG, "onResponse: ACCOUNT ID " + prefs.getString("account_id", null));
                    Log.d(TAG, "onResponse: MENU ID " + r.getId());
                    if(r.getId().equals(prefs.getString("account_id", null))) {
                        btnMake.setVisibility(View.GONE);
                    }
                }

                OpenAdapter();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Reviews>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        onItemsLoadComplete();
    }

    public void OpenAdapter(){
        adapter = new CommentsRec(EloReviews.this, listNames, listComments, listRatings, listDates);
        Log.d(TAG, "OpenAdapter: Set Adapter" + adapter);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(EloReviews.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
