package vip.bingzi.systemcall.lib;

import java.io.IOException;

public class SystemCmd {
    public static void onCmd(String s) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(s);
    }
}
