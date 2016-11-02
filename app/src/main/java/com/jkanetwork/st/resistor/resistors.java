package com.jkanetwork.st.resistor;

import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class resistors extends AppCompatActivity {

    private Button color1,color2,color3;
    private TextView resistorNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        color1 = (Button) findViewById(R.id.color1);
        color2 = (Button) findViewById(R.id.color2);
        color3 = (Button) findViewById(R.id.color3);
        resistorNum = (TextView) findViewById(R.id.resistor_num);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resistors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeColor1(View view){
        changeColor(color1);
    }
    public void changeColor2(View view){
        changeColor(color2);
    }
    public void changeColor3(View view){
        changeColor(color3);
    }

    private void changeColor(Button button){
        Color color;
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        String colorString = Integer.toHexString(buttonColor.getColor());
        color = Color.getColorByNum(colorString.substring(2,8));
        button.setBackgroundColor(android.graphics.Color.parseColor("#" +color.getColorNext()));
    }

    public void calculateResistance(View view){
        String number = "";
        Color[] colors = new Color[3];
        String[] colorString = new String[3];
        colorString[0] = Integer.toHexString((((ColorDrawable) color1.getBackground()).getColor()));
        colorString[1] = Integer.toHexString((((ColorDrawable) color2.getBackground()).getColor()));
        colorString[2] = Integer.toHexString((((ColorDrawable) color3.getBackground()).getColor()));

        for(int i = 0; i<3; i++) {
            colors[i] = Color.getColorByNum(colorString[i].substring(2,8));
            number += getResistorValue(colors[i],i);
        }
        resistorNum.setText(number);
    }

    private String getResistorValue(Color color, int p) {
        int value;
        switch (color) {
            case BLACK:
                value = 0;
                break;
            case BROWN:
                value = 1;
                break;
            case RED:
                value = 2;
                break;
            case ORANGE:
                value = 3;
                break;
            case YELLOW:
                value = 4;
                break;
            case GREEN:
                value = 5;
                break;
            case BLUE:
                value = 6;
                break;
            case VIOLET:
                value = 7;
                break;
            case GREY:
                value = 8;
                break;
            default:
                value = 9;
        }
        String ret = "";
        if (p == 2) {
            double power = (int) Math.pow(10, value);
            if (power >= Math.pow(10, 6)) {
                ret += power / Math.pow(10, 6) + "M";

            } else if (power >= Math.pow(10, 3)) {
                ret += power / Math.pow(10, 3) + "K";
            } else {
                ret += power;
            }
            ret += "\u0937";
        } else
            ret += value;
        return ret;
    }
}
