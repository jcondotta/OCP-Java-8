package chapter5;

import chapter5.locale.TestLocale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TestFormattingNumbers {

    static NumberFormat brazilianNumberFormat;
    static NumberFormat americanNumberFormat;
    static NumberFormat defaultIrishNumberFormat;

    public static void main(String[] args) throws ParseException {
        double numericValue = 3110.45;
        String stringValue = "3110,45";

        brazilianNumberFormat = NumberFormat.getNumberInstance(TestLocale.brazil);
        americanNumberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        defaultIrishNumberFormat = NumberFormat.getNumberInstance();

        System.out.println("Brazilian number Format: " + brazilianNumberFormat.format(numericValue));
        System.out.println("American number Format: " + americanNumberFormat.format(numericValue));
        System.out.println("Irish number Format: " + defaultIrishNumberFormat.format(numericValue));

        System.out.println("Brazilian number Parse: " + (Number) brazilianNumberFormat.parse("10000x10"));
        System.out.println("American number Parse: " + (Number) americanNumberFormat.parse(stringValue));
        System.out.println("Irish number Parse: " + (Number) defaultIrishNumberFormat.parse(stringValue));

        System.out.println("");

        brazilianNumberFormat = NumberFormat.getCurrencyInstance(TestLocale.brazil);
        americanNumberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        defaultIrishNumberFormat = NumberFormat.getCurrencyInstance();

        System.out.println("Brazilian currency Format: " + brazilianNumberFormat.format(numericValue));
        System.out.println("American currency Format: " + americanNumberFormat.format(numericValue));
        System.out.println("Irish currency Format: " + defaultIrishNumberFormat.format(numericValue));

        Double myValue = (Double) brazilianNumberFormat.parse("R$ 31,45");
        System.out.println("Brazilian currency Parse: " + myValue);
        System.out.println("American currency Parse: " + (Number) americanNumberFormat.parse("$" + stringValue));
        System.out.println("Irish currency Parse: " + (Number) defaultIrishNumberFormat.parse("€"+ stringValue));


    }


}