import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class IEImagePane extends TabPane {

	private IEToolManager toolManager;

	public IEImagePane(IEToolManager toolManager)
	{
		super();
		this.toolManager = toolManager;
	}

	/**
	 * Adds a tab to the image pane. The tab contains
	 * a scroll pane which contains the image opened
	 * by the user.
	 * @param iv An image to be opened in a new tab.
	 * @param tabName The name of the tab.
	 */
	public void addTab(Image img, String tabName)
	{
		Tab newTab = new Tab();
		newTab.setText(tabName);
		newTab.setContent(createNewScrollPane(createNewCanvas(img)));
		getTabs().add(newTab);
	}

	/**
	 * Adds a tab to the image pane that contains a new
	 * image.
	 * @param x The width of the new image.
	 * @param y The height of the new image.
	 */
	public void addTab(int x, int y)
	{
		Tab newTab = new Tab();
		newTab.setText("Untitled");

		WritableImage img = new WritableImage(x, y);
		PixelWriter   pw  = img.getPixelWriter();
		for(int xCor = 0 ; xCor < x ; xCor++)
		{
			for(int yCor = 0 ; yCor < y ; yCor++)
			{
				pw.setColor(xCor, yCor, Color.rgb(255, 255, 255));
			}
		}

		newTab.setContent(createNewScrollPane(createNewCanvas(img)));
		getTabs().add(newTab);
	}

	/**
	 * Creates a new canvas, assigns the canvas an image
	 * and adds the appropriate event handlers.
	 * @param img The image to be displayed on the canvas.
	 * @return The created canvas.
	 */
	private Canvas createNewCanvas(Image img)
	{
		Canvas canvas = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img, 0, 0);
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, toolManager);
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, toolManager);
		return canvas;
	}

	/**
	 * Creates a new scroll pane and adds the provided canvas
	 * to it.
	 * @param canvas The canvas to be set on the scroll pane.
	 * @return The created scroll pane.
	 */
	private ScrollPane createNewScrollPane(Canvas canvas)
	{
		ScrollPane sp = new ScrollPane();
		sp.setContent(canvas);
		sp.setStyle("-fx-background-color:LightGray");
		return sp;
	}

	/**
	 * Returns the image from the active tab.
	 * @return The image from the active tab.
	 */
	public WritableImage getCurrentImage()
	{
		return ((ScrollPane)(getSelectionModel().getSelectedItem().getContent())).getContent().snapshot(null, null);
	}

	/**
	 * Returns if there is an active tab or not.
	 * @return A boolean representing if there is an active tab.
	 */
	public boolean hasCurrentImage()
	{
		return getSelectionModel().getSelectedItem() != null;
	}
}
