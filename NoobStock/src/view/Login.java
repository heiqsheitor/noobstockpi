package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Login() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][][grow]", "[][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("NoobStock");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1, "flowx,cell 1 2,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 2");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton, "flowy,cell 1 3,alignx center");
		
		JButton btnNewButton_1 = new JButton("Não tenho cadastro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton_1, "cell 1 3,alignx center,aligny bottom");

	}

}
