package vip.bingzi.systemcall.lib;

import vip.bingzi.systemcall.SystemCall;

import java.util.List;

public class IOCommand {
    public static List<String> getCommandList() {
        return SystemCall.Command.getStringList("Command");
    }

    public static int getDelayTick() {
        return SystemCall.Command.getInt("DelayTick");
    }

    public static int getPeriodTick() {
        return SystemCall.Command.getInt("PeriodTick");
    }
}
