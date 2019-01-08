package ua.training.model.dao.mysql;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TicketMySqlDaoTest {

    @Test
    public void getTicketsBySeanceId() throws SQLException {
        TicketMySqlDao ticketMySqlDao = new TicketMySqlDao(ConnectionPool.getDataSource().getConnection());
        assertEquals(0, ticketMySqlDao.getTicketsBySeanceId(0).size());
    }

    @Test
    public void getTicketsByUserId() throws SQLException {
        TicketMySqlDao ticketMySqlDao = new TicketMySqlDao(ConnectionPool.getDataSource().getConnection());
        assertEquals(0, ticketMySqlDao.getTicketsByUserId(0).size());
    }
}