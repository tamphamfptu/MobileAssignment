package com.example.crudtest3;

import android.app.Dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;

public class ShowActivity extends Dropdown {
    Button btnAddProduct;
    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Product> productList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lv = findViewById(R.id.lvProduct);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        productList = ProductDAO.getAll(ShowActivity.this);

        adapter = new ArrayAdapter(ShowActivity.this, android.R.layout.simple_list_item_1,productList);
        lv.setAdapter(adapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();

            }
        });

      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              showDialogUpdate(position);
          }
      });
    }


    //add
    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product, null);
        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.show();

        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtBrand = view.findViewById(R.id.edtBrand);
        EditText edtColor = view.findViewById(R.id.edtColor);
        EditText edtPrice = view.findViewById(R.id.edtPrice);


        Button btnSave = view.findViewById(R.id.btnConfrim);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String brand = edtBrand.getText().toString().trim();
                String color = edtColor.getText().toString().trim();
                String price = edtPrice.getText().toString().trim();

                if(ProductDAO.insert(ShowActivity.this, name, brand, color, Float.valueOf(price))){

                    Toast.makeText(ShowActivity.this, "Added", Toast.LENGTH_LONG).show();
                    productList.clear();
                    productList.addAll(ProductDAO.getAll(ShowActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowActivity.this, "D Add", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Update product function
    private void showDialogUpdate(int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_product, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtName = view.findViewById(R.id.updtName);
        EditText edtBrand = view.findViewById(R.id.updtBrand);
        EditText edtColor = view.findViewById(R.id.updtColor);
        EditText edtPrice = view.findViewById(R.id.updtPrice);


        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnDelete = view.findViewById(R.id.btnDelete);
        Product product = productList.get(pos);


        edtName.setText(product.getProductName());
        edtBrand.setText(product.getBrand());
        edtColor.setText(product.getColor());
        edtPrice.setText(String.valueOf(product.getPrice()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setProductName(edtName.getText().toString().trim());
                product.setBrand(edtBrand.getText().toString().trim());
                product.setColor(edtColor.getText().toString().trim());
                product.setPrice(Float.parseFloat(edtPrice.getText().toString().trim()));
                if(ProductDAO.update(ShowActivity.this, product)){
                    Toast.makeText(ShowActivity.this, "Update dc ", Toast.LENGTH_LONG).show();
                    productList.clear();
                    productList.addAll(ProductDAO.getAll(ShowActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowActivity.this, "d Update dc", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProductDAO.delete(ShowActivity.this, product.getId())){
                    Toast.makeText(ShowActivity.this, "Delete dc ", Toast.LENGTH_LONG).show();
                    productList.clear();
                    productList.addAll(ProductDAO.getAll(ShowActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowActivity.this, "d Delete dc", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}