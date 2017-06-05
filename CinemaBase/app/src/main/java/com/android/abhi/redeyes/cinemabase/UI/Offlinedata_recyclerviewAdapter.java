package com.android.abhi.redeyes.cinemabase.UI;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.model.Offline_Data;

import java.util.List;

/**
 * Created by Abhilash Reddy on 6/5/2017.
 */

public class Offlinedata_recyclerviewAdapter extends RecyclerView.Adapter<Offlinedata_recyclerviewAdapter.Dataholder> {

    Context context;
    List<Offline_Data> datas;

    public Offlinedata_recyclerviewAdapter(Context context, List<Offline_Data> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public Dataholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offline_data, parent, false);
        Dataholder holder = new Dataholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Dataholder holder, final int position) {

        holder.title.setText(datas.get(position).getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.offlinedata_description);

                TextView title = (TextView) dialog.findViewById(R.id.offlinedata_desc_title);
                TextView desc = (TextView) dialog.findViewById(R.id.offlinedata_desc_overview);

                title.setText(datas.get(position).getTitle());
                desc.setText(datas.get(position).getOverview());
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Dataholder extends RecyclerView.ViewHolder {

        TextView title;

        public Dataholder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.offlinedata_title);
        }
    }
}
