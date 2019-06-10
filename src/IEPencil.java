import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class IEPencil extends IETool {

	@Override
	public void handleMouseAction(MouseEvent me, GraphicsContext gc)
	{
		@SuppressWarnings("unchecked")
		EventType<MouseEvent> e = (EventType<MouseEvent>)me.getEventType();
		if(e.equals(MOUSE_DRAGGED) || e.equals(MOUSE_CLICKED))
		{
			gc.fillRect(me.getX() - 5, me.getY() - 5, 10, 10);
		}
	}

}
