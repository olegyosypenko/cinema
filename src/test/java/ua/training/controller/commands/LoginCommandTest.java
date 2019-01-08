package ua.training.controller.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoginCommandTest {
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
        Mockito.when(request.getParameter("username")).thenReturn("asdf");
        Mockito.when(request.getParameter("password")).thenReturn("asdf");
    }
    @Test
    public void process() {
        String uri = loginCommand.process(request);
        assertEquals("redirect:free/home?login=true", uri);
        assertTrue(!users.isEmpty());
    }
}