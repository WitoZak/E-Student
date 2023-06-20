package com.kodilla.studentdatabase.views.list;

import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.service.GradeService;
import com.kodilla.studentdatabase.views.MainLayout;
import com.kodilla.studentdatabase.views.list.form.ApiForStudentsForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@RolesAllowed({"ADMIN", "STUDENT"})
@Route(value = "StudentOverview", layout = MainLayout.class)
@PageTitle("StudentOverview | Vaadin CRM")
public class ListViewStudentOverview extends HorizontalLayout {

    private final GradeService gradeService;
    private Grid<GradeDto> gradeGrid = new Grid<>(GradeDto.class);
    private ApiForStudentsForm form;

    @Autowired
    public ListViewStudentOverview(GradeService gradeService) {
        this.gradeService = gradeService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(gradeGrid, getContent());
        updateList();
    }

    private Component getContent() {
        VerticalLayout content = new VerticalLayout(gradeGrid, form);
        content.setFlexGrow(2, gradeGrid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        content.expand(gradeGrid);
        return content;
    }

    private void configureForm() {
        form = new ApiForStudentsForm();
        form.setWidth("30%");
        form.getElement().getStyle().set("margin-left", "auto");
        form.getElement().getStyle().set("margin-right", "0");

    }

    private void configureGrid() {
        gradeGrid.addClassNames("contact-grid");
        gradeGrid.setSizeFull();
        gradeGrid.setColumns("gradeTimestamp", "value", "subjectName", "lastName");
        gradeGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        gradeGrid.setItems(gradeService.getAllGradesDto());
    }
}
