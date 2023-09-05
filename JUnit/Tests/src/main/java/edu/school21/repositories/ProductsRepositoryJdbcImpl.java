package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private EmbeddedDatabase db;
    ProductsRepositoryJdbcImpl() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products;
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM store.product");
            products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(rs.getLong(1), rs.getString(2),
                        rs.getDouble(3)));
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
    public Optional<Product> findById(Long id) {
        Optional<Product> products;
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM store.product");
            while (rs.next()) {
                if (rs.getLong(1) == id) {
                    statement.close();
                    return Optional.of(new Product(rs.getLong(1), rs.getString(2),
                            rs.getDouble(3)));
                }
            }

        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try {
            boolean info = true;
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM store.product");
            while (rs.next()) {
                if (rs.getLong(1) == product.getId()) {
                    statement.execute("UPDATE store.product SET name = \'" +  product.getName()  +
                            "\', price = " + product.getPrice() + " WHERE id = " + product.getId() + ";");
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
    public void save(Product product) {
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM store.product");
            while (rs.next()) {
                if (rs.getLong(1) == product.getId()) {
                    throw new RuntimeException("Incorrect id");
                }
            }

            statement.execute("INSERT INTO Store.Product(id, name, price)\n" +
                    "VALUES\t(" + product.getId() + ", '" + product.getName() + "', " + product.getPrice() + ");");
            statement.close();
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            boolean info = true;
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM store.product");
            while (rs.next()) {
                if (rs.getLong(1) == id) {
                    statement.execute("DELETE FROM store.product WHERE id = " + id);
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

}
