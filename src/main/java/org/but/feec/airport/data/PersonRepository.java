package org.but.feec.airport.data;

import org.but.feec.airport.api.*;
import org.but.feec.airport.config.DataSourceConfig;
import org.but.feec.airport.exceptions.DataAccessException;
/**PreparedStatement is parametrized and reusable SQL query which forces the developer to write the SQL command and user-provided
data separately. The SQL query is then provided safely without risk of SQL injection.**/
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    public PersonAuthView findPersonByUsername(String username) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT username, password" +
                             " FROM security_staff s" +
                             " WHERE s.username = ?")
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }
    private PersonAuthView mapToPersonAuth(ResultSet rs) throws SQLException {
       PersonAuthView person = new PersonAuthView();
       person.setUsername(rs.getString("username"));
       person.setPassword(rs.getString("password"));
       return person;
    }
    public PersonDetailView findPersonDetailedView(Long personId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, first_name, last_name, passport_number, country_of_residence, country_of_citizenship, check_results" +
                             " FROM passenger p" +
                             " LEFT JOIN security_check s ON p.id = s.passenger_id" +
                             " WHERE p.id = ?")
        ) {
            preparedStatement.setLong(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public List<PersonBasicView> getPersonBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, first_name, last_name, passport_number, country_of_residence " +
                             "FROM passenger");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<PersonBasicView> personBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                personBasicViews.add(mapToPersonBasicView(resultSet));
            }
            return personBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);
        }
    }
        public void createPerson(PersonCreateView personCreateView) {
        String insertPersonSQL = "INSERT INTO public.passenger (first_name, last_name, check_results) VALUES (?,?,?)";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setString(1, personCreateView.getFirst_name());
            preparedStatement.setString(2, personCreateView.getLast_name());
            preparedStatement.setString(3, personCreateView.getSecurity_check());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

    public void editPerson(PersonEditView personEditView) {
        String insertPersonSQL = "UPDATE passenger SET id = ?, first_name = ?, last_name = ?, passport_number = ?, country_of_residence = ? WHERE id= ?";
        String checkIfExists = "SELECT passport_number FROM passenger WHERE id = ?";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setString(1, personEditView.getFirst_name());
            preparedStatement.setString(2, personEditView.getLast_name());
            preparedStatement.setString(3, personEditView.getPassport_number());
            preparedStatement.setString(4, personEditView.getCountry_of_residence());
            preparedStatement.setLong(5, personEditView.getId());

            try {
            connection.setAutoCommit(false);
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, personEditView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This person for edit does not exist.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating person failed, no rows affected.");
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            } finally {
            connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }


    private PersonBasicView mapToPersonBasicView(ResultSet rs) throws SQLException {
        PersonBasicView personBasicView = new PersonBasicView();
        personBasicView.setId(rs.getLong("id"));
        personBasicView.setFirst_name(rs.getString("first_name"));
        personBasicView.setLast_name(rs.getString("last_name"));
        personBasicView.setPassport_number(rs.getString("passport_number"));
        personBasicView.setCountry_of_residence(rs.getString("country_of_residence"));
        return personBasicView;
    }

    private PersonDetailView mapToPersonDetailView(ResultSet rs) throws SQLException {
        PersonDetailView personDetailView = new PersonDetailView();
        personDetailView.setId(rs.getLong("id"));
        personDetailView.setFirst_name(rs.getString("first_name"));
        personDetailView.setLast_name(rs.getString("last_name"));
        personDetailView.setPassport_number(rs.getString("passport_number"));
        personDetailView.setCountry_of_residence(rs.getString("country_of_residence"));
        personDetailView.setCountry_of_citizenship(rs.getString("country_of_citizenship"));
        personDetailView.setCheck_results(rs.getString("check_results"));
        return personDetailView;
    }

}


