package com.kodilla.studentdatabase.views.list;

import com.kodilla.studentdatabase.controller.StudentController;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.StudentService;
import com.kodilla.studentdatabase.views.MainLayout;
import com.kodilla.studentdatabase.views.list.form.AddNewStudentForm;
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

@RolesAllowed({"ADMIN"})
@Route(value = "Student", layout = MainLayout.class)
@PageTitle("Students List | Vaadin CRM")
public class ListViewStudent extends HorizontalLayout {
    private final StudentService studentService;
    private final StudentController studentController;
    private final Grid<StudentDto> studentGrid = new Grid<>(StudentDto.class);
    private AddNewStudentForm form;

    @Autowired
    public ListViewStudent(StudentService studentService, StudentController studentController) {
        this.studentService = studentService;
        this.studentController = studentController;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        bindFields();

        add(studentGrid, getContent());
        updateList();
    }

    private Component getContent() {
        VerticalLayout content = new VerticalLayout(studentGrid, form);
        content.setFlexGrow(2, studentGrid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        content.add(studentGrid, form);
        return content;
    }

    private void configureForm() {
        form = new AddNewStudentForm();
        form.setWidth("25em");
        form.getElement().getStyle().set("margin-left", "auto");
        form.getElement().getStyle().set("margin-right", "0");
    }

    private void configureGrid() {
        studentGrid.addClassNames("contact-grid");
        studentGrid.setSizeFull();
        studentGrid.setColumns("firstName", "studentLastName", "address", "dateOfBirth", "groupName", "logNumber", "mail", "phone");
        studentGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void bindFields() {
        studentGrid.asSingleSelect().addValueChangeListener(event -> {
            StudentDto selectedStudent = event.getValue();
            if (selectedStudent != null) {
                form.getFirstName().setValue(selectedStudent.getFirstName());
                form.getLastName().setValue(selectedStudent.getStudentLastName());
                form.getAddress().setValue(selectedStudent.getAddress());
                form.getDateOfBirth().setValue(selectedStudent.getDateOfBirth());
                form.getGroupName().setValue(selectedStudent.getGroupName());
                form.getLogNumber().setValue(selectedStudent.getLogNumber());
                form.getEmail().setValue(selectedStudent.getMail());
                form.getPhone().setValue(selectedStudent.getPhone());
            } else {
                clearForm();
            }
        });

        form.getSave().addClickListener(event -> {
            if (form.getBinder().isValid()) {
                StudentDto studentDto = new StudentDto();
                studentDto.setFirstName(form.getFirstName().getValue());
                studentDto.setStudentLastName(form.getLastName().getValue());
                studentDto.setAddress(form.getAddress().getValue());
                studentDto.setDateOfBirth(form.getDateOfBirth().getValue());
                studentDto.setGroupName(form.getGroupName().getValue());
                studentDto.setLogNumber(form.getLogNumber().getValue());
                studentDto.setMail(form.getEmail().getValue());
                studentDto.setPhone(form.getPhone().getValue());

                try {
                    ResponseEntity<Void> response = studentController.addStudent(studentDto);
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Student saved successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to save student.");
                    }
                } catch (TeacherNotFoundException | GradeNotFoundException | SubjectNotFoundException e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please fill in all the required fields.");
            }
        });

        form.getDelete().addClickListener(event -> {
            StudentDto selectedStudent = studentGrid.asSingleSelect().getValue();
            if (selectedStudent != null) {
                try {
                    ResponseEntity<Void> response = studentController.deleteStudent(selectedStudent.getId());
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Notification.show("Student deleted successfully.");
                        updateList();
                        clearForm();
                    } else {
                        Notification.show("Error: Failed to delete student.");
                    }
                } catch (Exception e) {
                    Notification.show("Error: " + e.getMessage());
                }
            } else {
                Notification.show("Please select a student to delete.");
            }
        });

        form.getClose().addClickListener(event -> clearForm());
    }

    private void clearForm() {
        form.getBinder().setBean(new StudentDto());
        form.getLogNumber().clear();
        form.getFirstName().clear();
        form.getLastName().clear();
        form.getDateOfBirth().clear();
        form.getAddress().clear();
        form.getEmail().clear();
        form.getPhone().clear();
        form.getGroupName().clear();


    }

    private void updateList() { studentGrid.setItems(studentService.findAllStudentsDto());
    }
}
