package br.gov.sp.cps.view;

import javax.swing.*;
import br.gov.sp.cps.controllers.CaixaEletronicoController;

public class TelaCaixaEletronico extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel lblTitulo, lblSaldo, lblValor;
	JTextField txtValor;
	JButton btnDepositar, btnSacar, btnExtrato;
	JTextArea txtHistorico;

	public TelaCaixaEletronico() {

		setTitle("Caixa Eletrônico");
		setSize(500, 430);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		lblTitulo = new JLabel("BANCO ETEC - CAIXA ELETRÔNICO");
		lblTitulo.setBounds(110, 10, 300, 30);
		add(lblTitulo);

		lblSaldo = new JLabel("SALDO DISPONÍVEL: R$ 1.000,00");
		lblSaldo.setBounds(130, 60, 300, 30);
		add(lblSaldo);

		lblValor = new JLabel("Valor da operação:");
		lblValor.setBounds(30, 120, 150, 25);
		add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(180, 120, 250, 25);
		add(txtValor);

		btnDepositar = new JButton("Depositar");
		btnDepositar.setBounds(40, 170, 130, 30);
		add(btnDepositar);

		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(185, 170, 100, 30);
		add(btnSacar);

		btnExtrato = new JButton("Extrato");
		btnExtrato.setBounds(300, 170, 110, 30);
		add(btnExtrato);

		JLabel lblHistorico = new JLabel("Histórico de movimentações:");
		lblHistorico.setBounds(30, 220, 250, 25);
		add(lblHistorico);

		txtHistorico = new JTextArea();
		txtHistorico.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtHistorico);
		scroll.setBounds(30, 245, 400, 120);
		scroll.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
		);

		add(scroll);

		CaixaEletronicoController controller = new CaixaEletronicoController(txtValor, lblSaldo, txtHistorico);

		btnDepositar.addActionListener(controller.acaoDepositar());
		btnSacar.addActionListener(controller.acaoSacar());
		btnExtrato.addActionListener(controller.acaoExtrato());
	}

	public static void main(String[] args) {
		TelaCaixaEletronico tela = new TelaCaixaEletronico();

		tela.setVisible(true);
	}
}
