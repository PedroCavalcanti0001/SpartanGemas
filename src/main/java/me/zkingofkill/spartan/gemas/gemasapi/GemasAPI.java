package me.zkingofkill.spartan.gemas.gemasapi;

import me.zkingofkill.spartan.gemas.SpartanGemas;
import me.zkingofkill.spartan.gemas.objects.Gemas;
import org.bukkit.OfflinePlayer;

public class GemasAPI {

    public static Gemas gemas(OfflinePlayer offlinePlayer){
        return SpartanGemas.getInstance().getCache().getGemas(offlinePlayer);
    }

}
