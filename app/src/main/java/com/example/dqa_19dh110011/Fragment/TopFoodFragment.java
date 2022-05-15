package com.example.dqa_19dh110011.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dqa_19dh110011.Adapter.FoodAdapter;
import com.example.dqa_19dh110011.Model.Food;
import com.example.dqa_19dh110011.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFoodFragment extends Fragment implements FoodAdapter.OnFoodItemClickListener {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<Food> topFoods;
    FoodAdapter foodAdapter;
    RecyclerView rvTopFood;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopFoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFoodFragment newInstance(String param1, String param2) {
        TopFoodFragment fragment = new TopFoodFragment();
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
        topFoods = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTopFood = view.findViewById(R.id.rvTopFood);
        foodAdapter = new FoodAdapter(topFoods, this);
        rvTopFood.setAdapter(foodAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTopFood.setLayoutManager(layoutManager);
        rvTopFood.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        Query query = reference.child("foods");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                topFoods.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Food food = dataSnapshot.getValue(Food.class);
                    topFoods.add(food);
                }
                Collections.sort(topFoods);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onFoodItemClick(Food food) {

    }
}