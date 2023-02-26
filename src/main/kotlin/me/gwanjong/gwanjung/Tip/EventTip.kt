package me.gwanjong.gwanjung.Tip

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class EventTip {

    //클릭 이벤트
    @EventHandler
    fun ClickEvent(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.LEFT_CLICK_BLOCK, Action.LEFT_CLICK_AIR)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta ?: return

        if (itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}HyperMonsterBow") return

    }




}