package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyApp";
    private MyViewModel viewModel;
    private ListView listView;
    private EditText editText;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        editText = findViewById(R.id.editTextTextPersonName2);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    viewModel.updateEditText(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(viewModel.setAdapter(this));

        viewModel.getEditText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                textView1.setText(data);
            }
        });

        viewModel.getTextView().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                textView2.setText(data);
            }
        });
    }



    public void AddElement(View view)
    {
        if (view.getId() == R.id.button)
        {
            listView.setAdapter(viewModel.updateAdapter(this));
        }
    }

    public void TextButtonView(View view)
    {
        viewModel.newTextView(view.findViewById(R.id.textview_id));
    }
}