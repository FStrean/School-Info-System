package ru.project.schoolInfoSystem.panel.tabs;

import ru.project.schoolInfoSystem.dao.SubjectDao;
import ru.project.schoolInfoSystem.dao.TeachersDao;
import ru.project.schoolInfoSystem.model.Subject;
import ru.project.schoolInfoSystem.model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

public class TeachersTab extends JPanel {

    private JTable teachersTable;
    private DefaultTableModel tableModel;

    public TeachersTab() {
        setLayout(new GridBagLayout());

        createTopUI();

        createMiddleUI();

        createBottomUI();

        update();
    }

    private void createTopUI() {
        JLabel nameLabel = new JLabel("ФИО");
        JLabel subjectLabel = new JLabel("Предмет");

        JTextField nameTextField = new JTextField();
        JTextField subjectTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");


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
    }

    private void createMiddleUI() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Идентификатор", "ФИО", "Пасспорт", "Опыт", "Образование", "Номер телефона", "Классное руководство"}){
            @Override
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        teachersTable = new JTable(tableModel);

        teachersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teachersTable.getTableHeader().setReorderingAllowed(false);


        JScrollPane tableScroll = new JScrollPane(teachersTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));


        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));
    }

    private void createBottomUI() {
        JButton addButton = new JButton("Добавить нового учителя");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(addButton, new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 100), 0, 0));
        add(editButton, new GridBagConstraints(4, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(deleteButton, new GridBagConstraints(5, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));

        JTextField fullNameTextField = new JTextField(50);
        JTextField passportTextField = new JTextField(50);
        JTextField experienceTextField = new JTextField(50);
        JTextField educationTextField = new JTextField(50);
        JTextField phoneTextField = new JTextField(50);

        addButton.addActionListener(listener -> {
            createAddNewEditButtonUI(TeachersDao::add, -1, fullNameTextField, passportTextField,
                    experienceTextField, educationTextField, phoneTextField);
            update();
        });

        editButton.addActionListener(listener -> {
            int selectedRow = teachersTable.getSelectedRow();
            fullNameTextField.setText((String)teachersTable.getValueAt(selectedRow, 1));
            passportTextField.setText((String)teachersTable.getValueAt(selectedRow, 2));
            experienceTextField.setText((String)teachersTable.getValueAt(selectedRow, 3));
            educationTextField.setText((String)teachersTable.getValueAt(selectedRow, 4));
            phoneTextField.setText((String)teachersTable.getValueAt(selectedRow, 5));
            createAddNewEditButtonUI(TeachersDao::update, selectedRow, fullNameTextField, passportTextField,
                    experienceTextField, educationTextField, phoneTextField);
            update();
        });

        deleteButton.addActionListener(listener -> {
            Long id = (Long)teachersTable.getValueAt(teachersTable.getSelectedRow(), 0);
            TeachersDao.delete(id);
            update();
        });
    }
    private void createAddNewEditButtonUI(Consumer<Teacher> function, int selectedRow, JTextField fullNameTextField,
                                          JTextField passportTextField, JTextField experienceTextField,
                                          JTextField educationTextField, JTextField phoneTextField) {
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

        JComboBox<Subject> subjectComboBox = new JComboBox<>();
        for(Subject subject : SubjectDao.getAll()) {
            subjectComboBox.addItem(subject);
        }

        JButton addEditTeacherButton = new JButton("Продолжить");

        addEditTeacherButton.addActionListener(listener -> {
            Teacher teacher = new Teacher();

            if(selectedRow != -1) {
                teacher.setId((Long) teachersTable.getValueAt(selectedRow, 0));
            }
            teacher.setTeacherName(fullNameTextField.getText());
            teacher.setSubjectId(((Subject) Objects.requireNonNull(subjectComboBox.getSelectedItem())).getId());
            teacher.setPassport(passportTextField.getText());
            teacher.setWorkExperience(experienceTextField.getText());
            teacher.setEducation(educationTextField.getText());
            teacher.setPhoneNumber(phoneTextField.getText());
            function.accept(teacher);
        });

        addDialog.add(fullNameLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(50, 100, 10, 0), 0, 0));
        addDialog.add(fullNameTextField,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(50, -50, 10, 100), 0, 0));

        addDialog.add(subjectTeacherLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        addDialog.add(subjectComboBox,new GridBagConstraints(1, 1, 1, 1, 1, 0,
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
        addDialog.add(phoneTextField,new GridBagConstraints(1, 5, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 100), 0, 0));

        addDialog.add(addEditTeacherButton,new GridBagConstraints(0, 7, 2, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 50, 100), 0, 0));

        addDialog.setVisible(true);
    }


    public void update() {
        tableModel.setRowCount(0);
        for (Teacher teacher : TeachersDao.getAll()) {
            tableModel.addRow(new Object[]{teacher.getId(), teacher.getTeacherName(), teacher.getPassport(),
            teacher.getWorkExperience(), teacher.getEducation(), teacher.getPhoneNumber()});
        }
    }
}
