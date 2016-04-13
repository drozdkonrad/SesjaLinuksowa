package pl.wroclaw.asi.sesja_linuksowa.Partner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import pl.wroclaw.asi.sesja_linuksowa.Model.Partner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PartnersFragment extends Fragment {
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.patron_recycler) RecyclerView patronRecycler;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.sponsor_recycler) RecyclerView sponsorRecycler;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.organisator_recycler) RecyclerView organisatorRecycler;


    public ArrayList<Partner> getsponsorList(){
        ArrayList<Partner> sponsorList = new ArrayList<>();
        sponsorList.add(new Partner("http://www.ovh.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.ovh));
        sponsorList.add(new Partner("http://mokyokit.com/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.mok_yok));
        sponsorList.add(new Partner("http://www.tieto.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.tieto));
        return sponsorList;
    }
    public ArrayList<Partner> getpatronList(){
        ArrayList<Partner> patronList = new ArrayList<>();
        patronList.add(new Partner("https://www.hswro.org/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.hacker_space));
        patronList.add(new Partner("http://www.linux.org.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.plug));
        patronList.add(new Partner("http://www.chip.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.chip));
        patronList.add(new Partner("http://www.ict-cluster.wroc.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.klaster));
        patronList.add(new Partner("http://programistamag.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.programista));
        patronList.add(new Partner("http://sdjournal.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.sdjournal));
        patronList.add(new Partner("http://www.umwd.dolnyslask.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.marszalek));
        patronList.add(new Partner("http://www.duw.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.wojewoda));
        return patronList;
    }
    public ArrayList<Partner> getorganisatorList(){
        ArrayList<Partner> patronList = new ArrayList<>();
        patronList.add(new Partner("http://asi.wroclaw.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.asi));
        patronList.add(new Partner("http://www.pwr.edu.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.pwr));
        patronList.add(new Partner("http://manus.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.manus));
        patronList.add(new Partner("http://wireless-group.pwr.wroc.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.wg));
        patronList.add(new Partner("http://wireless-group.pwr.wroc.pl/", pl.wroclaw.asi.sesja_linuksowa.R.drawable.nomad));
        return patronList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.partner_list, container, false);
        ButterKnife.bind(this, view);

        sponsorRecycler.setAdapter(new PartnerAdapter(getActivity().getApplicationContext(),getsponsorList()));
        sponsorRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        sponsorRecycler.setHasFixedSize(true);

        patronRecycler.setAdapter(new PartnerAdapter(getActivity().getApplicationContext(),getpatronList()));
        patronRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        patronRecycler.setHasFixedSize(true);

        organisatorRecycler.setAdapter(new PartnerAdapter(getActivity().getApplicationContext(), getorganisatorList()));
        organisatorRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        organisatorRecycler.setHasFixedSize(true);
        return view;
    }

}
