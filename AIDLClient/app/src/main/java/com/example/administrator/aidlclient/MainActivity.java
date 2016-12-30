package com.example.administrator.aidlclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.aidlservice.IService;

public class MainActivity extends AppCompatActivity {
  private TextView tv;
    private Button add;
    private EditText num1,num2;
     IService iService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.sum);
        add= (Button) findViewById(R.id.add);
        num1= (EditText) findViewById(R.id.num1);
        num2= (EditText) findViewById(R.id.num2);
        bindService();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                try {
                    i= iService.sum(Integer.parseInt(num1.getText().toString()),Integer.parseInt(num2.getText().toString()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                tv.setText(""+i);


            }
        });
    }

    private void bindService() {
        Intent it=new Intent();
        ComponentName cm=new ComponentName("com.example.administrator.aidlservice","com.example.administrator.aidlservice.RemoteService");
        it.setComponent(cm);
        bindService(it,mySc, Service.BIND_AUTO_CREATE);
    }

    ServiceConnection mySc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService=IService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iService = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mySc);
        super.onDestroy();

    }
}
