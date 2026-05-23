package ud8.users.persistance.rowmapper;

import ud8.users.domain.entity.User;
import ud8.common.persistance.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper extends RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User user = new User(id);

        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
