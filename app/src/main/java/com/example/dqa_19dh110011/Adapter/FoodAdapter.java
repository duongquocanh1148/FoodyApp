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

import com.example.dqa_19dh110011.Model.Food;
import com.example.dqa_19dh110011.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public  interface OnFoodItemClickListener{
        void onFoodItemClick(Food food);
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder{
        TextView tvName, tvRate,tvPrice;
        ImageView imageView;

        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvRate = itemView.findViewById(R.id.tvRate);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.ivImage);
        }
    }

    private List<Food> foods;
    private OnFoodItemClickListener foodItemClickListener;

    public FoodAdapter(List<Food> foods, OnFoodItemClickListener foodItemClickListener){
        this.foods = foods;
        this.foodItemClickListener = foodItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_top_food, parent, false);
        return new ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Food food = foods.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();;
        ViewHolderFood viewHolderFood = (ViewHolderFood) holder;
        StorageReference profileRef = storageReference.child("foods/" + food.getImage());
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolderFood.imageView);
            }
        });
        viewHolderFood.tvName.setText(food.getName());
        viewHolderFood.tvPrice.setText(food.getPrice()+"");
        viewHolderFood.tvRate.setText("Rate: "+food.getRate()+"");
        viewHolderFood.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodItemClickListener.onFoodItemClick(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
