package eloquente.com.eloquente.Modules;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
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

import com.stripe.android.RequestOptions;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.API.ApiInterface;
import eloquente.com.eloquente.AppConstants;
import eloquente.com.eloquente.MainDash;
import eloquente.com.eloquente.Models.Inclusion;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.WebResponse;
import eloquente.com.eloquente.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixedReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String TAG = "FixedReservation";

    ImageView setDateReserver;
    TextView txtDate;
    SharedPreferences prefs;
    TextView txtPackName;

    String txtPackID;

    TextView txtInclusions;
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


    List<String> listItems = new ArrayList<>();
    boolean[] checkedItems;
    String[] packMenuLimit;
    ArrayList<Integer> SelectedItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_reservation);


        prefs = getSharedPreferences(AppConstants.SHARED_PREFS_NAME, MODE_PRIVATE);

        txtPackName = findViewById(R.id.txtPackage);
        TotalAmount = findViewById(R.id.txtTotalAmount);
        txtPackID = getIntent().getStringExtra("packID");
        txtInclusions = findViewById(R.id.txtInclusions);
        txtInclusions.setText(getIntent().getStringExtra("inclusions"));
        txtPackName.setText(getIntent().getStringExtra("packName"));
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


        String xIntentLimit = getIntent().getStringExtra("limits");
        packMenuLimit = xIntentLimit.split(",");

        //Soup Selection
        btnSoupSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Soup", (TextView) findViewById(R.id.txtSoups), Integer.parseInt(packMenuLimit[0]));
            }
        });

        btnAppetizerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Appetizer", (TextView) findViewById(R.id.txtAppetizer), Integer.parseInt(packMenuLimit[1]));
            }

        });

        btnSaladSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Salad", (TextView) findViewById(R.id.txtSalad), Integer.parseInt(packMenuLimit[2]));
            }

        });

        btnBeefSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Beef", (TextView) findViewById(R.id.txtBeef), Integer.parseInt(packMenuLimit[5]));
            }

        });

        btnChickenSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Chicken", (TextView) findViewById(R.id.txtChicken), Integer.parseInt(packMenuLimit[7]));
            }

        });

        btnDessertSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Dessert", (TextView) findViewById(R.id.txtDessert), Integer.parseInt(packMenuLimit[8]));
            }

        });

        btnDrinksSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Drinks", (TextView) findViewById(R.id.txtDrinks), Integer.parseInt(packMenuLimit[9]));
            }

        });

        btnFishSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Fish", (TextView) findViewById(R.id.txtFish), Integer.parseInt(packMenuLimit[3]));
            }

        });

        btnPastaSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Pasta", (TextView) findViewById(R.id.txtPasta), Integer.parseInt(packMenuLimit[4]));
            }

        });

        btnVegetablesSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.clear();
                SelectedItems.clear();
                OpenDialogSelection("Vegetables", (TextView) findViewById(R.id.txtVegetables), Integer.parseInt(packMenuLimit[6]));
            }

        });
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, FixedReservation.this, Calendar.getInstance().getTime().getYear(), Calendar.getInstance().getTime().getDay(), Calendar.getInstance().getTime().getDate());

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
        Call<WebResponse> call = service.reservation("reservation",prefs.getString("account_id", null), txtPackID, txtInclusions.getText().toString(), txtDate.getText().toString(), txtStartTime.getSelectedItem().toString(), txtEndTime.getSelectedItem().toString(), fam, createJSON);

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    String cat = "";
    TextView targetText;
    String[] finalSelection;

    Context context = this;
    int Counter;
    int Limit;
    public void OpenDialogSelection(final String category, TextView txt, final int menulimit) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        targetText = txt;
        cat = category;
        Limit = menulimit;
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Menus>> call = service.getMenuCat("getMenu", category);

        call.enqueue(new Callback<List<Menus>>() {
            @Override
            public void onResponse(Call<List<Menus>> call, Response<List<Menus>> response) {
                List<Menus> menus = response.body();

                Counter = 0;
                for(Menus m : menus) {
                    listItems.add(m.getName());
                    Counter += 1;
                }

                if(Limit > Counter) {
                    Limit = Counter;
                }

                checkedItems = new boolean[listItems.size()];
                finalSelection = Arrays.copyOf(listItems.toArray(), listItems.size(), String[].class);


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FixedReservation.this);
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

                        if(Limit == SelectedItems.size()) {
                            for (int i = 0; i < SelectedItems.size(); i++) {
                                item = item + finalSelection[SelectedItems.get(i)];
                                if (i != SelectedItems.size() - 1) {
                                    item = item + ",\n";
                                }
                            }
                            targetText.setText(item);
                        }else{
                            Toast.makeText(context, "Must Select " + Limit + " items", Toast.LENGTH_LONG).show();
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
                mDialog.setTitle(category + " [ Select " + Limit  + " Items ]" );
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
