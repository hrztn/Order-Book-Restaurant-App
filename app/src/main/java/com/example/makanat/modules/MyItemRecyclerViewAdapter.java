package com.example.makanat.modules;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.makanat.EditActivity;
import com.example.makanat.R;
import com.example.makanat.model.OrderItemModelClass;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private List<OrderItemModelClass> mData;
    private LayoutInflater mInflater;
    private List<OrderItemModelClass> exampleListFull = new ArrayList<>();
    private Context mContext;

    public MyItemRecyclerViewAdapter(Context context, List<OrderItemModelClass> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public MyItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view, parent, false);
        return new MyItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        OrderItemModelClass mOrderItemModelClass = mData.get(position);
        holder.mOrderItemModelClass = mOrderItemModelClass;
        holder.name.setText(mOrderItemModelClass.getName());
        Picasso.get().load(mOrderItemModelClass.getImageUrl()).into(holder.ImageView);


    }

    public int getItemCount() {
        Log.d("TAG", "getItemCount: " + mData.size());
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        android.widget.ImageView ImageView;

        OrderItemModelClass mOrderItemModelClass = new OrderItemModelClass();

        ViewHolder(final View itemView) {
            super(itemView);
            final Context context = itemView.getContext();
            name = itemView.findViewById(R.id.itemNameTv);
            ImageView = itemView.findViewById(R.id.item_imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("item", (Serializable) mOrderItemModelClass);
                    context.startActivity(intent);
                }
            });
        }

    }


}
