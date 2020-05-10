package chapter5.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestLocale {

    public static Locale portuguese = new Locale.Builder().setLanguage("pt").build();
    static Locale portugal = new Locale("pt", "PT");
    public static Locale brazil = new Locale("pt", "BR");

    public static void main(String[] args) {
//        Arrays.asList(Locale.getAvailableLocales()).stream().forEach(System.out::println);
        //Locale brazil = new Locale.Builder().setRegion("BR").build(); // Invalid

        ResourceBundle rb = null;
//        rb = getResourceBundle(null);
//        printProperties(rb);

//        rb = getResourceBundle(Locale.ENGLISH); //page 264
//        printProperties(rb);

        rb = getResourceBundle(portuguese);
        printProperties(rb);
        System.out.println(MessageFormat.format(rb.getString("ola"), "jefferson"));

        rb = getResourceBundle(brazil);
        printProperties(rb);

        rb = getResourceBundle(portugal);
        printProperties(rb);
////        Properties properties = new Properties();
//        rb.keySet().stream().forEach(k -> properties.put(k, rb.getString(k)));
//
//        System.out.println(properties.getProperty("uihasuidas", "not found"));
    }

    private static ResourceBundle getResourceBundle(Locale locale){
        String pathResourceBundle = "chapter5.locale.Itaquerao";
        if(locale == null){
            System.out.println("Default Locale: " + Locale.getDefault().toString());
            return ResourceBundle.getBundle(pathResourceBundle);
        }
        System.out.println("Custom Locale: " + locale.toString());
        return ResourceBundle.getBundle(pathResourceBundle, locale);
    }

    private static void printProperties(ResourceBundle rb){
        rb.keySet().stream().map(k -> k + ":" + rb.getString(k)).forEach(System.out::println);
        System.out.println("");
    }
}
