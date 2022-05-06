package org.pms.javabase.thread;

public class TraditionalThread {
    public static void main(String args[]){
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        Thread.sleep(500);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("1 >>"+Thread.currentThread().getName());
                    System.out.println("2 >>"+this.getName());
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(500);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("runable >>"+Thread.currentThread().getName());
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runable >>"+Thread.currentThread().getId());
                }
            }
        }){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the thread >>"+Thread.currentThread().getId());
                }
            }
        };
        thread3.start();
    }


}
