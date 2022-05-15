package com.example.dqa_19dh110011.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dqa_19dh110011.Adapter.FoodBasketAdapter;
import com.example.dqa_19dh110011.Model.Basket;
import com.example.dqa_19dh110011.OrderActivity;
import com.example.dqa_19dh110011.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class BasketDialogFragment extends DialogFragment implements View.OnClickListener {
    public TextView tvTotal;
    public RecyclerView rvFoods;
    public Basket basket;
    public FoodBasketAdapter adapter;
    public Button btnPlaceOrder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketDialogFragment.
     */
     // TODO: Rename and change types and number of parameters
    public static BasketDialogFragment newInstance(String param1, String param2) {
        BasketDialogFragment fragment = new BasketDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public BasketDialogFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public BasketDialogFragment(Basket basket) {
        this.basket = basket;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTotal = view.findViewById(R.id.tvTotal);
        tvTotal.setText(basket.getTotalPrice()+"");
        rvFoods = view.findViewById(R.id.rvFoods);
        adapter = new FoodBasketAdapter(new ArrayList<>(basket.foods.values()));
        rvFoods.setAdapter(adapter);
        rvFoods.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(this);

    }
    @Override
    public void onResume() {
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().setCancelable(true);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPlaceOrder) {
            if (basket.getTotalItem() > 0) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra("basket", basket);
                startActivity(intent);
                getActivity().finish();
                getDialog().dismiss();
            } else {
                getDialog().dismiss();
            }
        }


        int id = v.getId();
    }
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}