package pl.wroclaw.asi.sesja_linuksowa.Partner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sp0rk on 28.03.16.
 */
public class PartnerViewHolder extends RecyclerView.ViewHolder{
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.partner_list_image) ImageView partnerImage;
    Context context;
    String webUrl;

    public PartnerViewHolder(final View itemView, final Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        itemView.setClickable(true);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(webUrl));
                context.startActivity(intent);
            }
        });
    }

    public void setWebUrl(String url){
        webUrl = url;
    }

}
