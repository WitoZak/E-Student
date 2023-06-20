package com.kodilla.studentdatabase.views.list.form;

import com.kodilla.studentdatabase.domain.GradeDto;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;

@Getter
public class AddNewGradeForm extends FormLayout {
    private final Binder<GradeDto> binder = new Binder<>(GradeDto.class);
    private final TextField subject = new TextField("Subject Name");
    private final TextField value = new TextField("Grade value");
    private final TextField teacher = new TextField("Teacher lastname");
    private final TextField student = new TextField("Student lastname");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    public AddNewGradeForm() {
        addClassName("add-grade-form");

        add(value,
                subject,
                teacher,
                student,
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
