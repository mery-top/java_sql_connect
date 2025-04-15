### Patient Management System (Java + MySQL)

This is a basic Java application to manage patient records using a MySQL database.  

### Set up the Database:
   ```bash
   CREATE DATABASE your_database_name;
    USE your_database_name;
    
    CREATE TABLE patients (
        name VARCHAR(100),
        gender VARCHAR(100),
        dob VARCHAR(50),
        age INT,
        phone INT,
        address VARCHAR(250),
        disease VARCHAR(100)
    );

   ```

### Features

- Add new patient records
- View all stored patients
- Update existing patient data
- Delete patient entries

