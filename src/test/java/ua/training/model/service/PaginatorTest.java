package ua.training.model.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PaginatorTest {

    private ArrayList<String> strings = new ArrayList<>();
    private Paginator<String> paginator = new Paginator<>(strings, 10);

    @Before
    public void setUp() {
        for (int i = 0; i < 53 ; i++) {
            strings.add("elem" + i);
        }
    }

    @Test
    public void getIndexes() {
        assertEquals(paginator.getIndexes(), Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void getFilmsPart() {
        assertEquals(paginator.getFilmsPart(4), strings.subList(30, 40));
        assertEquals(paginator.getFilmsPart(6), strings.subList(50, 53));
        assertEquals(paginator.getFilmsPart(7), strings.subList(50, 53));
    }

    @Test
    public void getPreviousIndex() {
        paginator.getFilmsPart(4);
        assertEquals(paginator.getPreviousIndex(), 3);
        paginator.getFilmsPart(1);
        assertEquals(paginator.getPreviousIndex(), 1);
    }

    @Test
    public void getNextIndex() {
        paginator.getFilmsPart(4);
        assertEquals(paginator.getNextIndex(), 5);
        paginator.getFilmsPart(6);
        assertEquals(paginator.getNextIndex(), 6);
        paginator.getFilmsPart(9);
        assertEquals(paginator.getNextIndex(), 6);
    }

    @Test
    public void getCurrentIndex() {
        paginator.getFilmsPart(4);
        assertEquals(paginator.getCurrentIndex(), 4);
        paginator.getFilmsPart(8);
        assertEquals(paginator.getCurrentIndex(), 6);
        paginator.getFilmsPart(1);
        assertEquals(paginator.getCurrentIndex(), 1);
    }
}