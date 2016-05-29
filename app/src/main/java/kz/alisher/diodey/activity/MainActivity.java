package kz.alisher.diodey.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kz.alisher.diodey.R;
import kz.alisher.diodey.adapter.OfficeViewAdapter;
import kz.alisher.diodey.model.Office;
import kz.alisher.diodey.utils.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

    private OfficeViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new OfficeViewAdapter(initData(), this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, SubOfficeActivity.class));
            }
        }));
    }

    private List<Office> initData() {
        List<Office> offices = new ArrayList<>();
        offices.add(new Office("Офис", R.drawable.icon));
        return offices;
    }
}
