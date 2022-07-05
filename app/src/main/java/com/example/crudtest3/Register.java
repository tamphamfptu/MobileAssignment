package com.example.crudtest3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dao.CustomerDAO;
import model.Customer;

public class Register extends AppCompatActivity {
    private Customer customer;
    private Button backButton;
    private EditText editText_Username;
    private EditText editText_Password;
    private Button registerBtn;
    private CustomerDAO customerDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

         backButton = findViewById(R.id.backButton);
         editText_Username = findViewById(R.id.username);
         editText_Password = findViewById(R.id.password);
         registerBtn = findViewById(R.id.confirm_Button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText_Username.getText().toString().trim();
                String password = editText_Password.getText().toString().trim();
                customerDAO = new CustomerDAO(Register.this);
                customerDAO.create(customer);
                editText_Username.setText("");
                editText_Password.setText("");
                loadData();
                // validating if the text fields are empty or not.
                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(Register.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Register.this,"Create success",Toast.LENGTH_LONG).show();

            }
        });

    }


    private void loadData(){
        SharedPreferences pref = getSharedPreferences("customerDetail.dat", MODE_PRIVATE);
        boolean check = pref.getBoolean("check", false);
        if(check){
            editText_Username.setText(pref.getString("username", ""));
            editText_Password.setText(pref.getString("password", ""));
        }
    }


}
