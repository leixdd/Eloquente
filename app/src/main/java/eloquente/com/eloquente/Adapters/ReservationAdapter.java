package eloquente.com.eloquente.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eloquente.com.eloquente.API.ApiClient;
import eloquente.com.eloquente.Models.Reservations;
import eloquente.com.eloquente.Models.Reserve;
import eloquente.com.eloquente.Modules.EloPayment;
import eloquente.com.eloquente.R;

/**
 * Created by ARIELLECIAS on 10/29/2018.
 */

public class ReservationAdapter  extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    Context mContext;
    List<Reservations> reservationList;

    public ReservationAdapter(Context mContext, List<Reservations> reservationList) {
        this.mContext = mContext;
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_listreserve, viewGroup, false);
        ReservationAdapter.ViewHolder holder = new ReservationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Reservations reservation = reservationList.get(i);

        Glide.with(mContext)
                .asBitmap()
                .load(ApiClient.BASE_URL + "img/menu/" + reservation.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_wallpaper_black_24dp).error(R.drawable.ic_wallpaper_black_24dp))
                .into(viewHolder.packImage);

        String fam = "₱ " + NumberFormat.getInstance().format(Double.parseDouble(reservation.getFx_totalAmount()));
        viewHolder.packSchedID.setText(reservation.getFx_sched_id());
        viewHolder.packAmount.setText(fam);
        viewHolder.packName.setText(reservation.getName());



        viewHolder.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EloPayment.class);
                intent.putExtra("packageName", viewHolder.packName.getText());
                intent.putExtra("packageAmount", viewHolder.packAmount.getText());
                intent.putExtra("schedID", viewHolder.packSchedID.getText());
                intent.putExtra("packageImage", ( (BitmapDrawable) viewHolder.packImage.getDrawable() ).getBitmap());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView packSchedID;
        TextView packName;
        TextView packAmount;
        CircleImageView packImage;

        Button payButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            packSchedID = itemView.findViewById(R.id.packSchedID);
            packName = itemView.findViewById(R.id.packName);
            packAmount = itemView.findViewById(R.id.packAmount);
            packImage = itemView.findViewById(R.id.packImage);
            payButton =  itemView.findViewById(R.id.btnPay);
        }
    }
}
