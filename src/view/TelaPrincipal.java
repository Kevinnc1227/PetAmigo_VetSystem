package view;

import java.awt.EventQueue;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("PetAmigo - Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastros");
		menuBar.add(mnCadastro);

		JMenuItem miAnimal = new JMenuItem("Animal");
		miAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroAnimal telaCadastAnimal = new TelaCadastroAnimal();
				telaCadastAnimal.setVisible(true);
			}
		});
		mnCadastro.add(miAnimal);

		JMenuItem miCliente = new JMenuItem("Cliente");
		miCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente telaCadastCliente = new TelaCadastroCliente();
				telaCadastCliente.setVisible(true);
			}
		});
		mnCadastro.add(miCliente);

		JMenuItem miVeterinario = new JMenuItem("Veterinário");
		mnCadastro.add(miVeterinario);

		JMenu mnConsulta = new JMenu("Consultas");
		menuBar.add(mnConsulta);

		JMenuItem miAgendarConsulta = new JMenuItem("Agendar Consulta");
		miAgendarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAgendamentoConsulta telaAgConsulta = new TelaAgendamentoConsulta();
				telaAgConsulta.setVisible(true);
			}
		});
		mnConsulta.add(miAgendarConsulta);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem miSobre = new JMenuItem("Sobre");
		miSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "PetAmigo \nSistema de gestão veterinária" );
			}
		});
		mnAjuda.add(miSobre);
	}

}
