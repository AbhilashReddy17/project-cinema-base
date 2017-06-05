package com.android.abhi.redeyes.cinemabase.UI;

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

    public Offlinedata_recyclerviewAdapter(Context cotext, List<Offline_Data> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public Dataholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offline_data,parent,false);
       Dataholder holder = new Dataholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Dataholder holder, int position) {

        holder.title.setText(datas.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Dataholder extends RecyclerView.ViewHolder{

        TextView title;
        public Dataholder(View itemView) {
            super(itemView);
           title= (TextView) itemView.findViewById(R.id.offlinedata_title);
        }
    }
}
