package ua.training.controller.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.dao.mysql.ConnectionPool;
import ua.training.model.dao.mysql.UserMySqlDao;
import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegisterCommandTest {

    private RegisterCommand registerCommand = new RegisterCommand();
    private LoginCommand loginCommand = new LoginCommand();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpSession session = Mockito.mock(HttpSession.class);
    private ServletContext context = Mockito.mock(ServletContext.class);
    private ArrayList<User> users = new ArrayList<>();


    @Before
    public void setUp() {
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute("logged-users")).thenReturn(users);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("username")).thenReturn("testname");
        Mockito.when(request.getParameter("password")).thenReturn("testpass");
        Mockito.when(request.getParameter("first-name")).thenReturn("");
        Mockito.when(request.getParameter("last-name")).thenReturn("");
        Mockito.when(request.getParameter("first-name-en")).thenReturn("");
        Mockito.when(request.getParameter("last-name-en")).thenReturn("");
    }
    @Test
    public void process() {
        String uri = registerCommand.process(request);
        assertEquals("/servlet/guest/login", uri);
        String loginUri = loginCommand.process(request);
        assertEquals("redirect:free/home?login=true", loginUri);
    }
    @After
    public void tearDown() throws SQLException {
        new UserMySqlDao(ConnectionPool.getDataSource().getConnection()).deleteUserByUsername("testname");
    }
}