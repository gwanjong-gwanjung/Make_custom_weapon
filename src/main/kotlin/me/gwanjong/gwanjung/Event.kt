package me.gwanjong.gwanjung

import me.gwanjong.gwanjung.UI.MainUI
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
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

}