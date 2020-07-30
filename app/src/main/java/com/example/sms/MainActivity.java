package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sms,phone;
    Button send,clear,contact;
    PendingIntent sentPI, deliveredPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sms=(EditText)findViewById(R.id.msg);
        phone=(EditText)findViewById(R.id.phone);
        send=(Button) findViewById(R.id.send);
        clear=(Button) findViewById(R.id.clear);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = sms.getText().toString();
                String telNr = phone.getText().toString();

                if(telNr.length()==10)
                {


                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                        Toast.makeText(getApplicationContext(), "SMS not sent!", Toast.LENGTH_SHORT).show();
                    } else {
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
                        Toast.makeText(getApplicationContext(), "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "SMS not sent !", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Enter 10 digit number !", Toast.LENGTH_SHORT).show();
                }

                phone.setText("");
                sms.setText("");


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setText("");
                sms.setText("");
            }
        });

    }

    }

