package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaAdicionarProduto extends JPanel {
	private JTextField TFProduto;
	private JTextField TFSKU;
	private JTextField TFQtd;
	private JTextField TFLocalizacao;
	private JTextField TFFornecedor;
	private JTextField TFCategoria;
	
	public TelaAdicionarProduto() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][][grow][grow 30]", "[grow 1][][][grow 1][grow 1][grow 1][grow 1][grow 1][grow 1][grow 1][]"));
		
		JLabel Voltar = new JLabel("");
		Voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		Voltar.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/button→svg.png")));
		add(Voltar, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("Adicionar Produto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_1, "cell 1 0");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/svg.png")));
		add(lblNewLabel_2, "flowy,cell 2 1");
		
		JLabel lblNewLabel_3 = new JLabel("Adicionar Produto ao Estoque");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_3, "cell 3 1,alignx left");
		
		JLabel lblNewLabel_4 = new JLabel("Preencha as informações abaixo para adicionar um novo produto ao seu estoque");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(192, 192, 192));
		add(lblNewLabel_4, "cell 3 2");
		
		JLabel lblNewLabel_5 = new JLabel("Nome do produto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_5, "flowy,cell 3 3");
		
		JLabel lblNewLabel_6 = new JLabel("SKU (Código do Produto)");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_6, "flowy,cell 3 4");
		
		JLabel lblNewLabel_7 = new JLabel("Quantidade");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_7, "flowy,cell 3 5");
		
		JLabel lblNewLabel_8 = new JLabel("Localização no Estoque");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_8, "flowy,cell 3 6");
		
		JLabel lblNewLabel_9 = new JLabel("Fornecedor");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_9, "flowy,cell 3 7");
		
		JLabel lblNewLabel_10 = new JLabel("Categoria do Produto");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_10, "flowy,cell 3 8");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCancelar, "flowx,cell 3 9,growx");
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pegar o que o usuário digitou
		        String nomeProduto = TFProduto.getText();
		        String SKUProd = TFSKU.getText();
		        String Qtd = TFQtd.getText();
		        String Localizacao = TFLocalizacao.getText();
		        String Fornecedor = TFFornecedor.getText();
		        String Categoria = TFCategoria.getText();

		        // Criar um objeto de Usuário com esses dados
		        // (O primeiro parâmetro é o ID, passamos null porque o banco cria sozinho)
		        model.Produto novo = new model.Produto(null, SKUProd, nomeProduto, Qtd, Localizacao, Fornecedor, Categoria);

		        // Chamar o DAO para levar esses dados ao MySQL
		        model.ProdutoDAO dao = new model.ProdutoDAO();
		        dao.cadastrarProduto(novo);

		        // Aviso na tela que funcionou
		        javax.swing.JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
		        
		        TFProduto.setText("");
		        TFSKU.setText("");
		        TFQtd.setText("");
		        TFLocalizacao.setText("");
		        TFFornecedor.setText("");
		        TFCategoria.setText("");
			}
		});
		btnAdicionar.setBackground(Color.BLACK);
		btnAdicionar.setForeground(new Color(255, 255, 255));
		add(btnAdicionar, "cell 3 9,growx");
		btnAdicionar.setOpaque(true);
		btnAdicionar.setBorderPainted(false);
		
		TFProduto = new JTextField();
		add(TFProduto, "cell 3 3,growx");
		TFProduto.setColumns(10);
		
		TFSKU = new JTextField();
		add(TFSKU, "cell 3 4,growx");
		TFSKU.setColumns(10);
		
		TFQtd = new JTextField();
		add(TFQtd, "cell 3 5,growx");
		TFQtd.setColumns(10);
		
		TFLocalizacao = new JTextField();
		add(TFLocalizacao, "cell 3 6,growx");
		TFLocalizacao.setColumns(10);
		
		TFFornecedor = new JTextField();
		add(TFFornecedor, "cell 3 7,growx");
		TFFornecedor.setColumns(10);
		
		TFCategoria = new JTextField();
		add(TFCategoria, "cell 3 8,growx");
		TFCategoria.setColumns(10);
	}   
}