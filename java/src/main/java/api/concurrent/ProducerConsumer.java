/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c-dmudawal
 */
public class ProducerConsumer {
    
    private List<String> list = new ArrayList<String>();
    private volatile int size = 0;
    private int MAX_LEN = 10;
    
    private void add(String data) throws InterruptedException {
        synchronized(list) {
            while(size < MAX_LEN) {
                list.wait();
            }
            list.add(data);
            list.notify();
        } 
    }

    private void remove(String data) {
        synchronized(list) {
            while(size > 0) {
                list.remove(data);
            }
        }
    }
    
}
