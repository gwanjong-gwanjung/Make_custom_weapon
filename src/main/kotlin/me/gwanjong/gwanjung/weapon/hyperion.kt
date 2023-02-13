package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


fun Hyperion(player: Player){

    val Lore = ArrayList<Component>()
    val Hyperion = MakeWeapon(ItemStack(Material.IRON_SWORD), "Hyperion","한손검","우클릭시 앞으로 8칸 이동하고 폭발을 일으킨다","들고 있으면 폭발데미지를 입지 않는다", Lore)
    Lore.add(Component.text("하이픽셀 스카이블럭의 최종 무기 중 하나"))


    Hyperion.lore(Lore)
    player.inventory.addItem(Hyperion)

}

class HyperionEvent(): Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (event.entity !is Player || event.isCancelled) return

        val itemInHand = (event.entity as Player).itemInHand
        if (itemInHand.type != Material.IRON_SWORD) return

        val itemMeta = itemInHand.itemMeta
        if (!itemMeta.hasDisplayName() || itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}Hyperion") return

        if (event.cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun HyperionEvent(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta as? ItemMeta ?: return

        if (itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}Hyperion") return

        val blockInFront = player.getTargetBlock(setOf(Material.AIR), 1)
        if (blockInFront.type != Material.AIR) return

        player.world.createExplosion(player.location, 10f)
        val currentLocation = player.location
        val forward = player.eyeLocation.direction.normalize()
        val newLocation = currentLocation.add(forward.multiply(8.0))
        player.teleport(newLocation)
        player.world.createExplosion(player.location, 10f)
    }
}