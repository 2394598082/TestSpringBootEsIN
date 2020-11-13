import java.util.concurrent.*;

public class Testss {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //实现callable接口  接受返回值
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            public Object call() throws Exception {
               // int i = 0;
                for (int j = 0; j < 100; j++) {
                   // i += j;
                    System.out.println("================");
                }
                return 0;
            }
        });
        //获取返回值
        Object o = submit.get();
//        System.out.println(o);
        //实现runnable 接口
        executorService.execute(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("*****************");
                }
            }
        });
        //放入线程池
        executorService.shutdown();
    }
}
