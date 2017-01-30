package com.example.lenovo.simple1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    Button start;
    public TextView showText;
    public TextView showText2;
    public TextView input;
    public String number;
    public String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        showText=  (TextView)findViewById(R.id.textView);
        showText2 = (TextView)findViewById(R.id.textView2);
        input = (TextView)findViewById(R.id.editText);



        start = (Button)findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number =  input.getText().toString();
               RetrieveFeedTask click1 = new RetrieveFeedTask(showText,showText2,number);

                click1.execute();




            }
        });


    }

}
