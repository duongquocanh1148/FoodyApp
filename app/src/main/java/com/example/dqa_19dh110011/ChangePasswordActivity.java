package com.example.dqa_19dh110011;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    Button btnSave;
    EditText edtPW, edtCPW;
    FirebaseUser firebaseUser;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtPW = findViewById(R.id.edtPW);
        edtCPW = findViewById(R.id.edtCPW);
        btnSave = findViewById(R.id.btnSave);
        toolbar = findViewById(R.id.tbChangePW);
        setSupportActionBar(toolbar);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edtPW.getText().toString()) && !TextUtils.isEmpty(edtCPW.getText().toString())){
                    if(edtPW.getText().toString().equals(edtCPW.getText().toString())){
                        firebaseUser.updatePassword(edtPW.getText().toString());
                        Toast.makeText(getApplicationContext(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else Toast.makeText(getApplicationContext(), "Không trùng password!", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Vui lòng điền thông tin!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}