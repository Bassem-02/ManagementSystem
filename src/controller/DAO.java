package controller;

import java.util.ArrayList;

public interface DAO<T> {
    boolean insert(T o);
    boolean update(T o);
    boolean delete(String id);
    T findById(String id);
    ArrayList<T> select();
}