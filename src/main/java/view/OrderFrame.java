package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;

import controller.ClientController;
import mainPanel.AreaManagerPanel;
import mainPanel.EmployeeManagerPanel;
import mainPanel.OrderPanel;
import mainPanel.ProductPanel;
import mainPanel.ProfilePanel;
import mainPanel.StatisticPanel;
import mainPanel.listTablePanel;
import models.Employee;
import models.Restaurant;
import models.Table;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Employee employee;
	private Socket socket;
	private Restaurant restaurant;
	private JButton buttonSelected;
	private ClientController clientController;
	private listTablePanel listTablePanel;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private OrderPanel orderPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame(Employee employee, ClientController clientController) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("ComboBox.buttonBackground", Color.decode("#FDED8F"));
		UIManager.put("TableHeader.font",new Font("Tahoma", Font.BOLD, 15));
		UIManager.put("TableHeader.height", 40);
		UIManager.put("Table.selectionBackground", Color.decode("#FDED8F"));
		UIManager.put("Table.selectionForeground", Color.black);
		
		this.employee = employee;
		this.clientController = clientController;
		this.restaurant = getRestaurantByID(employee.getIdRestaurant());
		

	
//		try {
//			restaurant = RestaurenDAO.getDao().get(employee.getIdRestaurant());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 104, 1284, 587);
		mainPanel.setLayout(cardLayout);
		
		JPanel SideMenuPanel = new JPanel();
		SideMenuPanel.setBackground(Color.decode("#0F433D"));
		SideMenuPanel.setBounds(0, 0, 211, 105);
		contentPane.add(SideMenuPanel);
		SideMenuPanel.setLayout(null);
		
		JLabel Logo = new JLabel();
		Logo.setBounds(10,41,186,52);
		
		ImageIcon a = new ImageIcon(getClass().getResource("/image/Logo.png"));
		Image b = a.getImage().getScaledInstance(186, 52, Image.SCALE_SMOOTH);
		Logo.setIcon(new ImageIcon(b));
		SideMenuPanel.add(Logo);
		
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
		
		JLabel restaurenNamelb = new JLabel();
		restaurenNamelb.setText(restaurant.getName());
		restaurenNamelb.setForeground(Color.WHITE);
		restaurenNamelb.setFont(new Font("Tahoma", Font.BOLD, 20));
		restaurenNamelb.setBounds(28, 30, 641, 31);
		panel_1.add(restaurenNamelb);
		
		JLabel addresslb = new JLabel();
		addresslb.setText(restaurant.getAddress());
		addresslb.setForeground(Color.WHITE);
		addresslb.setFont(new Font("Tahoma", Font.BOLD, 13));
		addresslb.setBounds(28, 69, 641, 14);
		panel_1.add(addresslb);
		
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
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblNewLabel_1.setText(this.employee.getName());
		lblNewLabel_1.setIcon(new FlatSVGIcon("svg/accountblack.svg"));
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblNewLabel_1.setForeground(Color.WHITE);
//		lblNewLabel_1.setBackground(Color.decode("#0F433D"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2_1 = new JLabel();
		lblNewLabel_2_1.setText(this.employee.getPermission() .getName());
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblNewLabel_2_1);
		setVisible(true);
		listTablePanel = new listTablePanel(this,this.clientController, restaurant);
		mainPanel.add(listTablePanel,"listTable");
		orderPanel = new OrderPanel(this,this.clientController, restaurant, this.employee);
		mainPanel.add(orderPanel,"order");
		contentPane.add(mainPanel);
		
		
		
	}
	public Restaurant getRestaurantByID(int id) {
		String[] controltemp = {"getRestaurantById", Integer.toString(id)};
		
		Gson gson = new Gson();
		Restaurant restaurant = gson.fromJson(clientController.sendRequest(controltemp), Restaurant.class);
		return restaurant;
	}
	public void setIdRestaurant(int idEmployee, int idRestaurant) {
		String[] controlTemp = {"setIdRestaurantForEmployee",Integer.toString(idEmployee), Integer.toString(idRestaurant)};
		clientController.sendRequest(controlTemp);
		
	}
	public void showOrderPanel(Table table) {
		orderPanel.setTable(table);
		cardLayout.show(mainPanel,"order");
		
	}
	public void showlistTable() {
		cardLayout.show(mainPanel,"listTable");
		listTablePanel.loadAll();
	}
}


