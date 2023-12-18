package com.java.features;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatText {
    public static void formatNumbers(){
        Locale[] locs = NumberFormat.getAvailableLocales();
        double d = -1234.56;
        NumberFormat nfmt;
        /**** General Format  ****/
        System.out.println("General Format :");
        for (Locale loc:locs){
            if (loc.getCountry().isEmpty()) continue;
            nfmt =NumberFormat.getInstance(loc);
            System.out.printf("%40s->%s %n",loc.getDisplayCountry(),nfmt.format(d));
        }
        /**** Integer Format ***/
        System.out.println("Integer Format : ");
        for (Locale loc:locs){
            if (loc.getCountry().isEmpty()) continue;
            nfmt = NumberFormat.getIntegerInstance(loc);
            System.out.printf("%40s->%s %n",loc.getDisplayCountry(),nfmt.format(d));
        }
        /***** Currency Format ***/
        System.out.println("Currency Format : ");
        /*** Percent Format ***/
        System.out.println("Percent Format : ");
        for (Locale loc:locs){
            if (loc.getCountry().length() ==0) continue;
            nfmt = NumberFormat.getCurrencyInstance(loc);
            System.out.printf("%40s->%s %n",loc.getDisplayCountry(),nfmt.format(d));
        }



    }
    public static void FormatCurrencies(){
        Locale[] locales ={Locale.US,Locale.FRANCE,Locale.JAPAN};
        Double d = Double.valueOf(-23.554);
        for (Locale loc : locales){
            NumberFormat nfmt = NumberFormat.getCurrencyInstance(loc);
            String fmtNumberStr = nfmt.format(d);
            System.out.printf("%15s :%s %n",loc.getDisplayCountry(),nfmt);

        }
        for (Locale locale:locales){
            NumberFormat fmt = NumberFormat.getInstance(locale);
            String fmtNumStr = fmt.format(d);
            System.out.printf("%15s :%s %n",locale.getDisplayCountry(), fmtNumStr);
        }
    }
}
