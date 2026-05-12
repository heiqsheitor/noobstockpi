package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComponentUtils {
	// Tela de acção de label, botões e etc.
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