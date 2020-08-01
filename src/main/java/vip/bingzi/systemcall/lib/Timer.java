package vip.bingzi.systemcall.lib;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import vip.bingzi.systemcall.SystemCall;

public class Timer implements Listener {
    // 定时执行器
    public static void onTimer() {
        Bukkit.getServer().getScheduler().runTaskTimer(SystemCall.getSystemCall(), () -> {
            for (String s : IOCommand.getCommandList()) {
                SystemCmd.onCmd(s);
            }
        }, IOCommand.getDelayTick(), IOCommand.getPeriodTick());
    }
}
