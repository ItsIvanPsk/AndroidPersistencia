package com.example.androidpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        EditText et = findViewById(R.id.editText);
        TextView tv = findViewById(R.id.fileText);

        try {
            InputStream is = openFileInput("dades.txt");
            tv.setText(convertStreamToString(is));
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
                    os.write(et.getText().toString().getBytes());
                    try {
                        InputStream is = openFileInput("dades.txt");
                        tv.setText(convertStreamToString(is));
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void refreshGUI(){

    }

    public String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

}