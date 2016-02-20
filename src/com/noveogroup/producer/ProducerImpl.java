package com.noveogroup.producer;

import com.noveogroup.buffer.Buffer;
import com.noveogroup.data.Data;
import com.noveogroup.data.DataImpl;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arty on 20.02.16.
 */
public class ProducerImpl implements Producer, Runnable {
    private Buffer buffer;
    private final static String[] NAMES = {"Connor", "Colin", "Jacob", "Hunter", "Dylan", "Jalen", "Darnell",
            "Jamal", "Xavier", "Jake", "Reginald", "Jack", "Dustin", "Lucas", "Maurice", "Cody",
            "Andre", "Scott", "Brett", "Logan", "Terrance", "Tanner", "Cole", "Darryl", "DeAndre",
            "Terrell", "Willie", "Demetrius", "Trevon", "Marquis", "Maxwell", "DeShawn", "Garrett",
            "Luke", "Malik", "Wyatt", "Dominique", "Tyrone", "Bradley", "Darius"};
    private Logger logger;
    Random random = new Random();

    public ProducerImpl(Buffer buffer) {
        this.buffer = buffer;
        logger = Logger.getLogger("producerLogger");
    }

    @Override
    public synchronized Data produceData() {
        return new DataImpl(NAMES[random.nextInt(NAMES.length)]);
    }

    @Override
    public void run() {
        int iterations = random.nextInt(100);
        for (int i = 0; i <iterations ; i++) {
            logger.log(Level.INFO, "Iteration: " + (i + 1));
            Data nextData = produceData();
            logger.log(Level.INFO, "Putting name:" + nextData.getName());
            buffer.putData(nextData);
        }
        buffer.putData(new DataImpl("END"));
    }
}
