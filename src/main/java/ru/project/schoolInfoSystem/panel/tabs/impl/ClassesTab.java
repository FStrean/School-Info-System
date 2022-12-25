package ru.project.schoolInfoSystem.panel.tabs.impl;

import ru.project.schoolInfoSystem.dao.ClassesDao;
import ru.project.schoolInfoSystem.dao.TeachersDao;
import ru.project.schoolInfoSystem.model.Class;
import ru.project.schoolInfoSystem.model.Teacher;
import ru.project.schoolInfoSystem.panel.tabs.PanelTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class ClassesTab extends PanelTab {

    private final JComboBox<Teacher> teachersComboBox;

    public ClassesTab() {
        teachersComboBox = new JComboBox<>();

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

        createTopUI(nameLabel, subjectLabel);
    }


    @Override
    protected void createTable() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Идентификатор", "Номер", "Кабинет", "Руководитель"}){
            @Override
            public java.lang.Class<?> getColumnClass(int columnIndex) {
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

        JTextField numberTextField = new JTextField(50);
        JTextField cabinetTextField = new JTextField(50);

        addButton.addActionListener(listener ->
                createPopUpWindow(ClassesDao::add, -1, numberTextField, cabinetTextField));

        editButton.addActionListener(listener -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1) {
                numberTextField.setText((String) table.getValueAt(selectedRow, 1));
                cabinetTextField.setText((String) table.getValueAt(selectedRow, 2));
                String teacher = (String) table.getValueAt(selectedRow, 3);
                teachersComboBox.setSelectedItem(Arrays.stream(teachersComboBox.getSelectedObjects()).filter(object ->
                        Objects.equals((object).toString(), teacher)
                ));

                createPopUpWindow(ClassesDao::update, selectedRow, numberTextField, cabinetTextField);
                update();
            }
        });

        deleteButton.addActionListener(listener -> {
            Long id = (Long)table.getValueAt(table.getSelectedRow(), 0);
            TeachersDao.delete(id);
            update();
        });
    }


    private void createPopUpWindow(Consumer<Class> function, int selectedRow, JTextField numberTextField,
                                   JTextField cabinetTextField) {
        JFrame frame = new JFrame();
        JDialog addDialog = new JDialog(frame, "Класс", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(null);
        addDialog.setLayout(new GridBagLayout());

        JLabel numberLabel = new JLabel("Номер");
        JLabel cabinetLabel = new JLabel("Кабинет");
        JLabel teacherLabel = new JLabel("Руководитель");


        JButton addEditButton = new JButton("Продолжить");

        addEditButton.addActionListener(listener -> {
            Class schoolClass = new Class();

            if(selectedRow != -1) {
                schoolClass.setId((Long) table.getValueAt(selectedRow, 0));
            }
            schoolClass.setNumber(numberTextField.getText());
            schoolClass.setCabinet(cabinetTextField.getText());
            schoolClass.setTeacherId(((Teacher) Objects.requireNonNull(teachersComboBox.getSelectedItem())).getId());

            function.accept(schoolClass);

            update();
            frame.dispose();
        });

        createPopUpWindowUI(addDialog, addEditButton, numberLabel, numberTextField,
                cabinetLabel, cabinetTextField, teacherLabel, teachersComboBox);
    }

    @Override
    public void update() {
        teachersComboBox.removeAllItems();
        for(Teacher teacher : TeachersDao.getAll()) {
            teachersComboBox.addItem(teacher);
        }
        tableModel.setRowCount(0);
        for (Class schoolClass : ClassesDao.getAll()) {
            Teacher teacher = TeachersDao.get(schoolClass.getTeacherId());
            tableModel.addRow(new Object[]{schoolClass.getId(), schoolClass.getNumber(),
                    schoolClass.getCabinet(), teacher.getTeacherName()});
        }
    }
}
