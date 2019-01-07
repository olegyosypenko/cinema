package ua.training.controller.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class AddMoneyCommandTest {
    AddMoneyCommand addMoneyCommand = new AddMoneyCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    User user = new User();
    HttpSession session = Mockito.mock(HttpSession.class);

    @Before
    public void setUp() {
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("money")).thenReturn("-100");
        Mockito.when(request.getSession()).thenReturn(session);

    }
    @Test
    public void process() {
        assertEquals(addMoneyCommand.process(request), "redirect:user/add-money-page?error=incorrect-input");
    }
}