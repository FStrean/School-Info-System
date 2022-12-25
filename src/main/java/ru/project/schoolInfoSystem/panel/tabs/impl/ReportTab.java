package ru.project.schoolInfoSystem.panel.tabs.impl;

import ru.project.schoolInfoSystem.dao.ReportDao;
import ru.project.schoolInfoSystem.model.Report;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.*;
import java.sql.Date;
import java.text.DecimalFormat;

import static ru.project.schoolInfoSystem.dao.ReportDao.*;


public class ReportTab extends JPanel {

    private JTable reportsTable;
    private DefaultTableModel tableModel;

    public ReportTab() {
        setLayout(new GridBagLayout());

        createTopUI();

        createMiddleUI();

        createBottomUI();

        update();
    }

    private void createTopUI() {
        JLabel dateLabel = new JLabel("Дата составления");
        JTextField dateTextField = new JTextField();

        JButton searchButton = new JButton("Поиск");

        add(dateLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 100, 10, 10), 0, 0));
        add(dateTextField, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, -50, 10, 10), 0, 0));
        add(searchButton, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 10, 100), 0, 0));
    }

    private void createMiddleUI() {
        tableModel = new DefaultTableModel(new String[][]{}, new String[]
                {"Id", "Дата", "Всего учеников", "Всего учителей", "Кабинеты", "Срений балл", "Отличники", "Хорошисты", "Троечники", "Двоечники"}){
            @Override
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reportsTable = new JTable(tableModel);

        reportsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reportsTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane tableScroll = new JScrollPane(reportsTable);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        add(tableScroll, new GridBagConstraints(0, 1, 6, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 100, 20, 100), 0, 0));

    }

    private void createBottomUI() {
        JButton generateButton = new JButton("Сгенерировать отчет");
        JButton printButton = new JButton("Напечатать отчет");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");

        add(generateButton, new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 100, 10, 10), 0, 0));
        add(printButton, new GridBagConstraints(1, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(editButton, new GridBagConstraints(2, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 10), 0, 0));
        add(deleteButton, new GridBagConstraints(3, 3, 1, 1, 1, 0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 100), 0, 0));




        deleteButton.addActionListener(listener -> {
            Long id = (Long)reportsTable.getValueAt(reportsTable.getSelectedRow(), 0);
            delete(id);
            update();
        });

        generateButton.addActionListener(listener -> {
            int selectedRow = reportsTable.getSelectedRow();

            long millis=System.currentTimeMillis();
            Date nowDate = new Date(millis);

            Report report = new Report();
            int studentsCount = generateStudentsCount();
            int teachersCount = generateTeachersCount();
            int classesCount = generateClassesCount();
            int classroomCounts = 10 + (int) (Math.random() * 40);

            float averageGrades = generateAverageGrade();

            int excellentCount = generateExcellentCount();
            int goodCount = generateGoodCount();
            int failureCount = generateFailureCount();
            int underachieverCount = generateUnderachieverCount();

            if(selectedRow != -1) {
                report.setId((Long) reportsTable.getValueAt(selectedRow, 0));
            }
            report.setReportDate(nowDate);
            report.setStudentsCount(studentsCount);
            report.setTeachersCount(teachersCount);
            report.setClassesCount(classesCount);
            report.setClassroomCount(classroomCounts);
            report.setAverageGrades(averageGrades);
            report.setExcellentCount(excellentCount);
            report.setGoodCount(goodCount);
            report.setFailureCount(failureCount);
            report.setUnderachieverCount(underachieverCount);

            ReportDao.generate(report);
            update();
        });
    }

    public void update() {
        tableModel.setRowCount(0);
        for (Report report : ReportDao.getAll()) {
            tableModel.addRow(new Object[]{report.getId(), report.getReportDate(), report.getStudentsCount(), report.getTeachersCount(),
                    report.getClassesCount(), report.getAverageGrades(), report.getExcellentCount(), report.getGoodCount(),
            report.getFailureCount(), report.getUnderachieverCount()});
        }
    }
}
