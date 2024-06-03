package mainPanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelListener;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import controller.ClientController;
import custemceltable.TableActionCellEditor;
import custemceltable.TableActionCellRender;
import custemceltable.TableActionEvent;
import models.Employee;
import models.Restaurant;
import view.HomeView;

import javax.swing.JScrollPane;

public class EmployeeManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField searchTextField;
	private JTable table;
	private HomeView homeview;
	private Restaurant restaurant;
	private Employee employee;
	private ArrayList<Employee> employees;
	private DefaultTableModel model;
	private Vector vData = new Vector();
	private Vector vHeader = new Vector();
	private JTextField textField_1;
	private ClientController clientController;

	/**
	 * Create the panel.
	 */
	public EmployeeManagerPanel(ClientController clientController,HomeView homeView, Restaurant restaurant, Employee employee) {

		this.homeview = homeView;
		this.restaurant = restaurant;
		this.employee = employee;
		this.clientController = clientController;
		setBackground(Color.WHITE);
		setBounds(new Rectangle(1073, 587));
		setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(10, 93, 1053, 2);
		add(separator);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Tất Cả");
		comboBox.addItem("Quản Lý");
		comboBox.addItem("Thu Ngân");
		comboBox.addItem("Order");
		comboBox.addItem("Đầu Bếp");

		comboBox.setBackground(Color.decode("#FDED8F"));
		comboBox.putClientProperty("", comboBox);
		comboBox.putClientProperty("JComponent.roundRect", true);
		comboBox.setBounds(10, 57, 180, 32);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pos = (String) comboBox.getSelectedItem();
				if (pos.equals("Tất Cả")) {
					getAll();
				} else
					filter(pos);
			}
		});
		add(comboBox);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(searchTextField.getText());
			}
		});
		btnNewButton_1.setBounds(354, 57, 34, 32);
		btnNewButton_1.setIcon(new FlatSVGIcon("svg/search.svg"));
		add(btnNewButton_1);

		searchTextField = new JTextField();
		searchTextField.setBounds(205, 57, 165, 32);
		searchTextField.setBackground(Color.decode("#FDED8F"));
		searchTextField.putClientProperty("JComponent.roundRect", true);
		searchTextField.putClientProperty("JTextField.placeholderText", "Nhập Tên...");
		add(searchTextField);
		searchTextField.setColumns(10);

		JButton btnNewButton = new JButton("Thêm Nhân Viên");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.putClientProperty("JButton.buttonType", "roundRect");
		btnNewButton.setBackground(Color.decode("#0F433D"));
		btnNewButton.setBounds(880, 57, 180, 32);
		btnNewButton.setIcon(new FlatSVGIcon("svg/add.svg"));
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Dialog addemployee = new addEmployeeDialog(getView(), EmployeeManagerPanel.this.restaurant.getId(),
						EmployeeManagerPanel.this.clientController);

			}
		});
		add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 111, 1028, 454);
		add(scrollPane);

		String[] Title = new String[] { "Tên Nhân Viên", "SĐT", "Chức Vụ", "Ngày Vào Làm", "Username", "" };
		for (int i = 0; i < 6; i++)
			vHeader.add(Title[i]);
		model = new DefaultTableModel(vData, vHeader);
//		model = new DefaultTableModel(new Object[][], Title);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(35);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cellRenderer);

		scrollPane.setViewportView(table);

		getAll();

		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
		table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				Employee employeeEdit = employees.get(row);
				Dialog editEmployee = new editEmployeeDialog(getView(), employeeEdit, EmployeeManagerPanel.this.clientController);
			}

			@Override
			public void onDelete(int row) {
				String employeeUsername = employees.get(row).getUsername();
				if (employeeUsername.equals(EmployeeManagerPanel.this.employee.getUsername())) {
					JOptionPane.showMessageDialog(homeview, "Bạn không thể tự xóa tài khoản của mình!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cho nhân viên này nghỉ việc không?","Xác nhận xóa", JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						String[] control = { "deleteByUsername", employeeUsername };
						clientController.sendRequest(control);
						getAll();
					}
					
				}

			}
		}));

	}

	public void loadTable() {
		vData.clear();
		for (Employee employee : employees) {
			Vector row = new Vector();
			row.add(employee.getName());
			row.add(employee.getPhoneNumber());
			row.add(employee.getPermission().getName());
			row.add(employee.getStartDate());
			row.add(employee.getUsername());
			row.add("");
			vData.add(row);
		}

		model.fireTableDataChanged();

	}

	public void getAll() {
		
			Gson gson = new Gson();
			String[] control = { "getAllEmployee", Integer.toString(restaurant.getId()) };
			String employeeJson = clientController.sendRequest(control);
			employees = gson.fromJson(employeeJson, new TypeToken<ArrayList<Employee>>() {
			}.getType());
			loadTable();
		

	}

	public void filter(String posision) {
			Gson gson = new Gson();
			String[] control = { "getEmployeeByPos", Integer.toString(restaurant.getId()), posision };
			String employeesJson = clientController.sendRequest(control);
			employees = gson.fromJson(employeesJson, new TypeToken<ArrayList<Employee>>() {
			}.getType());
			loadTable();
		
	}

	public void search(String keyword) {
	
			Gson gson = new Gson();
			String[] control = { "getEmployeeByWord", Integer.toString(restaurant.getId()), keyword };
			String employeesJson = clientController.sendRequest(control);
			employees = gson.fromJson(employeesJson, new TypeToken<ArrayList<Employee>>() {
			}.getType());
			loadTable();
		
	}

	public EmployeeManagerPanel getView() {
		return this;
	}
}
