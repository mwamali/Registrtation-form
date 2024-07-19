# Java Swing Registration Form

This project implements a simple registration form using Java Swing with database integration.

## Description

This application provides a graphical user interface for user registration. It allows users to input their details, stores the information in a MySQL database, and displays the registered users in a table.

Features:
- User-friendly GUI built with Java Swing
- Data persistence using MySQL database
- Real-time display of registered users

## Getting Started

### Dependencies

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)

### Installing

1. Clone the repository:
   ```
   git clone https://github.com/your-username/registration-form.git
   ```
2. Set up your MySQL database:
   - Create a new database named `registration_db`
   - Create a table named `users` with the following schema:
     ```sql
     CREATE TABLE users (
         id VARCHAR(50) PRIMARY KEY,
         name VARCHAR(100),
         gender VARCHAR(10),
         address VARCHAR(200),
         contact VARCHAR(50)
     );
     ```

3. Update the database connection details in `RegistrationForm.java`:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/registration_db";
   private static final String DB_USER = "your_mysql_username";
   private static final String DB_PASSWORD = "your_mysql_password";
   ```

### Executing program

- Compile and run the `RegistrationForm.java` file in your Java IDE or from the command line.

## Help

Common issues:
- If you encounter a `ClassNotFoundException`, ensure the MySQL JDBC driver is in your classpath.
- For database connection issues, verify your MySQL server is running and the connection details are correct.

## Authors

Brian Mtana

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
* [MySQL Java Tutorial](https://dev.mysql.com/doc/connector-j/en/)
```

To use this README:

1. Copy the content above.
2. Create a new file named `README.md` in the root directory of your GitHub repository.
3. Paste the content into this file.
4. Customize the following sections:
   - Update the repository URL in the "Installing" section.
   - Add your name in the "Authors" section.
   - Modify the "License" section if you're using a specific license.
   - Add any relevant acknowledgments or remove the section if not needed.

