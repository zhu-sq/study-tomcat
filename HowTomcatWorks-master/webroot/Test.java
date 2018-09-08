package webroot;

public class Test {
    public static void  main(String[] args){
        System.out.println("hello world");
        Test t = new Test();
        T1 t1=t.new T1();
        t1.assign();
    }

    class T1 implements Runnable {

        public void run(){
               System.out.println("--------------------t1");
        }

        public synchronized void assign(){
            try {
                notifyAll();
            }catch (IllegalMonitorStateException e){
                e.printStackTrace();
            }

        }
    }


     class T2 implements Runnable {
        public void run(){
            System.out.println("--------------------t2");
        }
    }
}
