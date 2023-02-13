package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.Main
import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.metadata.FixedMetadataValue

fun GuidedArrow(player: Player){

    val Lore = ArrayList<Component>()
    val GuidedArrow = MakeWeapon(ItemStack(Material.BOW), "신기전","신기전","우클릭을 누르면 100개의 화살이 발사된다","좌클릭을 하면 화살이 삭제된다", Lore)

    GuidedArrow.lore(Lore)
    player.inventory.addItem(GuidedArrow)

}


class GuidedArrowEvent(): Listener {

    private fun countArrows(player: Player): Int {
        var arrowCount = 0
        for (item in player.inventory.contents) {
            if (item?.type == Material.ARROW) {
                arrowCount += item.amount
            }
        }
        return arrowCount
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {

        if (event.action !in setOf(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR)) return

        val player = event.player
        if(player.inventory.itemInMainHand.type != Material.BOW) return

        val item = player.inventory.itemInMainHand
        val displayName = item.itemMeta?.displayName ?: return

        if (displayName != "${ChatColor.LIGHT_PURPLE}신기전") return

        val arrowCount = countArrows(player)
        if (event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK) {

            for(world in Bukkit.getWorlds()) {
                for(entity in world.entities) {
                    if(entity is Arrow) {
                    entity.remove()
                    }
                }
            }
        }
    }

    @EventHandler
    fun Shot(event: EntityShootBowEvent) {


        val entity = event.entity
        if(entity.type != EntityType.PLAYER) return

        val player = event.entity as Player
        if(player.inventory.itemInMainHand.type != Material.BOW) return

        val item = player.inventory.itemInMainHand
        val displayName = item.itemMeta?.displayName ?: return

        if (displayName != "${ChatColor.LIGHT_PURPLE}신기전") return
        event.isCancelled = true

        val arrowCount = countArrows(player)

        if (player.gameMode == GameMode.SURVIVAL || player.gameMode == GameMode.ADVENTURE) {
            if (arrowCount < 100) {
                player.sendMessage("${ChatColor.LIGHT_PURPLE}화살이 부족합니다")
                return
            }

            for (i in 0..99) {
                player.inventory.removeItem(ItemStack(Material.ARROW))
            }
        }
        for (i in 0..99) {
            player.launchProjectile(Arrow::class.java).damage = 1000.0
        }

    }


}