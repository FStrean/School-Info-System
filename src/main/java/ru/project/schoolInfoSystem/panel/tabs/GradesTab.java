package ru.project.schoolInfoSystem.panel.tabs;

import ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class GradesTab extends JPanel {
    public GradesTab() {
        setLayout(new GridBagLayout());

        JLabel classLabel = new JLabel("Номер класса");
        JLabel subjectLabel = new JLabel("Предмет");

        JTextField classTextField = new JTextField();
        JTextField subjectTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable studentsTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(studentsTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JButton rateButton = new JButton("Поставить оценку");
        JPanel palePanel = new JPanel();

        add(classLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 100, 10, 100), 0, 0));
        add(subjectLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(classTextField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 0), 0, 0));
        add(subjectTextField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));
        add(searchPanel, new GridBagConstraints(3, 0, 1, 2, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 50, 0, 100), 0, 0));
        searchPanel.add(searchButton, BorderLayout.CENTER);


        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));

        add(rateButton, new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        add(palePanel, new GridBagConstraints(2, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));

        rateButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            JDialog addDialog = new JDialog(frame, "Выставление оценки", true);
            addDialog.setSize(400, 300);
            addDialog.setLocationRelativeTo(null);
            addDialog.setLayout(new GridBagLayout());

            JLabel gradeLabel = new JLabel("Оценка");
            JLabel taskLabel = new JLabel("Работа");

            JTextField gradeTextField1 = new JTextField();
            JTextField taskTextField = new JTextField();

            JButton saveButton = new JButton("Сохранить");

            addDialog.add(gradeLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, 100, 10, 0), 0, 0));
            addDialog.add(gradeTextField1,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, -50, 10, 100), 0, 0));

            addDialog.add(taskLabel,new GridBagConstraints(0, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(taskTextField,new GridBagConstraints(1, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(saveButton,new GridBagConstraints(0, 4, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 50, 100), 0, 0));

            addDialog.setVisible(true);
        });

    }
}
