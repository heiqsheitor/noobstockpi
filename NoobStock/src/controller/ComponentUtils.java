package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ComponentUtils {
	public static void transformarEmLink(JLabel label, Runnable acaoAoClicar) {
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                acaoAoClicar.run(); // Executa o que a tela pedir
            }
        });
    }
}
