package src.main.java.ru.project.schoolInfoSystem.panel.tabs;

import src.main.java.ru.project.schoolInfoSystem.panel.TableModel;

import javax.swing.*;
import java.awt.*;

public class TimetableTab extends JPanel {
    public TimetableTab() {
        setLayout(new GridBagLayout());

        JLabel classLabel = new JLabel("Номер класса    ");
        JTextField classTextField = new JTextField();

        JButton searchButton = new JButton("Поиск");

        TableModel tableModel = new TableModel();
        JTable studentsTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(studentsTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        JLabel dayLabel = new JLabel("День недели");
        JLabel timeLabel = new JLabel("Время");
        JLabel subjectLabel = new JLabel("Предмет");
        JLabel teacherLabel = new JLabel("Учитель");
        JLabel classRoomLabel = new JLabel("Кабинет");

        JTextField dayTextField = new JTextField();
        JTextField timeTextField = new JTextField();
        JTextField subjectTextField = new JTextField();
        JTextField teacherTextField = new JTextField();
        JTextField classRoomTextField = new JTextField();

        JButton addButton = new JButton("Добавить урок");

        add(classLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 100, 10, 10), 0, 0));
        add(classTextField, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, -50, 10, 10), 0, 0));
        add(searchButton, new GridBagConstraints(2, 0, 2, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 10, 100), 0, 0));

        add(tableScroll, new GridBagConstraints(0, 1, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));

        add(dayLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        add(dayTextField, new GridBagConstraints(1, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));

        add(timeLabel, new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 0), 0, 0));
        add(timeTextField, new GridBagConstraints(1, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 0), 0, 0));

        add(subjectLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 20, 0), 0, 0));
        add(subjectTextField, new GridBagConstraints(1, 4, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 20, 0), 0, 0));

        add(teacherLabel, new GridBagConstraints(2, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 20, 10, 0), 0, 0));
        add(teacherTextField, new GridBagConstraints(3, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 20), 0, 0));

        add(classRoomLabel, new GridBagConstraints(2, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 20, 10, 0), 0, 0));
        add(classRoomTextField, new GridBagConstraints(3, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, -50, 10, 20), 0, 0));

        add(addButton, new GridBagConstraints(5, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 20, 0, 100), 0, 0));

    }
}
