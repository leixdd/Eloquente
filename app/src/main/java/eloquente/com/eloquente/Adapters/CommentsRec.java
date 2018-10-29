package eloquente.com.eloquente.Adapters;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import eloquente.com.eloquente.R;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class CommentsRec extends RecyclerView.Adapter<CommentsRec.ViewHolder> {

    private static final String TAG = "CommentsRec";

    private Context xContext;

    private ArrayList<String> reviewNameList  = new ArrayList<>();
    private ArrayList<String> reviewCommentList  = new ArrayList<>();
    private ArrayList<Float> reviewRateList  = new ArrayList<>();
    private ArrayList<String> reviewDateList  = new ArrayList<>();

    public CommentsRec(Context mContext, ArrayList<String> reviewNameList, ArrayList<String> reviewCommentList, ArrayList<Float> reviewRateList, ArrayList<String> reviewDateList) {
        this.xContext = mContext;
        this.reviewNameList = reviewNameList;
        this.reviewCommentList = reviewCommentList;
        this.reviewRateList = reviewRateList;
        this.reviewDateList = reviewDateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_reviews, viewGroup, false);
        CommentsRec.ViewHolder holder = new CommentsRec.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG, "Bind Calling From Comment Reviews");

        viewHolder.ReviewRate.setRating(reviewRateList.get(i));
        viewHolder.ReviewDate.setText(reviewDateList.get(i));
        viewHolder.ReviewComment.setText(reviewCommentList.get(i));
        viewHolder.ReviewName.setText(reviewNameList.get(i));

    }

    @Override
    public int getItemCount() {
        return reviewCommentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ReviewName;
        TextView ReviewComment;
        TextView ReviewDate;
        RatingBar ReviewRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ReviewName = itemView.findViewById(R.id.reviewName);
            ReviewComment = itemView.findViewById(R.id.reviewComment);
            ReviewDate = itemView.findViewById(R.id.reviewDate);
            ReviewRate = itemView.findViewById(R.id.ratingBar);

        }
    }
}
