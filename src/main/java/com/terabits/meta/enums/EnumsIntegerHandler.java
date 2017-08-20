package com.terabits.meta.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/8/20.
 */
public class EnumsIntegerHandler extends BaseTypeHandler<Integer> {
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Integer integer, JdbcType jdbcType) throws SQLException {

    }

    public Integer getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public Integer getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public Integer getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
