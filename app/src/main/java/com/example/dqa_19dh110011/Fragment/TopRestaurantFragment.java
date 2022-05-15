package com.example.dqa_19dh110011.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dqa_19dh110011.Adapter.RestaurantAdapter;
import com.example.dqa_19dh110011.Model.Restaurant;
import com.example.dqa_19dh110011.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopRestaurantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopRestaurantFragment extends Fragment implements RestaurantAdapter.OnRestaurantItemClickListener {

    FirebaseDatabase firebaseDatabase;
    RestaurantAdapter restaurantAdapter;
    ArrayList<Restaurant> topRestaurants;
    RecyclerView rvTopRestaurant;
    DatabaseReference reference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopRestaurantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopRestaurantFragment newInstance(String param1, String param2) {
        TopRestaurantFragment fragment = new TopRestaurantFragment();
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
        topRestaurants = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_restaurant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTopRestaurant = view.findViewById(R.id.rvTopRestaurant);
        restaurantAdapter = new RestaurantAdapter(topRestaurants, this, 0);
        rvTopRestaurant.setAdapter(restaurantAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvTopRestaurant.setLayoutManager(layoutManager);
        rvTopRestaurant.addItemDecoration(new DividerItemDecoration(getContext(), GridLayoutManager.VERTICAL));
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        Query query = reference.child("restaurants").orderByChild("rate").limitToLast(3);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                topRestaurants.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    topRestaurants.add(restaurant);
                }
                restaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onRestaurantItemClick(Restaurant restaurant) {

    }
}