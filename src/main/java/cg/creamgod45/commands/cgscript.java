package cg.creamgod45.commands;

import cg.creamgod45.Utils;
import cg.creamgod45.script.ScriptRunManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class cgscript implements TabCompleter, CommandExecutor {

    public void pagecontrol(Player player, Integer page) {
        TextComponent mainComponent = new TextComponent("頁面: ");
        mainComponent.setColor(ChatColor.YELLOW);
        if (page != 0) {
            TextComponent prev = new TextComponent("[上一頁]");
            prev.setColor(ChatColor.GREEN);
            prev.setBold(true);
            prev.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點我執行").create()));
            prev.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cgscript help " + (page - 1)));
            mainComponent.addExtra(prev);
        } else {
            TextComponent prev = new TextComponent("上一頁");
            prev.setColor(ChatColor.GRAY);
            mainComponent.addExtra(prev);
        }
        TextComponent sy = new TextComponent(" | ");
        mainComponent.addExtra(sy);
        if (page != 1) {
            TextComponent next = new TextComponent("[下一頁]");
            next.setColor(ChatColor.GREEN);
            next.setBold(true);
            next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點我執行").create()));
            next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cgscript help " + (page + 1)));
            mainComponent.addExtra(next);
        } else {
            TextComponent prev = new TextComponent("下一頁");
            prev.setColor(ChatColor.GRAY);
            mainComponent.addExtra(prev);
        }
        player.spigot().sendMessage(mainComponent);
    }

    public void helpmanal(Player player, int page) {
        player.sendMessage(Utils.format(" "));
        player.sendMessage(Utils.format("&e&l============================================="));
        switch (page) {
            default:
            case 0:
                player.sendMessage(Utils.format("&e&l[CGSCRIPT] &c變數細項說明 頁面: (" + page + "/1)"));
                player.sendMessage(Utils.format("&e說明: 這是一個[腳本系統]"));
                player.sendMessage(Utils.format("&9<page> &f數字"));
                pagecontrol(player, 0);
                break;
            case 1:
                // 5 Commands
                player.sendMessage(Utils.format("&e&l[CGSCRIPT] &c指令幫助 頁面: (" + page + "/1)"));
                player.sendMessage(Utils.format("&a指令簡稱 [cgscript, cgq, quests, quest, q]"));
                // CGSCRIPT help
                TextComponent mainComponent = new TextComponent(Utils.format("&a/cgscript help &9<page>"));
                mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點我輸入").create()));
                mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/cgscript help <page>"));
                player.spigot().sendMessage(mainComponent);
                player.sendMessage(Utils.format(" &a查看指定的幫助頁面"));
                // CGSCRIPT test
                TextComponent mainComponent1 = new TextComponent(Utils.format("&a/cgscript edit"));
                mainComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點我執行").create()));
                mainComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cgscript test"));
                player.spigot().sendMessage(mainComponent1);
                player.sendMessage(Utils.format(" &a測試腳本"));
                pagecontrol(player, 1);
                break;
        }
    }
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (command.getName().equalsIgnoreCase("cgscript") && Utils.HasPermission(player, "cgscript.use")) {
                if (args.length == 0) {
                    helpmanal(player, 1);
                } else {
                    String method = args[0];
                    switch (method) {
                        case "test":
                            List<String> actions = new ArrayList<>();
                            actions.add("if.BOOLEAN;true;var.set(yee,true)");
                            actions.add("if.BOOLEAN;%yee%;player.sendMessage(&aYee:%yee%)");
                            actions.add("if.MOREEQUAL;%mmocore_level%,1;player.sendMessage(&aMMOCORE 經驗等級 %mmocore_level%)");
                            Utils.ConsoleInfoPrint("List<String> actions = "+String.valueOf(actions), false);
                            ScriptRunManager scriptRunManager = new ScriptRunManager(actions);
                            scriptRunManager.RunScript(player);
                            break;
                        case "help":
                            if (args.length >= 2) {
                                String page = args[1];
                                helpmanal(player, Integer.parseInt(page));
                            } else {
                                helpmanal(player, 0);
                            }
                            break;
                        default:
                            helpmanal(player, 1);
                            break;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Utils.HasPermission(player, "CGSCRIPT.use")) {
                if (args.length == 1) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("!說明:選擇一個服務選項");
                    arguments.add("help");
                    arguments.add("test");
                    return arguments;
                }
                if (args.length > 0) {
                    if (args[0].equals("help")) {
                        if (args.length == 2) {
                            List<String> arguments = new ArrayList<>();
                            arguments.add("0");
                            arguments.add("1");
                            return arguments;
                        }
                    }
                }
            }
        }
        return null;
    }
}
