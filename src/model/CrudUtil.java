package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CrudUtil {
        public static <T> T execute(String sql, Object... params) throws SQLException {
            PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stm.setObject((i + 1), params[i]);
            }
            if (sql.startsWith("SELECT"))
                return (T)stm.executeQuery();
            return (T)(Boolean)(stm.executeUpdate() > 0);
        }
    }

