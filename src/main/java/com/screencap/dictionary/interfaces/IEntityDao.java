package com.screencap.dictionary.interfaces;


public interface IEntityDao<T> {

    public void save(T object);

    public void update(T object);

    public void delete(T object);
}
