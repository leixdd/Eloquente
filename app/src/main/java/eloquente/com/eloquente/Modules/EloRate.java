package eloquente.com.eloquente.Modules;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.MainActivity;
import eloquente.com.eloquente.MainDash;
import eloquente.com.eloquente.Models.WebResponse;
import eloquente.com.eloquente.R;
import eloquente.com.eloquente.RegisterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EloRate extends AppCompatActivity {

    private static final String TAG = "EloRate";

    String id;

    CircleImageView imageView;
    TextView menuName;
    TextView comment;
    RatingBar rating;
    Button btnComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elo_rate);

         imageView = findViewById(R.id.imageMenu);
         menuName = findViewById(R.id.menuName);
         comment = findViewById(R.id.txtComment);
         rating = findViewById(R.id.ratingBar);

        imageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra("menuImage"));
        id = getIntent().getStringExtra("menuID");
        menuName.setText(getIntent().getStringExtra("menuName"));

        btnComment = findViewById(R.id.btnFeed);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertComment();
            }
        });



    }

    public void InsertComment(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);
        Call<WebResponse> call = service.comment("makeComment", id, prefs.getString("account_id", null), comment.getText().toString(), String.valueOf(rating.getRating()) );

        call.enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                WebResponse webResponse = response.body();
                Log.d(TAG, "onResponse: Log Response = " + webResponse.isSuccess());
                if(webResponse.isSuccess()) {

                    Intent intent = new Intent(getApplicationContext(), MainDash.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Successfully Rated", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<WebResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
