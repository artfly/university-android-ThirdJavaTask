package com.noveogroup.buffer;


import com.noveogroup.data.Data;

public interface Buffer {
    void putData(Data data);
    Data getData();
}
