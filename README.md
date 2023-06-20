***E-STUDENT*** 

**Project for Kodilla Java Developer course**

*Project Description:*

![MainLayout](https://github.com/WitoZak/E-Student/assets/113088417/381557bb-3819-4354-9066-9e852ac640c5)

This project is a simple clone of an electronic school register system, similar to popular platforms like Librus, with some of its core functionalities. The application starts with a login screen, and there are three types of users: ADMIN, TEACHER, and STUDENT. Each user type has limited access and functionality, with the exception of the ADMIN user. 

![LogIn](https://github.com/WitoZak/E-Student/assets/113088417/a681c0ff-79b1-4ae9-8c45-ecd1161529b5)

- TEACHER: Teachers have access to a list of grades and can use a form to add and edit grades.

![GradesList](https://github.com/WitoZak/E-Student/assets/113088417/15a4d170-72df-46af-862d-d3a57ec0eb1b)

- STUDENT: Students can view their own grades and have access to the current time and weather forecast.

![StudentOverviewList](https://github.com/WitoZak/E-Student/assets/113088417/14fae0d9-27c7-4ccd-a393-db166173fc8d)

- ADMIN: In addition to the functionalities available to TEACHER and STUDENT users, the ADMIN user has the ability to add new Students, Teachers, Subjects, and Group names.

![TeacherList](https://github.com/WitoZak/E-Student/assets/113088417/3a064aa9-bcfe-46e3-b4b7-ea19e8e95a8e)
![StudentList](https://github.com/WitoZak/E-Student/assets/113088417/89aae742-ae7b-48b5-b6cb-cc22ebbfae64)

The application also includes a scheduler feature that sends a randomly generated "lucky number" to users every day.

Please note that this project is a work in progress and is not yet complete. Until the backend functionality is finalized, the frontend is not developed as a separate project, making it easier to test and incorporate new features.

*Upcoming Changes:*

In the future development of this project, the following changes and enhancements are planned:

- Adding combo boxes in appropriate areas of the application to provide users with predefined options and improve usability.
- Integrating an additional API or libraries to expand the application's capabilities.
- Implementing the ability to edit existing entries, allowing users to modify information as needed.
- Implementing role-based authentication to enable secure login and access based on user roles.
- Adding the functionality for the ADMIN user to create new Subjects and Groups.
- Separating the backend and frontend components and establishing communication through REST API for better separation of concerns and scalability.

*Technologies and Tools Used:*

- Backend:
  - Java - programming language
  - Spring Boot - framework for building Java applications
  - Gradle - build automation and dependency management tool
  - Spring Data JPA - persistence framework for database operations
  - MySQL - relational database for data storage
  - Lombok - library for reducing boilerplate code
  - Mailtrap - email testing and debugging service for development environments
  - JUnit 5 - testing framework for unit testing

![Test](https://github.com/WitoZak/E-Student/assets/113088417/4147ac52-1c4b-462e-b27a-af49136e0cf5)


- Frontend:
  - Vaadin - Java framework for building web applications with a rich user interface
  - Bootstrap - CSS framework for responsive web design
  - JavaScript libraries (e.g., jQuery) - for DOM manipulation and interaction

- API and Libraries:
  - Spring Web - module for building web applications with Spring
  - Spring Security - framework for authentication and authorization
  - REST API - for communication between frontend and backend
  - Git - version control system for collaboration and code management

