package eloquente.com.eloquente.Modules;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.MainDash;
import eloquente.com.eloquente.Models.WebResponse;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ARIELLECIAS on 10/29/2018.
 */

public class EloPayment extends AppCompatActivity {

    private static final String TAG = "EloPayment";

    TextView packName;
    TextView packAmount;
    CircleImageView packImage;

    SharedPreferences prefs;
    RadioGroup rg;
    String schedID;


    //Pay
    Integer finalPay = 0;
    Integer totalPay = 0;
    Integer remaining = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_payment);

        prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);

        packName = findViewById(R.id.packName);
        packAmount = findViewById(R.id.packAmount);
        packImage = findViewById(R.id.packImage);

        packName.setText(getIntent().getStringExtra("packageName"));
        packAmount.setText(getIntent().getStringExtra("packageAmount"));
        schedID = getIntent().getStringExtra("schedID");
        packImage.setImageBitmap((Bitmap) getIntent().getParcelableExtra("packageImage"));

        Button btnX = findViewById(R.id.btnPayx);

        rg = findViewById(R.id.rdoPaymentMethod);


        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EloPayment.this);
                mBuilder.setTitle("Halt!");
                mBuilder.setMessage("Are you sure you want to continue?");
                mBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Pay();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

    }

    public void Pay(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Validating your Data...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        CardInputWidget mCardInputWidget = findViewById(R.id.cardwidget);
        // Remember to validate the card object before you use it to save time.
        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            Toast.makeText(getApplicationContext(), "Invalid Card Data", Toast.LENGTH_LONG).show();
        }
        cardToSave.setName(prefs.getString("name",null));

        Stripe stripe = new Stripe(this, "pk_test_zjf58hFlZJ0fuTEpRJrwGIxc");
        stripe.createToken(
                cardToSave,
                new TokenCallback() {
                    public void onSuccess(Token token) {

                        //Will send to Backend Server through HTTP Post Request

                        progressDialog.dismiss();

                        progressDialog.setTitle("Sending Information...");
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();

                        RadioButton rdo = findViewById(rg.getCheckedRadioButtonId());
                        totalPay =  Integer.parseInt(packAmount.getText().toString().replace("₱ ", "").replace(",", ""));

                        if(!rdo.getText().equals("Full Payment")) {
                            finalPay = totalPay / 2;
                            remaining = totalPay - finalPay;
                        }else {
                            finalPay = totalPay;
                            remaining = 0;
                        }

                        Log.d(TAG, "onSuccess: FINAL PAY = " + finalPay + ", Remaining = " + remaining + ", Total Amount = " + totalPay );


                        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                        SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);
                        Call<WebResponse> call = service.payment("createPayment", prefs.getString("username", null), token.getId(), schedID, String.valueOf(finalPay), String.valueOf(totalPay), String.valueOf(remaining));
                        Log.d(TAG, "onSuccess: TOKEN : " + token.getId());

                        call.enqueue(new Callback<WebResponse>() {
                            @Override
                            public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                                WebResponse w = response.body();
                                if(w.isSuccess()) {
                                    Intent intent = new Intent(getApplicationContext(), MainDash.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Transaction Complete : Successfully Charged", Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "onSuccess: Successfully Charged");
                                }else {
                                    Toast.makeText(getApplicationContext(), "Error, Something went wrong", Toast.LENGTH_LONG).show();
                                }

                                progressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<WebResponse> call, Throwable t) {

                            }
                        });
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getApplicationContext(),
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );

    }

}
