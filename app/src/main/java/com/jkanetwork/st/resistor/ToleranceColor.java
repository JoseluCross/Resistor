package com.jkanetwork.st.resistor;

/**
 * Created by joselucross on 3/11/16.
 */
public enum ToleranceColor {
    BLACK("996B42" ),BROWN("FF0000"),RED("00FF00"),GREEN("0000FF"),
    BLUE("b168b4"),VIOLET("b3b3b3"),GREY("FFBF00"),GOLD("86979A"),SILVER("000000");

    private String colorNext;
    ToleranceColor(String next){
        this.colorNext=next;
    }
    public String getColorNext(){
        return this.colorNext;
    }
    public static ToleranceColor getColorByNum(String number){
        switch (number){
            case "000000":
                return ToleranceColor.BLACK;
            case "996b42":
                return ToleranceColor.BROWN;
            case "ff0000":
                return ToleranceColor.RED;
            case "00ff00":
                return ToleranceColor.GREEN;
            case "0000ff":
                return ToleranceColor.BLUE;
            case "b168b4":
                return ToleranceColor.VIOLET;
            case "b3b3b3":
                return ToleranceColor.GREY;
            case "ffbf00":
                return ToleranceColor.GOLD;
            default:
                return ToleranceColor.SILVER;
        }
    }
}
