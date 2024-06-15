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
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Restaurant;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ProductPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClientController clientController;
	private Restaurant restaurant;
	private ArrayList<FoodCategory> foodCategories;
	private ArrayList<FoodItem> foodItems;
	private Vector typeData = new Vector();
	private Vector typeHeader = new Vector();
	private Vector productData = new Vector();
	private Vector productHeader = new Vector();
	private DefaultTableModel productModel;
	private DefaultTableModel typeModel;
	private JTextField searchTextField;
	private JTable productTable;
	private JTable typeTable;
	private JTextField nametextField;
	private JTextField priceTextField;
	private JTextField unitTextField;
	private JLabel productImage;
	private Path imagePath = null;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public ProductPanel(ClientController clientController, Restaurant restaurant) {
		this.clientController = clientController;
		this.restaurant = restaurant;
		setLayout(null);
		setSize(1073, 587);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 140, 0));
		separator_1.setBackground(new Color(255, 140, 0));
		separator_1.setBounds(10, 54, 1053, 2);
		add(separator_1);

		JButton searchProductBtn = new JButton("");
		searchProductBtn.setBounds(480, 11, 34, 32);
		searchProductBtn.setIcon(new FlatSVGIcon("svg/search.svg"));
		searchProductBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				search(searchTextField.getText());
			}
		});
		add(searchProductBtn);

		searchTextField = new JTextField();
		searchTextField.setColumns(10);
		searchTextField.setBackground(new Color(253, 237, 143));
		searchTextField.setBounds(334, 11, 165, 32);
		searchTextField.putClientProperty("JComponent.roundRect", true);
		searchTextField.putClientProperty("JTextField.placeholderText", "Nhập Tên...");
		add(searchTextField);

		JButton addProductBtn = new JButton("Sản Phẩm Mới");
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
				// TODO Auto-generated method stub
				new addProductDialog(getView(), ProductPanel.this.restaurant.getId(),
						ProductPanel.this.clientController, foodCategories);
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
					getAllProduct();
				} else
					filter(foodCategories.get(idType - 1).getId());
			}
		});
		add(comboBox);

		JButton addTypeBtn = new JButton("");
		addTypeBtn.setBounds(1029, 311, 44, 42);
		addTypeBtn.setIcon(new FlatSVGIcon("svg/add.svg"));
		addTypeBtn.setBackground(Color.decode("#0F433D"));
		addTypeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String typeName = JOptionPane.showInputDialog("nhập tên loại sản phẩm mới");
				String[] control = { "addFoodCategory", Integer.toString(ProductPanel.this.restaurant.getId()),
						typeName };
				ProductPanel.this.clientController.sendRequest(control);
				getAllType();
				comboBox.addItem(typeName);
			}
		});
		add(addTypeBtn);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 67, 688, 500);
		add(scrollPane_2);

		productTable = new JTable();
		scrollPane_2.setViewportView(productTable);

		JLabel lblSnPhm = new JLabel("Sản Phẩm");
		lblSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSnPhm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSnPhm.setBounds(22, 11, 109, 32);
		add(lblSnPhm);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(255, 140, 0));
		separator_1_1.setBackground(new Color(255, 140, 0));
		separator_1_1.setBounds(730, 11, 2, 500);
		add(separator_1_1);

		JPanel panel = new JPanel();
		panel.setBounds(707, 66, 366, 234);
		add(panel);
		panel.setLayout(null);

		productImage = new JLabel("");
		productImage.setBounds(143, 0, 168, 145);
		panel.add(productImage);

		JButton chooseImageBtn = new JButton("");
		chooseImageBtn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		chooseImageBtn.setBounds(321, 0, 45, 45);
		chooseImageBtn.setIcon(new FlatSVGIcon("svg/OpenFolder.svg"));
		chooseImageBtn.setBackground(Color.decode("#0F433D"));
		chooseImageBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		panel.add(chooseImageBtn);

		JButton editProductBtn = new JButton("Cập Nhật");
		editProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = productTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(panel, "Vui lòng nhập đủ thông tin!");
				} else {
					FoodItem foodItem = foodItems.get(selectedRow);
					foodItem.setName(nametextField.getText());
					foodItem.setUnitName(unitTextField.getText());
					foodItem.setUnitPrice(Integer.parseInt(priceTextField.getText()));
					if(foodItem.getImagePath()!=imagePath.toString()) {
						foodItem.setImagePath(copyFile(imagePath));
					}
					updateFoodItem(foodItem);
				}
			}
		});
		editProductBtn.setForeground(Color.WHITE);
		editProductBtn.setBounds(97, 200, 166, 23);
		editProductBtn.setBackground(Color.decode("#0F433D"));
		panel.add(editProductBtn);

		JLabel lblNewLabel = new JLabel("Tên Sản Phẩm");
		lblNewLabel.setBounds(20, 131, 102, 14);
		panel.add(lblNewLabel);

		nametextField = new JTextField();
		nametextField.setBounds(20, 156, 309, 33);
		panel.add(nametextField);
		nametextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Giá");
		lblNewLabel_1.setBounds(20, 19, 46, 14);
		panel.add(lblNewLabel_1);

		priceTextField = new JTextField();
		priceTextField.setBounds(20, 44, 102, 20);
		panel.add(priceTextField);
		priceTextField.setColumns(10);

		unitTextField = new JTextField();
		unitTextField.setColumns(10);
		unitTextField.setBounds(20, 100, 102, 20);
		panel.add(unitTextField);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn vị ");
		lblNewLabel_1_1.setBounds(20, 75, 46, 14);
		panel.add(lblNewLabel_1_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(730, 311, 290, 256);
		add(scrollPane_1);

		typeHeader.add("ID");
		typeHeader.add("Các Loại Sản Phẩm");
		typeHeader.add("");
		typeModel = new DefaultTableModel(typeData, typeHeader);

		typeTable = new JTable();
		typeTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typeTable.setRowHeight(35);
		scrollPane_1.setViewportView(typeTable);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		typeTable.setDefaultRenderer(Object.class, cellRenderer);

		getAllType();

//		typeTable.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRender());
		typeTable.setModel(typeModel);
		typeTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		typeTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		typeTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
		typeTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				FoodCategory type = foodCategories.get(row);
				JOptionPane.showMessageDialog(scrollPane_1, "edit");
			}

			@Override
			public void onDelete(int row) {
				JOptionPane.showConfirmDialog(typeTable, "Tất cả món thuộc loại này sẽ bị xóa. Xác nhận?");
				int idType = foodCategories.get(row).getId();
				String[] control = { "DeleteFoodCategory", Integer.toString(idType) };
				ProductPanel.this.clientController.sendRequest(control);
				getAllType();
				getAllProduct();
				comboBox.setSelectedIndex(0);
				comboBox.removeItemAt(row+1);
			}
		}));

		String[] tempHeader = { "ID", "Tên Sản Phẩm", "Đơn Vị Tính", "Giá", "Loại", "" };
		for (int i = 0; i < 6; i++)
			productHeader.add(tempHeader[i]);
		productModel = new DefaultTableModel(productData, productHeader);

		productTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		productTable.setRowHeight(35);

		DefaultTableCellRenderer cellRenderer1 = new DefaultTableCellRenderer();
		cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
		productTable.setDefaultRenderer(Object.class, cellRenderer1);

		
		
		getAllProduct();
		productTable.setModel(productModel);
		productTable.getSelectionModel().addListSelectionListener(event -> {

			if (!event.getValueIsAdjusting()) {
				int selectedRow = productTable.getSelectedRow();
				if (selectedRow != -1) {
					FoodItem foodItem = foodItems.get(selectedRow);
					priceTextField.setText(Integer.toString(foodItem.getUnitPrice()));
					unitTextField.setText(foodItem.getUnitName());
					nametextField.setText(foodItem.getName());
					if (foodItem.getImagePath() != null) {
						ImageIcon originalImage = new ImageIcon(foodItem.getImagePath());
						Image image = originalImage.getImage().getScaledInstance(168, 145, Image.SCALE_SMOOTH);
						productImage.setIcon(new ImageIcon(image));
						imagePath = Paths.get(foodItem.getImagePath());
					}
					else {
						ImageIcon temp = new ImageIcon(getClass().getResource("/image/food.png"));
						Image image = temp.getImage().getScaledInstance(168, 145,Image.SCALE_SMOOTH);
						productImage.setIcon(new ImageIcon(image));
					}

				}
			}
		});
		productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		productTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		productTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
		productTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				FoodCategory type = foodCategories.get(row);
				JOptionPane.showMessageDialog(scrollPane_1, "edit");
			}

			@Override
			public void onDelete(int row) {
			}
		}));
		comboBox.addItem("Tất cả");
		for (FoodCategory foodCategory : foodCategories) {
			comboBox.addItem(foodCategory.getName());
		}
		
	}

	public void getAllType() {

		Gson gson = new Gson();
		String[] control = { "getAllType", Integer.toString(restaurant.getId()) };
		foodCategories = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodCategory>>() {
		}.getType());
		loadTypeTable();

	}

	public void getAllProduct() {
		
		Gson gson = new Gson();
		String[] control = { "getAllProduct", Integer.toString(restaurant.getId()) };
		foodItems = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodItem>>() {
		}.getType());
		loadProductTable();
		
	}

	public void loadTypeTable() {
		typeData.clear();
		for (FoodCategory foodCategory : foodCategories) {
			Vector row = new Vector();
			row.add(foodCategory.getId());
			row.add(foodCategory.getName());
			row.add("");
			typeData.add(row);

		}

		typeModel.fireTableDataChanged();
	}

	public void loadProductTable() {
		productData.clear();
		for (FoodItem foodItem : foodItems) {
			Vector row = new Vector();
			row.add(foodItem.getId());
			row.add(foodItem.getName());
			row.add(foodItem.getUnitName());
			row.add(foodItem.getUnitPrice());
			row.add(foodItem.getCategoryName());
			row.add("");
			productData.add(row);
		}
		productModel.fireTableDataChanged();
	}

	public void filter(int idType) {

		Gson gson = new Gson();
		String[] control = { "getProductByType", Integer.toString(idType) };
		foodItems = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodItem>>() {
		}.getType());
		loadProductTable();

	}

	public void search(String keyword) {

		Gson gson = new Gson();
		String[] control = { "getProductByWord", Integer.toString(restaurant.getId()), keyword };

		foodItems = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodItem>>() {
		}.getType());
		loadProductTable();

	}

	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif",
				"gfif");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon originalImage = new ImageIcon(selectedFile.getAbsolutePath());
			Image image = originalImage.getImage().getScaledInstance(168, 145, Image.SCALE_SMOOTH);
			productImage.setIcon(new ImageIcon(image));
			imagePath = selectedFile.toPath();
		}
	}

	public void updateFoodItem(FoodItem foodItem) {
		Gson gson = new Gson();
		String foodJson = gson.toJson(foodItem);
		String[] control = { "updateProduct", foodJson };
		String result = clientController.sendRequest(control);
		if (result.equals("0")) {
			JOptionPane.showMessageDialog(nametextField, "Cập nhật thông tin món thất bại!");
		} else {
			JOptionPane.showMessageDialog(nametextField, "Cập nhật thông tin món thành công!");
			comboBox.setSelectedIndex(0);
			getAllProduct();
		}
	}

	public ProductPanel getView() {
		return this;
	}

	private String copyFile(Path sourcePath) {
		String fileName = sourcePath.getFileName().toString();
		Path destinationPath;
		
			try {
				File resourcesDirectory = new File("src/main/resources");
				String resourcesPath = resourcesDirectory.getAbsolutePath();
				String temp = resourcesPath+"/image/"+fileName;
				destinationPath = 	Paths.get(temp);
				Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				return destinationPath.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}


	
}
