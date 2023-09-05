package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;
    private Connection connection;
    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        connection = dataSource.getConnection();
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser;");
            while (rs.next()) {
                if (id.equals(rs.getLong(1))) {
                    return Optional.of(new User(rs.getLong(1), rs.getString(2)));
                }
            }

        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public List findAll() {
        List<User> products;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser");
            products = new ArrayList<>();
            while (rs.next()) {
                products.add(new User(rs.getLong(1), rs.getString(2)));
            }
            statement.close();
            return products;
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;

    }

    @Override
    public void save(Object entity){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser");
            while (rs.next()) {
                if (rs.getLong(1) == ((User)entity).getId()) {
                    throw new RuntimeException("Incorrect id");
                }
            }
            statement.execute("INSERT INTO usser(id, email)\n" +
                    "VALUES\t(" + ((User)entity).getId() + ", '" + ((User)entity).getEmail() + "'); ");
            statement.close();
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void update(Object entity) throws SQLException {
        try {
            boolean info = true;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser");
            while (rs.next()) {
                if (rs.getLong(1) == ((User)entity).getId()) {
                    statement.execute("UPDATE usser SET email = '" +  ((User)entity).getEmail() +
                            "' WHERE id = " + ((User)entity).getId() + ";");
                    info = false;
                    return ;
                }
            }
            if (info)
                throw new RuntimeException("Incorrect id");
            statement.close();
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            boolean info = true;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser");
            while (rs.next()) {
                if (rs.getLong(1) == id) {
                    statement.execute("DELETE FROM usser WHERE id = " + id);
                    info = false;
                }
            }
            if (info)
                throw new RuntimeException("Incorrect id");
            statement.close();
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public Optional findByEmail(String email) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usser;");
            while (rs.next()) {
                if (email.equals(rs.getString(2))) {
                    return Optional.of(new User(rs.getLong(1), rs.getString(2)));
                }
            }
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return Optional.empty();
    }
}
