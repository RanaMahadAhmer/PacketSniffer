package PacketSniffer.abstractClasses;
/**This is a simple swing worker thread class which is available on the official oracle website*/
import javax.swing.*;
// Talhas Commit!!
public abstract class SniffThread {

    private Object value;

    private Thread thread;
    private ThreadVar threadVar;

    public abstract Object construct();

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

    protected synchronized Object getValue() {
        return value;
    }

    private synchronized void setValue(Object x) {
        value = x;
    }

    public void finished() {
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

    public void start() {
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
    }
}
