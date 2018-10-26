package eloquente.com.eloquente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Models.Client;
import eloquente.com.eloquente.Models.WebResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("username", null);
        if (restoredText != null) {
            Intent login_intent = new Intent(getApplicationContext(), MainDash.class);
            startActivity(login_intent);
        }

        final EditText txtEmail = findViewById(R.id.txtEmail);
        final EditText txtFN = findViewById(R.id.txtFirstName);
        final EditText txtLN = findViewById(R.id.txtLastName);
        final EditText txtMN = findViewById(R.id.txtMiddleName);
        final EditText txtConfirmPass = findViewById(R.id.txtConfirmPass);
        final EditText txtPass = findViewById(R.id.txtPassword);
        final EditText txtPhone = findViewById(R.id.txtPhone);

        final Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setTitle("Processing your credentials");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();



                if(!txtPass.getText().toString().equals(txtConfirmPass.getText().toString())) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                }else {
                    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                    Call<WebResponse> call = service.register(
                            txtEmail.getText().toString(),
                            txtPass.getText().toString(),
                            txtFN.getText().toString(),
                            txtLN.getText().toString(),
                            txtMN.getText().toString(),
                            txtPhone.getText().toString()
                    );

                    call.enqueue(new Callback<WebResponse>() {
                        @Override
                        public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                            WebResponse webResponse = response.body();
                            if(webResponse.isSuccess()) {

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(RegisterActivity.this, "Register successful!, You could now logged in", Toast.LENGTH_SHORT).show();
                            }else{

                                Toast.makeText(RegisterActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }


                        @Override
                        public void onFailure(Call<WebResponse> call, Throwable t) {

                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
