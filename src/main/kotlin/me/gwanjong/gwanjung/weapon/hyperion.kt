package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.MakeWeapon
import me.gwanjong.gwanjung.cultural_language
import net.kyori.adventure.text.Component
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
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


fun Hyperion(): ItemStack {

    if(cultural_language) {
        val Lore = ArrayList<Component>()
        val Hyperion = MakeWeapon(ItemStack(Material.IRON_SWORD), "폭발적 축지법 칼","한손칼","오른쪽 누름을 하면 8브로크 앞으로 축지법이 사용되고 터짐을 일으킨다","들고 있으면 수령님의 은혜를 받아 폭발데미지를 입지 않는다", 100,Lore)
        Lore.add(Component.text("수령님이 개발하신 위대한 무기"))
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

        Hyperion.lore(Lore)
        return Hyperion


    } else {
        val Lore = ArrayList<Component>()
        val Hyperion = MakeWeapon(ItemStack(Material.IRON_SWORD), "Hyperion","한손검","우클릭시 앞으로 8칸 이동하고 폭발을 일으킨다","들고 있으면 폭발데미지를 입지 않는다", 100,Lore)
        Lore.add(Component.text("하이픽셀 스카이블럭의 최종 무기 중 하나"))
        Lore.add(Component.text("${ChatColor.BLUE}"))
        Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

        Hyperion.lore(Lore)
        return Hyperion


    }


}

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
        }else { return }

    }


    @EventHandler
    fun playerDeadEvent(event : EntityDeathEvent) {
        if(event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player
        player.setCooldown(Material.IRON_SWORD, 0)
    }
}