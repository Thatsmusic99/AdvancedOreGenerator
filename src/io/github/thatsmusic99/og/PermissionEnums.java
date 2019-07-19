package io.github.thatsmusic99.og;

public enum PermissionEnums {

    ALLOWED_WORLDS("worlds [Page #]", "Shows worlds in which the Ore Generator is working.", "aog.view.worlds"),
    DENIED_WORLDS("dworlds [Page #]", "Shows worlds in which the Ore Generator is disabled.", "aog.view.disabled"),
    ADD_CUSTOM("addcustom <World name>", "Allows a world to have a custom setup.", "aog.add.custom"),
    DEL_CUSTOM("delcustom <World name>", "Sets a world back to the default generator settings.", "aog.del.custom"),
    ADD_WORLD("disable <World name>", "Adds a world to the list in which ore generating is disabled (stops ores from generating).", "aog.disable"),
    DEL_WORLD("enable <World name>", "Removes a world to the list in which ore generating is disabled (allows ores to generate in that world).", "aog.enable"),
    WORLD("world <World name>", "Views world information.", "aog.view.world"),
    RELOAD("reload", "Reloads the configuration.", "aog.reload"),
    HELP("help [Page #]", "Displays the help menu.", "aog.view.help"),
    INFO("info", "Displays plugin information.", "aog.info");

    public String cmd;
    public String desc;
    public String perm;

    PermissionEnums(String c, String d, String p) {
        this.cmd = c;
        this.desc = d;
        this.perm = p;
    }

}
