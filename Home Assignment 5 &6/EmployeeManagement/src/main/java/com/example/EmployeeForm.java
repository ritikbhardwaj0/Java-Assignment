package com.example;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/** Swing UI for adding employees and viewing all employees stored in MySQL. */
public final class EmployeeForm extends JFrame {
    private final EmployeeDAO dao = new EmployeeDAO();
    private final JTextField idField = new JTextField(18);
    private final JTextField nameField = new JTextField(18);
    private final JTextField departmentField = new JTextField(18);
    private final JTextField salaryField = new JTextField(18);
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Employee ID", "Name", "Department", "Salary"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable employeeTable = new JTable(tableModel);

    private EmployeeForm() {
        super("Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(760, 500));
        setLocationByPlatform(true);
        buildUi();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel title = new JLabel("Employee Management");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        root.add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(14, 14));
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Add a new employee"));
        addField(form, "Employee ID", idField, 0);
        addField(form, "Name", nameField, 1);
        addField(form, "Department", departmentField, 2);
        addField(form, "Salary", salaryField, 3);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> saveEmployee());
        JButton viewButton = new JButton("View All Employees");
        viewButton.addActionListener(event -> loadEmployees());
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(viewButton);
        actions.add(submitButton);
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0; buttonConstraints.gridy = 4;
        buttonConstraints.gridwidth = 2; buttonConstraints.anchor = GridBagConstraints.EAST;
        buttonConstraints.insets = new Insets(12, 8, 8, 8);
        form.add(actions, buttonConstraints);
        content.add(form, BorderLayout.NORTH);

        employeeTable.setFillsViewportHeight(true);
        employeeTable.setRowHeight(24);
        employeeTable.setAutoCreateRowSorter(true);
        JScrollPane tableScroll = new JScrollPane(employeeTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Employees"));
        content.add(tableScroll, BorderLayout.CENTER);
        root.add(content, BorderLayout.CENTER);
        setContentPane(root);
    }

    private static void addField(JPanel panel, String label, JTextField field, int row) {
        GridBagConstraints left = new GridBagConstraints();
        left.gridx = 0; left.gridy = row; left.anchor = GridBagConstraints.LINE_START;
        left.insets = new Insets(6, 8, 6, 10);
        panel.add(new JLabel(label + ":"), left);
        GridBagConstraints right = new GridBagConstraints();
        right.gridx = 1; right.gridy = row; right.weightx = 1;
        right.fill = GridBagConstraints.HORIZONTAL; right.insets = new Insets(6, 0, 6, 8);
        panel.add(field, right);
    }

    private void saveEmployee() {
        final int id;
        final BigDecimal salary;
        String name = nameField.getText().trim();
        String department = departmentField.getText().trim();
        try {
            id = Integer.parseInt(idField.getText().trim());
            salary = new BigDecimal(salaryField.getText().trim());
        } catch (NumberFormatException exception) {
            showError("Employee ID must be a whole number and salary must be a valid decimal.");
            return;
        }
        if (id <= 0) { showError("Employee ID must be greater than zero."); return; }
        if (name.isEmpty() || name.length() > 100) { showError("Name is required and must be at most 100 characters."); return; }
        if (department.isEmpty() || department.length() > 50) { showError("Department is required and must be at most 50 characters."); return; }
        if (salary.signum() < 0 || salary.precision() - salary.scale() > 8 || salary.scale() > 2) {
            showError("Salary must be non-negative and have at most 8 whole digits and 2 decimal places.");
            return;
        }
        try {
            dao.addEmployee(id, name, department, salary);
            JOptionPane.showMessageDialog(this, "Employee saved successfully.", "Saved", JOptionPane.INFORMATION_MESSAGE);
            idField.setText(""); nameField.setText(""); departmentField.setText(""); salaryField.setText("");
            loadEmployees();
        } catch (SQLException exception) {
            showDatabaseError(exception);
        }
    }

    private void loadEmployees() {
        try {
            List<EmployeeDAO.Employee> employees = dao.getAllEmployees();
            tableModel.setRowCount(0);
            for (EmployeeDAO.Employee employee : employees) {
                tableModel.addRow(new Object[]{employee.id(), employee.name(), employee.department(), employee.salary()});
            }
        } catch (SQLException exception) {
            showDatabaseError(exception);
        }
    }

    private void showDatabaseError(SQLException exception) {
        String message = "Could not access the employee database. Check that MySQL is running and DB_URL, DB_USER, and DB_PASSWORD are correct.\n\n" + exception.getMessage();
        showError(message);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Employee Management", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) { /* Use Swing's default look and feel. */ }
            new EmployeeForm().setVisible(true);
        });
    }
}
