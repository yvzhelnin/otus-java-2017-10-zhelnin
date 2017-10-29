package ru.zhelnin.otus.lesson3;

import org.junit.Assert;
import org.junit.Test;
import ru.zhelnin.otus.lesson3.collection.CustomList;
import ru.zhelnin.otus.lesson3.exception.CustomListIndexOutOfBoundsException;
import ru.zhelnin.otus.lesson3.util.StringConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class CustomListTest {

    @Test
    public void testSizeOfEmptyCustomList() {
        Assert.assertEquals(0, new CustomList<>().size());
    }

    @Test(expected = CustomListIndexOutOfBoundsException.class)
    public void testGetOutOfBoundsElement() {
        Assert.assertNull(new CustomList<>().get(12));
    }

    @Test
    public void testAddObjectToCustomList() {
        List<String> customList = new CustomList<>();
        customList.add(StringConstant.FIRST);

        Assert.assertEquals(StringConstant.FIRST, customList.get(0));
    }

    @Test
    public void testAddLotsOfObjectsToCustomList() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);

        Assert.assertEquals(StringConstant.SIXTH, source.get(5));
    }

    @Test
    public void testAddElementByIndexToCustomList() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(2, StringConstant.FIFTH);
        source.add(3, StringConstant.SIXTH);

        Assert.assertEquals(StringConstant.FIFTH, source.get(2));
        Assert.assertEquals(StringConstant.SIXTH, source.get(3));
        Assert.assertEquals(StringConstant.THIRD, source.get(4));
        Assert.assertEquals(StringConstant.FORTH, source.get(5));
    }

    @Test
    public void testAddCollectionToCustomListByIndex() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        Collection<String> addingList = new ArrayList<>();
        addingList.add(StringConstant.FORTH);
        addingList.add(StringConstant.FIFTH);
        addingList.add(StringConstant.SIXTH);

        source.addAll(1, addingList);

        Assert.assertEquals(StringConstant.FORTH, source.get(1));
        Assert.assertEquals(StringConstant.FIFTH, source.get(2));
        Assert.assertEquals(StringConstant.SIXTH, source.get(3));
        Assert.assertEquals(StringConstant.SECOND, source.get(4));
        Assert.assertEquals(StringConstant.THIRD, source.get(5));
    }

    @Test
    public void testRemoveCollectionFromCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);

        Collection<String> removingList = new ArrayList<>();
        removingList.add(StringConstant.SECOND);
        removingList.add(StringConstant.THIRD);
        removingList.add(StringConstant.SIXTH);

        source.removeAll(removingList);

        Assert.assertEquals(StringConstant.FIRST, source.get(0));
        Assert.assertEquals(StringConstant.FORTH, source.get(1));
        Assert.assertEquals(StringConstant.FIFTH, source.get(2));
    }

    @Test
    public void testRetainCollectionFromCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);

        Collection<String> retainingElements = new ArrayList<>();
        retainingElements.add(StringConstant.SECOND);
        retainingElements.add(StringConstant.THIRD);
        retainingElements.add(StringConstant.SIXTH);

        source.retainAll(retainingElements);

        Assert.assertEquals(StringConstant.SECOND, source.get(0));
        Assert.assertEquals(StringConstant.THIRD, source.get(1));
        Assert.assertEquals(StringConstant.SIXTH, source.get(2));
    }

    @Test
    public void testCustomListContainsObject() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);

        Assert.assertTrue(source.contains(StringConstant.SECOND));
    }

    @Test
    public void testCustomListNotContainsObject() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);

        Assert.assertTrue(!source.contains(StringConstant.THIRD));
    }

    @Test
    public void testCustomListContainsAllCollection() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);

        Collection<String> containsInList = new ArrayList<>();
        containsInList.add(StringConstant.SECOND);
        containsInList.add(StringConstant.SIXTH);
        containsInList.add(StringConstant.THIRD);

        Assert.assertTrue(source.containsAll(containsInList));
    }

    @Test
    public void testCustomListNotContainsAllCollection() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);

        Collection<String> containsInList = new ArrayList<>();
        containsInList.add(StringConstant.SECOND);
        containsInList.add(StringConstant.SIXTH);
        containsInList.add(StringConstant.THIRD);
        containsInList.add(StringConstant.SEVENTH);

        Assert.assertTrue(!source.containsAll(containsInList));
    }

    @Test
    public void testIsEmptyEmptyCustomList() {
        Assert.assertTrue(new CustomList<>().isEmpty());
    }

    @Test
    public void testIsEmptyNotEmptyCustomList() {
        Assert.assertTrue(new CustomList<>(12).isEmpty());
    }

    @Test
    public void testCustomListToArray() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        Assert.assertArrayEquals(new String[]{"first string", "second string", "third string"}, source.toArray());
    }

    @Test
    public void testRemoveElementFromCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.remove(StringConstant.FIRST);

        Assert.assertEquals(StringConstant.SECOND, source.get(0));
    }

    @Test
    public void testRemoveElementByIndexFromCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.remove(1);

        Assert.assertEquals(StringConstant.THIRD, source.get(1));
    }

    @Test
    public void testSetElementByIndexInCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.set(1, StringConstant.THIRD);

        Assert.assertEquals(StringConstant.THIRD, source.get(1));
    }

    @Test(expected = CustomListIndexOutOfBoundsException.class)
    public void testSetElementByIndexOutOfBoundsCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.set(3, StringConstant.THIRD);
    }

    @Test
    public void testCustomListIndexOf() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        Assert.assertEquals(1, source.indexOf(StringConstant.SECOND));
    }

    @Test
    public void testCustomListLastIndexOf() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.FIRST);

        Assert.assertEquals(4, source.lastIndexOf(StringConstant.SECOND));
    }

    @Test
    public void testClearCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.clear();

        Assert.assertEquals(0, source.size());
        Assert.assertArrayEquals(new Object[0], source.toArray());
    }

    @Test
    public void testCustomListAddAllCollection() {
        List<String> source = new CustomList<>(4);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        Collection<String> newElements = new ArrayList<>();
        newElements.add(StringConstant.FORTH);
        newElements.add(StringConstant.FIFTH);
        newElements.add(StringConstant.SIXTH);
        newElements.add(StringConstant.SEVENTH);

        source.addAll(newElements);

        Assert.assertEquals(StringConstant.FORTH, source.get(3));
        Assert.assertEquals(StringConstant.FIFTH, source.get(4));
        Assert.assertEquals(StringConstant.SIXTH, source.get(5));
        Assert.assertEquals(StringConstant.SEVENTH, source.get(6));
    }

    @Test
    public void testGetSubListFromCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FORTH);
        source.add(StringConstant.FIFTH);
        source.add(StringConstant.SIXTH);
        source.add(StringConstant.SEVENTH);

        List<String> subList = source.subList(2, 4);

        Assert.assertEquals(StringConstant.THIRD, subList.get(0));
        Assert.assertEquals(StringConstant.FORTH, subList.get(1));
        Assert.assertEquals(StringConstant.FIFTH, subList.get(2));
    }

    @Test
    public void testCollectionsAddAllElementsToCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        Collections.addAll(source, StringConstant.FORTH, StringConstant.FIFTH, StringConstant.SIXTH, StringConstant.SEVENTH);

        Assert.assertEquals(StringConstant.FORTH, source.get(3));
        Assert.assertEquals(StringConstant.FIFTH, source.get(4));
        Assert.assertEquals(StringConstant.SIXTH, source.get(5));
        Assert.assertEquals(StringConstant.SEVENTH, source.get(6));
    }

    @Test
    public void testCollectionsCopyCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        List<Object> dest = new CustomList<>();
        dest.add(StringConstant.PLACEHOLDER);
        dest.add(StringConstant.PLACEHOLDER);
        dest.add(StringConstant.PLACEHOLDER);
        Collections.copy(dest, source);

        Assert.assertEquals(StringConstant.FIRST, dest.get(0));
        Assert.assertEquals(StringConstant.SECOND, dest.get(1));
        Assert.assertEquals(StringConstant.THIRD, dest.get(2));
    }

    @Test
    public void testCustomListIteratorSet() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.THIRD);

        ListIterator iterator = source.listIterator();
        iterator.next();
        iterator.next();
        iterator.set(StringConstant.FORTH);

        Assert.assertEquals(StringConstant.FORTH, source.get(1));
    }

    @Test
    public void testCustomListIteratorIterations() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.THIRD);
        source.add(StringConstant.FIRST);
        source.add(StringConstant.SECOND);

        ListIterator iterator = source.listIterator();

        Assert.assertEquals(StringConstant.THIRD, iterator.next());
        Assert.assertEquals(StringConstant.FIRST, iterator.next());
        Assert.assertEquals(StringConstant.SECOND, iterator.next());
        Assert.assertEquals(StringConstant.FIRST, iterator.previous());
        Assert.assertEquals(StringConstant.THIRD, iterator.previous());
    }

    @Test
    public void testCollectionsSortCustomList() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.THIRD);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.FIRST);
        Collections.sort(source, String::compareToIgnoreCase);

        Assert.assertEquals(StringConstant.FIRST, source.get(0));
        Assert.assertEquals(StringConstant.SECOND, source.get(1));
        Assert.assertEquals(StringConstant.THIRD, source.get(2));
    }
}
