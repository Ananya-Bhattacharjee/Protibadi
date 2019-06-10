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
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
/*www. j  av  a  2 s. c  om*/
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



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
            //ImageEditor i=new ImageEditor();
            ImageEditor.main("FullScreenshot.jpg");
            System.out.println(1);



/*
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TestPane());
            frame.pack();
            frame.setVisible(true);

            BufferedImage img = ImageIO.read(new File("FullScreenshot.jpg"));
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.RED);
            g2d.drawRect(0, 0, 100, 100);
            g2d.dispose();*/


        } catch (AWTException | IOException | InterruptedException ex) {
            System.err.println(ex);
        }
    }
}

/*class TestPane extends JPanel {
    private BufferedImage myImage;
    private Rectangle myOffice = new Rectangle(150, 50, 300, 200);
    public TestPane() {
        try {
            myImage = ImageIO.read(new File("FullScreenshot.jpg"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return myImage == null ? new Dimension(200, 200) : new Dimension(
                myImage.getWidth(), myImage.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (myImage != null) {
            int x = (getWidth() - myImage.getWidth()) / 2;
            int y = (getHeight() - myImage.getHeight()) / 2;
            g2d.drawImage(myImage, x, y, this);

            g2d.setColor(Color.RED);
            g2d.translate(x, y);
            g2d.draw(myOffice);
        }
        g2d.dispose();

    }
}
*/
