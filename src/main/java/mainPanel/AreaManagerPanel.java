package mainPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.midi.Patch;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.ClientController;
import custemceltable.TableActionCellEditor;
import custemceltable.TableActionCellRender;
import custemceltable.TableActionEvent;
import models.Area;
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Restaurant;
import models.Table;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AreaManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClientController clientController;
	private Restaurant restaurant;
	private ArrayList<Table> tables;
	private ArrayList<Area> areas;
	private Vector areaData = new Vector();
	private Vector areaHeader = new Vector();
	private Vector tableData = new Vector();
	private Vector tableHeader = new Vector();
	private DefaultTableModel tableModel;
	private DefaultTableModel areaModel;
	private JTable tableTable;
	private JTable areaTable;
	private Path imagePath = null;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public AreaManagerPanel(ClientController clientController, Restaurant restaurant) {
		this.clientController = clientController;
		this.restaurant = restaurant;
		setLayout(null);
		setSize(1073, 587);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 140, 0));
		separator_1.setBackground(new Color(255, 140, 0));
		separator_1.setBounds(10, 54, 1053, 2);
		add(separator_1);

		JButton addProductBtn = new JButton("Thêm bàn");
		addProductBtn.setForeground(Color.WHITE);
		addProductBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addProductBtn.setBackground(new Color(15, 67, 61));
		addProductBtn.setBounds(524, 11, 180, 32);
		addProductBtn.putClientProperty("JButton.buttonType", "roundRect");
		addProductBtn.setBackground(Color.decode("#0F433D"));
		addProductBtn.setIcon(new FlatSVGIcon("svg/add.svg"));
		addProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addProductBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new addTableDialog(getView(), AreaManagerPanel.this.restaurant.getId(), AreaManagerPanel.this.clientController,areas);
			}
		});
		add(addProductBtn);

		comboBox = new JComboBox();
		comboBox.putClientProperty("", comboBox);
		comboBox.putClientProperty("JComponent.roundRect", true);
		comboBox.setBackground(new Color(253, 237, 143));
		comboBox.setBounds(141, 11, 180, 32);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idType = comboBox.getSelectedIndex();

				if (idType == 0) {
					getAllTable();
				} else
					filter(areas.get(idType - 1).getId());
			}
		});
		add(comboBox);

		JButton addTypeBtn = new JButton("Thêm khu vực");
		addTypeBtn.setForeground(Color.WHITE);
		addTypeBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addTypeBtn.setBounds(898, 11, 165, 32);
		addTypeBtn.setIcon(new FlatSVGIcon("svg/add.svg"));
		addTypeBtn.setBackground(Color.decode("#0F433D"));
		addTypeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String areaName = JOptionPane.showInputDialog("nhập tên khu vực mới");
				Area newArea = new Area();
				newArea.setName(areaName);
				newArea.setIdRestaurant(AreaManagerPanel.this.restaurant.getId());
				Gson gson = new Gson();
				String areaJson = gson.toJson(newArea);
				String[] control = { "addArea", areaJson};
				String result = AreaManagerPanel.this.clientController.sendRequest(control);
				if(result.equals("0")) {
					JOptionPane.showMessageDialog(addTypeBtn, "Thêm khu vực thất bại!");
				}
				else{
					JOptionPane.showMessageDialog(addTypeBtn, "Thêm khu vực thành công!");
					getAllArea();
					comboBox.addItem(areaName);
				}
				
			}
		});
		add(addTypeBtn);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 67, 688, 500);
		add(scrollPane_2);

		tableTable = new JTable();
		scrollPane_2.setViewportView(tableTable);

		JLabel lblSnPhm = new JLabel("Bàn");
		lblSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSnPhm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSnPhm.setBounds(22, 11, 109, 32);
		add(lblSnPhm);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(757, 67, 306, 500);
		add(scrollPane_1);

		areaHeader.add("ID");
		areaHeader.add("Khu vực");
		areaHeader.add("");
		areaModel = new DefaultTableModel(areaData, areaHeader);

		areaTable = new JTable();
		areaTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		areaTable.setRowHeight(35);
		scrollPane_1.setViewportView(areaTable);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		areaTable.setDefaultRenderer(Object.class, cellRenderer);

		getAllArea();

//		typeTable.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRender());
		areaTable.setModel(areaModel);
		areaTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		areaTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		areaTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
		areaTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				Area type = areas.get(row);
				JOptionPane.showMessageDialog(scrollPane_1, "edit");
			}

			@Override
			public void onDelete(int row) {
				JOptionPane.showConfirmDialog(tableTable, "Tất cả bàn thuộc khu vực này sẽ bị xóa. Xác nhận?");
				int idArea = areas.get(row).getId();
				String[] control = { "DeleteArea", Integer.toString(idArea)};
				AreaManagerPanel.this.clientController.sendRequest(control);
				getAllArea();
				getAllTable();
				comboBox.setSelectedIndex(0);
				comboBox.removeItemAt(row+1);
			}
		}));

		String[] tempHeader = { "ID", "Tên bàn", "Khu vực", "Trạng thái","" };
		for (int i = 0; i < 5; i++)
		tableHeader.add(tempHeader[i]);
		tableModel = new DefaultTableModel(tableData, tableHeader);
		tableTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTable.setRowHeight(35);

		DefaultTableCellRenderer cellRenderer1 = new DefaultTableCellRenderer();
		cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
		tableTable.setDefaultRenderer(Object.class, cellRenderer1);

		
		
		getAllTable();
		tableTable.setModel(tableModel);
		
//		tableTable.getColumnModel().getColumn(0).setPreferredWidth(30);
//		tableTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
		tableTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				Area type = areas.get(row);
				JOptionPane.showMessageDialog(scrollPane_1, "edit");
			}

			@Override
			public void onDelete(int row) {
			}
		}));
		comboBox.addItem("Tất cả");
		for (Area area : areas) {
			comboBox.addItem(area.getName());
		}
		
	}

	public void getAllArea() {

		Gson gson = new Gson();
		String[] control = { "getAllArea", Integer.toString(restaurant.getId()) };
		areas = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<Area>>() {
		}.getType());
		loadAreaTable();

	}

	public void getAllTable() {
		
		Gson gson = new Gson();
		String[] control = { "getAllTable", Integer.toString(restaurant.getId()) };
		tables = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<Table>>() {
		}.getType());
		loadTableTable();
		
	}

	public void loadAreaTable() {
		areaData.clear();
		for (Area area : areas) {
			Vector row = new Vector();
			row.add(area.getId());
			row.add(area.getName());
			row.add("");
			areaData.add(row);

		}

		areaModel.fireTableDataChanged();
	}

	public void loadTableTable() {
		tableData.clear();
		for (Table table : tables) {
			Vector row = new Vector();
			row.add(table.getId());
			row.add(table.getName());
			row.add(table.getArea().getName());
			row.add(table.getStatus().getName());
			row.add("");
			tableData.add(row);
		}
		tableModel.fireTableDataChanged();
	}

	public void filter(int idArea) {

		Gson gson = new Gson();
		String[] control = { "getTableByArea", Integer.toString(idArea) };
		tables = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<Table>>() {
		}.getType());
		loadTableTable();

	}

	
	public AreaManagerPanel getView() {
		return this;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}


	
}
