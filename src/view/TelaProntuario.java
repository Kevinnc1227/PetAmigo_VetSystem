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

import model.Animal;
import model.Prontuario;
import model.ProntuarioDAO;

public class TelaProntuario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtIdAnimal;

	private JTextArea txtHistorico;
	private JTextArea txtObservacoes;

	private JButton btnSalvar;
	private JButton btnLocalizar;
	private JTextField txtPeso;

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
		
		JLabel lblObservacoes = new JLabel("Observações:");
		lblObservacoes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservacoes.setBounds(30, 208, 100, 20);
		contentPane.add(lblObservacoes);

		JScrollPane spObservacoes = new JScrollPane();

		spObservacoes.setBounds(140, 208, 300, 80);
		contentPane.add(spObservacoes);
		
				txtObservacoes = new JTextArea();
				spObservacoes.setViewportView(txtObservacoes);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(110, 382, 120, 30);
		contentPane.add(btnSalvar);
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLocalizar.setBounds(272, 382, 120, 30);
		contentPane.add(btnLocalizar);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(140, 323, 120, 25);
		contentPane.add(txtPeso);
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPeso.setBounds(30, 324, 82, 20);
		contentPane.add(lblPeso);

		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            if (txtIdAnimal.getText().trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Por favor, informe o ID do Animal.");
		                return;
		            }
		            int idAnimal = Integer.parseInt(txtIdAnimal.getText().trim());

		            Prontuario verifProntuario = new Prontuario();
		            Animal animal = new Animal();
		            animal.setId(idAnimal);

		            verifProntuario.setAnimal(animal);
		            ProntuarioDAO daoVerificar = new ProntuarioDAO();
		            daoVerificar.setProntuario(verifProntuario);
		            boolean existe = daoVerificar.localizar();
		            if (txtPeso.getText().trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Informe o peso.");
		                return;
		            }

		            Prontuario prontuario = new Prontuario();
		            Animal animal2 = new Animal();
		            animal2.setId(idAnimal);
		            prontuario.setAnimal(animal2);
		            prontuario.setHistorico(txtHistorico.getText());
		            prontuario.setObservacoes(txtObservacoes.getText());
		            prontuario.setPeso(Float.parseFloat(txtPeso.getText()));

		            ProntuarioDAO daoSalvar = new ProntuarioDAO();
		            daoSalvar.setProntuario(prontuario);

		            String mensagem = daoSalvar.atualizar(existe ? model.TipoOperacaoBD.ALTERACAO : model.TipoOperacaoBD.INCLUSAO);
		            JOptionPane.showMessageDialog(null, mensagem);
		            txtIdAnimal.setText("");
		            txtHistorico.setText("");
		            txtObservacoes.setText("");
		            txtPeso.setText("");
		            txtIdAnimal.requestFocus();
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, " ID do animal ou peso inválido.");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro na tela: " + ex.getMessage());
		        }
		    }
		});

		btnLocalizar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            if (txtIdAnimal.getText().trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Digite o ID do Animal para localizar.");
		                return;
		            }

		            int idAnimal = Integer.parseInt(txtIdAnimal.getText().trim());
		            Prontuario prontuario = new Prontuario();
		            Animal animal = new Animal();
		            animal.setId(idAnimal);
		            prontuario.setAnimal(animal);
		            ProntuarioDAO dao = new ProntuarioDAO();
		            dao.setProntuario(prontuario);
		            if (dao.localizar()) {
		                txtHistorico.setText(prontuario.getHistorico());
		                txtObservacoes.setText(prontuario.getObservacoes());
		                txtPeso.setText(String.valueOf(prontuario.getPeso()));
		            } else {
		                JOptionPane.showMessageDialog(null, "Nenhum prontuário encontrado para o ID " + idAnimal);
		                txtHistorico.setText("");
		                txtObservacoes.setText("");
		                txtPeso.setText("");  
		            }

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "O ID do animal precisa ser um número inteiro.");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao localizar: " + ex.getMessage());
		        }
		    }
		});
		
	}
}
