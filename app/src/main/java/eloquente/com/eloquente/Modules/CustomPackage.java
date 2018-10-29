package eloquente.com.eloquente.Modules;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.MainDash;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.WebResponse;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomPackage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "CustomPackage";

    ImageView setDateReserver;
    TextView txtDate;
    SharedPreferences prefs;

    Spinner txtStartTime;
    Spinner txtEndTime;
    TextView TotalAmount;

    Button btnSoupSelection;
    Button btnAppetizerSelection;
    Button btnSaladSelection;
    Button btnFishSelection;
    Button btnPastaSelection;
    Button btnBeefSelection;
    Button btnVegetablesSelection;
    Button btnChickenSelection;
    Button btnDessertSelection;
    Button btnDrinksSelection;
    Button btnInclusionsSelection;

    TextView txtInclusions;



    List<String> listItems = new ArrayList<>();
    boolean[] checkedItems;
    String[] packMenuLimit;
    ArrayList<Integer> SelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_package);

        prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);

        TotalAmount = findViewById(R.id.txtTotalAmount);
        TotalAmount.setText(getIntent().getStringExtra("totalAmount"));
        txtStartTime = findViewById(R.id.txtTimeFrom);
        txtEndTime = findViewById(R.id.txtTimeTo);

        //Button Inits
        btnSoupSelection = findViewById(R.id.selectingSoup);
        btnAppetizerSelection = findViewById(R.id.selectingAppetizer);
        btnSaladSelection = findViewById(R.id.selectingSalad);
        btnFishSelection = findViewById(R.id.selectingFish);
        btnPastaSelection = findViewById(R.id.selectingPasta);
        btnBeefSelection = findViewById(R.id.selectingBeef);
        btnVegetablesSelection = findViewById(R.id.selectingVegetables);
        btnChickenSelection = findViewById(R.id.selectingChicken);
        btnDessertSelection = findViewById(R.id.selectingDessert);
        btnDrinksSelection = findViewById(R.id.selectingDrinks);
        btnInclusionsSelection = findViewById(R.id.selectingInclusions);

        txtInclusions = findViewById(R.id.txtInclusions);

        btnSoupSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Soup", (TextView) findViewById(R.id.txtSoups));
            }
        });

        btnAppetizerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Appetizer", (TextView) findViewById(R.id.txtAppetizer));
            }

        });

        btnSaladSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Salad", (TextView) findViewById(R.id.txtSalad));
            }

        });

        btnBeefSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Beef", (TextView) findViewById(R.id.txtBeef));
            }

        });

        btnChickenSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Chicken", (TextView) findViewById(R.id.txtChicken));
            }

        });

        btnDessertSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Dessert", (TextView) findViewById(R.id.txtDessert));
            }

        });

        btnDrinksSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Drinks", (TextView) findViewById(R.id.txtDrinks));
            }

        });

        btnFishSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Fish", (TextView) findViewById(R.id.txtFish));
            }

        });

        btnPastaSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Pasta", (TextView) findViewById(R.id.txtPasta));
            }

        });

        btnVegetablesSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Vegetables", (TextView) findViewById(R.id.txtVegetables));
            }

        });

        btnInclusionsSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Inclusions", (TextView) findViewById(R.id.txtInclusions));
            }
        });

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, CustomPackage.this, Calendar.getInstance().getTime().getYear(), Calendar.getInstance().getTime().getDay(), Calendar.getInstance().getTime().getDate());

        setDateReserver = findViewById(R.id.setDate);
        txtDate = findViewById(R.id.txtDate);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 15);
        date = cal.getTime();

        datePickerDialog.getDatePicker().setMinDate(date.getTime());
        setDateReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        Button charge = findViewById(R.id.btnSelectPack);

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pay(view);
                reserve();
            }
        });
    }

    public void reserve(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sending your Reservation...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        String createJSON = "{";

        createJSON += "\"soup\" : \"" + ((TextView) findViewById(R.id.txtSoups)).getText() + "\"," +
                "\"appetizer\" : \"" + ((TextView) findViewById(R.id.txtAppetizer)).getText() + "\"," +
                "\"salad\" : \"" + ((TextView) findViewById(R.id.txtSalad)).getText() + "\"," +
                "\"fish\" : \"" + ((TextView) findViewById(R.id.txtFish)).getText() + "\"," +
                "\"pasta\" : \"" + ((TextView) findViewById(R.id.txtPasta)).getText() + "\"," +
                "\"beef\" : \"" + ((TextView) findViewById(R.id.txtBeef)).getText() + "\"," +
                "\"vegetables\" : \"" + ((TextView) findViewById(R.id.txtVegetables)).getText() + "\"," +
                "\"chicken\" : \"" + ((TextView) findViewById(R.id.txtChicken)).getText() + "\"," +
                "\"dessert\" : \"" + ((TextView) findViewById(R.id.txtDessert)).getText() + "\"," +
                "\"drinks\" : \"" + ((TextView) findViewById(R.id.txtDrinks)).getText() + "\"";

        createJSON += "}";

        String am = TotalAmount.getText().toString().replace(",", "");
        String fam = am.replace("₱ ", "");
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);
        Call<WebResponse> call = service.reservation("reservation",prefs.getString("account_id", null), "99", txtInclusions.getText().toString(), txtDate.getText().toString(), txtStartTime.getSelectedItem().toString(), txtEndTime.getSelectedItem().toString(), fam, createJSON);

        call.enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                WebResponse webResponse = response.body();
                Log.d(TAG, "onResponse: Log Response = " + webResponse.isSuccess());
                if(webResponse.isSuccess()) {
                    Intent intent = new Intent(getApplicationContext(), MainDash.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Reservation Success", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<WebResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        txtDate.setText(new StringBuilder().append(i).append("-").append(i1).append("-").append(i2));
    }

    String cat = "";
    TextView targetText;
    String[] finalSelection;

    Context context = this;
    int Counter;
    int Limit;
    public void OpenDialogSelection(final String category, TextView txt) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        targetText = txt;
        cat = category;
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Menus>> call = service.getMenuCat("getMenu", category);

        call.enqueue(new Callback<List<Menus>>() {
            @Override
            public void onResponse(Call<List<Menus>> call, Response<List<Menus>> response) {
                List<Menus> menus = response.body();

                for(Menus m : menus) {
                    listItems.add(m.getName());
                }


                checkedItems = new boolean[listItems.size()];
                finalSelection = Arrays.copyOf(listItems.toArray(), listItems.size(), String[].class);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CustomPackage.this);
                mBuilder.setTitle("Select " + cat);
                mBuilder.setMultiChoiceItems(finalSelection, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            SelectedItems.add(i);
                        }else{
                            SelectedItems.remove((Integer.valueOf(i)));
                        }
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        String item = "";

                        if(SelectedItems.size() != 0) {
                            for (int i = 0; i < SelectedItems.size(); i++) {
                                item = item + finalSelection[SelectedItems.get(i)];
                                if (i != SelectedItems.size() - 1) {
                                    item = item + ",\n";
                                }
                            }
                            targetText.setText(item);
                        }else{
                            Toast.makeText(context, "Must Select 1 item", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int x = 0; x < checkedItems.length; x++) {
                            checkedItems[x] = false;
                            SelectedItems.clear();
                            targetText.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Menus>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }


}
