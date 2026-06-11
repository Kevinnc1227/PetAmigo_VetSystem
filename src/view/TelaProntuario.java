package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Prontuario;
import model.ProntuarioDAO;

public class TelaProntuario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtIdAnimal;
	private JTextField txtUltimaVacina;

	private JTextArea txtHistorico;
	private JTextArea txtObservacoes;

	private JButton btnSalvar;
	private JButton btnLocalizar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProntuario frame = new TelaProntuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaProntuario() {

		setTitle("PetAmigo - Prontuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Cadastro de Prontuário");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(160, 10, 250, 25);
		contentPane.add(lblTitulo);

		JLabel lblIdAnimal = new JLabel("ID Animal:");
		lblIdAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdAnimal.setBounds(30, 60, 100, 20);
		contentPane.add(lblIdAnimal);

		txtIdAnimal = new JTextField();
		txtIdAnimal.setBounds(140, 60, 120, 25);
		contentPane.add(txtIdAnimal);

		JLabel lblHistorico = new JLabel("Histórico:");
		lblHistorico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHistorico.setBounds(30, 100, 100, 20);
		contentPane.add(lblHistorico);

		txtHistorico = new JTextArea();
		JScrollPane spHistorico = new JScrollPane(txtHistorico);

		spHistorico.setBounds(140, 100, 300, 80);
		contentPane.add(spHistorico);

		JLabel lblVacina = new JLabel("Última Vacina:");
		lblVacina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVacina.setBounds(30, 200, 100, 20);
		contentPane.add(lblVacina);

		txtUltimaVacina = new JTextField();
		txtUltimaVacina.setBounds(140, 200, 300, 25);
		contentPane.add(txtUltimaVacina);

		JLabel lblObservacoes = new JLabel("Observações:");
		lblObservacoes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservacoes.setBounds(30, 250, 100, 20);
		contentPane.add(lblObservacoes);

		txtObservacoes = new JTextArea();

		JScrollPane spObservacoes = new JScrollPane(txtObservacoes);

		spObservacoes.setBounds(140, 250, 300, 80);
		contentPane.add(spObservacoes);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(120, 370, 120, 30);
		contentPane.add(btnSalvar);

		btnLocalizar = new JButton("Localizar");
		btnLocalizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLocalizar.setBounds(280, 370, 120, 30);
		contentPane.add(btnLocalizar);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idAnimal = Integer.parseInt(txtIdAnimal.getText());

					Prontuario prontuario = new Prontuario();

					prontuario.setIdAnimal(idAnimal);
					prontuario.setHistorico(txtHistorico.getText());
					prontuario.setUltimaVacina(txtUltimaVacina.getText());
					prontuario.setObservacoes(txtObservacoes.getText());

					ProntuarioDAO dao = new ProntuarioDAO();
					dao.setProntuario(prontuario);
					String mensagem = dao.atualizar(1);
					JOptionPane.showMessageDialog(null, mensagem);

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int idAnimal = Integer.parseInt(txtIdAnimal.getText());
					Prontuario prontuario = new Prontuario();
					prontuario.setIdAnimal(idAnimal);
					ProntuarioDAO dao = new ProntuarioDAO();
					dao.setProntuario(prontuario);
					if (dao.localizar()) {
						txtHistorico.setText(prontuario.getHistorico());
						txtUltimaVacina.setText(prontuario.getUltimaVacina());
						txtObservacoes.setText(prontuario.getObservacoes());
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum prontuário encontrado para o animal informado.");
					}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		setLocationRelativeTo(null);
		// É só trocar o final pelo nome da nova foto (ex: "foto_cliente.png")
		java.net.URL url = getClass().getResource("/Pictures/download (2).jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}
}
