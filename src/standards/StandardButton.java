package standards;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StandardButton extends JButton {

    private String text;
    private Color defaultColor = Color.decode("#5cb85c");
    private boolean change = false;
    private ImageIcon icon;
    private boolean icons = false;
    private Font fonts = null;
    private Graphics2D g2;

    public StandardButton() {
        this.text = text;
        super.setFont(new Font("arial", Font.BOLD, 15));
        setFocusPainted(false);
        setFocusable(false);

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFonts() {
        return fonts;
    }

    public void setFonts(Font fonts) {
        this.fonts = fonts;
        super.setFont(fonts);
    }

    public Font getGraphicsFont() {
        return g2.getFont();
    }

}
