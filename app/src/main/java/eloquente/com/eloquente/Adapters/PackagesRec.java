package eloquente.com.eloquente.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import eloquente.com.eloquente.Models.Inclusion;
import eloquente.com.eloquente.Models.Packages;
import eloquente.com.eloquente.Modules.FixedReservation;
import eloquente.com.eloquente.R;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class PackagesRec extends RecyclerView.Adapter<PackagesRec.ViewHolder> {

    private static final String TAG = "PackagesRec";

    private ArrayList<String> packageName  = new ArrayList<>();
    private ArrayList<String> packagePrice  = new ArrayList<>();
    private ArrayList<String> packageOtherText  = new ArrayList<>();
    private ArrayList<String> packageID  = new ArrayList<>();

    private List<Packages> packs = new ArrayList<>();
    private List<Inclusion> inclusions = new ArrayList<>();

    private Context Context;

    public PackagesRec(Context mContext, List packs, List inclusions) {
        this.Context = mContext;
        this.packs = packs;
        this.inclusions = inclusions;
    }

    @NonNull
    @Override
    public PackagesRec.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pac_rec, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    String totalAmount;
    @Override
    public void onBindViewHolder(@NonNull final PackagesRec.ViewHolder viewHolder, int i) {
        Log.d(TAG, "Bind: Called");

        Packages finalPacks = packs.get(i);

        Double mayPasobraSiMayor = Double.parseDouble(finalPacks.getAmount()) * (Double.parseDouble(finalPacks.getPax()) * 0.1) ;
        Double fin = (Double.parseDouble(finalPacks.getAmount()) * Double.parseDouble(finalPacks.getPax()) + mayPasobraSiMayor + 10000);
        totalAmount = "₱ " + NumberFormat.getInstance().format(fin);

        String otherText =
                " - " + finalPacks.getPax() + "pax minimum guaranteed\n" +
                        " - Choice of " + finalPacks.getSoup() + " soup\n" +
                        " - Choice of " + finalPacks.getAppetizer() + " appetizer\n" +
                        " - Choice of " + finalPacks.getSalad() + " salad\n" +
                        " - Choice of " + finalPacks.getFish() + " fish\n" +
                        " - Choice of " + finalPacks.getPasta() + " pasta\n" +
                        " - Choice of " + finalPacks.getBeef() + " beef\n" +
                        " - Choice of " + finalPacks.getVegetables() + " vegetables\n" +
                        " - Choice of " + finalPacks.getChicken() + " chicken\n" +
                        " - Choice of " + finalPacks.getDessert() + " dessert\n" +
                        " - Choice of " + finalPacks.getDrinks() + " drinks\n" +
                        "\n" +
                        "Inclusions: \n\n"
                ;

        StringBuilder s = new StringBuilder();

        for(Inclusion inc : inclusions) {
            Log.d(TAG, "onBindViewHolder: inclusions " + inc.getInclusions());
            s.append(inc.getInclusions()).append("\n");
        }

        String fx = otherText + s;

        String limits = finalPacks.getSoup() + "," +
                finalPacks.getAppetizer() + "," +
                finalPacks.getSalad() + "," +
                finalPacks.getFish() + "," +
                finalPacks.getPasta() + "," +
                finalPacks.getBeef() + "," +
                finalPacks.getVegetables() + "," +
                finalPacks.getChicken() + "," +
                finalPacks.getDessert() + "," +
                finalPacks.getDrinks() + ",";

        viewHolder.PackMenuLimit.setText(limits);
        viewHolder.PackageOther.setText(fx);
        viewHolder.PackagePrice.setText(String.valueOf(totalAmount));
        viewHolder.PackageTitle.setText(finalPacks.getPackage_name());
        viewHolder.PackageID.setText(finalPacks.getPackageID());
        viewHolder.PackageInclusions.setText(s);

        //whenclick

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, FixedReservation.class);
                intent.putExtra("packName", viewHolder.PackageTitle.getText());
                intent.putExtra("packID", viewHolder.PackageID.getText());
                intent.putExtra("totalAmount", viewHolder.PackagePrice.getText());
                intent.putExtra("inclusions", viewHolder.PackageInclusions.getText());
                intent.putExtra("limits", viewHolder.PackMenuLimit.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView PackageOther;
        TextView PackageTitle;
        TextView PackagePrice;
        TextView PackageID;
        Button button;
        TextView PackageInclusions;
        TextView PackMenuLimit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            PackageOther = itemView.findViewById(R.id.packageOtherText);
            PackageTitle = itemView.findViewById(R.id.packageTitle);
            PackagePrice = itemView.findViewById(R.id.packagePrice);
            PackageID = itemView.findViewById(R.id.packageID);
            PackageInclusions = itemView.findViewById(R.id.packageInc);
            button = itemView.findViewById(R.id.btnSelectPack);
            PackMenuLimit = itemView.findViewById(R.id.menuLimit);
        }
    }
}
