import javax.swing.border.AbstractBorder;
import java.awt.*;

@SuppressWarnings("serial")
class CustomeBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        super.paintBorder(c, g, x, y, width, height);
        Graphics2D view = (Graphics2D)g;
        view.setStroke(new BasicStroke(12));
        view.setColor(Color.DARK_GRAY);
        view.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
    }
}
