package src.main.java.ru.project.schoolInfoSystem.panel.tabs;

import src.main.java.ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class StudentsTab extends JPanel {

    public StudentsTab() {
        setLayout(new GridBagLayout());


        JLabel nameLabel = new JLabel("ФИО");
        JLabel classLabel = new JLabel("Номер класса");

        JTextField nameTextField = new JTextField();
        JTextField classTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable studentsTable = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(studentsTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JButton addButton = new JButton("Добавить нового ученика");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 1), 0, 0));
        add(classLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 1), 0, 0));
        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(classTextField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
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
            JDialog addDialog = new JDialog(frame, "Добавление нового ученика", true);
            addDialog.setSize(400, 300);
            addDialog.setLocationRelativeTo(null);
            addDialog.setLayout(new GridBagLayout());

            JLabel nameLabel1 = new JLabel("ФИО");
            JLabel birthdayLabel = new JLabel("Дата рождения");
            JLabel addressLabel = new JLabel("Адрес проживания");
            JLabel parentsLabel = new JLabel("ФИО родителя");
            JLabel phoneLabel = new JLabel("Телефон");
            JLabel classLabel1 = new JLabel("Класс");

            JTextField nameTextField1 = new JTextField(50);
            JTextField birthdayTextField = new JTextField(50);
            JTextField addressTextField = new JTextField(50);
            JTextField parentsTextField = new JTextField(50);
            JTextField phoneTextField = new JTextField(50);
            JTextField classTextField1 = new JTextField(50);

            JButton addStudentButton = new JButton("Добавить ученика");

            addDialog.add(nameLabel1,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(nameTextField1,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(birthdayLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(birthdayTextField,new GridBagConstraints(1, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(addressLabel,new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(addressTextField,new GridBagConstraints(1, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(parentsLabel,new GridBagConstraints(0, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(parentsTextField,new GridBagConstraints(1, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(phoneLabel,new GridBagConstraints(0, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(phoneTextField,new GridBagConstraints(1, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(classLabel1,new GridBagConstraints(0, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            addDialog.add(classTextField1,new GridBagConstraints(1, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));

            addDialog.add(addStudentButton,new GridBagConstraints(0, 6, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));



            addDialog.setVisible(true);
        });
    }

}
