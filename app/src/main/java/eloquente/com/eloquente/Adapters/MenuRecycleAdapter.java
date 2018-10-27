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

public class MenuRecycleAdapter extends RecyclerView.Adapter<MenuRecycleAdapter.ViewHolder> {

    private static final String TAG = "MenuRecycleAdapter";

    private ArrayList<String> s_menuNames  = new ArrayList<>();
    private ArrayList<String> s_menuImages  = new ArrayList<>();
    private ArrayList<String> s_menuRatings  = new ArrayList<>();

    private Context mContext;

    public MenuRecycleAdapter(Context mContext, ArrayList<String> s_menuNames, ArrayList<String> s_menuImages, ArrayList<String> s_menuRatings) {
        this.s_menuNames = s_menuNames;
        this.s_menuImages = s_menuImages;
        this.s_menuRatings = s_menuRatings;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MenuRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menureclayout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecycleAdapter.ViewHolder viewHolder, int i) {
        Log.d(TAG, "Bind: Called");
        Glide.with(mContext)
                .asBitmap()
                .load(s_menuImages.get(i))
                .into(viewHolder.MenuImage);

        viewHolder.MenuName.setText(s_menuNames.get(i));

        viewHolder.ratingBar.setRating(Float.parseFloat(s_menuRatings.get(i)));

        //whenclick
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return s_menuNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView MenuImage;
        TextView MenuName;
        RelativeLayout relativeLayout;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MenuImage = itemView.findViewById(R.id.image);
            MenuName = itemView.findViewById(R.id.menuName);
            relativeLayout = itemView.findViewById(R.id.mainRel);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }
}
