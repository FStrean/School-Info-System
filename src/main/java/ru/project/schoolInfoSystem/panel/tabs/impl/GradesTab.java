package ru.project.schoolInfoSystem.panel.tabs.impl;

import ru.project.schoolInfoSystem.dao.GradesDao;
import ru.project.schoolInfoSystem.dao.SubjectDao;
import ru.project.schoolInfoSystem.dto.GradesDto;
import ru.project.schoolInfoSystem.model.Grades;
import ru.project.schoolInfoSystem.model.Subject;
import ru.project.schoolInfoSystem.panel.tabs.PanelTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

import static ru.project.schoolInfoSystem.dao.GradesDao.findStudentId;

public class GradesTab extends PanelTab {

    private JTable gradesTable;
    private DefaultTableModel tableModel;
    private final JComboBox<Subject> subjectComboBox;

    public GradesTab() {
        subjectComboBox = new JComboBox<>();
        setLayout(new GridBagLayout());
        createTop();
        createTable();
        createBottom();
        update();
    }

    @Override
    protected void createTop() {
        JLabel classLabel = new JLabel("Номер класса");
        JLabel subjectLabel = new JLabel("Предмет");

        JTextField classTextField = new JTextField();
        JTextField subjectTextField = new JTextField();

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Поиск");

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
    }

    @Override
    protected void createTable() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Id", "ФИО студента", "Предмет", "Итоговая оценка"}){
            @Override
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        gradesTable = new JTable(tableModel);

        gradesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradesTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane tableScroll = new JScrollPane(gradesTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        add(tableScroll, new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));
    }

    @Override
    protected void createBottom() {
        JButton addButton = new JButton("Поставить оценку");
        JButton editButton = new JButton("Изменить оценку");
        JButton deleteButton = new JButton("Удалить оценку");

        add(addButton, new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 20), 0, 0));
        add(editButton, new GridBagConstraints(1, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 20), 0, 0));
        add(deleteButton, new GridBagConstraints(2, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 0), 0, 0));


        JTextField fullNameTextField = new JTextField(50);
        JTextField gradeTextField = new JTextField();


        addButton.addActionListener(listener -> {
            fullNameTextField.setText("");
            gradeTextField.setText("");
            createAddNewEditButtonUI(GradesDao::add, -1, fullNameTextField, gradeTextField);
            update();
        });

        editButton.addActionListener(listener -> {
            int selectedRow = gradesTable.getSelectedRow();
            fullNameTextField.setText((String) gradesTable.getValueAt(selectedRow, 1));
            fullNameTextField.setEnabled(false);
            gradeTextField.setText((String) gradesTable.getValueAt(selectedRow, 3));
            Subject subject = SubjectDao.findByName((String) gradesTable.getValueAt(selectedRow, 2));
            subjectComboBox.getModel().setSelectedItem(subject);

            createAddNewEditButtonUI(GradesDao::update, selectedRow, fullNameTextField, gradeTextField);
            update();
        });

        deleteButton.addActionListener(listener -> {
            Long id = (Long)gradesTable.getValueAt(gradesTable.getSelectedRow(), 0);
            GradesDao.delete(id);
            update();
        });
    }

    private void createAddNewEditButtonUI(Consumer<Grades> function, int selectedRow, JTextField fullNameTextField,
                                          JTextField gradeTextField) {
        JFrame frame = new JFrame();
        JDialog addDialog = new JDialog(frame, "Выставление оценки", true);
        addDialog.setSize(450, 300);
        addDialog.setLocationRelativeTo(null);
        addDialog.setLayout(new GridBagLayout());

        JLabel fullNameLabel = new JLabel("ФИО");
        JLabel subjectTeacherLabel = new JLabel("Предмет");
        JLabel gradeLabel = new JLabel("Оценка");

        JComboBox<Subject> subjectComboBox = new JComboBox<>();
        for(Subject subject : SubjectDao.getAll()) {
            subjectComboBox.addItem(subject);
        }

        JButton addEditTeacherButton = new JButton("Сохранить");

        addEditTeacherButton.addActionListener(listener -> {
            Grades grades = new Grades();
            if(selectedRow != -1) {
                grades.setId((Long) gradesTable.getValueAt(selectedRow, 0));
            }
            grades.setStudentId(findStudentId(fullNameTextField.getText()));
            grades.setSubjectId(((Subject) Objects.requireNonNull(subjectComboBox.getSelectedItem())).getId());
            grades.setMark(Integer.parseInt(gradeTextField.getText()));
            function.accept(grades);
            update();
            frame.dispose();
        });

        addDialog.add(fullNameLabel,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(50, 100, 10, 0), 0, 0));
        addDialog.add(fullNameTextField,new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(50, 0, 10, 100), 0, 0));

        addDialog.add(subjectTeacherLabel,new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        addDialog.add(subjectComboBox,new GridBagConstraints(1, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));

        addDialog.add(gradeLabel,new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        addDialog.add(gradeTextField,new GridBagConstraints(1, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));

        addDialog.add(addEditTeacherButton,new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 50, 100), 0, 0));

        addDialog.setVisible(true);

    }

    public void update() {
        for(Subject subject : SubjectDao.getAll()) {
            subjectComboBox.addItem(subject);
        }
        tableModel.setRowCount(0);
        for (GradesDto grades : GradesDao.getAll()) {
            tableModel.addRow(new Object[]{grades.getId(), grades.getStudentName(), grades.getSubjectName(),
                    grades.getMark()});
        }
    }
}
