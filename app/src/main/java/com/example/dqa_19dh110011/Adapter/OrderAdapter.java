package com.example.dqa_19dh110011.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dqa_19dh110011.Model.OrderFinished;
import com.example.dqa_19dh110011.Model.Restaurant;
import com.example.dqa_19dh110011.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.protobuf.StringValue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface OnOrderItemListener{
        void onOrderItemListener(OrderFinished orderFinished);
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder{
        TextView tvName, tvAddress, tvSum, tvID, tvDate, tvStatus;
        ImageView imageView;
        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameOrder);
            tvAddress = itemView.findViewById(R.id.tvAddressOrder);
            tvSum = itemView.findViewById(R.id.tvSumOrder);
            tvID = itemView.findViewById(R.id.tvID);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.ivOrderFinish);
        }
    }

    private List<Restaurant> restaurants;
    private List<OrderFinished> orderFinisheds;
    private OnOrderItemListener onOrderItemListener;

    public OrderAdapter(List<OrderFinished> orderFinisheds, List<Restaurant> restaurants, OnOrderItemListener onOrderItemListener){
        this.orderFinisheds = orderFinisheds;
        this.restaurants = restaurants;
        this.onOrderItemListener = onOrderItemListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_order_finished, parent, false);
        return new OrderAdapter.ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderFinished orderFinished = orderFinisheds.get(position);
        Restaurant restaurant = new Restaurant();
        for(Restaurant res: restaurants){
            if(orderFinished.getFoodBaskets().get(0).getResKey().equals((res.getResKey()))){
                restaurant = res;
                break;
            }
        }
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();;
        ViewHolderOrder viewHolderOrder = (ViewHolderOrder) holder;
        StorageReference profileRef = storageReference.child("restaurants/" + restaurant.getLogo());
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolderOrder.imageView);
            }
        });
        viewHolderOrder.tvName.setText(restaurant.getName());
        viewHolderOrder.tvAddress.setText(restaurant.getAddress());
        viewHolderOrder.tvID.setText(orderFinished.getOrderID());
        viewHolderOrder.tvDate.setText(orderFinished.getOrderDate());
        viewHolderOrder.tvSum.setText(orderFinished.getOrderSum());
        viewHolderOrder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrderItemListener.onOrderItemListener(orderFinished);
            }
        });



     }

    @Override
    public int getItemCount() {
        return orderFinisheds.size();
    }
}
