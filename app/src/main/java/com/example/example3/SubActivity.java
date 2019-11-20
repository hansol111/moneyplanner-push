package com.example.example3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        Intent intent = getIntent();

        Button pushButton = findViewById(R.id.limitbutton);
        final Button backButton = findViewById(R.id.backactivity);
        pushButton.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                        EditText editText = findViewById(R.id.limittext);
                        String abc = editText.getText().toString();
                        //Log.d("abctag", abc);
                        final int limit = Integer.parseInt(abc);
                backButton.setOnClickListener(new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubActivity.this, MainActivity.class);
                        intent.putExtra("limit", limit);
                        startActivity(intent);

                    }
                });

            }
        });
        backButton.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
