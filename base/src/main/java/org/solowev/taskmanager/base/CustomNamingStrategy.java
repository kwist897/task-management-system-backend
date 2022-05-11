package org.solowev.taskmanager.base;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Кастомный класс для преобразования названий таблиц и столбцов из camelCase
 */
public class CustomNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
    /**
     * Префикс для таблиц
     */
    private static final String TABLE_PREFIX = "tmp_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        String tableName = TABLE_PREFIX + identifier.getText();
        return new Identifier(tableName, identifier.isQuoted());
    }
}
