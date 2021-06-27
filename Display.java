
import java.awt.EventQueue;

public class Display {
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
            public void run() { 
                try {
                      new GUI();

                } catch (Exception e) {
                        System.out.print("ERROR JFrame");
                }
            }
        });
    }
}
