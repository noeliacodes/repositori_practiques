package ud8.bank.persistance.rowmapper;

import ud8.bank.domain.entity.BankAccount;
import ud8.common.persistance.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper extends RowMapper<BankAccount> {
    @Override
    public BankAccount mapRow(ResultSet rs) throws SQLException {
        String iban = rs.getString("iban");
        double balance = rs.getDouble("balance");

        return new BankAccount(iban, balance);
    }
}
