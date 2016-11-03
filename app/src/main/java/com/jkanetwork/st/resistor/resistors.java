package com.jkanetwork.st.resistor;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class resistors extends AppCompatActivity {

    private Button color1,color2,color3,tolerance;
    private TextView resistorNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistors);
        color1 = (Button) findViewById(R.id.color1);
        color2 = (Button) findViewById(R.id.color2);
        color3 = (Button) findViewById(R.id.color3);
        tolerance = (Button) findViewById(R.id.tolerance);
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
    public void changeToleranceColor(View view){
        ToleranceColor color;
        ColorDrawable buttonColor = (ColorDrawable) tolerance.getBackground();
        String colorString = Integer.toHexString(buttonColor.getColor());
        color = ToleranceColor.getColorByNum(colorString.substring(2,8));
        tolerance.setBackgroundColor(android.graphics.Color.parseColor("#"+color.getColorNext()));
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
        double resistanceValue=0;
        String[] colorString = new String[3];
        colorString[0] = Integer.toHexString((((ColorDrawable) color1.getBackground()).getColor()));
        colorString[1] = Integer.toHexString((((ColorDrawable) color2.getBackground()).getColor()));
        colorString[2] = Integer.toHexString((((ColorDrawable) color3.getBackground()).getColor()));
        for(int i = 0; i<3; i++) {
            colors[i] = Color.getColorByNum(colorString[i].substring(2,8));
            double value = getResistorValue(colors[i],i);
            switch (i) {
                case 0:
                    resistanceValue += (value * 10);
                    break;
                case 1:
                    resistanceValue += value;
                    break;
                case 2:
                    resistanceValue *= value;
                    break;
                }
        }
        char potential;
        if(resistanceValue > Math.pow(10,6)) {
            potential = 'M';
            resistanceValue /= 1000000;
        }else if(resistanceValue > Math.pow(10,3)) {
            potential = 'K';
            resistanceValue /= 1000;
        }else
            potential = ' ';
        int toleranceColor = ((ColorDrawable) this.tolerance.getBackground()).getColor();
        ToleranceColor tolerance = ToleranceColor.getColorByNum(Integer.toHexString(toleranceColor).substring(2,8));
        number = getTolerance(tolerance);
        resistorNum.setText(resistanceValue + "" + potential + "" + number + "Ω");
    }

    private String getTolerance(ToleranceColor color){
        String tolerance = " ±";
        switch (color){
            case BLACK:
                return "";
            case BROWN:
                return tolerance + "1%";
            case RED:
                return tolerance + "2%";
            case GREEN:
                return tolerance + "0.5%";
            case BLUE:
                return tolerance + "0.25%";
            case VIOLET:
                return tolerance + "0.10%";
            case GREY:
                return tolerance + "0.05%";
            case GOLD:
                return tolerance + "5%";
            case SILVER:
                return tolerance + "10%";
            default:
                return "";
        }
    }
    private double getResistorValue(Color color, int p) {
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
        if (p == 2) {
            return Math.pow(10, value);
        } else
            return value;
    }
}
