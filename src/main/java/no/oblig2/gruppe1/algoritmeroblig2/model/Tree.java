package no.oblig2.gruppe1.algoritmeroblig2.model;

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> extends Collection<E> {
    boolean search(E e);
    boolean insert(E e);
    boolean delete(E e);
    int getSize();

    BST.TreeNode<E> getRoot();

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }
    @Override
    default boolean contains(Object e){
        return search((E)e);
    }

    @Override
    default boolean add(E e){
        return insert(e);
    }
    @Override
    default boolean remove(Object e){
        return delete((E)e);
    }
    @Override
    default int size(){
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> c){
        for(Object item : c)
            if (!contains(item))return false;
        return true;
    }
    @Override
    default boolean addAll(Collection<? extends E> c){
        boolean allAdded = true;
        for(E item : c)
            if(add(item)) allAdded = false;
        return allAdded;
    }
    @Override
    default boolean removeAll(Collection<?> c){
        boolean allRemoved = true;
        for(Object item : c)
            if(remove(item)) allRemoved = false;
        return allRemoved;
    }

    // Ikke ferdig
    @Override
    default boolean retainAll (Collection<?> c){
        return false;
    }
    @Override
    default Object[] toArray() {
        Object[] array = new Object[size()];
        int index = 0;
        for (Iterator<E> it = iterator(); it.hasNext(); ) {
            array[index++] = it.next();
        }
        return array;
    }
    @Override
    default <T> T[] toArray(T[] array){
        return (T[]) toArray();
    }

}
