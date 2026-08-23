package mango.ast.commands.commands.client;

import mango.ast.Astralis;
import mango.ast.commands.Command;
import mango.ast.util.io.HwidUtil;
import mango.ast.util.render.ChatUtil;
import java.io.File;
import net.minecraft.client.Minecraft;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super(new String[]{"config", "c"}, "Manages configurations: .config <save|relod|load|delete> <name>");
    }

    @Override
    public void execute(String[] args, String message) {
        String[] words = message.split(" ");

        if (words.length < 2) {
            ChatUtil.print("Usage: #config <save|load|delete|reload|folder|list> <name>");
            return;
        }

        String action = words[1].toLowerCase();
        String configName = words.length > 2 ? message.substring(message.indexOf(action) + action.length()).trim() : null;

        Astralis.getInstance().getConfigManager().addConfigsToHashMap();

        switch (action) {
            case "folder":
                if (HwidUtil.PlatformInfo.detect() == HwidUtil.PlatformInfo.OS.WIN) {
                    File moduleDirectory = new File(
                            Minecraft.getInstance().gameDirectory,
                            "/" + Astralis.NAME.toLowerCase() + "/Configs"
                    );
                    try {
                        Runtime.getRuntime().exec("explorer.exe  /select," + moduleDirectory.getAbsolutePath());
                    } catch (Exception ex) {
                        System.out.println("Error - " + ex);
                    }
                } else {
                    ChatUtil.print("This command is only available for windows users.");
                }
                break;
            case "list":
                ChatUtil.print("available configs: ");
                for (String cfg : Astralis.getInstance().getConfigManager().configs.keySet()) {
                    ChatUtil.print(cfg);
                }
                break;
            case "save":
                if (configName == null || configName.isEmpty()) {
                    ChatUtil.print("Usage: #config save <name>");
                    return;
                }

                Astralis.getInstance().getConfigManager().saveConfig(configName);
                ChatUtil.print("Saved config: " + configName);
                break;
            case "load":
                if (configName == null || configName.isEmpty()) {
                    ChatUtil.print("Usage: #config load <name>");
                    return;
                }
                Astralis.getInstance().getConfigManager().loadConfig(configName);
                break;
            case "delete":
                if (configName == null || configName.isEmpty()) {
                    ChatUtil.print("Usage: #config delete <name>");
                    return;
                }
                Astralis.getInstance().getConfigManager().deleteConfig(configName);
                ChatUtil.print("Deleted config: " + configName);
                break;
          /*  case "reload":
                Astralis.getInstance().getConfigManager().addConfigsToHashMap();
                break;*/
            default:
                ChatUtil.print("Invalid action. Use save, load, delete, folder, or list");
                break;
        }
    }
}
