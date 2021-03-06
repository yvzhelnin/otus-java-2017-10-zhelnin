package ru.zhelnin.otus.lesson9.database.execution;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedHandler {

    void prepare(PreparedStatement statement) throws SQLException;
}
