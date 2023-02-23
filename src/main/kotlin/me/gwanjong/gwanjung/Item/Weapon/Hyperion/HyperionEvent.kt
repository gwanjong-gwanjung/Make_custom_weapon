package me.gwanjong.gwanjung.Item.Weapon.Hyperion

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.ItemMeta

class HyperionEvent(): Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (event.entity !is Player || event.isCancelled) return

        val itemInHand = (event.entity as Player).itemInHand
        if (itemInHand.type != Material.IRON_SWORD) return

        val itemMeta = itemInHand.itemMeta
        if (itemMeta.displayName == "${ChatColor.LIGHT_PURPLE}Hyperion" || itemMeta.displayName == "${ChatColor.LIGHT_PURPLE}폭발적 축지법 칼") {
            if (event.cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                event.isCancelled = true
            }

        } else{ return }


    }

    @EventHandler
    fun HyperionEvent(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta as? ItemMeta ?: return

        if (itemMeta.displayName == "${ChatColor.LIGHT_PURPLE}Hyperion" || itemMeta.displayName == "${ChatColor.LIGHT_PURPLE}폭발적 축지법 칼") {
            val blockInFront = player.getTargetBlock(setOf(Material.AIR), 1)
            if (blockInFront.type != Material.AIR) return

            if(player.getCooldown(Material.IRON_SWORD) != 0) { return }

            player.world.createExplosion(player.location, 10f)
            val currentLocation = player.location
            val forward = player.eyeLocation.direction.normalize()
            val newLocation = currentLocation.add(forward.multiply(8.0))
            player.teleport(newLocation)
            player.world.createExplosion(player.location, 10f)
            player.setCooldown(player.inventory.itemInMainHand.type,100)
        } else { return }

    }


    @EventHandler
    fun playerDeadEvent(event : EntityDeathEvent) {
        if(event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player
        player.setCooldown(Material.IRON_SWORD, 0)
    }
}