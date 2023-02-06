package cg.creamgod45.script;

import cg.creamgod45.Script;
import cg.creamgod45.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ScriptParser {

    public ScriptParser() {
    }

    public String placeholder(String cmd, ScriptVarible scriptVarible, Player player) {
        Utils.ConsoleInfoPrint("placeholder: " + cmd);
        final String[] temp = {cmd};
        scriptVarible.memory.forEach((s, o) -> {
            Utils.ConsoleInfoPrint("var: " + s + "value: " + o.toString());
            temp[0] = temp[0].replaceAll("%" + s + "%", String.valueOf(o));
        });
        Boolean placeholderAPI = Script.HookingPlugins.get("PlaceholderAPI");
        if (placeholderAPI) {
            temp[0] = PlaceholderAPI.setPlaceholders(player, temp[0]);
        }
        return temp[0];
    }

    public void run(String command, Player player, ScriptVarible scriptVarible, String VarName) {
        command = placeholder(command, scriptVarible, player);
        if (command.startsWith("player.")) {
            String cmd = command.replace("player.", "");
            if (cmd.startsWith("check.")) {
                cmd = cmd.replace("check.", "");
                PlayerCheck(command, player, scriptVarible, VarName, cmd);
            } else if (cmd.startsWith("sendMessage(")) {
                String string = cmd.replace("sendMessage(", "");
                string = string.replace(")", "");
                player.sendMessage(Utils.format(string));
            }
        } else if (command.startsWith("var.")) {
            String cmd = command.replace("var.", "");
            Var(command, player, scriptVarible, VarName, cmd);
        } else if (command.startsWith("if.")) {
            String cmd = command.replace("if.", "");
            IF(command, player, scriptVarible, VarName, cmd);
        }

        Utils.ConsoleInfoPrint("command: " + command);
        Utils.ConsoleInfoPrint("player.getName(): " + player.getName());
        Utils.ConsoleInfoPrint("scriptVarible: " + scriptVarible.memory.toString());
        Utils.ConsoleInfoPrint("VarName: " + VarName);
        Utils.ConsoleInfoPrint("----------------------------------");
        Utils.ConsoleInfoPrint("");
    }

    private void IF(String command, Player player, ScriptVarible scriptVarible, String VarName, String cmd) {
        if (cmd.toUpperCase().startsWith("EQUAL;")) {
            String inside = cmd.replace("EQUAL;", "");
            String[] conditions = inside.split(",");
            String c1 = conditions[0];
            String c2 = conditions[1].split(";")[0];
            if (c1.equals(c2)) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
        else if (cmd.toUpperCase().startsWith("MORE;")) {
            String inside = cmd.replace("MORE;", "");
            String[] conditions = inside.split(",");
            int c1 = Integer.parseInt(conditions[0]);
            int c2 = Integer.parseInt(conditions[1].split(";")[0]);
            if (c1 > c2) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
        else if (cmd.toUpperCase().startsWith("MOREEQUAL;")) {
            String inside = cmd.replace("MOREEQUAL;", "");
            String[] conditions = inside.split(",");
            int c1 = Integer.parseInt(conditions[0]);
            int c2 = Integer.parseInt(conditions[1].split(";")[0]);
            if (c1 >= c2) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
        else if (cmd.toUpperCase().startsWith("LESS;")) {
            String inside = cmd.replace("LESS;", "");
            String[] conditions = inside.split(",");
            int c1 = Integer.parseInt(conditions[0]);
            int c2 = Integer.parseInt(conditions[1].split(";")[0]);
            if (c1 < c2) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
        else if (cmd.toUpperCase().startsWith("LESSEQUAL;")) {
            String inside = cmd.replace("LESSEQUAL;", "");
            String[] conditions = inside.split(",");
            int c1 = Integer.parseInt(conditions[0]);
            int c2 = Integer.parseInt(conditions[1].split(";")[0]);
            if (c1 <= c2) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
        else if (cmd.toUpperCase().startsWith("BOOLEAN;")) {
            String inside = cmd.replace("BOOLEAN;", "");
            boolean c1 = Boolean.parseBoolean(inside.split(";")[0]);
            Utils.ConsoleInfoPrint(String.valueOf(c1));
            if (c1) {
                String IFCMD = inside.split(";")[1];
                Utils.ConsoleInfoPrint("IFCMD=" + IFCMD, false);
                String[] cmds = IFCMD.split(", ");
                Utils.ConsoleInfoPrint("cmds=" + Arrays.toString(cmds), false);
                if (cmds.length > 1) {
                    int i = 0;
                    for (String s : cmds) {
                        Utils.ConsoleInfoPrint("run(multi) key: " + i + " =>" + s, false);
                        run(s, player, scriptVarible, VarName);
                        i++;
                    }
                } else {
                    Utils.ConsoleInfoPrint("run(single)=>" + IFCMD, false);
                    run(IFCMD, player, scriptVarible, VarName);
                }
            }
        }
    }

    private void Var(String command, Player player, ScriptVarible scriptVarible, String VarName, String cmd) {
        if (cmd.startsWith("get(")) {
            cmd = cmd.replace("get(", "");
            cmd = cmd.replace(")", "");
        } else if (cmd.startsWith("set(")) {
            cmd = cmd.replace("set(", "");
            cmd = cmd.replace(")", "");
            String[] split = cmd.split(",");
            Utils.ConsoleInfoPrint(Arrays.toString(split));
            String varname = split[0];
            String value = split[1];
            if (value.startsWith("(i)")) {
                value = value.replace("(i)", "");
                scriptVarible.memory.put(varname, Integer.valueOf(value));
            } else if (value.startsWith("(d)")) {
                value = value.replace("(d)", "");
                scriptVarible.memory.put(varname, Double.valueOf(value));
            } else if (value.startsWith("(f)")) {
                value = value.replace("(f)", "");
                scriptVarible.memory.put(varname, Float.valueOf(value));
            } else if (value.startsWith("(b)")) {
                value = value.replace("(b)", "");
                scriptVarible.memory.put(varname, Boolean.valueOf(value));
            } else {
                scriptVarible.memory.put(varname, value);
            }
        }
    }

    private void PlayerCheck(String command, Player player, ScriptVarible scriptVarible, String VarName, String cmd) {
        if ("isOnline".equals(cmd)) {
            OfflinePlayer offlinePlayer = player;
            scriptVarible.memory.put("temp", offlinePlayer.isOnline());
        } else if (cmd.startsWith("isOnline(") && VarName != null) {
            String playername = cmd.replace("isOnline(", "");
            playername = playername.replace(")", "");
            playername = playername.replace("%" + VarName + "%", String.valueOf(scriptVarible.memory.get(VarName)));
            OfflinePlayer player1 = Utils.getPlayer(playername);
            scriptVarible.memory.put("temp", player1.isOnline());
        } else if (cmd.startsWith("isOnline(")) {
            String playername = cmd.replace("isOnline(", "");
            playername = playername.replace(")", "");
            scriptVarible.memory.put("temp", Utils.getPlayer(playername).isOnline());
        }
    }
}
