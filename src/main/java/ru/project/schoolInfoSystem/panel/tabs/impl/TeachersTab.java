package ru.project.schoolInfoSystem.panel.tabs.impl;

import ru.project.schoolInfoSystem.dao.SubjectDao;
import ru.project.schoolInfoSystem.dao.TeachersDao;
import ru.project.schoolInfoSystem.model.Subject;
import ru.project.schoolInfoSystem.model.Teacher;
import ru.project.schoolInfoSystem.panel.tabs.PanelTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

public class TeachersTab extends PanelTab {

    private final JComboBox<Subject> subjectComboBox;

    public TeachersTab() {
        subjectComboBox = new JComboBox<>();

        setLayout(new GridBagLayout());

        createTop();

        createTable();

        createBottom();

        update();
    }


    @Override
    protected void createTop() {
        JLabel nameLabel = new JLabel("ФИО");
        JLabel subjectLabel = new JLabel("Предмет");
        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");
        createTopUI(nameLabel, subjectLabel);
    }


    @Override
    protected void createTable() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Идентификатор", "ФИО", "Пасспорт", "Опыт", "Образование", "Номер телефона", "Классное руководство"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        createTableUI();
    }


    @Override
    protected void createBottom() {
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        createBottomUI(addButton, editButton, deleteButton);

        JTextField fullNameTextField = new JTextField(50);
        JTextField passportTextField = new JTextField(50);
        JTextField experienceTextField = new JTextField(50);
        JTextField educationTextField = new JTextField(50);
        JTextField phoneTextField = new JTextField(50);

        addButton.addActionListener(listener ->
                createPopUpWindow(TeachersDao::add, -1, fullNameTextField, passportTextField,
                experienceTextField, educationTextField, phoneTextField));

        editButton.addActionListener(listener -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1) {
                fullNameTextField.setText((String) table.getValueAt(selectedRow, 1));
                passportTextField.setText((String) table.getValueAt(selectedRow, 2));
                experienceTextField.setText((String) table.getValueAt(selectedRow, 3));
                educationTextField.setText((String) table.getValueAt(selectedRow, 4));
                phoneTextField.setText((String) table.getValueAt(selectedRow, 5));
                createPopUpWindow(TeachersDao::update, selectedRow, fullNameTextField, passportTextField,
                        experienceTextField, educationTextField, phoneTextField);
                update();
            }
        });

        deleteButton.addActionListener(listener -> {
            Long id = (Long)table.getValueAt(table.getSelectedRow(), 0);
            TeachersDao.delete(id);
            update();
        });
    }


    private void createPopUpWindow(Consumer<Teacher> function, int selectedRow, JTextField fullNameTextField,
                                          JTextField passportTextField, JTextField experienceTextField,
                                          JTextField educationTextField, JTextField phoneTextField) {
        JFrame frame = new JFrame();
        JDialog addDialog = new JDialog(frame, "Учитель", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(null);
        addDialog.setLayout(new GridBagLayout());

        JLabel fullNameLabel = new JLabel("ФИО");
        JLabel subjectTeacherLabel = new JLabel("Предмет");
        JLabel passportLabel = new JLabel("Паспорт");
        JLabel experienceLabel = new JLabel("Опыт работы");
        JLabel educationLabel = new JLabel("Образование");
        JLabel phoneLabel = new JLabel("Телефон");

        JButton addEditTeacherButton = new JButton("Продолжить");

        addEditTeacherButton.addActionListener(listener -> {
            Teacher teacher = new Teacher();

            if(selectedRow != -1) {
                teacher.setId((Long) table.getValueAt(selectedRow, 0));
            }
            teacher.setTeacherName(fullNameTextField.getText());
            teacher.setSubjectId(((Subject) Objects.requireNonNull(subjectComboBox.getSelectedItem())).getId());
            teacher.setPassport(passportTextField.getText());
            teacher.setWorkExperience(experienceTextField.getText());
            teacher.setEducation(educationTextField.getText());
            teacher.setPhoneNumber(phoneTextField.getText());
            function.accept(teacher);

            update();
            frame.dispose();
        });

        createPopUpWindowUI(addDialog, addEditTeacherButton, fullNameLabel, fullNameTextField, subjectTeacherLabel,
                subjectComboBox, passportLabel, passportTextField, experienceLabel, experienceTextField,
                educationLabel, educationTextField, phoneLabel, phoneTextField);
    }


    @Override
    public void update() {
        for(Subject subject : SubjectDao.getAll()) {
            subjectComboBox.addItem(subject);
        }
        tableModel.setRowCount(0);
        for (Teacher teacher : TeachersDao.getAll()) {
            String schoolClass = TeachersDao.getTeacherClass(teacher).getNumber();

            tableModel.addRow(new Object[]{teacher.getId(), teacher.getTeacherName(), teacher.getPassport(),
            teacher.getWorkExperience(), teacher.getEducation(), teacher.getPhoneNumber(), schoolClass != null ? schoolClass : ""});
        }
    }
}
