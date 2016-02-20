package com.noveogroup.consumer;

import com.noveogroup.buffer.Buffer;
import com.noveogroup.data.Data;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arty on 20.02.16.
 */
public class ConsumerImpl implements Consumer, Runnable {
    private Buffer buffer;
    private Logger logger;

    public ConsumerImpl(Buffer buffer) {
        this.buffer = buffer;
        logger = Logger.getLogger("ConsumerLogger");
    }

    @Override
    public synchronized void consumeData(Data data) {
        logger.log(Level.INFO, "Consumed data: " + data.getName());
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Taking first data in consumer");
        Data data = buffer.getData();
        logger.log(Level.INFO, "Start of loop in consumer");
        while (!data.getName().equals("END")) {
            consumeData(data);
            data = buffer.getData();
        }
    }
}
