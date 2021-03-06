package com.jkanetwork.st.resistor;

/**
 * Created by joselucross on 2/11/16.
 */
public enum Color {
    BLACK("996B42" ),BROWN("FF0000"),RED("FFA500"),
    ORANGE("FFFF00"),YELLOW("00FF00"),GREEN("0000FF"),
    BLUE("b168b4"),VIOLET("b3b3b3"),GREY("FFFFFF"),
    WHITE("000000");

    //Attributes
    private String colorNext;
    //Builder
    private Color(String colorNext){
        this.colorNext = colorNext;
    }
    //Getters
    public String getColorNext(){ return this.colorNext; }

    public static Color getColorByNum(String number){
        switch (number){
            case "000000":
                return Color.BLACK;
            case "996b42":
                return Color.BROWN;
            case "ff0000":
                return Color.RED;
            case "ffa500":
                return  Color.ORANGE;
            case "ffff00":
                return Color.YELLOW;
            case "00ff00":
                return Color.GREEN;
            case "0000ff":
                return  Color.BLUE;
            case "b168b4":
                return  Color.VIOLET;
            case "b3b3b3":
                return  Color.GREY;
            default:
                return Color.WHITE;
        }
    }
}
