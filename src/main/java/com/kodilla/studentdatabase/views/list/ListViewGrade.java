package com.kodilla.studentdatabase.views.list;

import com.kodilla.studentdatabase.controller.GradeController;
import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.GradeService;
import com.kodilla.studentdatabase.views.MainLayout;
import com.kodilla.studentdatabase.views.list.form.AddNewGradeForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@RolesAllowed({"ADMIN", "TEACHER"})
@Route(value = "Grade", layout = MainLayout.class)
@PageTitle("Grades List | Vaadin CRM")
public class ListViewGrade extends HorizontalLayout {
    private final GradeService gradeService;
    private final GradeController gradeController;
    private final Grid<GradeDto> gradeGrid = new Grid<>(GradeDto.class);
    private AddNewGradeForm form;

    @Autowired
    public ListViewGrade(GradeService gradeService, GradeController gradeController) {
        this.gradeService = gradeService;
        this.gradeController = gradeController;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        bindFields();

        add(gradeGrid, getContent());
        updateList();
    }

    private Component getContent() {
        VerticalLayout content = new VerticalLayout(gradeGrid, form);
        content.setFlexGrow(2, gradeGrid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        content.add(gradeGrid, form);
        return content;
    }

    private void configureForm() {
        form = new AddNewGradeForm();
        form.setWidth("25em");
        form.getElement().getStyle().set("margin-left", "auto");
        form.getElement().getStyle().set("margin-right", "0");

    }

    private void configureGrid() {
        gradeGrid.addClassNames("contact-grid");
        gradeGrid.setSizeFull();
        gradeGrid.setColumns("gradeTimestamp", "value", "subjectName", "lastName", "studentLastName");
        gradeGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void bindFields() {
        gradeGrid.asSingleSelect().addValueChangeListener(event -> {
            GradeDto selectedGrade = event.getValue();
            if (selectedGrade != null) {
                form.getSubject().setValue(selectedGrade.getSubjectName());
                form.getValue().setValue(selectedGrade.getValue());
                form.getTeacher().setValue(selectedGrade.getLastName());
                form.getStudent().setValue(selectedGrade.getStudentLastName());
            } else {
                clearForm();
            }
        });

        form.getSave().addClickListener(event -> {
            if (form.getBinder().isValid()) {
                GradeDto gradeDto = new GradeDto();
                gradeDto.setValue(form.getValue().getValue());
                gradeDto.setGradeTimestamp(LocalDateTime.now());
                gradeDto.setSubjectName(form.getSubject().getValue());
                gradeDto.setLastName(form.getTeacher().getValue());
                gradeDto.setStudentLastName(form.getStudent().getValue());

                try {
                    ResponseEntity<Void> response = gradeController.addGrade(gradeDto);
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Grade saved successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to save grade.");
                    }
                } catch (SubjectNotFoundException | TeacherNotFoundException | StudentNotFoundException e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please fill in all the required fields.");
            }
        });

        form.getDelete().addClickListener(event -> {
            GradeDto selectedGrade = gradeGrid.asSingleSelect().getValue();
            if (selectedGrade != null) {
                try {
                    ResponseEntity<Void> response = gradeController.deleteGrade(selectedGrade.getId());
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Grade deleted successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to delete grade.");
                    }
                } catch (Exception e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please select a grade to delete.");
            }
        });

        form.getClose().addClickListener(event -> clearForm());
    }

    private void clearForm() {
        form.getBinder().setBean(new GradeDto());
        form.getSubject().clear();
        form.getValue().clear();
        form.getTeacher().clear();
        form.getStudent().clear();
    }

    private void updateList() {
        gradeGrid.setItems(gradeService.getAllGradesDto());
    }
}
