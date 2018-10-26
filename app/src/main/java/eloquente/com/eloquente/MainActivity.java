package eloquente.com.eloquente;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.Models.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("username", null);
        if (restoredText != null) {
            Intent login_intent = new Intent(getApplicationContext(), MainDash.class);
            startActivity(login_intent);
        }

        //CreateDialogURL();

        final EditText txtUsername = findViewById(R.id.txtUsername);
        final EditText txtPassword = findViewById(R.id.txtPassword);
        final TextView lblRegister = findViewById(R.id.lblRegister);

        final Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create Progress Dialog
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Logging in...");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                Call<Client> call = service.login(txtUsername.getText().toString(), txtPassword.getText().toString());

                call.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        if(response.isSuccessful()) {
                            if(response.body().getCode().equals("login_success")) {

                                Client clientModel = response.body();

                                SharedPreferences.Editor editor = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("name", clientModel.getFirstname() + " " + clientModel.getMiddlename() + " "  + clientModel.getSurname());
                                editor.putString("username", txtUsername.getText().toString());
                                editor.putString("account_id" , clientModel.getAccount_id());
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, MainDash.class);
                                startActivity(intent);

                                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!" + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CreateDialogURL() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set API URL - [ example: 127.0.0.1:8001 ]");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApiClient.setBaseUrl(input.getText().toString());
            }
        });

        builder.show();
    }
}
