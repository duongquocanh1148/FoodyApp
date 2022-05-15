package com.example.dqa_19dh110011.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dqa_19dh110011.ChangePasswordActivity;
import com.example.dqa_19dh110011.Model.User;
import com.example.dqa_19dh110011.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ImageView imageView;
    EditText edtName, edtEmail, edtMoblie, edtAddress;
    Button btnUpdate, btnChangePW;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.ivUser);
        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddressProfile);
        edtEmail = view.findViewById(R.id.edtEmailProfile);
        edtMoblie = view.findViewById(R.id.edtPhoneProfile);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnChangePW = view.findViewById(R.id.btnChangePW);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                edtName.setText(user.getFirstname()+" "+user.getLastname());
                edtEmail.setText(user.getEmail());
                edtMoblie.setText(user.getMobile());
                edtAddress.setText(user.getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile, address;
                mobile = edtMoblie.getText().toString().trim();
                address = edtAddress.getText().toString().trim();
                List<String> name = Arrays.asList(edtName.getText().toString().trim().split(" "));
                if(!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && name.size()>0){
                    Map<String, Object> user = new HashMap<>();
                    user.put("firstname", name.get(0));
                    user.put("lastname", name.get(1));
                    user.put("mobile", mobile);
                    user.put(address, address);
                    reference.updateChildren(user);
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}