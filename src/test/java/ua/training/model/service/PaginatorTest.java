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
        assertEquals(paginator.getItemsPart(4), strings.subList(30, 40));
        assertEquals(paginator.getItemsPart(6), strings.subList(50, 53));
        assertEquals(paginator.getItemsPart(7), strings.subList(50, 53));
    }

    @Test
    public void getPreviousIndex() {
        paginator.getItemsPart(4);
        assertEquals(paginator.getPreviousIndex(), 3);
        paginator.getItemsPart(1);
        assertEquals(paginator.getPreviousIndex(), 1);
    }

    @Test
    public void getNextIndex() {
        paginator.getItemsPart(4);
        assertEquals(paginator.getNextIndex(), 5);
        paginator.getItemsPart(6);
        assertEquals(paginator.getNextIndex(), 6);
        paginator.getItemsPart(9);
        assertEquals(paginator.getNextIndex(), 6);
    }

    @Test
    public void getCurrentIndex() {
        paginator.getItemsPart(4);
        assertEquals(paginator.getCurrentIndex(), 4);
        paginator.getItemsPart(8);
        assertEquals(paginator.getCurrentIndex(), 6);
        paginator.getItemsPart(1);
        assertEquals(paginator.getCurrentIndex(), 1);
    }
}