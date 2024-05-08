package four;
import javax.swing.*;

public class ApplicationRunner {
    public static void main(String[] args) throws  Exception {

        Runnable initFrame = new Runnable(){
            @Override
            public void run() {
                new ConnectFour();
            }
        };

        SwingUtilities.invokeAndWait(initFrame);
    }
}