package vip.bingzi.systemcall;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SystemCall extends JavaPlugin {
    @Override
    public void onLoad() {//对一些基础文件进行检查
        File fileConfig = new File(getDataFolder(), "config.yml");
        File fileCommand = new File(getDataFolder(), "Command.yml");
        onFileExamine(fileCommand, false, "命令文件");
        onFileExamine(fileConfig, true, "配置文件");
    }

    @Override
    public void onEnable() {

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
                    getLogger().warning("配置文件版本与插件版本不一致！");
                    if (getConfig().getBoolean("AutoUpDate")) {
                        // 抑制对delete()方法检查
                        //noinspection ResultOfMethodCallIgnored
                        file.delete();
                        saveDefaultConfig();
                        reloadConfig();
                    }
                }
            }
        } else {
            getLogger().warning("未检测到[ " + fileName + " ]存在，正在释放默认配置文件。");
            if (isConfig) {
                saveDefaultConfig();
            }
            getLogger().info("[ " + fileName + " ]文件释放完成。");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
    }
}
