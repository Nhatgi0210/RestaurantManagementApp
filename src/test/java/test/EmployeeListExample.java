package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeListExample extends JFrame {

    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeListExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Employee List");

        // Tạo một DefaultTableModel với các cột "Name", "Position", "Edit", "Delete"
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");
        tableModel.addColumn("Edit");
        tableModel.addColumn("Delete");

        // Tạo một JTable với DefaultTableModel
        employeeTable = new JTable(tableModel);

        // Thiết lập trình render tùy chỉnh cho cột "Edit"
        employeeTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer("Edit"));

        // Thiết lập trình render tùy chỉnh cho cột "Delete"
        employeeTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer("Delete"));

        // Thiết lập trình xử lý sự kiện tùy chỉnh cho cột "Edit"
        employeeTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin nhân viên từ hàng được chọn và xử lý logic sửa thông tin
                    String name = (String) tableModel.getValueAt(selectedRow, 0);
                    String position = (String) tableModel.getValueAt(selectedRow, 1);
                    // Logic sửa thông tin nhân viên
                    System.out.println("Edit employee: " + name + ", " + position);
                }
            }
        }));

        // Thiết lập trình xử lý sự kiện tùy chỉnh cho cột "Delete"
        employeeTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin nhân viên từ hàng được chọn và xử lý logic xóa thông tin
                    String name = (String) tableModel.getValueAt(selectedRow, 0);
                    String position = (String) tableModel.getValueAt(selectedRow, 1);
                    // Logic xóa thông tin nhân viên
                    System.out.println("Delete employee: " + name + ", " + position);
                    tableModel.removeRow(selectedRow);
                }
            }
        }));

        // Tạo một JScrollPane để bao bọc JTable
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // Thêm JScrollPane vào JFrame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public void addEmployee(String name, String position) {
        // Thêm một hàng mới với thông tin nhân viên vào DefaultTableModel
        tableModel.addRow(new Object[]{name, position});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeListExample example = new EmployeeListExample();
            example.setVisible(true);
            // Thêm các nhân viên vào danh sách
            example.addEmployee("John Doe", "Manager");
            example.addEmployee("Jane Smith", "Developer");
            example.addEmployee("Mike Johnson", "Designer");
        });
    }

    // Lớp trình render tùy chỉnh cho cột "Edit" và "Delete"
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer(String text) {
            setText(text);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Lớp trình xử lý sự kiện tùy chỉnh cho cột "Edit" và "Delete"
    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private ActionListener actionListener;

        public ButtonEditor(ActionListener actionListener) {
            super(new JTextField());
            this.actionListener = actionListener;

            button = new JButton();
            button.addActionListener(actionListener);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
}