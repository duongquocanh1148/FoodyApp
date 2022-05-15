package com.example.dqa_19dh110011;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class LocationServiceTask {

    public static boolean isLocationServiceEnabled(Context context) {
        boolean isGPSEnabled;
        boolean isNetworkEnabled;
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS enabled
            isGPSEnabled = true;
        } else {
            //GPS disabled
            isGPSEnabled = false;
        }
        if (locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //GPS enabled
            isNetworkEnabled = true;
        } else {
            //GPS disabled
            isNetworkEnabled = false;
        }
        return isGPSEnabled || isNetworkEnabled;
    }

    public static void displayEnableLocationServiceDialog(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Please enable Location Services to detect your location.";

        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                context.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }

    public static String getAddressFromLatLng(Context context, LatLng latLng) {
        // 1
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        Address address;
        String addressText = "";
        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses.get(0);
                if (address.getMaxAddressLineIndex() > 0) {
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        addressText += (i == 0) ? address.getAddressLine(i) : "\n" + address.getAddressLine(i);
                    }
                } else {
                    addressText = address.getAddressLine(0);
                }
            }
        } catch (IOException e) {
            Log.e("CSC", e.getLocalizedMessage());
        }
        return addressText;
    }

    public static LatLng getLatLngFromAddress(Context context, String strAddress) {
        // 1
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;

        try {
            //Get latLng from String
            addresses = geocoder.getFromLocationName(strAddress, 5);

            //check for null
            if (addresses != null) {

                //Lets take first possibility from the all possibilities.
                try {
                    Address location = addresses.get(0);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    return latLng;
                } catch (IndexOutOfBoundsException er) {
                    Log.d("ABC", er.getMessage());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
