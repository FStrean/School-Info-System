package ru.project.schoolInfoSystem.panel.tabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class PanelTab extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;

    protected void createTableUI() {
        table = new JTable(tableModel);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);


        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(700, 200));


        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));
    }

    protected void createTopUI(JLabel jLabel1, JLabel jLabel2) {
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");


        add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 100, 10, 100), 0, 0));
        add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(textField1, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 0), 0, 0));
        add(textField2, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));
        add(searchPanel, new GridBagConstraints(3, 0, 2, 2, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 0, 0, 0), 0, 0));
        searchPanel.add(searchButton, BorderLayout.CENTER);
    }

    protected void createBottomUI(JButton addButton, JButton editButton, JButton deleteButton) {
        add(addButton, new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(editButton, new GridBagConstraints(4, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(deleteButton, new GridBagConstraints(5, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));
    }

    protected void createPopUpWindowUI (JDialog dialog, JButton addEditButton, JComponent ...components) {
        boolean isLabel = true;
        int rowCount = 0;
        for (JComponent component : components) {
            if(isLabel) {
                dialog.add(component, new GridBagConstraints(0, rowCount, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 20, 10, 0), 0, 0));
            } else {
                dialog.add(component, new GridBagConstraints(1, rowCount, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 10, 10, 20), 0, 0));
                rowCount += 1;
            }
            isLabel = !isLabel;
        }

        rowCount += 1;

        dialog.add(addEditButton, new GridBagConstraints(0, rowCount, 2, 1, 1, 0,
        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
        new Insets(0, 100, 50, 100), 0, 0));

        dialog.setVisible(true);
    }

    protected abstract void createTop();
    protected abstract void createTable();
    protected abstract void createBottom();

    public abstract void update();
}
