package abstractClasses;
import javax.swing.*;



public abstract class SniffThread {

    private final ThreadVar threadVar;
    private Object value;
    private Thread thread;

    
    public SniffThread() {
        final Runnable doFinished = new Runnable() {
            public void run() {
                finished();
            }
        };

        Runnable doConstruct = new Runnable() {
            public void run() {
                try {
                    setValue(construct());
                } finally {
                    threadVar.clear();
                }
                SwingUtilities.invokeLater(doFinished);
            }
        };
        Thread t = new Thread(doConstruct);
        threadVar = new ThreadVar(t);
    }

    
    public abstract Object construct();

    
    protected synchronized Object getValue() {
        return value;
    }

  
    private synchronized void setValue(Object x) {
        value = x;
    }

    
    public void finished() {
        // Default implementation does nothing.
    }

    
    public void interrupt() {
        Thread t = threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        threadVar.clear();
    }

    
    public Object get() {
        while (true) {
            Thread t = threadVar.get();
            if (t == null) {
                return getValue();
            }
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
    }

   
    public void start() {
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
    }

    
    private static class ThreadVar {

        private Thread thread;

        
        ThreadVar(Thread t) {
            thread = t;
        }

        
        synchronized Thread get() {
            return thread;
        }

        
        synchronized void clear() {
            thread = null;
        }
    }
}