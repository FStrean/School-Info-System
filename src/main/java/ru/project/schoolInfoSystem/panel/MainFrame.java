package ru.project.schoolInfoSystem.panel;

import ru.project.schoolInfoSystem.panel.tabs.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final String title;
    private final Dimension d;


    public MainFrame(String title, Dimension d) {
        this.title = title;
        this.d = d;
    }

    public void init() {
        setTitle(title);
        setSize(d);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        getContentPane().add(tabs);

        StudentsTab studentsTab = new StudentsTab();
        TeachersTab teachersTab = new TeachersTab();
        ClassesTab classesTab = new ClassesTab();
        TimetableTab timetableTab = new TimetableTab();
        ReportTab reportTab = new ReportTab();
        GradesTab gradesTab = new GradesTab();

        tabs.addTab("Ученики", studentsTab);
        tabs.addTab("Учителя", teachersTab);
        tabs.addTab("Классы", classesTab);
        tabs.addTab("Расписание", timetableTab);
        tabs.addTab("Отчет", reportTab);
        tabs.addTab("Оценки", gradesTab);


        setVisible(true);
    }
}
