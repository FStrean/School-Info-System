package ru.project.schoolInfoSystem.panel.tabs.impl;

import org.jdatepicker.JDatePicker;
import ru.project.schoolInfoSystem.dao.ClassesDao;
import ru.project.schoolInfoSystem.dao.StudentsDao;
import ru.project.schoolInfoSystem.dao.TeachersDao;
import ru.project.schoolInfoSystem.model.Class;
import ru.project.schoolInfoSystem.model.Student;
import ru.project.schoolInfoSystem.panel.tabs.PanelTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.function.Consumer;
public class StudentsTab extends PanelTab {

    private final JComboBox<Class> classComboBox;

    public StudentsTab() {
        classComboBox = new JComboBox<>();

        setLayout(new GridBagLayout());

        createTop();

        createTable();

        createBottom();

        update();
    }


    @Override
    protected void createTop() {
        JLabel nameLabel = new JLabel("ФИО");
        JLabel subjectLabel = new JLabel("Номер класса");

        createTopUI(nameLabel, subjectLabel);
    }


    @Override
    protected void createTable() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Идентификатор", "ФИО", "Дата рождения", "Адрес", "ФИО родителя", "Номер телефона", "Класс"}){
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

        JTextField studentName = new JTextField(50);
        JDatePicker birthdayDatePicker = new JDatePicker();
        birthdayDatePicker.setTextEditable(true);
        JTextField address = new JTextField(50);
        JTextField parentName = new JTextField(50);
        JTextField phoneNumber = new JTextField(50);

        addButton.addActionListener(listener ->
                createPopUpWindow(StudentsDao::add, -1, studentName, birthdayDatePicker, address,
                        parentName, phoneNumber));

        editButton.addActionListener(listener -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                studentName.setText((String) table.getValueAt(selectedRow, 1));

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(((Date) table.getValueAt(selectedRow, 2)));

                birthdayDatePicker.getModel().setYear(calendar.get(Calendar.YEAR));
                birthdayDatePicker.getModel().setMonth(calendar.get(Calendar.MONTH));
                birthdayDatePicker.getModel().setDay(calendar.get(Calendar.DAY_OF_MONTH));
                birthdayDatePicker.getModel().setSelected(true);

                address.setText((String) table.getValueAt(selectedRow, 3));
                parentName.setText((String) table.getValueAt(selectedRow, 4));
                phoneNumber.setText((String) table.getValueAt(selectedRow, 5));

                /*
                 *TODO: check work of this part (if student's class will be in combobox when i try to edit this student)
                 */
                String schoolClass = (String) table.getValueAt(selectedRow, 6);
                classComboBox.setSelectedItem(Arrays.stream(classComboBox.getSelectedObjects()).filter(object ->
                        Objects.equals((object).toString(), schoolClass)
                ));

                createPopUpWindow(StudentsDao::update, selectedRow, studentName, birthdayDatePicker,
                        address, parentName, phoneNumber);
                update();
            }
        });

        deleteButton.addActionListener(listener -> {
            Long id = (Long)table.getValueAt(table.getSelectedRow(), 0);
            TeachersDao.delete(id);
            update();
        });
    }


    private void createPopUpWindow(Consumer<Student> function, int selectedRow, JTextField studentNameTextField,
                                   JDatePicker birthdayDatePicker, JTextField addressTextField,
                                   JTextField parentNameTextField, JTextField phoneNumberTextField) {
        JFrame frame = new JFrame();
        JDialog addDialog = new JDialog(frame, "Ученик", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(null);
        addDialog.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("ФИО");
        JLabel birthdateLabel = new JLabel("Дата рождения");
        JLabel addressLabel = new JLabel("Адрес");
        JLabel parentNameLabel = new JLabel("ФИО родителя");
        JLabel phoneNumberLabel = new JLabel("Номер телефона");
        JLabel classLabel = new JLabel("Класс");


        JButton addEditButton = new JButton("Продолжить");

        addEditButton.addActionListener(listener -> {
            Student student = new Student();

            if(selectedRow != -1) {
                student.setId((Long) table.getValueAt(selectedRow, 0));
            }
            student.setStudentName(studentNameTextField.getText());
            student.setBirthdate(new Date(((GregorianCalendar)birthdayDatePicker.getModel().getValue()).getTimeInMillis()));
            student.setAddress(addressTextField.getText());
            student.setParentName(parentNameTextField.getText());
            student.setPhoneNumber(phoneNumberTextField.getText());
            student.setClassId(((Class) Objects.requireNonNull(classComboBox.getSelectedItem())).getId());

            function.accept(student);

            update();
            frame.dispose();
        });

        createPopUpWindowUI(addDialog, addEditButton, nameLabel, studentNameTextField,
                birthdateLabel, birthdayDatePicker, addressLabel, addressTextField,
                parentNameLabel, parentNameTextField, phoneNumberLabel, phoneNumberTextField,
                classLabel, classComboBox);
    }

    @Override
    public void update() {
        classComboBox.removeAllItems();
        for(Class schoolClass : ClassesDao.getAll()) {
            classComboBox.addItem(schoolClass);
        }
        tableModel.setRowCount(0);
        for (Student student : StudentsDao.getAll()) {
            Class schoolClass = ClassesDao.get(student.getClassId());
            tableModel.addRow(new Object[]{student.getId(), student.getStudentName(),
                    student.getBirthdate(), student.getAddress(), student.getParentName(),
            student.getPhoneNumber(), schoolClass.getNumber()});
        }
    }
}
