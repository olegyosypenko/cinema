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

    private int getNumberOfPages() {
        if (items.size() % numberOfItems != 0) {
            return items.size() / numberOfItems + 1;
        } else {
            return items.size() / numberOfItems;
        }

    }

    /**
     * Creates list of indexes that can be applied
     */
    public List<Integer> getIndexes() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getNumberOfPages(); i++) {
            list.add(i + 1);
        }
        return list;
    }

    /**
     * This method returns part of items specified by index.
     * and sets index to currentIndex.
     * @return required part of list
     */
    public List<E> getItemsPart(int index) {
        if (index < 1){
            index = 1;
        } else if (index > getNumberOfPages()) {
            index = getNumberOfPages();
        }
        this.currentIndex = index;
        int i = index - 1;
        int numberOfPages = items.size() / numberOfItems;
        if (i == numberOfPages) {
            return items.subList(i * numberOfItems, items.size());
        }
        return items.subList(i * numberOfItems, i * numberOfItems + numberOfItems);
    }

    /**
     * Returns previous index or 1 if currentIndex is 1
     */
    public int getPreviousIndex() {
        if (currentIndex == 1) {
            return currentIndex;
        } else {
            return currentIndex - 1;
        }
    }
    /**
     * Returns next index or last index if currentIndex is last index
     */
    public int getNextIndex() {
        if (currentIndex == getNumberOfPages()) {
            return currentIndex;
        } else {
            return currentIndex + 1;
        }
    }

    /**
     * Returns current index
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
}
