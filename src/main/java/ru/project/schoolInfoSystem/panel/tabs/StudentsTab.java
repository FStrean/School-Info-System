package ru.project.schoolInfoSystem.panel.tabs;

import org.jdatepicker.DateLabelFormatter;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import ru.project.schoolInfoSystem.dao.StudentsDao;
import ru.project.schoolInfoSystem.model.Student;
import ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

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
                new Insets(20, 100, 10, 100), 0, 0));
        add(classLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 0), 0, 0));
        add(classTextField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));
        add(searchPanel, new GridBagConstraints(3, 0, 1, 2, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 50, 0, 0), 0, 0));
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
            JDialog addDialog = new JDialog(frame, "Добавление нового ученика", true);
            addDialog.setSize(500, 400);
            addDialog.setLocationRelativeTo(null);
            addDialog.setLayout(new GridBagLayout());

            JLabel fullNameLabel = new JLabel("ФИО");
            JLabel birthdayLabel = new JLabel("Дата рождения");
            JLabel addressLabel = new JLabel("Адрес проживания");
            JLabel parentsLabel = new JLabel("ФИО родителя");
            JLabel phoneLabel = new JLabel("Телефон");
            JLabel studentClassLabel = new JLabel("Класс");

            JTextField nameTextField1 = new JTextField(50);


            JDatePicker birthdayTextField = new JDatePicker();
            birthdayTextField.setTextEditable(true);

            JTextField addressTextField = new JTextField(50);
            JTextField parentsTextField = new JTextField(50);
            JTextField phoneTextField = new JTextField(50);
            JTextField classTextField1 = new JTextField(50);

            JButton addStudentButton = new JButton("Добавить ученика");
            addStudentButton.addActionListener(listener -> {
                Student student = new Student();
                student.setStudentName(nameTextField1.getText());
                student.setBirthdate(new Date(((GregorianCalendar)birthdayTextField.getModel().getValue()).getTimeInMillis()));
                student.setAddress(student.getAddress());
                student.setParentName(student.getParentName());
                student.setPhoneNumber(student.getPhoneNumber());
                student.setClassId(student.getClassId());
                StudentsDao.add(student);
            });

            addDialog.add(fullNameLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, 100, 10, 0), 0, 0));
            addDialog.add(nameTextField1,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(50, -50, 10, 100), 0, 0));

            addDialog.add(birthdayLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(birthdayTextField,new GridBagConstraints(1, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(addressLabel,new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(addressTextField,new GridBagConstraints(1, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(parentsLabel,new GridBagConstraints(0, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(parentsTextField,new GridBagConstraints(1, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(phoneLabel,new GridBagConstraints(0, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(phoneTextField,new GridBagConstraints(1, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(studentClassLabel,new GridBagConstraints(0, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 10, 0), 0, 0));
            addDialog.add(classTextField1,new GridBagConstraints(1, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, -50, 10, 100), 0, 0));

            addDialog.add(addStudentButton,new GridBagConstraints(0, 6, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 100, 50, 100), 0, 0));

            addDialog.setVisible(true);
        });
    }

}
