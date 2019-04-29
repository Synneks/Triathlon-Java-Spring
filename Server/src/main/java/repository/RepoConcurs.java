package repository;

import database.JDBCUtil;
import model.Concurent;
import model.UpdatePoints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class RepoConcurs extends RepoConcursImpl {
    private JDBCUtil jdbcUtil;

    public RepoConcurs(Properties serverProps) {
        this.jdbcUtil = new JDBCUtil(serverProps);
    }

    /**
     * Get competitors from the database
     *
     * @return list with competitiors
     */
    @Override
    public ArrayList<Concurent> getConcurenti() {
        ArrayList<Concurent> concurenti = new ArrayList<>();
        Connection con = jdbcUtil.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from concurenti")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                concurenti.add(new Concurent(resultSet.getString("Nume"), resultSet.getInt("Alergat") + resultSet.getInt("Inot") + resultSet.getInt("Ciclism")));
            }
        } catch (Exception e) {
            System.out.println("Search Error");
            e.printStackTrace();
        }
        return concurenti;
    }

    @Override
    public void updatePoints(UpdatePoints updatePoints) {
        String compName = updatePoints.getCompName();
        int points = updatePoints.getPoints();
        String challange = updatePoints.getChallenge();
        Connection con = jdbcUtil.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("update concurenti set " + challange + " = " + points + " WHERE Nume = '" + compName +"'")) {
//            preparedStatement.setString(1, challange);
//            preparedStatement.setInt(2, points);
//            preparedStatement.setString(3, compName);
            System.out.println(preparedStatement.toString());
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Update Error");
            e.printStackTrace();
        }
    }
}
