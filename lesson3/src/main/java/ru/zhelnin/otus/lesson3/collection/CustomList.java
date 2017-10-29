package ru.zhelnin.otus.lesson3.collection;

import ru.zhelnin.otus.lesson3.exception.CustomListIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomList<E> implements List<E> {

    private static final int DEFAULT_SIZE = 8;

    private int elementsCount = 0;

    private Object[] elements;

    public CustomList(int elementsSize) {
        elements = new Object[elementsSize];
    }

    public CustomList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < elementsCount; i++) {
            if (o != null && elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < elements.length && elements[nextIndex] != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) elements[nextIndex++];
                }
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        if (elementsCount == 0) {
            return new Object[0];
        }
        return Arrays.copyOf(elements, elementsCount);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.arraycopy(elements, 0, a, 0, elementsCount);

        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        if (elementsCount == elements.length - 1) {
            elements = Arrays.copyOf(elements, 3 * elements.length / 2);
        }
        elements[elementsCount++] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int targetIndex = indexOf(o);
        if (targetIndex == -1) {
            return false;
        }
        if (targetIndex == elementsCount - 1) {
            elements[targetIndex] = null;
            elementsCount--;

            return true;
        }
        System.arraycopy(elements, targetIndex + 1, elements, targetIndex, elements.length - 1 - targetIndex);
        elementsCount--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        int equalsCount = 0;
        for (Object current : c) {
            if (contains(current)) {
                equalsCount++;
            }
        }
        return equalsCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            return false;
        }
        if (elements.length - elementsCount < c.size()) {
            elements = Arrays.copyOf(elements, 3 * elements.length / 2 + c.size());
        }
        for (Object newElement : c) {
            elements[elementsCount++] = newElement;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        if (c == null) {
            return false;
        }
        if (elements.length - elementsCount < c.size()) {
            elements = Arrays.copyOf(elements, 3 * elements.length / 2 + c.size());
        }
        for (E element : c) {
            add(index++, element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        for (Object element : c) {
            remove(element);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        clear();
        if (elements.length - elementsCount < c.size()) {
            elements = Arrays.copyOf(elements, 3 * elements.length / 2 + c.size());
        }
        for (Object element : c) {
            add((E) element);
        }

        return true;
    }

    @Override
    public void clear() {
        elements = new Object[DEFAULT_SIZE];
        elementsCount = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        elements[index] = element;

        return element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        if (elementsCount == elements.length - 1) {
            elements = Arrays.copyOf(elements, 3 * elements.length / 2);
        }
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
        elements[index] = element;
        elementsCount++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        E removedElement = get(index);
        if (index == elementsCount - 1) {
            elements[index] = null;
            elementsCount--;

            return removedElement;
        }
        System.arraycopy(elements, index + 1, elements, index, elements.length - 1 - index);
        elementsCount--;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        for (int i = 0; i < elementsCount; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }
        for (int i = elementsCount - 1; i >= 0; i--) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            private int nextIndex = 0;
            private int previousIndex = -1;

            private boolean isUsedNext = true;

            @Override
            public boolean hasNext() {
                return nextIndex < elements.length && elements[nextIndex] != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    previousIndex++;
                    isUsedNext = true;

                    return (E) elements[nextIndex++];
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return previousIndex >= 0;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    nextIndex--;
                    isUsedNext = false;

                    return (E) elements[--previousIndex];
                }
                return null;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return previousIndex;
            }

            @Override
            public void remove() {
                if (isUsedNext) {
                    if (nextIndex == elementsCount - 1) {
                        elements[nextIndex] = null;
                    }
                } else {
                    if (previousIndex == elementsCount - 1) {
                        elements[previousIndex] = null;
                        previousIndex--;
                        nextIndex--;
                    }
                }
                System.arraycopy(elements, previousIndex + 1, elements, previousIndex, elements.length - 1 - previousIndex);
                elementsCount--;
            }

            @Override
            public void set(E e) {
                if (isUsedNext) {
                    elements[nextIndex - 1] = e;
                } else {
                    elements[previousIndex + 1] = e;
                }
            }

            @Override
            public void add(E e) {
                if (elementsCount == elements.length - 1) {
                    elements = Arrays.copyOf(elements, 3 * elements.length / 2);
                }
                if (isUsedNext) {
                    System.arraycopy(elements, nextIndex, elements, nextIndex + 1, elements.length - nextIndex - 1);
                    elements[nextIndex] = e;
                } else {
                    System.arraycopy(elements, previousIndex, elements, previousIndex + 1, elements.length - previousIndex - 1);
                    elements[previousIndex] = e;
                }
                elementsCount++;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIterator<E>() {

            private int nextIndex = index;
            private int previousIndex = index - 1;

            private boolean isUsedNext = true;

            @Override
            public boolean hasNext() {
                return nextIndex < elements.length && elements[nextIndex] != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    previousIndex++;
                    isUsedNext = true;

                    return (E) elements[nextIndex++];
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return previousIndex >= 0;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    nextIndex--;
                    isUsedNext = false;

                    return (E) elements[--previousIndex];
                }
                return null;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return previousIndex;
            }

            @Override
            public void remove() {
                if (isUsedNext) {
                    if (nextIndex == elementsCount - 1) {
                        elements[nextIndex] = null;
                    }
                } else {
                    if (previousIndex == elementsCount - 1) {
                        elements[previousIndex] = null;
                        previousIndex--;
                        nextIndex--;
                    }
                }
                System.arraycopy(elements, previousIndex + 1, elements, previousIndex, elements.length - 1 - previousIndex);
                elementsCount--;
            }

            @Override
            public void set(E e) {
                if (isUsedNext) {
                    elements[nextIndex - 1] = e;
                } else {
                    elements[previousIndex + 1] = e;
                }
            }

            @Override
            public void add(E e) {
                if (elementsCount == elements.length - 1) {
                    elements = Arrays.copyOf(elements, 3 * elements.length / 2);
                }
                if (isUsedNext) {
                    System.arraycopy(elements, nextIndex, elements, nextIndex + 1, elements.length - nextIndex - 1);
                    elements[nextIndex] = e;
                } else {
                    System.arraycopy(elements, previousIndex, elements, previousIndex + 1, elements.length - previousIndex - 1);
                    elements[previousIndex] = e;
                }
                elementsCount++;
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            throw new CustomListIndexOutOfBoundsException();
        }
        if (fromIndex < 0 || fromIndex >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        if (toIndex < 0 || toIndex >= elementsCount) {
            throw new CustomListIndexOutOfBoundsException();
        }
        List<E> result = new CustomList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            result.add((E) elements[i]);
        }
        return result;
    }
}
