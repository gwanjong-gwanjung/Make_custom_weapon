package me.gwanjong.gwanjung.weapon

import io.papermc.paper.event.entity.EntityMoveEvent
import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.scoreboard.Criteria.AIR

fun ElderWand(player: Player){
    val Lore = ArrayList<Component>()
    val ElderWand = MakeWeapon(ItemStack(Material.STICK), "딱총나무 지팡이","마법 지팡이","좌클릭시 전방으로 미사일을 발사한다","우클릭시 전방으로 화염구를 발사한다", Lore)
    Lore.add(Component.text("해리 포터 시리즈의 죽음의 성물 중 하나"))
    Lore.add(Component.text("지팡이의 재료는 딱총나무와 세스트럴의 꼬리털이라고 한다."))
    Lore.add(Component.text("다른 지팡이에 비하여 월등한 능력을 가진 것으로 묘사되며, 최강의 지팡이로 거론되는 죽음의 성물 중 하나다."))

    ElderWand.lore(Lore)
    player.inventory.addItem(ElderWand)

}

class ElderWandEvent():Listener{
    @EventHandler
    fun EladerWandEvent(event: PlayerInteractEvent) {
        val action = event.action
        val player = event.player

        if (action !in listOf(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val item = event.item
        val itemMeta = item!!.itemMeta
        val displayName = itemMeta!!.displayName

        if (displayName != "${ChatColor.LIGHT_PURPLE}딱총나무 지팡이") return

        fun setCannonStats(entity: LivingEntity) {
            val location = player.eyeLocation
            entity.equipment!!.helmet = ItemStack(Material.BLACKSTONE)
            entity.isInvisible = true
            entity.maxHealth = 100.0
            entity.health = 100.0
            entity.velocity = location.direction.multiply(3)
            entity.customName="cannon"
        }

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            val cannon = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as LivingEntity
            setCannonStats(cannon)
            cannon.isInvulnerable = true
        } else {
            val Fireball = player.launchProjectile(Fireball::class.java)
            Fireball.velocity = player.location.direction.multiply(5)
        }
    }

    @EventHandler
    fun onEntityMove(event: EntityMoveEvent) {
        val entity = event.entity
        val from = event.from
        val to = event.to
        val block = entity.location.block

        if (entity.isOnGround || isTouchingWall(to,from)) {
            if (entity.customName == "cannon") {
                entity.world.createExplosion(entity.location, 5f)
                entity.remove()
            }
        }
    }

    private fun isTouchingWall(to: Location, from: Location): Boolean {
        val blockTo = to.block
        val blockFrom = from.block
        return blockTo.type.isSolid || blockFrom.type.isSolid
    }
}