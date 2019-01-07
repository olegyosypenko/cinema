package ua.training.model.service;

import java.util.ArrayList;
import java.util.List;

public class Paginator<E> {

    private List<E> items;
    private int numberOfItems;
    private int currentIndex;

    public Paginator(List<E> items, int numberOfItems) {
        this.items = items;
        this.numberOfItems = numberOfItems;
        this.currentIndex = 1;
    }



    private int getAmountOfPages() {
        if (items.size() % numberOfItems != 0) {
            return items.size() / numberOfItems + 1;
        } else {
            return items.size() / numberOfItems;
        }

    }

    public List<Integer> getIndexes() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getAmountOfPages(); i++) {
            list.add(i + 1);
        }
        return list;
    }

    public List<E> getItemsPart(int index) {
        if (index < 1){
            index = 1;
        } else if (index > getAmountOfPages()) {
            index = getAmountOfPages();
        }
        this.currentIndex = index;
        int i = index - 1;
        int numberOfPages = items.size() / numberOfItems;
        if (i == numberOfPages) {
            return items.subList(i * numberOfItems, items.size());
        }
        return items.subList(i * numberOfItems, i * numberOfItems + numberOfItems);
    }

    public int getPreviousIndex() {
        if (currentIndex == 1) {
            return currentIndex;
        } else {
            return currentIndex - 1;
        }
    }
    public int getNextIndex() {
        if (currentIndex == getAmountOfPages()) {
            return currentIndex;
        } else {
            return currentIndex + 1;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
