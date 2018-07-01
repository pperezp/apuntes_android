package com.example.pperezp.test_permisos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnProcesar_onClick(View v){

        StringBuilder sb = new StringBuilder(" \n");
        final Field[] manifestFields = Manifest.permission.class.getDeclaredFields();
        for (final Field field : manifestFields) {
            System.out.println(field.getName());
        }

        int PER_GRAN = PackageManager.PERMISSION_GRANTED;

        int WC_PER = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_CONTACTS
        );

        int GPS_PER = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        );

        int CAM_PER = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        );

        int NS_PER = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
        );

        if(
            WC_PER != PER_GRAN ||
            GPS_PER != PER_GRAN ||
            CAM_PER != PER_GRAN ||
            NS_PER != PER_GRAN
        ){
            ActivityCompat.requestPermissions(
                this,
                new String[]{
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.CALL_PHONE
                },
                1
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if(requestCode == 1){
            int i = 0;
            for (int gr : grantResults) {
                if(gr == PackageManager.PERMISSION_GRANTED){
                    Log.v("PERMISOS("+permissions[i]+")","SI");
                }else{
                    Log.v("PERMISOS("+permissions[i]+")","NO");
                }
                i++;
            }
        }
    }
}
