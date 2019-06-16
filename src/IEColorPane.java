import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class IEColorPane extends IEButtonPane<Color> {
	
	private IEToolManager toolManager;
	
	public IEColorPane(IEToolManager toolManager)
	{
		super();
		this.toolManager = toolManager;
		initButtons();
	}
	
	/**
	 * Initializes the color pane and adds the appropriate colored buttons.
	 */
	private void initButtons()
	{
		final Button b1 = new Button(" ");
		//final Button b3 = new Button(" ");
		//final Button b2 = new Button(" ");
		
		b1.setStyle("-fx-background-color:red");
		//b2.setStyle("-fx-background-color:green");
		//b3.setStyle("-fx-background-color:blue");
		
		addButtonToMap(b1, Color.RED);
		//addButtonToMap(b2, Color.GREEN);
		//addButtonToMap(b3, Color.BLUE);
		
		b1.setOnAction(getButtonHandler());
		//b2.setOnAction(getButtonHandler());
		//b3.setOnAction(getButtonHandler());
		
		add(b1, 0, 0);
		//add(b2, 1, 0);
		//add(b3, 0, 1);
	}

	@Override
	protected void handleButton(Button b) {
		toolManager.setCurColor(getValue(b));
	}
}
