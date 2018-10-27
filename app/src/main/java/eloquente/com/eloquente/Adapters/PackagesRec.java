package eloquente.com.eloquente.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.R;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class PackagesRec extends RecyclerView.Adapter<PackagesRec.ViewHolder> {

    private static final String TAG = "PackagesRec";

    private ArrayList<String> packageName  = new ArrayList<>();
    private ArrayList<String> packagePrice  = new ArrayList<>();
    private ArrayList<String> packageOtherText  = new ArrayList<>();

    private Context Context;

    public PackagesRec( Context mContext, ArrayList<String> packageName, ArrayList<String> packagePrice, ArrayList<String> packageOtherText) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageOtherText = packageOtherText;
        this.Context = mContext;
    }

    @NonNull
    @Override
    public PackagesRec.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pac_rec, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PackagesRec.ViewHolder viewHolder, int i) {
        Log.d(TAG, "Bind: Called");

        viewHolder.PackageOther.setText(packageOtherText.get(i));
        viewHolder.PackagePrice.setText(packagePrice.get(i));
        viewHolder.PackageTitle.setText(packageName.get(i));

        //whenclick
    }

    @Override
    public int getItemCount() {
        return packageName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView PackageOther;
        TextView PackageTitle;
        TextView PackagePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            PackageOther = itemView.findViewById(R.id.packageOtherText);
            PackageTitle = itemView.findViewById(R.id.packageTitle);
            PackagePrice = itemView.findViewById(R.id.packagePrice);

        }
    }
}
