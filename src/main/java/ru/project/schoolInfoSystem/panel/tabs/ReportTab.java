package ru.project.schoolInfoSystem.panel.tabs;

import ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class ReportTab extends JPanel {
    public ReportTab() {
        setLayout(new GridBagLayout());

        JLabel dateLabel = new JLabel("Дата составления");
        JTextField dateTextField = new JTextField();

        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable studentsTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(studentsTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JButton generateButton = new JButton("Сгенерировать отчет");
        JButton printButton = new JButton("Напечатать отчет");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(dateLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 100, 10, 10), 0, 0));
        add(dateTextField, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 10), 0, 0));
        add(searchButton, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 10, 100), 0, 0));

        add(tableScroll, new GridBagConstraints(0, 1, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));


        add(generateButton, new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 10), 0, 0));
        add(printButton, new GridBagConstraints(1, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(editButton, new GridBagConstraints(2, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(deleteButton, new GridBagConstraints(3, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));
    }
}
