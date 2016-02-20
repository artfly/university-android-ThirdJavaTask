package com.noveogroup.buffer;

import com.noveogroup.data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by arty on 20.02.16.
 */
public class BufferImpl implements Buffer {
    private int count = 0;
    private static final int BUF_SIZE = 5;
    private List<Data> dataList = new ArrayList<>();

    public synchronized Data getData() {
        while (dataList.size() == 0) {
            try {
                Logger.getLogger("ConsumerLogger").log(Level.INFO, "Waiting...");
                wait();
            } catch (InterruptedException e) {}
        }
        Data data = dataList.remove(dataList.size() - 1);
        notifyAll();
        return data;
    }

    public synchronized void putData(Data data) {
        while (dataList.size() == 5) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        dataList.add(data);
        notifyAll();
    }
}
