package kz.alisher.diodey.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import kz.alisher.diodey.R;
import kz.alisher.diodey.bluetooth.ConnectThread;
import kz.alisher.diodey.model.Device;

/**
 * Created by Alikdemon on 29.05.2016.
 */
public class SubOfficeActivity extends AppCompatActivity {

    private BluetoothAdapter bTAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private ListView deviceList;
    private SeekBar seekBar;
    private Switch mSwitch;
    private ArrayAdapter adapter;
    private BluetoothDevice btDevice;
    private ConnectThread thread;
    private UUID MY_UUID = UUID.fromString("8e5db0d6-529f-4586-aadd-330ec3cdedd0");

    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d("DEVICE LIST", "Bluetooth device found");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                adapter.add(new Device(device.getName(), device.getAddress()));
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_office);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        mSwitch = (Switch) findViewById(R.id.switch1);
        deviceList = (ListView) findViewById(R.id.listView);

        bTAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!bTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }

        setDeviceList();

        startDiscovery();

        seekBar.setEnabled(false);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    seekBar.setEnabled(true);
                } else {
                    seekBar.setEnabled(false);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (btDevice == null) {
                    Toast.makeText(SubOfficeActivity.this, "Please choose bluetooth device", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        thread.sendData(progress);
                        Toast.makeText(SubOfficeActivity.this, progress+"", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setDeviceList() {
        pairedDevices = bTAdapter.getBondedDevices();
        final ArrayList<Device> list = new ArrayList<>();
        for (BluetoothDevice bt : pairedDevices) {
            list.add(new Device(bt.getName(), bt.getAddress()));
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btDevice = bTAdapter.getRemoteDevice(list.get(position).getAddress());
                thread = new ConnectThread();
                thread.connect(btDevice, MY_UUID);
                Log.d("DEVICE", btDevice.getName() + ", " + btDevice.getAddress());
            }
        });
    }

    public void startDiscovery() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bReciever, filter);
        bTAdapter.startDiscovery();
    }

    public void stopDiscovery() {
        unregisterReceiver(bReciever);
        bTAdapter.cancelDiscovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ON DESTROY", "stop discovery");
        stopDiscovery();
    }
}