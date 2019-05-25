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
/*    public static String getUrl() throws IOException, UnsupportedFlavorException, InterruptedException, AWTException {
        Thread.sleep(3000);//
        Robot r=new Robot();
        r.keyPress(KeyEvent.VK_ALT);  to get focus on taskbar
   r.keyPress(KeyEvent.VK_D);
        //r.keyRelease(KeyEvent.VK_ALT);
        r.keyPress(KeyEvent.VK_D);
        r.keyRelease(KeyEvent.VK_ALT);
        r.keyRelease(KeyEvent.VK_D);
        r.keyPress(KeyEvent.VK_CONTROL);  /* to copy it*/
/*        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_C);
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex) {
        }
        String selectedText =(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // it extracts the highlighted text of system clipboard
        return selectedText;
    }
*/
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        //Url url = ((WebRequest)RequestCycle.get().getRequest()).getUrl();
        //String fullUrl = RequestCycle.get().getUrlRenderer().renderFullUrl(url);

        /*System.setProperty("webdriver.gecko.driver",
                "F:\\Software\\geckodriver.exe");

        WebDriver driver= new FirefoxDriver();
        driver.get("http://google.com");
        while(true) {
            String currentURL = driver.getCurrentUrl();
            System.out.println(currentURL);
            if (currentURL.length()>400) break;
        }*/


        FullScreenCapture f = new FullScreenCapture();
        try {
            /*
             * Let the program wait for 5 seconds to allow you to open the
             * window whose screenshot has to be captured
             */
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

            String fileName = "D://Protibadi/FullScreenshot.jpg";

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