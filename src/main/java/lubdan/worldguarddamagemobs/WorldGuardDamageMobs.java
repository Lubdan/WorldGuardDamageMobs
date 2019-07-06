package lubdan.worldguarddamagemobs;


import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class WorldGuardDamageMobs extends JavaPlugin implements Listener {
    public static final Flag MY_CUSTOM_FLAG = new StateFlag("player-damage-mobs", true);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);}

    @Override
    public void onLoad(){FlagRegistry registry = WorldGuardPlugin.inst().getFlagRegistry();registry.register(MY_CUSTOM_FLAG);}

    @EventHandler
    public void preventDamage(EntityDamageByEntityEvent event){if(event.getDamager().getType() == EntityType.PLAYER && !WorldGuardPlugin.inst().getRegionManager(event.getDamager().getWorld()).getApplicableRegions(event.getDamager().getLocation()).allows((StateFlag)MY_CUSTOM_FLAG) && event.getEntity().getType() != EntityType.PLAYER){ event.setCancelled(true);}}




}
