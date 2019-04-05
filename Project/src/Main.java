import model.PasswordManagerModel;
import org.jdom2.JDOMException;
import view.PasswordManagerView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        testView();
    }

    public static void testView() throws IOException, JDOMException {
        PasswordManagerModel pmm = new PasswordManagerModel();
        PasswordManagerView pmv = new PasswordManagerView(pmm);
        pmv.createStartView();
    }
}