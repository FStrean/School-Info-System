package ru.project.schoolInfoSystem.panel.tabs;

import ru.project.schoolInfoSystem.dao.SubjectDao;
import ru.project.schoolInfoSystem.dao.TeachersDao;
import ru.project.schoolInfoSystem.model.Subject;
import ru.project.schoolInfoSystem.model.Teacher;
import ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class TeachersTab extends JPanel {


    public TeachersTab() {
        setLayout(new GridBagLayout());

        createTopUI();

        createMiddleUI();

        createBottomUI();
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
        DefaultTableModel tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Идентификатор", "ФИО", "Пасспорт", "Опыт", "Образование", "Номер телефона"}){
            @Override
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };;
        JTable teachersTable = new JTable(tableModel);

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

        addButton.addActionListener(listener -> {
            addNewTeacherButtonIsClicked();
        });
    }

    private void addNewTeacherButtonIsClicked() {
        createAddNewButtonUI();
    }

    private void createAddNewButtonUI() {
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

        JTextField fullNameTextField = new JTextField(50);

        JComboBox<Subject> subjectComboBox = new JComboBox<>();
        for(Subject subject : getSubjectsFromDatabase()) {
            subjectComboBox.addItem(subject);
        }

        JTextField passportTextField = new JTextField(50);
        JTextField experienceTextField = new JTextField(50);
        JTextField educationTextField = new JTextField(50);
        JTextField phoneTextField = new JTextField(50);
        JTextField schoolClassTextField = new JTextField(50);

        JButton addTeacherButton = new JButton("Добавить учителя");

        addTeacherButton.addActionListener(listener -> {
            addToDatabase(fullNameTextField.getText(), ((Subject)subjectComboBox.getSelectedItem()).getId(), passportTextField.getText(), experienceTextField.getText(),
                    educationTextField.getText(), phoneTextField.getText() /*schoolClass*/);
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

        addDialog.add(classLabel,new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        addDialog.add(schoolClassTextField,new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 100), 0, 0));

        addDialog.add(addTeacherButton,new GridBagConstraints(0, 7, 2, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 50, 100), 0, 0));

        addDialog.setVisible(true);
    }

    private void addToDatabase(String name, Long subjectId, String passport, String workExperience,
                               String education, String phone /*Long schoolClassId*/) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(name);
        teacher.setSubjectId(subjectId);
        teacher.setPassport(passport);
        teacher.setWorkExperience(workExperience);
        teacher.setEducation(education);
        teacher.setPhoneNumber(phone);
        //teacher.setClassId(schoolClassId);

        TeachersDao.add(teacher);
    }

    private java.util.List<Subject> getSubjectsFromDatabase() {
        return SubjectDao.getAll();
    }
}
