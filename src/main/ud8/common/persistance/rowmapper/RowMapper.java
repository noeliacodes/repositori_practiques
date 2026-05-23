package ud8.common.persistance.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class RowMapper<E> {
    public abstract E mapRow(ResultSet rs) throws SQLException;

    public List<E> map(ResultSet rs) throws SQLException {
        if (rs == null)
            return null;

        List<E> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        return list;
    }
}
