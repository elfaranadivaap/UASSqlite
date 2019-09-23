package com.example.uaspwpbel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private EditText input;
    private Button btnCreate, btnUpdate, btnDelete;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_date);

        Calendar calendar =  Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh:mm:ss a");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);


        text = (TextView) findViewById(R.id.text);
        input = (EditText) findViewById(R.id.input);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.getText().toString().trim().equals("")) {
                    if (!input.getText().toString().trim().equals("")) {
                        new CRUD(MainActivity.this).createData(input.getText().toString());
                        input.setText("");
                        sukses("Create");
                    } else {
                        input.setError("Input Tidak Boleh Kosong");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Text Sudah Ada Isinya, Silahkan Update/Delete", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!text.getText().toString().trim().equals("")) {
                    if (!input.getText().toString().trim().equals("")) {
                        new CRUD(MainActivity.this).updateData(input.getText().toString(), text.getText().toString());
                        sukses("Update");
                    } else {
                        input.setError("Input Tidak Boleh Kosong");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Text Belum Ada Isinya, Silahkan Create Dahulu", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!text.getText().toString().trim().equals("")) {
                    new CRUD(MainActivity.this).deleteData(text.getText().toString());
                    input.setText("");
                    sukses("Delete");
                } else {
                    Toast.makeText(MainActivity.this, "Text Belum Ada Isinya, Silahkan Create Dahulu", Toast.LENGTH_LONG).show();
                }
            }
        });

        showText();

    }

    private void showText() {
        text.setText(new CRUD(this).getData());
    }

    private void sukses(String info) {
        showText();
        Toast.makeText(MainActivity.this, info + " Berhasil", Toast.LENGTH_LONG).show();
    }


}