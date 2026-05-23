package ud8.bank.persistance.dao.impl;

import ud8.bank.domain.entity.BankAccount;
import ud8.bank.persistance.dao.BankAccountDao;
import ud8.bank.persistance.rowmapper.BankAccountRowMapper;
import ud8.common.persistance.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDaoJdbc implements BankAccountDao {
    private final DatabaseConnection connection;
    private final BankAccountRowMapper bankAccountRowMapper;

    public BankAccountDaoJdbc() {
        this.connection = DatabaseConnection.getInstance();
        this.bankAccountRowMapper = new BankAccountRowMapper();
    }

    @Override
    public BankAccount findByIBAN(String iban) {
        String sql = "SELECT * FROM bank_account WHERE iban = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, iban);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
                return null;

            return bankAccountRowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BankAccount latest() {
        String sql = "SELECT iban, balance FROM bank_account ORDER BY iban DESC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
                return null;

            return bankAccountRowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(BankAccount bank) {
        String sql = "INSERT INTO bank_account (iban, balance) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bank.getIban());
            preparedStatement.setDouble(2, bank.getBalance());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(BankAccount bank) {
        String sql = "UPDATE bank_account SET balance = ? WHERE iban = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, bank.getBalance());
            preparedStatement.setString(2, bank.getIban());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int delete(String iban) {
        String sql = "DELETE FROM bank_account WHERE iban = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, iban);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
