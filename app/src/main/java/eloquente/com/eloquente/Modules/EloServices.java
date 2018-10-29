package eloquente.com.eloquente.Modules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import eloquente.com.eloquente.R;


/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class EloServices extends Fragment {

    Fragment fragment = null;

    Activity context;

    TextView serviceA;
    TextView serviceB;
    TextView serviceC;
    TextView serviceD;
    TextView serviceE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        context = getActivity();


        return inflater.inflate(R.layout.elo_pack, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        serviceA = view.findViewById(R.id.serviceA_id);
        serviceB = view.findViewById(R.id.serviceB_id);
        serviceC = view.findViewById(R.id.serviceC_id);
        serviceD = view.findViewById(R.id.serviceD_id);

        LinearLayout wed = view.findViewById(R.id.weddingLay);
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                wed.putExtra("service_id", serviceA.getText());
                startActivity(wed);
            }
        });

        LinearLayout bday = view.findViewById(R.id.BirthdayLay);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                wed.putExtra("service_id", serviceB.getText());
                startActivity(wed);
            }
        });

        LinearLayout debut = view.findViewById(R.id.debutLay);
        debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                wed.putExtra("service_id", serviceC.getText());
                startActivity(wed);
            }
        });

        LinearLayout pt = view.findViewById(R.id.partyLay);
        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                wed.putExtra("service_id", serviceD.getText());
                startActivity(wed);
            }
        });

        LinearLayout customLay = view.findViewById(R.id.CUSTOMLay);
        customLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                wed.putExtra("service_id", serviceD.getText());
                startActivity(wed);
            }
        });







        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Eloquente Packages");
    }



}
