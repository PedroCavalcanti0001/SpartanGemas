package me.zkingofkill.spartan.gemas.hooks;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.zkingofkill.spartan.gemas.gemasapi.GemasAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlaceHolderAPI extends EZPlaceholderHook {

    public PlaceHolderAPI(Plugin plugin,String identifier){
        super(plugin, identifier);
    }

    public String onPlaceholderRequest(final Player p, final String i) {
        if (p == null) {
            return null;
        }
        if (i.equals("format")) {
            return GemasAPI.gemas(p).getFormatedGemas();
        }
        return null;
    }
}
