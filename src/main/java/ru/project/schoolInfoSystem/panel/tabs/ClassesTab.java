package src.main.java.ru.project.schoolInfoSystem.panel.tabs;

import src.main.java.ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class ClassesTab extends JPanel {
    public ClassesTab() {
        setLayout(new GridBagLayout());

        JLabel classLabel = new JLabel("Номер класса    ");
        JLabel teacherLabel = new JLabel("Классный руководитель         ");

        JTextField classTextField = new JTextField();
        JTextField teacherTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable classesTable = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(classesTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JButton addButton = new JButton("Добавить новый класс");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(classLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 1), 0, 0));
        add(teacherLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 1), 0, 0));
        add(classTextField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(teacherTextField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(searchPanel, new GridBagConstraints(3, 0, 2, 2, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        searchPanel.add(searchButton, BorderLayout.CENTER);


        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        add(addButton, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(editButton, new GridBagConstraints(4, 3, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(deleteButton, new GridBagConstraints(5, 3, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));

        addButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            JDialog addDialog = new JDialog(frame, "Добавление нового класса", true);
            addDialog.setSize(400, 300);
            addDialog.setLocationRelativeTo(null);
            addDialog.setLayout(new GridBagLayout());

            JLabel classNumberLabel = new JLabel("Номер класса");
            JLabel classTeacherLabel = new JLabel("Классный руководитель");
            JLabel StudentsLabel = new JLabel("Ученики");
            JLabel classRoomLabel = new JLabel("Кабинет");

            JTextField classNumberTextField1 = new JTextField(50);
            JTextField classTeacherTextField = new JTextField(50);
            JTextField StudentsTextField = new JTextField(50);
            JTextField classRoomTextField = new JTextField(50);

            JButton addClassButton = new JButton("Добавить класс");

            addDialog.add(classNumberLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(classNumberTextField1,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(classTeacherLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(classTeacherTextField,new GridBagConstraints(1, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(StudentsLabel,new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(StudentsTextField,new GridBagConstraints(1, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(classRoomLabel,new GridBagConstraints(0, 3, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(classRoomTextField,new GridBagConstraints(1, 3, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(addClassButton,new GridBagConstraints(0, 4, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.setVisible(true);
        });
    }
}
