package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase db;

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .setName("BD")
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

    @Test
    public void testConnection() throws SQLException {
        Assertions.assertNotNull(db.getConnection());
    }

    @AfterEach
    public void shutdown() {
        db.shutdown();
    }
}
