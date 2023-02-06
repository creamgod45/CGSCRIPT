package cg.creamgod45.script;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScriptRunManager {
    public static Boolean debug = false;
    private List<String> Actions = new ArrayList<>();
    private ScriptVarible scriptVarible = new ScriptVarible();
    private ScriptParser scriptParser = new ScriptParser();

    public ScriptRunManager(List<String> actions) {
        Actions = actions;
        debug = false;
    }

    public void RunScript(Player p) {
        for (String action : Actions) {
            //Script.plugin.getServer().getScheduler().runTask(Websocket.plugin, () -> scriptParser.run(action, p, scriptVarible, null));
            scriptParser.run(action, p, scriptVarible, null);
        }
    }

    public ScriptVarible getScriptVarible() {
        return scriptVarible;
    }

    public void setScriptVarible(ScriptVarible scriptVarible) {
        this.scriptVarible = scriptVarible;
    }
}
