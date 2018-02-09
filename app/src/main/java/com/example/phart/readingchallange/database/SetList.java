package com.example.phart.readingchallange.database;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PHART on 2/7/2018.
 */

public class  SetList <T> extends ArrayList<T> implements Set<T> {
    final Set<T> set;

    public SetList(Collection<T> c){
        set = new HashSet<>(c);
        super.addAll(set);
    }

    public SetList() {
        set = new HashSet<>();
    }

    @Override
    public boolean contains(final Object o) {
        return set.contains(o);
    }

    @Override
    public boolean add(final T t) {
        if(set.contains(t)) {
            return false;
        }
        return super.add(t);
    }

    @Override
    public T remove(final int index) {
        T remove = super.remove(index);
        set.remove(remove);
        return remove;
    }

    @Override
    public boolean remove(final Object o) {
        return set.remove(o) &&  super.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull final Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull final Collection<? extends T> c) {
        return set.addAll(c) && super.addAll(c);
    }

    @Override
    public boolean retainAll(@NonNull final Collection<?> c) {
        return set.retainAll(c) && super.retainAll(c);
    }

    @Override
    public boolean removeAll(@NonNull final Collection<?> c) {
        return set.removeAll(c) && super.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
        super.clear();
    }

}
