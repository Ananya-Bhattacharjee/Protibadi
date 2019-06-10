import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import jfx.messagebox.MessageBox;

public class ImageEditor extends Application {

	private IEImagePane imagePane;
	private Stage         mainStage;
	private IEFileManager fileManager;

	public static void main(String args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage){
		stage.setTitle("Image Editor");
		final Screen        screen      = Screen.getPrimary();
		final Rectangle2D   bounds      = screen.getVisualBounds();
		final IEToolManager toolManager = new IEToolManager();
		final MenuBar       menuBar     = new MenuBar();
		final IEToolPane toolPane    = new IEToolPane(toolManager);
		final IEColorPane   colorPane   = new IEColorPane(toolManager);
		mainStage                 	    = stage;
		imagePane                       = new IEImagePane(toolManager);
	
		initFileManager();
		menuBar.getMenus().addAll(initMenus());

		stage.setX(bounds.getMinX());
		stage.setY(bounds.getMinY());
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());

		VBox leftBox = new VBox();
		leftBox.setSpacing(10);
		leftBox.getChildren().addAll(toolPane, colorPane);
		leftBox.setStyle("-fx-background-color:grey;");

		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(imagePane);
		bp.setLeft(leftBox);

		stage.setScene(new Scene(bp, bounds.getWidth(), bounds.getHeight()));
		stage.show();
	}

	/**
	 * Initializes the file manager with the appropriate file extensions.
	 */
	private void initFileManager()
	{
		ObservableList<String> extensionsStrings = 
			    FXCollections.observableArrayList(
			        ".png",
			        ".gif"
			    );
		fileManager = new IEFileManager(extensionsStrings);
	}
	
	/**
	 * Initializes Menus and their corresponding menu items.
	 * @return A list of the initialized menus.
	 */
	private List<Menu> initMenus()
	{
		final Menu fileMenu     = new Menu("File");
		fileMenu.getItems().addAll(initFileMenuItems());

		final Menu editMenu = new Menu("Edit");
		editMenu.getItems().addAll(initEditMenuItems());

		final Menu helpMenu  = new Menu("Help");
		helpMenu.getItems().addAll(initHelpMenuItems());

		return new ArrayList<Menu>(Arrays.asList(fileMenu, editMenu, helpMenu));
	}

	/**
	 * Initializes the menu items for the file menu.
	 * @return A list containing the menu items for the file menu.
	 */
	private List<MenuItem> initFileMenuItems()
	{
		//Creates a new image.
		final MenuItem newImage = new MenuItem("New");
		newImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				newImageDialog();
			}
		});
		//Opens an existing image.
		final MenuItem open = new MenuItem("Open");
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				openImage();
			}
		});
		//Saves the image the user is currently working on.
		final MenuItem save = new MenuItem("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					saveImage();
				} catch (AWTException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		return new ArrayList<MenuItem>(Arrays.asList(newImage, open, save));
	}

	/**
	 * Initializes the menu items for the help menu.
	 * @return A list containing the menu items for the help menu.
	 */
	private List<MenuItem> initHelpMenuItems()
	{
		final MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				openAboutWindow();
			}
		});

		return new ArrayList<MenuItem>(Arrays.asList(about));
	}

	/**
	 * Initializes the menu items for the edit menu.
	 * @return A list containing the menu items for the edit menu.
	 */
	private List<MenuItem> initEditMenuItems()
	{
		final MenuItem undo = new MenuItem("Undo");
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

			}
		});
		final MenuItem copy = new MenuItem("Copy");
		copy.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

			}
		});

		return new ArrayList<MenuItem>(Arrays.asList(undo, copy));
	}

	/**
	 * Prompts the user for dimensions of a new image.
	 * @return The dimensions of the new image.
	 */
	private void newImageDialog()
	{
		final Stage newImageStage = new Stage();
		newImageStage.initModality(Modality.WINDOW_MODAL);
		newImageStage.initOwner(mainStage);
		newImageStage.setTitle("New Image");
		newImageStage.setResizable(false);

		final GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));

		final Label     widthLabel      = new Label("Width:");
		final Label 	heightLabel     = new Label("Height:");
		final TextField widthTextField  = new TextField();
		final TextField heightTextField = new TextField();
		final Button    createButton    = new Button("Create");

		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try
				{
					int width = Integer.parseInt(widthTextField.getText());
					int height = Integer.parseInt(heightTextField.getText());
					imagePane.addTab(width, height);
					((Node)(e.getSource())).getScene().getWindow().hide();
				}
				catch(NumberFormatException exception)
				{
					showErrorDialog("Illegal arguments were provided for the new image.", newImageStage);
				}
			}
		});

		gp.add(widthLabel,      0, 0);
		gp.add(widthTextField,  1, 0);
		gp.add(heightLabel,     0, 1);
		gp.add(heightTextField, 1, 1);
		gp.add(createButton,    0, 2);

		newImageStage.setScene(new Scene(gp, 300, 200));
		newImageStage.show();
	}

	/**
	 * Uses the file manager to open an image. Any exception
	 * is caught because we just want to show that an error
	 * occurred when opening the image. We don't care what
	 * exception is thrown because there were many possibilities
	 * found during testing.
	 */
	private void openImage()
	{
		try
		{
			//final File imageFile = fileManager.chooseFile(mainStage);
			final Image image = fileManager.loadImage(new File("FullScreenshot.jpg"));
			//imagePane.addTab(image, imageFile.getName());
			imagePane.addTab(image,"FullScreenshot.jpg");
			imagePane.setMaxHeight(1000);
			imagePane.setMaxWidth(1000);
		}
		catch(Exception exception)
		{
			//There were many types of exceptions thrown when doing various testing.
			//We don't really care what is thrown, we care that there was an error
			//loading the image.
			showErrorDialog("There was an error opening the selected image.", mainStage);
		}
		
	}

	/**
	 * Saves an image using the file manager. If no image
	 * is currently open then an error dialog is opened.
	 */
	private void saveImage() throws AWTException, IOException {
		if(imagePane.hasCurrentImage())
		{
			//fileManager.saveImageDialog(mainStage, imagePane.getCurrentImage());
			//fileManager.saveFile(mainStage);
			String fileName = "FullScreenshot.jpg";
			Robot robot = new Robot();

//			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
//					.getScreenSize());

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
					.getScreenSize());
			screenRect = new Rectangle(0, 0, screenRect.width, screenRect.height-30);
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, "jpg", new File(fileName));



		}
		else
		{
			showErrorDialog("There is no image to save.", mainStage);
		}
	}

	/**
	 * Opens a window providing information about the image editor.
	 */
	private void openAboutWindow()
	{

	}

	/**
	 * Shows an error dialog with the given string.
	 * @param error The error message to be displayed.
	 */
	private void showErrorDialog(String error, Stage stage)
	{
		//MessageBox.show(stage, error, "Error", MessageBox.ICON_ERROR);
	}
}
