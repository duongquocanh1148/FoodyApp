package com.example.dqa_19dh110011;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.dqa_19dh110011.Model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    Menu mMenu;
    boolean flag = true;
    TextView tvFullName,tvEmail;


    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvEmail);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.orderFragment, R.id.profileFragment, R.id.logoutFragment)
                .setDrawerLayout(drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                tvFullName.setText(user.getFirstname()+" "+user.getLastname());
                tvEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.profileFragment) {
                    //mMenu.findItem(R.id.mnuCart).setVisible(false);
                } else if (mMenu != null) {
                    //mMenu.findItem(R.id.mnuCart).setVisible(true);

                }
            }
        });
        */

        /*String userID = mAuth.getCurrentUser().getUid();
        mDatabase.getReference().child("users").child(userID).get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                       // user.setUserID(userID);
                       // tvFullName.setText(user.getFirstName()+""+user.getLastName());
                       // tvEmail.setText(user.getEmail());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}