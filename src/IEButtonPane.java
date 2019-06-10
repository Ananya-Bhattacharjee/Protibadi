import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public abstract class IEButtonPane<T> extends GridPane {

	private final Map<Button, T> buttonMap = new HashMap<Button, T>();
	private final ButtonHandler  buttonHandler = new ButtonHandler();

	protected void addButtonToMap(Button b, T t)
	{
		buttonMap.put(b, t);
	}
	
	protected T getValue(Button b)
	{
		return buttonMap.get(b);
	}
	
	protected ButtonHandler getButtonHandler()
	{
		return buttonHandler;
	}
	
	protected abstract void handleButton(Button b);
	
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) {
			handleButton((Button)e.getSource());
		}	
	}
}
