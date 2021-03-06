package vip.bingzi.systemcall.lib;

import org.bukkit.Bukkit;
import vip.bingzi.systemcall.SystemCall;

public class Timer implements Runnable {
    @Override
    public void run() {
        Bukkit.getServer().getScheduler().runTaskTimer(SystemCall.getSystemCall(), () -> {
            for (String s : IOCommand.getCommandList()) {
                SystemCmd.onCmd(s);
            }
        }, IOCommand.getDelayTick(), IOCommand.getPeriodTick());

    }
}
