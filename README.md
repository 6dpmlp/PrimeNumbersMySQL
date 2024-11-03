PrimeNumbersMySQL
This Java program calculates prime numbers up to a user-specified limit, interacts with a MySQL database to store these primes, and saves the data to a file. Specifically, the program:

    Prompts the user to input a number, then calculates all prime numbers up to that limit.
    Deletes an existing MySQL table if present.
    Inserts the calculated prime numbers into the database table.
    Reads back the primes from the table, reverses their order, and writes them to a text file.

Features

    Prime Number Calculation: Calculates primes up to a user-defined limit.
    Database Integration:
        Deletes an existing database table to avoid duplicate data.
        Inserts prime numbers into the table.
        Retrieves primes from the table for further processing.
    File Output: Saves the prime numbers in reverse order to a text file.

Requirements

    JDK (Java Development Kit) version 8 or higher.
    MySQL Database server with a table for storing prime numbers.
    MySQL JDBC Driver (mysql-connector-java.jar) included in the project classpath.

Setup Instructions

    Database Configuration: Ensure a MySQL database is created (e.g., prime_numbers_db).
        Table Structure: Create a table called prime_numbers in the database with the following SQL command:

        sql

    CREATE TABLE prime_numbers (
        number INT NOT NULL
    );

Database Credentials: properties file attached to this repo.

Usage

    Run the program, which will prompt you to enter an integer to define the upper limit for prime numbers.
    The program will:
        Calculate primes up to the specified limit.
        Delete the existing primes table in the MySQL database.
        Insert each prime number into the table.
        Retrieve the primes, reverse the order, and save them to output.txt in the project directory.

Example Command-Line Output

plaintext

Please enter a positive integer not bigger than 10 000 000: 999999

Deletion of table
Deletion has performed!

Insertion to table
Insertion has performed!

Reading data from SQL
Reading has performed!

Thank you for using this program!
Now you have the numbers listed in primes.txt.
Have a nice day!

File Output

The file primes.txt will contain the prime numbers in reverse order, for example:

Prime numbers:
    47
    43
    41
    37
    31
    29
    23
    19
    17
    13
    11
     7
     5
     3
     2
