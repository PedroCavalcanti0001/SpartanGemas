package me.zkingofkill.spartan.gemas.utils;

import me.zkingofkill.spartan.gemas.SpartanGemas;

import java.text.DecimalFormat;

public class Utils {
    public static String numberFormat(double value) {
        String[] suffix = SpartanGemas.getInstance().getConfig().getStringList("CasasDecimais").toArray(new String[0]);
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s %s", decimalFormat.format(value), suffix[index]);
    }

}
