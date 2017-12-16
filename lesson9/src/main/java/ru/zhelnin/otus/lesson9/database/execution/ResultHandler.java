package ru.zhelnin.otus.lesson9.database.execution;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler<T> {

    T handle(ResultSet resultSet) throws SQLException;
}
