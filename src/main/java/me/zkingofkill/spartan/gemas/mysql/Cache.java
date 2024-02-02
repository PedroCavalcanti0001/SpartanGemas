package me.zkingofkill.spartan.gemas.mysql;

import me.zkingofkill.spartan.gemas.SpartanGemas;
import me.zkingofkill.spartan.gemas.objects.Gemas;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<Gemas> gemas = new ArrayList<>();

    public void setup(){
        SpartanGemas.getInstance().getMysql().loadGemas();
        new BukkitRunnable() {
            @Override
            public void run() {
                saveGemas();
            }
        }.runTaskTimer(SpartanGemas.getInstance(), 20*5*60,20*5*60);
    }
    public void saveGemas(){
        for(Gemas gemas : gemas){
            if(gemas.isModified()){
                if(SpartanGemas.getInstance().getMysql().hasGemas(gemas.getPlayer())){
                    SpartanGemas.getInstance().getMysql().updateGema(gemas);
                }else{
                    SpartanGemas.getInstance().getMysql().insertGema(gemas);
                }
            }
        }
    }
    public Gemas getGemas(OfflinePlayer player){
        return gemas.stream().filter(gemas -> gemas.getPlayer().equals(player)).findAny().orElseGet(() ->{Gemas g = new Gemas(0.0, player); gemas.add(g); return g; });
    }

    public List<Gemas> getGemas() {
        return gemas;
    }
}
