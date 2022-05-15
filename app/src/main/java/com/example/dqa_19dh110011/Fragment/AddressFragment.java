package com.example.dqa_19dh110011.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dqa_19dh110011.LocationServiceTask;
import com.example.dqa_19dh110011.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {

    EditText edtAddress, edtMobile;
    Button btnNext;
    NavController navController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtMobile = view.findViewById(R.id.edtMobile);
        btnNext = view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(view1 -> {
            if(!TextUtils.isEmpty(edtAddress.getText()) && !TextUtils.isEmpty(edtMobile.getText())) {
                LatLng latLng = LocationServiceTask.getLatLngFromAddress(getContext(), edtAddress.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("address", edtAddress.getText().toString());
                bundle.putString("mobile", edtMobile.getText().toString());
                bundle.putDouble("latitude", latLng.latitude);
                bundle.putDouble("longitude", latLng.longitude);
                bundle.putString("firstname", getArguments().getString("firstname"));
                bundle.putString("lastname", getArguments().getString("lastname"));
                navController.navigate(R.id.action_addressFragment_to_userPassFragment, bundle);
            }
            else Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        });
    }
/*    @Override
    public void onResume() {
        super.onResume();
        if (LocationServiceTask.isLocationServiceEnabled(getActivity())) {
            if (PermissionTask.isLocationServiceAllowed(getActivity()))
                getLastLocation(getActivity());
            else
                PermissionTask.requestLocationServicePermissions(getActivity());
        } else {
            LocationServiceTask.displayEnableLocationServiceDialog(getActivity());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionTask.LOCATION_SERVICE_REQUEST_CODE && grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLastLocation(getActivity());
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void getLastLocation(Context context) {

    }*/
}