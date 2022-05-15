package com.example.dqa_19dh110011.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dqa_19dh110011.Model.FoodBasket;
import com.example.dqa_19dh110011.R;

import java.util.List;

public class FoodBasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public class ViewHolderFoodBasket extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice, tvQuantity, tvSum, tvBasket;

        public ViewHolderFoodBasket(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtNameBasket);
            tvPrice = itemView.findViewById(R.id.txtPriceBasket);
            tvQuantity = itemView.findViewById(R.id.txtQuantity);
            tvSum = itemView.findViewById(R.id.txtSumBasket);
            tvBasket = itemView.findViewById(R.id.tvTitle);

        }
    }
    public FoodBasketAdapter(){
    }

    private List<FoodBasket> foodBaskets;

    public FoodBasketAdapter(List<FoodBasket> foodBaskets){
        this.foodBaskets = foodBaskets;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_basket, parent, false);
        return new ViewHolderFoodBasket(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FoodBasket foodBasket =  foodBaskets.get(position);
        ViewHolderFoodBasket viewHolderFoodBasket = (ViewHolderFoodBasket) holder;
        viewHolderFoodBasket.tvName.setText(foodBasket.getName());
        viewHolderFoodBasket.tvQuantity.setText(String.valueOf(foodBasket.getQuantity()));
        viewHolderFoodBasket.tvPrice.setText(String.valueOf(foodBasket.getPrice()));
        viewHolderFoodBasket.tvSum.setText(String.valueOf(foodBasket.getSum()));
    }

    @Override
    public int getItemCount() {
        return foodBaskets.size();
    }
}
