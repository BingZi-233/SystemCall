package vip.bingzi.systemcall.lib;

import vip.bingzi.systemcall.SystemCall;

import java.io.IOException;
import java.util.List;

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
        List<String> listNoCmd = SystemCall.NoCmd.getStringList("Command");
        for (String NoCmd : listNoCmd) {
            if (s.equalsIgnoreCase(NoCmd)) {
                return;
            }
        }
        thread.start();
    }
}
