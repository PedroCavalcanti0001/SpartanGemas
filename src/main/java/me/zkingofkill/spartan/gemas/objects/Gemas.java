package me.zkingofkill.spartan.gemas.objects;

import me.zkingofkill.spartan.gemas.utils.Utils;
import org.bukkit.OfflinePlayer;

public class Gemas {
    private OfflinePlayer player;
    private double gemas;
    private boolean modified;

    public void deposit(double gemas){
        this.gemas = this.gemas+gemas;
        this.modified = true;
    }
    public void withdraw(double gemas){
        this.gemas = this.gemas-gemas;
        this.modified = true;
    }
    public double getGemas(){
        return gemas;
    }

    public String getFormatedGemas(){
        return Utils.numberFormat(gemas);
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public Gemas(double gemas, OfflinePlayer player) {
        this.gemas = gemas;
        this.player = player;
    }
}
