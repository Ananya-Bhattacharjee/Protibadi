import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private static final String USER_AGENT = "Mozilla/5.0";

    private static void sendPOST() throws IOException {
        String token="EAAFbJIHdvPUBAAbdXttPowZBI6GZBiEdBoptuTB04SvyNopjJhoj8JjEs6jqQiaB1mEMuJXJwrPZAGZCPsfZB5mZAFqwVKahs4N0XFROpEbmqodaAZBDXSHZBrgx51SbwWgqcI1ZCBsz1qJwZBvSLu0XVZBSt5NPBEzxoUiiutQUSNZAemTX5bgX1oWRNoDL2Cq8SCxuVG0xgJkyZAwZDZD";
        URL obj = new URL("https://graph.facebook.com/trolledabrhk/feed?message=Hello Fans! &access_token=EAAFbJIHdvPUBAGWVp1SclZB5sInpmLFeURyIo6M1yX761EeLMGH0rJJxWHQLEAlaGRS8RQfZCpC12TMMO6VdNYW1j5uPPmpYPDszrGhCP3LtONZBZBQdlfjvYsMwZCCiqmwK9yxZCHaWrU3jMSlC8QY0miSQtSqFWXV7r3yyXcQZCodUK00FOJwTXZBVbPlm8SMZD");

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        //con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        //os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpsURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.err.println();
            System.out.println("POST request not worked");
        }
    }


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
            final FacebookClient fb = new DefaultFacebookClient("EAAFbJIHdvPUBAOyJPensazRimQcnxKz8fM6ZAzqmJ6VkOwFfGFtjkvcwrYo4rl0jEN2OQUh5CyNpRcZBqUQ8sBudKGT3TT4slttgLDDeHfXUOhPYAPRIQsoDOz41eJZCVxKX1PslbGh46470gtrCTsZCIlULKQOhbWhOOnjimssMwUqgZB8F7S1DWNBvSAVPfqhL4BNLYrwZDZD");
            final Page page = fb.fetchObject("349243719108528", Page.class);
            //fb.publish("324917484323515/feed", FacebookType.class, Parameter.with("message", "Rest FB test"));
            fb.publish("349243719108528/photos", FacebookType.class,  BinaryAttachment.with("FullScreenshot.jpg", new FileInputStream(new File("FullScreenshot.jpg"))),Parameter.with("message","www.facebook.com/"+selectedText.substring(36) ));





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
