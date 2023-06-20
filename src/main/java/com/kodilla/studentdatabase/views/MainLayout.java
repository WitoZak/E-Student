package com.kodilla.studentdatabase.views;

import com.kodilla.studentdatabase.security.SecurityService;
import com.kodilla.studentdatabase.views.list.ListViewGrade;
import com.kodilla.studentdatabase.views.list.ListViewStudent;
import com.kodilla.studentdatabase.views.list.ListViewStudentOverview;
import com.kodilla.studentdatabase.views.list.ListViewTeacher;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value="main")
@PageTitle("Main Page | Vaadin E-Student")
public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Welcome To E-Student");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE, 
            LumoUtility.Margin.MEDIUM);

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout( 
                new RouterLink("Students List", ListViewStudent.class),
                new RouterLink("Teachers List", ListViewTeacher.class),
                new RouterLink("Grades List", ListViewGrade.class),
                new RouterLink("Student Overview", ListViewStudentOverview.class)
        ));
    }
}