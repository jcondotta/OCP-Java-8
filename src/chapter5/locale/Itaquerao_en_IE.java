package chapter5.locale;

import java.util.ListResourceBundle;

public class Itaquerao_en_IE extends ListResourceBundle {

    public Itaquerao_en_IE() {
        System.out.println("Itaquerao_en_IE");
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"obrigado","Cheers"},
                {"atelogo","see ya"}
        };
    }
}
