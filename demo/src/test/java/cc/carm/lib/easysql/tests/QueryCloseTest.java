package cc.carm.lib.easysql.tests;

import cc.carm.lib.easysql.TestHandler;
import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.SQLQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCloseTest extends TestHandler {


    @Override
    public void onTest(SQLManager sqlManager) throws SQLException {

        try (SQLQuery query = sqlManager.createQuery()
                .inTable("test_user_table")
                .orderBy("id", false)
                .setLimit(5)
                .build().execute()) {
            ResultSet resultSet = query.getResultSet();

            while (resultSet.next()) {

                System.out.printf(
                        "id: %d username: %s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("username")
                );

            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
