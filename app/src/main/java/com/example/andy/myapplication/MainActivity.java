package com.example.andy.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.phoneNumber);
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            textView.setText(mPhoneNumber);
            return;
        } else {
            requestPermission();

        }

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                String str = "";
                str += "DeviceId(IMEI) = " + tMgr.getDeviceId() + "\n";
                str += "DeviceSoftwareVersion = " + tMgr.getDeviceSoftwareVersion() + "\n";
                str += "Line1Number = " + tMgr.getLine1Number() + "\n";
                str += "NetworkCountryIso = " + tMgr.getNetworkCountryIso() + "\n";
                str += "NetworkOperator = " + tMgr.getNetworkOperator() + "\n";
                str += "NetworkOperatorName = " + tMgr.getNetworkOperatorName() + "\n";
                str += "NetworkType = " + tMgr.getNetworkType() + "\n";
                str += "honeType = " + tMgr.getPhoneType() + "\n";
                str += "SimCountryIso = " + tMgr.getSimCountryIso() + "\n";
                str += "SimOperator = " + tMgr.getSimOperator() + "\n";
                str += "SimOperatorName = " + tMgr.getSimOperatorName() + "\n";
                str += "SimSerialNumber = " + tMgr.getSimSerialNumber() + "\n";
                str += "SimState = " + tMgr.getSimState() + "\n";
                str += "SubscriberId(IMSI) = " + tMgr.getSubscriberId() + "\n";
                str += "VoiceMailNumber = " + tMgr.getVoiceMailNumber() + "\n";

                textView.setText(str);
                break;
        }
    }
}
