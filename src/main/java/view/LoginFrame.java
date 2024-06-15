package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.ui.FlatPanelUI;
import com.google.gson.Gson;

import controller.ClientController;
import models.Employee;

import others.MaHoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					UIManager.put("Button.hoverBackground", Color.decode("#5271FF"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame( ClientController clientController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,620);
		setLocationRelativeTo(null);
//		setIconImage(new ImageIcon(getClass().getResource("/image/LogoBlack.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		LoginPanel loginPanel = new LoginPanel(this,clientController);
		loginPanel.setBackground(new Color(255,255,255,50));
		loginPanel.setBounds(525,121,415,338);
		
		
		
		contentPane.add(loginPanel);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(65, 132, 286, 81);
		
		File resourcesDirectory = new File("src/main/resources");
		String resourcesPath = resourcesDirectory.getAbsolutePath();
		String temp = resourcesPath+"/image/Logo.png";
		ImageIcon a = new ImageIcon(temp);
		Image b = a.getImage().getScaledInstance(286, 81, Image.SCALE_SMOOTH);
		
		lblNewLabel.setIcon(new ImageIcon(b));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Giúp bạn quản lý nhà hàng một cách");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(97, 234, 383, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("thuận tiện nhất");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1_1.setBounds(97, 259, 383, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(97, 346, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("-");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNewLabel_2_1.setBounds(123, 346, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Chưa có tài khoản? ->");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1_1_1.setBounds(97, 394, 221, 22);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Đăng Ký Ngay!");
		lblNewLabel_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new SignupFrame(clientController);
				
			}
		});
		lblNewLabel_1_1_2.setForeground(Color.decode("#0CC0DF"));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1_1_2.setBounds(316, 394, 156, 22);
		lblNewLabel_1_1_2.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(0, 0, 1008, 581);
		
		ImageIcon ii = new ImageIcon(getClass().getResource("/image/BackgroundLogin.png"));
		Image i = ii.getImage().getScaledInstance(1024, 620, Image.SCALE_SMOOTH);
		
		labelBackground.setIcon(new ImageIcon(i));
		contentPane.add(labelBackground);
		setVisible(true);
	}
}

