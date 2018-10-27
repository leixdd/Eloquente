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

import eloquente.com.eloquente.EloWed;
import eloquente.com.eloquente.R;


/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class EloServices extends Fragment {

    Fragment fragment = null;

    Activity context;

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



        ImageView wed = view.findViewById(R.id.wedTrigger);

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wed = new Intent(context, EloWed.class);
                startActivity(wed);
            }
        });


        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Eloquente Packages");
    }


}
