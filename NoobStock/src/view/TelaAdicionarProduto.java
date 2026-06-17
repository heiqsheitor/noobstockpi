package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import controller.ComponentUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.ProdutoDAO;
import model.Fornecedor;
import model.FornecedorDAO;
import model.CategoriaDAO;

public class TelaAdicionarProduto extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField TFProduto, TFSKU, TFQtd, TFLocalizacao, TFPreco;
	private JComboBox<String> cbFornecedor;
	private JComboBox<String> cbCategoria;
	private JButton btnAdicionar;
	private JLabel Voltar;
	private String produtoIdEmEdicao = null;

	public TelaAdicionarProduto() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][][grow][grow 30]",
				"[][][grow 1][][][grow 1][grow 10][][][grow 1][grow 10][][][grow 1][grow 10][][][grow 1][grow 10][][][grow 1][grow 10][][][grow 1][grow 10][][][grow 1][grow 10][]"));

		Voltar = new JLabel("");
		Voltar.setFont(new Font("Tahoma", Font.PLAIN, 29));
		Voltar.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/button→svg.png")));
		add(Voltar, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Adicionar Produto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblNewLabel_1, "cell 1 0");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/svg.png")));
		add(lblNewLabel_2, "flowy,cell 2 1");

		JLabel lblNewLabel_3 = new JLabel("Adicionar Produto ao Estoque");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewLabel_3, "flowy,cell 3 1,alignx left");

		JLabel lblNewLabel_5 = new JLabel("Nome do produto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_5, "cell 3 3");

		TFProduto = new JTextField();
		add(TFProduto, "cell 3 4 1 2,grow");

		JLabel lblNewLabel_6 = new JLabel("SKU (Gerado automaticamente)");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_6, "cell 3 7");

		TFSKU = new JTextField();
		TFSKU.setEditable(false);
		TFSKU.setBackground(new Color(240, 240, 240));
		TFSKU.setForeground(new Color(120, 120, 120));
		TFSKU.setText("Será gerado automaticamente ao salvar...");
		add(TFSKU, "cell 3 8 1 2,grow");

		JLabel lblNewLabel_7 = new JLabel("Quantidade");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_7, "cell 3 11,alignx left,aligny bottom");

		TFQtd = new JTextField();
		add(TFQtd, "cell 3 12 1 2,grow");

		JLabel lblPreco = new JLabel("Preço Unitario");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblPreco, "cell 3 15");

		TFPreco = new JTextField();
		add(TFPreco, "cell 3 16 1 2,grow");
		ComponentUtils.aplicarMascaraMoeda(TFPreco);

		JLabel lblNewLabel_8 = new JLabel("Localização no Estoque (Clique para selecionar)");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_8, "flowy,cell 3 19");

		TFLocalizacao = new JTextField();
		TFLocalizacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TFLocalizacao.setEditable(false);
		TFLocalizacao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		TFLocalizacao.setText("Clique para definir a prateleira...");
		TFLocalizacao.setBackground(new Color(245, 245, 245));
		add(TFLocalizacao, "cell 3 20 1 2,grow");

		TFLocalizacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mostrarMenuLocalizacao(e.getComponent(), e.getX(), e.getY());
			}
		});

		JLabel lblNewLabel_9 = new JLabel("Fornecedor");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_9, "flowy,cell 3 23,alignx left,aligny bottom");

		cbFornecedor = new JComboBox<>();
		add(cbFornecedor, "cell 3 24 1 2,grow");

		JLabel lblNewLabel_10 = new JLabel("Categoria do Produto");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_10, "flowy,cell 3 27,alignx left,aligny bottom");

		cbCategoria = new JComboBox<>();
		add(cbCategoria, "cell 3 28 1 2,grow");

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdicionar.setBackground(Color.BLACK);
		btnAdicionar.setForeground(new Color(255, 255, 255));
		add(btnAdicionar, "cell 3 31,growx");
		btnAdicionar.setOpaque(true);
		btnAdicionar.setBorderPainted(false);

		JLabel lblNewLabel_4 = new JLabel("Preencha as informações abaixo para adicionar um novo produto");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(192, 192, 192));
		add(lblNewLabel_4, "cell 3 1");

		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentShown(java.awt.event.ComponentEvent e) {
				carregarComboBoxFornecedores();
				carregarComboBoxCategorias();
			}
		});
		
		ComponentUtils.associarTeclaEnter(this, btnAdicionar);
	}

	public boolean validarCampos() {
		if (getNomeProduto().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O nome do produto é obrigatório!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			TFProduto.requestFocus();
			return false;
		}
		if (isEdicao() && getSKU().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O SKU é obrigatório!", "Aviso", JOptionPane.WARNING_MESSAGE);
			TFSKU.requestFocus();
			return false;
		}
		if (getQuantidade().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "A quantidade é obrigatória!", "Aviso", JOptionPane.WARNING_MESSAGE);
			TFQtd.requestFocus();
			return false;
		}
		try {
			int qtd = Integer.parseInt(getQuantidade().trim());
			if (qtd < 0) {
				JOptionPane.showMessageDialog(this, "A quantidade não pode ser negativa!", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				TFQtd.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "A quantidade deve ser um número inteiro válido!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			TFQtd.requestFocus();
			return false;
		}
		if (getPreco().trim().equals("0,00") || getPreco().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O preço unitário deve ser maior que zero!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			TFPreco.requestFocus();
			return false;
		}
		if (getLocalizacao().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione uma localização válida no estoque!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (cbFornecedor.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione um fornecedor válido!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			cbFornecedor.requestFocus();
			return false;
		}
		if (cbCategoria.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione uma categoria válida!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			cbCategoria.requestFocus();
			return false;
		}
		return true;
	}

	private void mostrarMenuLocalizacao(Component invoker, int x, int y) {
		JPopupMenu popup = new JPopupMenu();
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> todosProdutos = dao.listarProdutos();
		List<String> locaisOcupados = new ArrayList<>();

		for (Produto p : todosProdutos) {
			if (p.getLocalização() != null && !p.getLocalização().trim().isEmpty()) {
				if (isEdicao() && p.getId_produto().equals(this.produtoIdEmEdicao))
					continue;
				locaisOcupados.add(p.getLocalização());
			}
		}

		JMenu menuArmazem1 = new JMenu("Armazém 1 - Estoque Principal");
		JMenu menuArmazem2 = new JMenu("Armazém 2 - Excedentes");
		String[] corredores = { "Corredor A", "Corredor B", "Corredor C" };

		for (String corredor : corredores) {
			JMenu menuCorredor = new JMenu(corredor);
			for (int i = 1; i <= 5; i++) {
				String nomeLocal = "Arm. 1 - " + corredor + " - Prat. " + i;
				JMenuItem itemPrateleira = new JMenuItem("Prateleira " + i);

				if (locaisOcupados.contains(nomeLocal)) {
					itemPrateleira.setText("Prateleira " + i + " (Ocupada)");
					itemPrateleira.setEnabled(false);
				} else {
					itemPrateleira.addActionListener(e -> TFLocalizacao.setText(nomeLocal));
				}
				menuCorredor.add(itemPrateleira);
			}
			menuArmazem1.add(menuCorredor);
		}

		popup.add(menuArmazem1);
		popup.add(menuArmazem2);
		popup.show(invoker, x, y);
	}

	public void carregarComboBoxFornecedores() {
		cbFornecedor.removeAllItems();
		cbFornecedor.addItem("0 - Selecione um fornecedor...");
		List<Fornecedor> lista = new FornecedorDAO().listar();
		for (Fornecedor f : lista) {
			cbFornecedor.addItem(f.getIdfornecedor() + " - " + f.getNome());
		}
	}

	public void carregarComboBoxCategorias() {
		cbCategoria.removeAllItems();
		cbCategoria.addItem("0 - Selecione uma categoria...");
		List<String> lista = new CategoriaDAO().listarCategorias();
		for (String cat : lista) {
			cbCategoria.addItem(cat);
		}
	}

	public void preencherParaEdicao(Produto p) {
		this.produtoIdEmEdicao = p.getId_produto();
		TFProduto.setText(p.getNome());
		TFSKU.setForeground(new Color(30, 30, 30));
		TFSKU.setText(p.getSKU());
		TFQtd.setText(p.getQtd());
		TFLocalizacao.setText(p.getLocalização() != null ? p.getLocalização() : "Clique para definir a prateleira...");
		java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
		TFPreco.setText(df.format(p.getPreco()));
		carregarComboBoxFornecedores();

		if (cbFornecedor.getItemCount() > 0) {
			cbFornecedor.setSelectedIndex(0);
			if (p.getFornecedor() != null && !p.getFornecedor().isEmpty()) {
				for (int i = 0; i < cbFornecedor.getItemCount(); i++) {
					String item = cbFornecedor.getItemAt(i);
					if (item.contains(" - " + p.getFornecedor()) || item.startsWith(p.getFornecedor() + " -")) {
						cbFornecedor.setSelectedIndex(i);
						break;
					}
				}
			}
		}

		carregarComboBoxCategorias();

		if (cbCategoria.getItemCount() > 0) {
			cbCategoria.setSelectedIndex(0);
			if (p.getCategoria() != null && !p.getCategoria().isEmpty()) {
				for (int i = 0; i < cbCategoria.getItemCount(); i++) {
					String item = cbCategoria.getItemAt(i);
					if (item.contains(" - " + p.getCategoria()) || item.startsWith(p.getCategoria() + " -")) {
						cbCategoria.setSelectedIndex(i);
						break;
					}
				}
			}
		}

		btnAdicionar.setText("Salvar alterações");
	}

	public boolean isEdicao() {
		return produtoIdEmEdicao != null;
	}

	public String getProdutoIdEmEdicao() {
		return produtoIdEmEdicao;
	}

	public void voltaracaoo(Runnable acao) {
		ComponentUtils.transformarEmLink(this.Voltar, acao);
	}

	public void adicionarproduto(ActionListener actionListener) {
		this.btnAdicionar.addActionListener(actionListener);
	}

	public String getNomeProduto() {
		return TFProduto.getText();
	}

	public String getSKU() {
		return TFSKU.getText();
	}

	public void setSKU(String sku) {
		TFSKU.setForeground(new Color(30, 30, 30));
		TFSKU.setText(sku);
	}

	public String getQuantidade() {
		return TFQtd.getText();
	}

	public String getPreco() {
		if (TFPreco == null || TFPreco.getText().trim().isEmpty()) {
			return "0,00";
		}
		return TFPreco.getText();
	}

	public String getLocalizacao() {
		if (TFLocalizacao.getText().startsWith("Clique"))
			return "";
		return TFLocalizacao.getText();
	}

	public String getFornecedor() {
		if (cbFornecedor.getSelectedItem() == null)
			return "";
		String selecionado = cbFornecedor.getSelectedItem().toString();
		if (selecionado.startsWith("0"))
			return "";
		int hifenIdx = selecionado.indexOf(" - ");
		return hifenIdx != -1 ? selecionado.substring(hifenIdx + 3) : selecionado;
	}

	public String getCategoria() {
		if (cbCategoria.getSelectedItem() == null)
			return "";
		String selecionado = cbCategoria.getSelectedItem().toString();
		if (selecionado.startsWith("0"))
			return "";
		int hifenIdx = selecionado.indexOf(" - ");
		return hifenIdx != -1 ? selecionado.substring(hifenIdx + 3) : selecionado;
	}

	public void limparCampos() {
		TFProduto.setText("");
		TFSKU.setForeground(new Color(120, 120, 120));
		TFSKU.setText("Será gerado automaticamente ao salvar...");
		TFQtd.setText("");
		TFLocalizacao.setText("Clique para definir a prateleira...");
		TFPreco.setText("0,00");

		if (cbFornecedor.getItemCount() > 0)
			cbFornecedor.setSelectedIndex(0);
		if (cbCategoria.getItemCount() > 0)
			cbCategoria.setSelectedIndex(0);

		this.produtoIdEmEdicao = null;
		btnAdicionar.setText("Adicionar");
	}
}
