package com.kodilla.studentdatabase.views.list;

import com.kodilla.studentdatabase.controller.TeacherController;
import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.domain.TeacherDto;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.TeacherService;
import com.kodilla.studentdatabase.views.MainLayout;
import com.kodilla.studentdatabase.views.list.form.AddNewTeacherForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RolesAllowed({"ADMIN"})
@Route(value="Teacher", layout = MainLayout.class)
@PageTitle("Teachers List | Vaadin CRM")
public class ListViewTeacher extends HorizontalLayout {
    Grid<Teacher> teacherGrid = new Grid<>(Teacher.class);
    TextField filterText = new TextField();
    AddNewTeacherForm form;
    private final TeacherService teacherService;
    private final TeacherController teacherController;

    @Autowired
    public ListViewTeacher(TeacherService teacherService, TeacherController teacherController) {
        this.teacherService = teacherService;
        this.teacherController = teacherController;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        bindFields();

        add(teacherGrid, getContent());
        updateList();
    }

    private Component getContent() {
        VerticalLayout content = new VerticalLayout(teacherGrid, form);
        content.setFlexGrow(2, teacherGrid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        content.add(teacherGrid, form);
        return content;
    }

    private void configureForm() {
        form = new AddNewTeacherForm();
        form.setWidth("25em");
        form.getElement().getStyle().set("margin-left", "auto");
        form.getElement().getStyle().set("margin-right", "0");
    }

    private void configureGrid() {
        teacherGrid.addClassNames("contact-grid");
        teacherGrid.setSizeFull();
        teacherGrid.setColumns("firstName", "lastName", "mail",  "phone");
        teacherGrid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void bindFields() {
        teacherGrid.asSingleSelect().addValueChangeListener(event -> {
            Teacher selectedTeacher = event.getValue();
            if (selectedTeacher != null) {
                form.getFirstName().setValue(selectedTeacher.getFirstName());
                form.getLastName().setValue(selectedTeacher.getLastName());
                form.getEmail().setValue(selectedTeacher.getMail());
                form.getPhone().setValue(selectedTeacher.getPhone());
            } else {
                clearForm();
            }
        });

        form.getSave().addClickListener(event -> {
            if (form.getBinder().isValid()) {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.setFirstName(form.getFirstName().getValue());
                teacherDto.setLastName(form.getLastName().getValue());
                teacherDto.setMail(form.getEmail().getValue());
                teacherDto.setPhone(form.getPhone().getValue());

                try {
                    ResponseEntity<Void> response = teacherController.addTeacher(teacherDto);
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Teacher saved successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to save teacher.");
                    }
                } catch (TeacherNotFoundException e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please fill in all the required fields.");
            }
        });

        form.getDelete().addClickListener(event -> {
            Teacher selectedTeacher = teacherGrid.asSingleSelect().getValue();
            if (selectedTeacher != null) {
                try {
                    ResponseEntity<Void> response = teacherController.deleteTeacher(selectedTeacher.getId());
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Teacher deleted successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to delete teacher.");
                    }
                } catch (Exception e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please select a teacher to delete.");
            }
        });


        form.getClose().addClickListener(event -> clearForm());
    }

    private void clearForm() {
        form.getBinder().setBean(new TeacherDto());
        form.getFirstName().clear();
        form.getLastName().clear();
        form.getEmail().clear();
        form.getPhone().clear();
    }

    private void updateList() {
        teacherGrid.setItems(teacherService.findAllTeachers(filterText.getValue()));
    }
}
