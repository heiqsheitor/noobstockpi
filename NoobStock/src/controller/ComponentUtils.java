package controller;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class ComponentUtils {

	public static void transformarEmLink(JLabel label, Runnable acaoAoClicar) {
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				acaoAoClicar.run();
			}
		});
	}

	public static void aplicarMascaraCpfCnpj(JTextField textField) {
		AbstractDocument doc = (AbstractDocument) textField.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (string == null)
					return;
				processarTexto(fb, offset, 0, string, attr);
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text == null)
					return;
				processarTexto(fb, offset, length, text, attrs);
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				processarTexto(fb, offset, length, "", null);
			}

			private void processarTexto(FilterBypass fb, int offset, int length, String novoTexto, AttributeSet attrs)
					throws BadLocationException {
				String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
				String textoFuturo = textoAtual.substring(0, offset) + novoTexto
						+ textoAtual.substring(offset + length);

				textoFuturo = textoFuturo.replaceAll("[^0-9]", "");

				if (textoFuturo.length() <= 14) {
					fb.replace(0, fb.getDocument().getLength(), formatar(textoFuturo), attrs);
				}
			}

			private String formatar(String texto) {
				if (texto.length() <= 11) {
					return texto.replaceFirst("(\\d{3})(\\d)", "$1.$2")
							.replaceFirst("(\\d{3})\\.(\\d{3})(\\d)", "$1.$2.$3")
							.replaceFirst("(\\d{3})\\.(\\d{3})\\.(\\d{3})(\\d{1,2})$", "$1.$2.$3-$4");
				} else {
					return texto.replaceFirst("(\\d{2})(\\d)", "$1.$2")
							.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3")
							.replaceFirst("(\\d{2})\\.(\\d{3})\\.(\\d{3})(\\d)", "$1.$2.$3/$4")
							.replaceFirst("(\\d{2})\\.(\\d{3})\\.(\\d{3})/(\\d{4})(\\d{1,2})$", "$1.$2.$3/$4-$5");
				}
			}
		});
	}

	public static void aplicarMascaraMoeda(JTextField textField) {
		textField.setText("0,00");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);

		AbstractDocument doc = (AbstractDocument) textField.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				processar(fb, offset, 0, string, attr);
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				processar(fb, offset, length, text, attrs);
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				processar(fb, offset, length, "", null);
			}

			private void processar(FilterBypass fb, int offset, int length, String novoTexto, AttributeSet attrs)
					throws BadLocationException {
				String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
				String textoFuturo = textoAtual.substring(0, offset) + novoTexto
						+ textoAtual.substring(offset + length);

				String apenasNumeros = textoFuturo.replaceAll("[^0-9]", "");
				if (apenasNumeros.isEmpty())
					apenasNumeros = "0";

				if (apenasNumeros.length() > 10)
					return;

				double valor = Double.parseDouble(apenasNumeros) / 100;

				java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
				String textoFormatado = df.format(valor);

				fb.replace(0, fb.getDocument().getLength(), textoFormatado, attrs);
			}
		});
	}
	
	public static void associarTeclaEnter(JPanel painel, JButton botao) {
        // Pega o mapa de inputs do painel quando ele está focado na janela
        InputMap inputMap = painel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painel.getActionMap();

        // Mapeia a tecla "ENTER" para uma ação chamada "pressionarEnter"
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "pressionarEnter");
        
        // Define o que a ação "pressionarEnter" faz (clica no botão)
        actionMap.put("pressionarEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se o botão estiver visível e habilitado, ele realiza o clique
                if (botao.isEnabled() && botao.isVisible()) {
                    botao.doClick();
                }
            }
        });
    }
}