package pl.wroclaw.asi.sesja_linuksowa.Partner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.wroclaw.asi.sesja_linuksowa.Model.Partner;

import java.util.ArrayList;

/**
 * Created by sp0rk on 28.03.16.
 */
public class PartnerAdapter extends RecyclerView.Adapter<PartnerViewHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<Partner> partnerList;

    public PartnerAdapter(Context context, ArrayList<Partner> partnerList) {
        this.context = context;
        this.partnerList = partnerList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PartnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.list_item_partner,parent,false);
        return new PartnerViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(PartnerViewHolder holder, int position) {
        holder.setWebUrl(partnerList.get(position).getWebUrl());
        holder.partnerImage.setImageResource(partnerList.get(position).getimgRID());
    }

    @Override
    public int getItemCount() {
        return partnerList.size();
    }
}
