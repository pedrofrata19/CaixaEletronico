package br.gov.sp.cps.controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class CaixaEletronicoController {

	private JTextField txtValor;
	private JLabel lblSaldo;
	private JTextArea txtHistorico;

	private double saldo = 1000.00;

	public CaixaEletronicoController(JTextField txtValor, JLabel lblSaldo, JTextArea txtHistorico) {
		this.txtValor = txtValor;
		this.lblSaldo = lblSaldo;
		this.txtHistorico = txtHistorico;
	}

	public ActionListener acaoDepositar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String valorDigitado = txtValor.getText().trim();

				if (valorDigitado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe um valor para depósito!");
					return;
				}
				try {
					String valorFormatado = valorDigitado.replace(",", ".");
					double deposito = Double.parseDouble(valorFormatado);

					if (deposito <= 0) {
						JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!");
						return;
					}

					saldo = saldo + deposito;
					lblSaldo.setText("SALDO DISPONÍVEL: "
							+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(saldo));
					txtHistorico.append("Depósito realizado: "
							+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(deposito)
							+ "\nNovo saldo: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(saldo)
							+ "\n\n");
					JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
					txtValor.setText("");
					txtValor.requestFocus();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números.");
				}
			}
		};
	}

	public ActionListener acaoSacar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String valorDigitado = txtValor.getText().trim();

				if (valorDigitado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe um valor para saque!");
					return;
				}
				try {
					String valorFormatado = valorDigitado.replace(",", ".");
					double saque = Double.parseDouble(valorFormatado);

					if (saque <= 0) {
						JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!");
					} else if (saque > saldo) {
						JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
					} else if (saque > 1000) {
						JOptionPane.showMessageDialog(null, "O saque máximo por operação é de R$ 1.000,00!");
					} else if (saque % 10 != 0) {
						JOptionPane.showMessageDialog(null, "O valor do saque deve ser múltiplo de 10!");
					} else {
						int valorSaque = (int) saque;
						int notas100 = valorSaque / 100;
						valorSaque = valorSaque % 100;
						int notas50 = valorSaque / 50;
						valorSaque = valorSaque % 50;
						int notas10 = valorSaque / 10;

						saldo = saldo - saque;
						lblSaldo.setText("SALDO DISPONÍVEL: "
								+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(saldo));
						txtHistorico.append("Saque realizado: "
								+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(saque) + "\n");

						if (notas100 > 0) {
							txtHistorico.append(notas100 + " nota(s) de R$ 100,00\n");
						}

						if (notas50 > 0) {
							txtHistorico.append(notas50 + " nota(s) de R$ 50,00\n");
						}
						if (notas10 > 0) {
							txtHistorico.append(notas10 + " nota(s) de R$ 10,00\n");
						}
						txtHistorico.append("Novo saldo: "
								+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(saldo) + "\n\n");
						JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
						txtValor.setText("");
						txtValor.requestFocus();
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números.");
				}
			}
		};
	}

	public ActionListener acaoExtrato() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtHistorico.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nenhuma movimentação realizada!");
				} else {

					JOptionPane.showMessageDialog(null, txtHistorico.getText(), "Extrato bancário",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
	}
}
