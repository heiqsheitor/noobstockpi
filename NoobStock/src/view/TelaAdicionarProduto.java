package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

import controller.ComponentUtils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Produto;
import model.Fornecedor;
import model.FornecedorDAO;

public class TelaAdicionarProduto extends JPanel {
	private JTextField TFProduto;
	private JTextField TFSKU;
	private JTextField TFQtd;
	private JTextField TFLocalizacao;
	private JComboBox<String> cbFornecedor; // 👉 CAMPO ALTERADO PARA JComboBox
	private JTextField TFCategoria;
	private JButton btnCancelar, btnAdicionar;
	private JLabel Voltar;

	// Armazena o ID do produto em edição (null = modo cadastro)
	private String produtoIdEmEdicao = null;

	public TelaAdicionarProduto() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][][grow][grow 30]", "[grow 1][][][grow 1][grow 1][grow 1][grow 1][grow 1][grow 1][grow 1][]"));
		
		Voltar = new JLabel("");
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCancelar, "flowx,cell 3 9,growx");
		
		btnAdicionar = new JButton("Adicionar");
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
		
		// 👉 AQUI INICIALIZAMOS O JCOMBOBOX
		cbFornecedor = new JComboBox<>();
		add(cbFornecedor, "cell 3 7,growx");
		
		TFCategoria = new JTextField();
		add(TFCategoria, "cell 3 8,growx");
		TFCategoria.setColumns(10);

		// 👉 EVENTO QUE ATUALIZA A LISTA DE FORNECEDORES AO ABRIR A TELA
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent e) {
				carregarComboBoxFornecedores();
			}
		});
	}

	// ── CARREGA OS FORNECEDORES DO BANCO PARA O COMBOBOX ──────────────────────
	public void carregarComboBoxFornecedores() {
		cbFornecedor.removeAllItems();
		cbFornecedor.addItem("0 - Selecione um fornecedor..."); 
		
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> lista = dao.listar();
		
		for (Fornecedor f : lista) {
			// Formato: "ID - Nome"
			cbFornecedor.addItem(f.getIdfornecedor() + " - " + f.getNome());
		}
	}

	// ── PRÉ-PREENCHE OS CAMPOS PARA EDIÇÃO ────────────────────────────────────
	public void preencherParaEdicao(Produto p) {
		this.produtoIdEmEdicao = p.getId_produto();
		TFProduto.setText(p.getNome());
		TFSKU.setText(p.getSKU());
		TFQtd.setText(p.getQtd());
		TFLocalizacao.setText(p.getLocalização() != null ? p.getLocalização() : "");
		TFCategoria.setText(p.getCategoria() != null ? p.getCategoria() : "");
		
		// Lógica para selecionar o fornecedor correto no JComboBox
		cbFornecedor.setSelectedIndex(0);
		if (p.getFornecedor() != null && !p.getFornecedor().isEmpty()) {
			for (int i = 0; i < cbFornecedor.getItemCount(); i++) {
				String item = cbFornecedor.getItemAt(i);
				// Tenta casar o nome ou ID do fornecedor do produto com a lista
				if (item.contains(" - " + p.getFornecedor()) || item.startsWith(p.getFornecedor() + " -")) {
					cbFornecedor.setSelectedIndex(i);
					break;
				}
			}
		}
		
		// Muda o botão para indicar que é uma edição
		btnAdicionar.setText("Salvar alterações");
	}

	// ── INDICA SE ESTÁ EM MODO EDIÇÃO ─────────────────────────────────────────
	public boolean isEdicao() {
		return produtoIdEmEdicao != null;
	}

	// ── RETORNA O ID DO PRODUTO EM EDIÇÃO ─────────────────────────────────────
	public String getProdutoIdEmEdicao() {
		return produtoIdEmEdicao;
	}

	// ── AÇÕES ─────────────────────────────────────────────────────────────────
	public void voltaracaoo(Runnable acao) {
        ComponentUtils.transformarEmLink(this.Voltar, acao);
    }
	
	public void adicionarproduto(ActionListener actionListener) {
		this.btnAdicionar.addActionListener(actionListener);
	}

	// ── GETTERS DOS CAMPOS ────────────────────────────────────────────────────
	public String getNomeProduto() {
	    return TFProduto.getText();
	}

	public String getSKU() {
	    return TFSKU.getText();
	}

	public String getQuantidade() {
	    return TFQtd.getText();
	}

	public String getLocalizacao() {
	    return TFLocalizacao.getText();
	}

	// 👉 AGORA O GET FORNECEDOR RETORNA O ID SELECIONADO NA COMBOBOX
	public String getFornecedor() {
		if (cbFornecedor.getSelectedItem() == null) return "";
		String selecionado = cbFornecedor.getSelectedItem().toString();
		
		if (selecionado.startsWith("0")) return ""; 
		
		// Separa a string "ID - Nome" e pega só a primeira parte (o ID)
		return selecionado.split(" - ")[0]; 
	}

	public String getCategoria() {
	    return TFCategoria.getText();
	}

	// ── LIMPA OS CAMPOS E RESETA O MODO EDIÇÃO ────────────────────────────────
	public void limparCampos() {
	    TFProduto.setText("");
	    TFSKU.setText("");
	    TFQtd.setText("");
	    TFLocalizacao.setText("");
	    TFCategoria.setText("");
	    
	    if (cbFornecedor.getItemCount() > 0) {
	    	cbFornecedor.setSelectedIndex(0); // Volta para "Selecione um fornecedor..."
	    }
	    
	    // Reseta o modo edição
	    this.produtoIdEmEdicao = null;
	    btnAdicionar.setText("Adicionar");
	}
}