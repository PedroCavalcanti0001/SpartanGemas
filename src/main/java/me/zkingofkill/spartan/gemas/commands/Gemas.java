package me.zkingofkill.spartan.gemas.commands;

import me.zkingofkill.spartan.gemas.SpartanGemas;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gemas implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){
                me.zkingofkill.spartan.gemas.objects.Gemas gemas = SpartanGemas.getInstance().getCache().getGemas((Player) sender);
                sender.sendMessage("§6Suas gemas: §e"+gemas.getFormatedGemas());
            }else{
                sender.sendMessage("Use /gemas ajuda");
            }
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("ajuda")){
                sender.sendMessage("§6§l* Gemas");
                sender.sendMessage("§c/gemas [jogador]");
                if(sender.hasPermission("gemas.admin")){
                    sender.sendMessage("§cUse /gemas dar <Jogador> <valor>");
                    sender.sendMessage("§cUse /gemas remover <jogador> <valor>");
                }
            }else if(Bukkit.getOfflinePlayer(args[0]) != null) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                if (player != null) {
                    sender.sendMessage("§6Gemas de " + player.getName() + ": §e" + SpartanGemas.getInstance().getCache().getGemas(player).getFormatedGemas());
                } else {
                    sender.sendMessage("§cPor favor digite um nome de um jogador valido!");
                }
            }else{
                sender.sendMessage("§cUse /gemas ajuda!");
            }
        }else if(args.length >= 3){
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
            me.zkingofkill.spartan.gemas.objects.Gemas gemas = SpartanGemas.getInstance().getCache().getGemas(player);
            double valor = Double.parseDouble(args[2]);
            if(args[0].equalsIgnoreCase("dar")){
                sender.sendMessage("§aGemas depositadas com sucesso!");
                gemas.deposit(valor);
            }else if(args[0].equalsIgnoreCase("remover")){
                if(valor <= gemas.getGemas()) {
                    gemas.withdraw(valor);
                    sender.sendMessage("§cGemas removidas com sucesso!");
                }else{
                    sender.sendMessage("§cO valor a ser removido precisa ser menor que a quantidade de gemas do jogador!");
                }
            }
        }
        return false;
    }
}
