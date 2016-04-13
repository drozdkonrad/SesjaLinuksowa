package pl.wroclaw.asi.sesja_linuksowa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sp0rk on 25.03.16.
 */
public class MiddlePartyFragment extends Fragment {

    @Bind(R.id.middle_party_image) ImageView image;
    @Bind(R.id.middle_party_googlemaps_image) ImageView googlemapsimage;
    @Bind(R.id.middle_party_jakdojade_image) ImageView jakdojadeimage;
    @Bind(R.id.middle_party_fab) FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.middle_party_fragment, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getContext())
                .load("http://wroclawodkuchni.pl/wp-content/uploads/2014/04/6U9C9939-2-868x578.jpg")
                .centerCrop()
                .into(image);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://212lifestyle.pl/menu/"));
                startActivity(intent);
            }
        });
        googlemapsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.pl/maps/place/212!+Lifestyle+Cafe/@51.0949871,17.0181971,18z/data=!4m2!3m1!1s0x470fc26aaf835bb3:0xd7b1699911fc8c45"));
                startActivity(intent);
            }
        });
        jakdojadeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://wroclaw.jakdojade.pl/index.html?td=Middle%20Party&tn=212!%20Lifestyle%20Cafe&tc=51.09357:17.01997&cid=2000"));
                startActivity(intent);
            }
        });

        return view;
    }
}
