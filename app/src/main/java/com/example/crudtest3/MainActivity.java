package com.example.crudtest3;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dao.CustomerDAO;
import model.Customer;

public class MainActivity extends AppCompatActivity {

    private static CustomerDAO customerDAO;
    public static model.Customer Customer;
    EditText editText_username;
    EditText editText_pass;
    CheckBox rememberChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerDAO = new CustomerDAO(MainActivity.this);
        rememberChk = findViewById(R.id.rememberChk);
        editText_username = findViewById(R.id.username);
        editText_pass = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signupButton);
        loadData();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        //admin and admin
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText_username.getText().toString().trim();
                String password = editText_pass.getText().toString().trim();
                boolean check = rememberChk.isChecked();
                Customer customer = new Customer(username, password);

                if(customerDAO.check(customer)){
                    LuuTT(username,password,check);
                    Customer = customer;
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("keyusername", username);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                }


            }
        });


    }   private void LuuTT(String userName, String passWord, boolean chk){
        SharedPreferences pref = getSharedPreferences("customerDetail.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(chk){
            editor.putString("username", userName);
            editor.putString("password", passWord);
            editor.putBoolean("username", chk);

        }
        else{
            editor.clear();
        }
        editor.commit();
    }

    private void loadData(){
        SharedPreferences pref = getSharedPreferences("customerDetail.dat", MODE_PRIVATE);
        boolean check = pref.getBoolean("check", false);
        if(check){
            editText_username.setText(pref.getString("username", ""));
            editText_pass.setText(pref.getString("password", ""));
            rememberChk.setChecked(check);
        }

    }
}