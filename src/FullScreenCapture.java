import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*www. j  av  a  2 s. c  om*/



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

            //f.setLocation(500, 500);
            //JLabel text = new JLabel("A full screenshot saved!");
            //f.add(text);
            //f.setSize(200, 100);
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //f.getContentPane().setLayout(new FlowLayout());
            //f.setVisible(true);
            //ImageEditor i=new ImageEditor();
            ImageEditor.main("FullScreenshot.jpg");
            System.out.println(1);

            //String accessToken="";
            //FacebookClient fbClient=new DefaultFacebookClient(accessToken);
            //Connection<Gr=fbClient.fetchConnection("me/groups",Group.class);
            //sendPOST();
            //System.out.println("GET DONE");







            final FacebookClient fb = new DefaultFacebookClient("EAAFbJIHdvPUBAOyJPensazRimQcnxKz8fM6ZAzqmJ6VkOwFfGFtjkvcwrYo4rl0jEN2OQUh5CyNpRcZBqUQ8sBudKGT3TT4slttgLDDeHfXUOhPYAPRIQsoDOz41eJZCVxKX1PslbGh46470gtrCTsZCIlULKQOhbWhOOnjimssMwUqgZB8F7S1DWNBvSAVPfqhL4BNLYrwZDZD");
            final Page page = fb.fetchObject("349243719108528", Page.class);
            //fb.publish("324917484323515/feed", FacebookType.class, Parameter.with("message", "Rest FB test"));
            fb.publish("349243719108528/photos", FacebookType.class,  BinaryAttachment.with("FullScreenshot.jpg", new FileInputStream(new File("FullScreenshot.jpg"))),Parameter.with("message","www.facebook.com/"+selectedText.substring(36) ));





        } catch (AWTException | IOException | InterruptedException ex) {
            System.err.println(ex);
        }
    }
}

