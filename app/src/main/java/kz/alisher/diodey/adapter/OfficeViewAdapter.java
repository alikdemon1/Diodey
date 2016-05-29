package kz.alisher.diodey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kz.alisher.diodey.R;
import kz.alisher.diodey.model.Office;

/**
 * Created by Alikdemon on 29.05.2016.
 */
public class OfficeViewAdapter extends RecyclerView.Adapter<OfficeViewAdapter.ViewHolder> {
    List<Office> contents;
    Context ctx;

    public OfficeViewAdapter(List<Office> contents, Context ctx) {
        this.ctx = ctx;
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.office_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Office item = contents.get(position);
        holder.name.setText(item.getName());
        holder.image.setImageResource(item.getImage());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.office_image);
            name = (TextView) itemView.findViewById(R.id.office_name);
        }
    }
}
