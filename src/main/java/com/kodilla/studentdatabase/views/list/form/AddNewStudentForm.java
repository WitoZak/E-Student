package com.kodilla.studentdatabase.views.list.form;

import com.kodilla.studentdatabase.domain.StudentDto;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;

@Getter
public class AddNewStudentForm extends FormLayout {
    private final Binder<StudentDto> binder = new Binder<>(StudentDto.class);
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField address = new TextField("Address");
    private final TextField dateOfBirth = new TextField("Date of birth");
    private final TextField groupName = new TextField("Group name");
    private final IntegerField logNumber = new IntegerField( "Log number");
    private final EmailField email = new EmailField("Email");
    private final IntegerField phone = new IntegerField("Phone");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    public AddNewStudentForm() {
        addClassName("student-form");

        add(firstName,
                lastName,
                address,
                dateOfBirth,
                groupName,
                logNumber,
                email,
                phone,
                createButtonsLayout());
    }


    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);


        return new HorizontalLayout(save, delete, close);
    }

}