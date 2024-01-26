package software.ulpgc.moneyCalculator.swing;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class ImagePanelUI extends PanelUI {
    private Image backgroundImage;

    public ImagePanelUI(String fileName) {
        this.backgroundImage = createBackgroundImage(fileName);
    }

    private Image createBackgroundImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        // Dibujar la imagen de fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, c.getWidth(), c.getHeight(), c);
        }
    }
}