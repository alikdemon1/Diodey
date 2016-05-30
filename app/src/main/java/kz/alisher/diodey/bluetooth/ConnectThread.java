package kz.alisher.diodey.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by Alikdemon on 30.05.2016.
 */
public class ConnectThread extends Thread {
    private BluetoothSocket bTSocket;

    public boolean connect(BluetoothDevice bTDevice, UUID mUUID) {
        try {
            bTSocket = bTDevice.createRfcommSocketToServiceRecord(mUUID);
        } catch (IOException e) {
            Log.d("CONNECT THREAD", "Could not create RFCOMM socket:" + e.toString());
            return false;
        }
        try {
            bTSocket.connect();
        } catch (IOException e) {
            Log.d("CONNECT THREAD", "Could not connect: " + e.toString());
            try {
                bTSocket.close();
            } catch (IOException close) {
                Log.d("CONNECT THREAD", "Could not close connection:" + e.toString());
                return false;
            }
        }
        return true;
    }

    public void sendData(int data) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(4);
        output.write(data);
        OutputStream outputStream = bTSocket.getOutputStream();
        outputStream.write(output.toByteArray());
    }

    public boolean cancel() {
        try {
            bTSocket.close();
        } catch (IOException e) {
            Log.d("CONNECT THREAD", "Could not close connection:" + e.toString());
            return false;
        }
        return true;
    }
}