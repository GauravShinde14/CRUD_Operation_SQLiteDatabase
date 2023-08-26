package com.gaurav.stockmanage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText qty;
    private Button save;
    private Button view,delete,update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhandler dBhandler = new DBhandler(MainActivity.this);
//        SQLiteDatabase sqLiteDatabase = dBhandler.getReadableDatabase();
        name = findViewById(R.id.pname);
        qty = findViewById(R.id.qty);
        save = findViewById(R.id.save);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String qty1 = qty.getText().toString();

                if(name1.equals("")||qty1.equals("")){
                    Toast.makeText(MainActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                        boolean i = dBhandler.insert(name1,qty1);
                        if(i==true){
                            Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                            qty.setText("");
                            name.setText("");
                        }else {
                            Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,viewActivity.class);
                startActivity(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("database");
//                builder.s
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty2 = qty.getText().toString();
                dBhandler.delete(qty2);
                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                qty.setText("");
                name.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name3 = name.getText().toString();
                String qty3 = qty.getText().toString();
                dBhandler.update(name3,qty3);
                Toast.makeText(MainActivity.this, "updated ", Toast.LENGTH_SHORT).show();
            }
        });




    }
}