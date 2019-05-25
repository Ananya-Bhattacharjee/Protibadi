import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This program demonstrates how to capture a screenshot (full screen) as an
 * image which will be saved into a file.
 *
 */
public class FullScreenCapture extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) throws UnsupportedFlavorException {


        FullScreenCapture f = new FullScreenCapture();
        try {
            Thread.sleep(5000);
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(500);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(500);
            String selectedText =(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // it extracts the highlighted text of system clipboard
            System.out.println(selectedText);

            String fileName = "FullScreenshot.jpg";

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
                    .getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, "jpg", new File(fileName));

            f.setLocation(500, 500);
            JLabel text = new JLabel("A full screenshot saved!");
            f.add(text);
            f.setSize(200, 100);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout());
            f.setVisible(true);
        } catch (AWTException | IOException | InterruptedException ex) {
            System.err.println(ex);
        }
    }
}