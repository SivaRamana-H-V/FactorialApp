package com.example.factorialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnokay;
    EditText editText;
    PopupWindow popupWindow;
    RelativeLayout relativeLayout;
    ImageButton button;
    TextView valueoffact;
    TextView valueofn;
    Switch simpleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btnokay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long startTime = System.nanoTime();


                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Kindly enter the value of n", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Request queued...", Toast.LENGTH_SHORT).show();
                
                String n = editText.getText().toString();
                //calling the fact method of the factorial class to get the factorial of n
                Boolean switchState = simpleSwitch.isChecked();

                BigInteger ans;
                if(switchState){
                    ans = factorial.fact(Integer.parseInt(n));
                }
                else
                    ans = factorial.fact(Integer.parseInt(n));



                //Toast.makeText(MainActivity.this, ans.toString(), Toast.LENGTH_SHORT).show();

                //showing the popup
                if(!(Integer.parseInt(n)>2 && ans.equals(0))){
                    LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.popup,null);
                    popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    button = customView.findViewById(R.id.cancel);
                    valueoffact = customView.findViewById(R.id.factorialans);
                    valueoffact.setMovementMethod(new ScrollingMovementMethod());
                    valueofn = customView.findViewById(R.id.n);

                    valueoffact.setText(ans.toString());
                    valueofn.setText(n);

                    popupWindow.setTouchable(true);
                    popupWindow.setHeight(1800);
                    popupWindow.setWidth(800);
                    //popupWindow.setFocusable(true);
                    //popupWindow.update();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                }
                long endTime = System.nanoTime();
                Toast.makeText(MainActivity.this, "Time taken = "+(endTime - startTime) +"ns", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initialize() {
        relativeLayout = findViewById(R.id.relativel);
        btnokay = findViewById(R.id.okbtn);
        editText = findViewById(R.id.entern);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
    }
}