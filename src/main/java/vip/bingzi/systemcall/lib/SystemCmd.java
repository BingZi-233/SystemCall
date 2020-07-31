package vip.bingzi.systemcall.lib;

import java.io.IOException;

public class SystemCmd {
    public static void onCmd(String s) {
        // 对调用进行异步操作
        Thread thread = new Thread(() -> {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
