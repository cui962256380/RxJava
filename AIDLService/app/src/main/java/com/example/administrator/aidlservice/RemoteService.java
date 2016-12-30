package com.example.administrator.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by Administrator on 2016/12/30.
 */

public class RemoteService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    class MyBind extends IService.Stub {

        @Override
        public int sum(int num1, int num2) throws RemoteException {
            return num1+num2;
        }
    }

}
