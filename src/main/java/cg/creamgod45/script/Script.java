package cg.creamgod45.script;

public class Script {
    private String Parent;
    private String Command;
    private String Name;
    private String Description;
    private Object Result;

    public Script(String p, String c, String n, String d, Object r) {
        Parent = p;
        Command = c;
        Name = n;
        Description = d;
        Result = r;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }
}
