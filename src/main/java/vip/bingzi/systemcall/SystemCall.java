package vip.bingzi.systemcall;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import vip.bingzi.systemcall.lib.SystemCmd;

import java.io.File;
import java.io.IOException;

public final class SystemCall extends JavaPlugin {
    @Override
    public void onLoad() {//对一些基础文件进行检查
        File fileConfig = new File(getDataFolder(), "config.yml");
        File fileCommand = new File(getDataFolder(), "Command.yml");
        File fileUserName = new File(getDataFolder(), "username.yml");
        onFileExamine(fileConfig, true, "配置文件");
        onFileExamine(fileCommand, false, "命令文件");
        onFileExamine(fileUserName, false, "可执行者名单");
    }

    @Override
    public void onEnable() {
        // 抑制对命令注册语句可能为空的检查
        //noinspection ConstantConditions
        Bukkit.getPluginCommand("SystemCall").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("结束服务");
    }

    private void onFileExamine(File file, Boolean isConfig, String fileName) {
        // 检查文件是否存在
        if (file.exists()) {
            getLogger().info("[ " + fileName + " ]已经存在，正在检查版本中...");
            // 只有配置文件才需要检查这个
            if (isConfig) {
                if (getDescription().getVersion().equalsIgnoreCase(getConfig().getString("Version"))) {
                    getLogger().info("[ " + fileName + " ]版本与插件版本一致！");
                } else {
                    getLogger().warning("[ " + fileName + " ]版本与插件版本不一致！");
                    if (getConfig().getBoolean("AutoUpDate")) {
                        getLogger().info("根据用户设定，正在进行自动更换为新版[ " + fileName + " ]");
                        // 抑制对delete()方法检查
                        //noinspection ResultOfMethodCallIgnored
                        file.delete();
                        saveDefaultConfig();
                        reloadConfig();
                        getLogger().info("[ " + fileName + " ]更换完毕");
                    } else {
                        getLogger().warning("根据用户设定，拒绝进行自动更换");
                        getLogger().warning("请到GitHub上查看并修改配置文件");
                        getLogger().warning("GitHub：https://github.com/BingZi-233/SystemCall/blob/master/src/main/resources/config.yml");
                    }
                }
            }
        } else {
            getLogger().warning("未检测到[ " + fileName + " ]存在，正在释放默认配置文件。");
            if (isConfig) {
                saveDefaultConfig();
            } else {
                saveResource(file.getName(), false);
            }
            getLogger().info("[ " + fileName + " ]文件释放完成。");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("help".equalsIgnoreCase(args[0])) {
            sender.sendMessage("§a§l§m===========================");
            sender.sendMessage("  SystemCall - 主命令");
            sender.sendMessage("    cmd - 以cmd的方式运行");
            sender.sendMessage("      String - 执行的命令");
            sender.sendMessage("§a§l§m===========================");
        }
        if ("cmd".equalsIgnoreCase(args[0])) {
            try {
                SystemCmd.onCmd(args[2 - (args.length - 2)]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
