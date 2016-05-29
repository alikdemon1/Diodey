package kz.alisher.diodey.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import kz.alisher.diodey.R;

/**
 * Created by Alikdemon on 29.05.2016.
 */
public class SubOfficeActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Switch mSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_office);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        mSwitch = (Switch) findViewById(R.id.switch1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(SubOfficeActivity.this, progress + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
