package com.noveogroup.data;

/**
 * Created by arty on 20.02.16.
 */
public class DataImpl implements Data {
    private String name;

    public DataImpl(String name) {
        this.name = name;
    }

    @Override
    public synchronized String getName() {
        return name;
    }
}
