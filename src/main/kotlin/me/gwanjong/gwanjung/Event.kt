package me.gwanjong.gwanjung

import me.gwanjong.gwanjung.Enchant.EnchantUI
import me.gwanjong.gwanjung.Item.DropItem.ElderGuardianEye
import me.gwanjong.gwanjung.Item.DropItem.WardenHeart
import me.gwanjong.gwanjung.UI.MainUI
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent

class Event : Listener {

    @EventHandler
    fun Event(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val player = event.player
        val item = event.item ?: return

        if(item.type == Material.BOOK) {
            MainUI(player)
        }

    }

    @EventHandler
    fun Enchantment(event: InventoryOpenEvent) {
        if(event.inventory.type != InventoryType.ENCHANTING) return
        val player = event.player as Player
        event.isCancelled = true
        EnchantUI().open(player)
    }


    @EventHandler
    fun WardenDeathEvent(event: EntityDeathEvent) {
        if (event.entity.type != EntityType.WARDEN) return
        event.entity.world.dropItem(event.entity.location, WardenHeart())
    }

    @EventHandler
    fun ElderGuardianDeathEvent(event: EntityDeathEvent) {
        if (event.entity.type != EntityType.ELDER_GUARDIAN) return
        event.entity.world.dropItem(event.entity.location, ElderGuardianEye())
    }

}