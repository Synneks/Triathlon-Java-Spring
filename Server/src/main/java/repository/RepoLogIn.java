package repository;

import database.JDBCUtil;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class RepoLogIn extends RepoLogInImpl {
    private JDBCUtil jdbcUtil;

    public RepoLogIn(Properties serverProps) {
        this.jdbcUtil = new JDBCUtil(serverProps);
    }

    @Override
    public User getOne(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Connection con = jdbcUtil.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from arbitrii where arbitrii.Username = ? and arbitrii.Password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getString("Nume"), resultSet.getString("Prenume"), username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
