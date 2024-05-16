package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import models.Employee;
import net.miginfocom.examples.TestMigLayout;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

public class HomeView extends JFrame {

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
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeView(Employee employee, Socket socket) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SideMenuPanel = new JPanel();
		SideMenuPanel.setBackground(Color.decode("#0F433D"));
		SideMenuPanel.setBounds(0, 0, 211, 691);
		contentPane.add(SideMenuPanel);
		SideMenuPanel.setLayout(null);
		
		JLabel Logo = new JLabel();
		Logo.setBounds(10,41,186,52);
		
		ImageIcon a = new ImageIcon(getClass().getResource("/image/Logo.png"));
		Image b = a.getImage().getScaledInstance(186, 52, Image.SCALE_SMOOTH);
		Logo.setIcon(new ImageIcon(b));
		SideMenuPanel.add(Logo);
		
		JButton statisticButton = new JButton("Thống Kê");
		statisticButton.setHorizontalAlignment(SwingConstants.LEFT);
		statisticButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		statisticButton.setForeground(Color.WHITE);
		statisticButton.setBounds(0, 199, 211, 52);
		statisticButton.setBackground(Color.decode("#0F433D"));
		statisticButton.setBorderPainted(false);
		statisticButton.setIcon(new FlatSVGIcon("svg/statistic.svg"));
		SideMenuPanel.add(statisticButton);
		
		JButton employeeManagerButton = new JButton("Quản Lý Nhân Sự");
		employeeManagerButton.setHorizontalAlignment(SwingConstants.LEFT);
		employeeManagerButton.setForeground(Color.WHITE);
		employeeManagerButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		employeeManagerButton.setBorderPainted(false);
		employeeManagerButton.setBackground(new Color(15, 67, 61));
		employeeManagerButton.setBounds(0, 251, 211, 52);
		employeeManagerButton.setIcon(new FlatSVGIcon("svg/employee.svg"));
		SideMenuPanel.add(employeeManagerButton);
		
		JButton productButton = new JButton("Sản Phẩm");
		productButton.setHorizontalAlignment(SwingConstants.LEFT);
		productButton.setForeground(Color.WHITE);
		productButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		productButton.setBorderPainted(false);
		productButton.setBackground(new Color(15, 67, 61));
		productButton.setBounds(0, 304, 211, 52);
		productButton.setIcon(new FlatSVGIcon("svg/product.svg"));
		SideMenuPanel.add(productButton);
		
		JButton areaManagerButton = new JButton("Khu Vực");
		areaManagerButton.setHorizontalAlignment(SwingConstants.LEFT);
		areaManagerButton.setForeground(Color.WHITE);
		areaManagerButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		areaManagerButton.setBorderPainted(false);
		areaManagerButton.setBackground(new Color(15, 67, 61));
		areaManagerButton.setBounds(0, 358, 211, 52);
		areaManagerButton.setIcon(new FlatSVGIcon("svg/area.svg"));
		SideMenuPanel.add(areaManagerButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 141, 211, 2);
		SideMenuPanel.add(separator);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setHorizontalAlignment(SwingConstants.LEFT);
		profileButton.setForeground(Color.WHITE);
		profileButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		profileButton.setBorderPainted(false);
		profileButton.setBackground(new Color(15, 67, 61));
		profileButton.setBounds(0, 479, 211, 52);
		profileButton.setIcon(new FlatSVGIcon("svg/profile.svg"));
		SideMenuPanel.add(profileButton);
		
		JButton settingButton = new JButton("Cài đặt");
		settingButton.setHorizontalAlignment(SwingConstants.LEFT);
		settingButton.setForeground(Color.WHITE);
		settingButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		settingButton.setBorderPainted(false);
		settingButton.setBackground(new Color(15, 67, 61));
		settingButton.setBounds(0, 532, 211, 52);
		settingButton.setIcon(new FlatSVGIcon("svg/setting.svg"));
		SideMenuPanel.add(settingButton);
		
		JButton logoutButton = new JButton("Đăng xuất");
		logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		logoutButton.setBorderPainted(false);
		logoutButton.setBackground(new Color(15, 67, 61));
		logoutButton.setBounds(0, 586, 211, 52);
		logoutButton.setIcon(new FlatSVGIcon("svg/logout.svg"));
		SideMenuPanel.add(logoutButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(211, 0, 1073, 105);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 679, 105);
		panel.add(panel_1);
		panel_1.setBackground(Color.decode("#145DA0"));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chào mừng bạn đến với");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(28, 11, 269, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNhHngAbc = new JLabel("Nhà Hàng Thiên Đường Quán");
		lblNhHngAbc.setForeground(Color.WHITE);
		lblNhHngAbc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNhHngAbc.setBounds(28, 30, 641, 31);
		panel_1.add(lblNhHngAbc);
		
		JLabel lblaCh = new JLabel("Địa Chỉ: 793 Trần Hưng Đạo, Điện Ngọc, Điện Bàn, Quảng Nam");
		lblaCh.setForeground(Color.WHITE);
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblaCh.setBounds(28, 69, 641, 14);
		panel_1.add(lblaCh);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(679, 0, 393, 105);
		panel_2.setBackground(Color.decode("#145DA0"));
		panel.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);
		
		JPanel panel_3 = new JPanel();
		sl_panel_2.putConstraint(SpringLayout.NORTH, panel_3, 21, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, panel_3, 84, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, panel_3, -23, SpringLayout.EAST, panel_2);
//		panel_3.putClientProperty("Component.arc", 20);
//		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		panel_3.putClientProperty("arc", 400);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText(employee.getName());
		lblNewLabel_1.setIcon(new FlatSVGIcon("svg/accountblack.svg"));
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblNewLabel_1.setForeground(Color.WHITE);
//		lblNewLabel_1.setBackground(Color.decode("#0F433D"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2_1 = new JLabel();
		lblNewLabel_2_1.setText(employee.getPermission() .getName());
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblNewLabel_2_1);
		
//		
//		JLabel statisticButton = new JLabel("Thống Kê");
//		SideMenuPanel.add(statisticButton);
//		
//		JLabel employeeManagerButton = new JLabel("Quản Lý Nhân Sự");
//		SideMenuPanel.add(employeeManagerButton);
//		
//		JLabel productButton = new JLabel("Sản Phẩm");
//		SideMenuPanel.add(productButton);
//		
//		JLabel areaManagerButton = new JLabel("Quản Lý Khu Vực");
//		SideMenuPanel.add(areaManagerButton);
		setVisible(true);
	}
}
