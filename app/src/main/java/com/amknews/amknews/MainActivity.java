package com.amknews.amknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.amknews.amknews.R.id.recyclerView;
import static com.amknews.amknews.R.id.toolbar;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindCheckNewsButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindCheckNewsButton = (Button) findViewById(R.id.findCheckNewsButton);
        mFindCheckNewsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
    }

    recyclerView.setOnScrollListener(new MyScrollListeneView(this) {
        @Override
        public void onMoved(int distance) {
            toolbar.setTranslationY(-distance);
        }
    });
}
