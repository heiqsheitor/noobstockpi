package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import controller.ComponentUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Fornecedor;

public class TelaAdicionarFornecedor extends JPanel {
	private JTextField TFornecedor;
	private JTextField TFCNPJ;
	private JTextField TFEmail;
	private JTextField TFAtivo;
	private JButton btnCancelar, btnAdicionar;
	private JLabel Voltar;
	private Runnable acaoVoltar;

	// Armazena o ID do fornecedor em edição (-1 = modo cadastro)
	private int fornecedorIdEmEdicao = -1;

	public TelaAdicionarFornecedor() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][][grow][grow 30]", "[grow 1][][][grow 1][grow 1][grow 1][grow 1][grow 1][]"));

		Voltar = new JLabel("");
		Voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (acaoVoltar != null) acaoVoltar.run();
			}
		});
		Voltar.setIcon(new ImageIcon(TelaAdicionarFornecedor.class.getResource("/img/button→svg.png")));
		add(Voltar, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Adicionar Fornecedor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_1, "cell 1 0");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaAdicionarFornecedor.class.getResource("/img/svg.png")));
		add(lblNewLabel_2, "flowy,cell 2 1");

		JLabel lblNewLabel_3 = new JLabel("Adicionar novo fornecedor");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_3, "cell 3 1,alignx left");

		JLabel lblNewLabel_4 = new JLabel("Preencha as informações abaixo para adicionar um novo fornecedor");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(192, 192, 192));
		add(lblNewLabel_4, "cell 3 2");

		JLabel lblNewLabel_5 = new JLabel("Nome do fornecedor");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_5, "flowy,cell 3 3");

		JLabel lblNewLabel_6 = new JLabel("CPF/CNPJ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_6, "flowy,cell 3 4");

		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_7, "flowy,cell 3 5");

		JLabel lblNewLabel_8 = new JLabel("Duração de contrato");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_8, "flowy,cell 3 6");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				if (acaoVoltar != null) acaoVoltar.run();
			}
		});
		add(btnCancelar, "flowx,cell 3 7,growx");

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(Color.BLACK);
		btnAdicionar.setForeground(new Color(255, 255, 255));
		add(btnAdicionar, "cell 3 7,growx");
		btnAdicionar.setOpaque(true);
		btnAdicionar.setBorderPainted(false);

		TFornecedor = new JTextField();
		add(TFornecedor, "cell 3 3,growx");
		TFornecedor.setColumns(10);

		TFCNPJ = new JTextField();
		add(TFCNPJ, "cell 3 4,growx");
		TFCNPJ.setColumns(10);
		ComponentUtils.aplicarMascaraCpfCnpj(TFCNPJ);

		TFEmail = new JTextField();
		add(TFEmail, "cell 3 5,growx");
		TFEmail.setColumns(10);

		TFAtivo = new JTextField();
		add(TFAtivo, "cell 3 6,growx");
		TFAtivo.setColumns(10);
	}

	// ── PRÉ-PREENCHE OS CAMPOS PARA EDIÇÃO ────────────────────────────────────
	public void preencherParaEdicao(Fornecedor f) {
		this.fornecedorIdEmEdicao = f.getIdfornecedor();
		TFornecedor.setText(f.getNome() != null ? f.getNome() : "");
		TFCNPJ.setText(f.getCnpj() != null ? f.getCnpj() : "");
		TFEmail.setText(f.getContato() != null ? f.getContato() : "");
		TFAtivo.setText(f.getDuracaoContrato() != null ? f.getDuracaoContrato() : "");
		// Muda o botão para indicar que é uma edição
		btnAdicionar.setText("Salvar alterações");
	}

	// ── INDICA SE ESTÁ EM MODO EDIÇÃO ─────────────────────────────────────────
	public boolean isEdicao() {
		return fornecedorIdEmEdicao != -1;
	}

	// ── RETORNA O ID DO FORNECEDOR EM EDIÇÃO ──────────────────────────────────
	public int getIdEmEdicao() {
		return fornecedorIdEmEdicao;
	}

	// ── AÇÕES ─────────────────────────────────────────────────────────────────
	public void setAdicionarAcao(ActionListener actionListener) {
		this.btnAdicionar.addActionListener(actionListener);
	}

	public void setAcaoVoltar(Runnable acao) {
		this.acaoVoltar = acao;
		ComponentUtils.transformarEmLink(Voltar, acao);
	}

	// ── GETTERS DOS CAMPOS ────────────────────────────────────────────────────
	public String getNomeFornecedor() { return TFornecedor.getText(); }
	public String getCnpj()          { return TFCNPJ.getText(); }
	public String getEmail()         { return TFEmail.getText(); }
	public String getDuracao()       { return TFAtivo.getText(); }

	// ── LIMPA OS CAMPOS E RESETA O MODO EDIÇÃO ────────────────────────────────
	public void limparCampos() {
		TFornecedor.setText("");
		TFCNPJ.setText("");
		TFEmail.setText("");
		TFAtivo.setText("");
		// Reseta o modo edição
		this.fornecedorIdEmEdicao = -1;
		btnAdicionar.setText("Adicionar");
	}
}
