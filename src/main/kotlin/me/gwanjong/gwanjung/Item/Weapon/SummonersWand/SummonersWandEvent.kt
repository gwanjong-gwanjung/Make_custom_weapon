package me.gwanjong.gwanjung.Item.Weapon.SummonersWand

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Trident
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitTask

class SummonersWandEvent : Listener {

    @EventHandler
    fun SummonersWandSkill(event: PlayerInteractEvent) {
        val player = event.player
        if(player.inventory.itemInMainHand.type == Material.AIR) return
        if(player.inventory.itemInMainHand.itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}소환사의 지팡이") return
        if (event.action !in setOf(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK)) return

        val trident = player.launchProjectile(Trident::class.java)
        trident.damage = 10.0
        trident.setGravity(false)
        trident.addScoreboardTag("SummonersWand")

        val plugin = Bukkit.getPluginManager().getPlugin("custom_weapon")
        val scheduler = plugin!!.server.scheduler
        var time = 0

        scheduler.runTaskTimer(plugin, { task: BukkitTask ->
            time++
            val fang = trident.world.spawnEntity(trident.location.add(0.0,-1.0,0.0), EntityType.EVOKER_FANGS)
            if(time == 100) {
                task.cancel()
                trident.remove()
            }

            if(trident.isDead) {
                task.cancel()
            }


        }, 0L, 1L)

    }

    @EventHandler
    fun SummonersWandHit(event: ProjectileHitEvent) {
        if(event.entity.scoreboardTags.contains("SummonersWand")) {
            event.entity.remove()
        }
    }



}


