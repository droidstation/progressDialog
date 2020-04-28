package com.example.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button circle;
    Button horizontal;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle = findViewById(R.id.btnCircle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Downloading File");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.setCancelable(true);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }
        });

        horizontal = findViewById(R.id.btnHorizontal);
        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Progress Dialog");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog
                                    .getMax()) {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog
                                        .getMax()) {
                                    progressDialog.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.incrementProgressBy(1);
        }
    };

}
