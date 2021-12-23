This is the implementation of the semestral project for subject Bezpečnost databázových systémů, supervised by Mgr.Ing.Pavel Šeda. The project is licensed under the MIT license, the full wording of the license can be seen here https://opensource.org/licenses/MIT. The application is build on PostgreSQL database, that deals with airport maintenance. Project is using Java version 11.0.12 and Maven version 3.8.4. GUI was created using JavaFX Scene Builder. Furthermore, application uses BCrypt for hashing the passwords. All dependencies necessary for building, compiling, running and proper working of the programme can be found in a single file called pom.xml. File Gitignore informs Git which files to ignore when commiting to GitHub. Directory config contains class DataSourceConfig that contains the Hikari config that provides a connection pooling mechanism, that promises to be lightweight. Directory controllers contains the implementation of each fxml file. Directory data contains class PersonRepository with methods for editing and creating persons. Directory exceptions handles exceptions the can occur throghout the programme. Fxml files are stored in resources in fxml directory. Application properties contain information needed for database connection.
Steps for building the project using command line: 1) git clone https://github.com/ArielaStastna/bds-airport-1/
                                                   2) cd bds-airport-1 
                                                   3) mvn clean install
                                                   4) cd target
                                                   5) java -jar bds-airport-1.0.0.jar
