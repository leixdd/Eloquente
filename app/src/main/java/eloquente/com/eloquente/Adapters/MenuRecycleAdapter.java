package eloquente.com.eloquente.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.Modules.EloReviews;
import eloquente.com.eloquente.R;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class MenuRecycleAdapter extends RecyclerView.Adapter<MenuRecycleAdapter.ViewHolder> {

    private static final String TAG = "MenuRecycleAdapter";

    Context context;

    private ArrayList<String> s_menuNames  = new ArrayList<>();
    private ArrayList<String> s_menuImages  = new ArrayList<>();
    private ArrayList<String> s_menuRatings  = new ArrayList<>();
    private ArrayList<String> s_menuID = new ArrayList<>();
    private ArrayList<String> s_reviews = new ArrayList<>();

    private Context mContext;

    public MenuRecycleAdapter(Context mContext, ArrayList<String> s_menuNames, ArrayList<String> s_menuImages, ArrayList<String> s_menuRatings, ArrayList<String> s_menuID, ArrayList<String> review) {
        this.s_menuNames = s_menuNames;
        this.s_menuImages = s_menuImages;
        this.s_menuRatings = s_menuRatings;
        this.mContext = mContext;
        this.s_menuID = s_menuID;
        this.s_reviews = review;
    }

    @NonNull
    @Override
    public MenuRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menureclayout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuRecycleAdapter.ViewHolder viewHolder, int i) {
        Log.d(TAG, "Bind: Called");

        Glide.with(mContext)
                .asBitmap()
                .load(s_menuImages.get(i))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_wallpaper_black_24dp).error(R.drawable.ic_wallpaper_black_24dp))
                .into(viewHolder.MenuImage);


        viewHolder.MenuName.setText(s_menuNames.get(i));

        viewHolder.ratingBar.setRating(Float.parseFloat(s_menuRatings.get(i)));

        viewHolder.menuID.setText(s_menuID.get(i));

        String revNum = s_reviews.get(i) == null ? "0"  : s_reviews.get(i);
        String rev = s_reviews.get(i) + (Integer.parseInt(revNum) == 1 ? " Review " : " Reviews");
        viewHolder.reviews.setText(rev);

        //whenclick
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                Intent wed = new Intent(view.getContext(), EloReviews.class);

                wed.putExtra("menuRate", viewHolder.ratingBar.getRating());
                wed.putExtra("menuImage", ((BitmapDrawable) viewHolder.MenuImage.getDrawable()).getBitmap() );
                wed.putExtra("menuName", viewHolder.MenuName.getText());
                wed.putExtra("menuID", viewHolder.menuID.getText());

                context.startActivity(wed);
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
        TextView menuID;
        Button reviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MenuImage = itemView.findViewById(R.id.image);
            MenuName = itemView.findViewById(R.id.menuName);
            relativeLayout = itemView.findViewById(R.id.mainRel);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            menuID = itemView.findViewById(R.id.menuID);
            reviews = itemView.findViewById(R.id.reviews);

        }
    }
}
