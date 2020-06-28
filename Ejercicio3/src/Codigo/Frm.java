package Codigo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class Frm extends JFrame {

	private JPanel contentPane;
	private JTextField txtEntrada;
	private JTextArea txtResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm frame = new Frm();
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
	public Frm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtEntrada = new JTextField();
		txtEntrada.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(txtEntrada, BorderLayout.NORTH);
		txtEntrada.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				File archivo = new File("archivo.txt");
		        PrintWriter escribir;
		        try {
		            escribir = new PrintWriter(archivo);
		            escribir.print(txtEntrada.getText());
		            escribir.close();
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(Frm.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        
		        try {
		            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
		            Lexer lexer = new Lexer(lector);
		            String resultado = "";
		            while (true) {
		                Tokens tokens = lexer.yylex();
		                if (tokens == null) {
		                    resultado += "FIN";
		                    txtResultado.setText(resultado);
		                    return;
		                }
		                switch (tokens) {
		                    case ERROR:
		                        resultado += lexer.lexeme+": Patron no definido\n";
		                        break;
		                   case Patron: 
		                	   resultado += lexer.lexeme + ": Es un patron permitido, comienza con 10 y posee 5 digitos \n";
		                	   break;
		                   case Reservadas: 
		                	   resultado += lexer.lexeme + ": Palabra reservada, no permitido \n";
		                	   break;

		                    default:
		                        resultado += "No definido en el analizador\n";
		                        break;
		                }
		            }
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(Frm.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (IOException ex) {
		            Logger.getLogger(Frm.class.getName()).log(Level.SEVERE, null, ex);
		        }
				
				
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		txtResultado = new JTextArea();
		contentPane.add(txtResultado, BorderLayout.CENTER);
	}

}
