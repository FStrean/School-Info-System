package src.main.java.ru.project.schoolInfoSystem.panel.tabs;

import src.main.java.ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class TeachersTab extends JPanel {


    public TeachersTab() {
        setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("ФИО");
        JLabel subjectLabel = new JLabel("Предмет");

        JTextField nameTextField = new JTextField();
        JTextField subjectTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable teachersTable = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(teachersTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JButton addButton = new JButton("Добавить нового учителя");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 100, 10, 100), 0, 0));
        add(subjectLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 0), 0, 0));
        add(subjectTextField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));
        add(searchPanel, new GridBagConstraints(3, 0, 2, 2, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 0, 0, 0), 0, 0));
        searchPanel.add(searchButton, BorderLayout.CENTER);


        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));

        add(addButton, new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(editButton, new GridBagConstraints(4, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(deleteButton, new GridBagConstraints(5, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));

        addButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            JDialog addDialog = new JDialog(frame, "Добавление нового учителя", true);
            addDialog.setSize(500, 400);
            addDialog.setLocationRelativeTo(null);
            addDialog.setLayout(new GridBagLayout());

            JLabel fullNameLabel = new JLabel("ФИО");
            JLabel subjectTeacherLabel = new JLabel("Предмет");
            JLabel passportLabel = new JLabel("Паспорт");
            JLabel experienceLabel = new JLabel("Опыт работы");
            JLabel educationLabel = new JLabel("Образование");
            JLabel phoneLabel = new JLabel("Телефон");
            JLabel classLabel = new JLabel("Классное руководство");

            JTextField fullNameTextField1 = new JTextField(50);
            JTextField subjectTeacherTextField = new JTextField(50);
            JTextField passportTextField = new JTextField(50);
            JTextField experienceTextField = new JTextField(50);
            JTextField educationTextField = new JTextField(50);
            JTextField phoneTextField1 = new JTextField(50);
            JTextField classTextField1 = new JTextField(50);

            JButton addTeacherButton = new JButton("Добавить учителя");

            addDialog.add(fullNameLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, 100, 10, 0), 0, 0));
            addDialog.add(fullNameTextField1,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, -50, 10, 100), 0, 0));

            addDialog.add(subjectTeacherLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(subjectTeacherTextField,new GridBagConstraints(1, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(passportLabel,new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(passportTextField,new GridBagConstraints(1, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(experienceLabel,new GridBagConstraints(0, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(experienceTextField,new GridBagConstraints(1, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(educationLabel,new GridBagConstraints(0, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(educationTextField,new GridBagConstraints(1, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(phoneLabel,new GridBagConstraints(0, 5, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(phoneTextField1,new GridBagConstraints(1, 5, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(classLabel,new GridBagConstraints(0, 6, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(classTextField1,new GridBagConstraints(1, 6, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(addTeacherButton,new GridBagConstraints(0, 7, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 50, 100), 0, 0));

            addDialog.setVisible(true);
        });
    }

}
