package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM usser WHERE id = " + id,
                (resultSet, rowNum) -> {
                    return new User(resultSet.getLong(1), resultSet.getString(2));
                }));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM usser",
                (resultSet, rowNum) -> {
            return new User(resultSet.getLong(1), resultSet.getString(2));
        });
    }

    @Override
    public void save(Object entity) {
        jdbcTemplate.update("insert into usser (id, email) values (?, ?)",
                ((User)entity).getId(), ((User)entity).getEmail());
    }

    @Override
    public void update(Object entity) {
        jdbcTemplate.update("update usser SET email = ? WHERE id = ?",
                ((User)entity).getEmail(), ((User)entity).getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "delete from usser where id = ?", id);
    }

    @Override
    public Optional findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM usser WHERE email = " + email,
                (resultSet, rowNum) -> {
                    return new User(resultSet.getLong(1), resultSet.getString(2));
                }));
    }
}
