package chapter5.locale;

import java.util.ListResourceBundle;

public class Itaquerao_en extends ListResourceBundle {

    public Itaquerao_en() {
        System.out.println("Itaquerao_en");
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"obrigado","Thank you"},
                {"atelogo","see you later"},
                {"bemvindo", "Welcome"}
        };
    }
}
