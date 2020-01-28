package ua.training.model.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class UserServiceTest {

    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String CRYPTED_PASSWORD = "crypted_password";

    @Mock
    private DaoFactory daoFactory;

    @Mock
    private UserDao userDao;

    @Mock
    private Transaction transaction;

    @Mock
    private CryptoService cryptoService;

    @Mock
    private User user;

    @InjectMocks
    private UserService sut;

    @Before
    public void setUp() {
        mockStatic(DaoFactory.class);
        when(DaoFactory.getInstance()).thenReturn(daoFactory);
        when(daoFactory.createUserDao()).thenReturn(userDao);
        when(daoFactory.getTransaction()).thenReturn(transaction);
        when(cryptoService.encryptString(PASSWORD)).thenReturn(CRYPTED_PASSWORD);
    }

    @Test
    public void shouldFindUserByUsernameAndPassword() {
        when(userDao.getUserByUsernameAndPassword(USERNAME, CRYPTED_PASSWORD)).thenReturn(user);

        User actual = sut.getUserByUsernameAndPassword(USERNAME, PASSWORD);

        verify(userDao).getUserByUsernameAndPassword(USERNAME, CRYPTED_PASSWORD);
        verify(cryptoService).encryptString(PASSWORD);
        verifyCommon();
        assertSame(actual, user);
    }

    @Test
    public void shouldCreateUser() {
        UserDto userToSave = new UserDto();
        userToSave.setPassword(PASSWORD);
        userToSave.setUsername(USERNAME);

        sut.createUser(userToSave);

        verify(userDao).createUser(userToSave);
        verify(cryptoService).encryptString(PASSWORD);
        verifyCommon();
    }

    @Test
    public void shouldAddMoneyToUser() {
        int id = 23;
        int amountOfMoney = 44;

        sut.addMoneyByUserId(id, amountOfMoney);

        verify(userDao).addMoneyToUser(id, amountOfMoney);
        verifyCommon();
    }

    @Test
    public void shouldFindUserById() {
        int id = 23;
        when(userDao.getUserById(id)).thenReturn(user);

        User actual = sut.getUserById(id);

        assertSame(actual, user);
        verifyCommon();
    }

    private void verifyCommon() {
        verify(daoFactory).createUserDao();
        verify(daoFactory).getTransaction();
        verify(transaction).close();
    }
}