package me.gwanjong.gwanjung.Weapon.ElderWand

import io.papermc.paper.event.entity.EntityMoveEvent
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class ElderWandEvent():Listener {
    @EventHandler
    fun EladerWandEvent(event: PlayerInteractEvent) {
        val action = event.action
        val player = event.player
        if(player.inventory.itemInMainHand.type != Material.STICK) return
        if (action !in listOf(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val item = event.item
        val itemMeta = item!!.itemMeta
        val displayName = itemMeta!!.displayName

        if (displayName != "${ChatColor.LIGHT_PURPLE}딱총나무 지팡이") return
        if(player.getCooldown(Material.STICK) != 0) {
            player.sendMessage("${ChatColor.RED}사용대기시간이 남아 사용 할 수 없습니다")
            return
        }

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
            player.setCooldown(player.inventory.itemInMainHand.type,60)
        } else {
            val Fireball = player.launchProjectile(Fireball::class.java)
            Fireball.velocity = player.location.direction.multiply(5)
            player.setCooldown(player.inventory.itemInMainHand.type,60)
        }
    }

    @EventHandler
    fun onEntityMove(event: EntityMoveEvent) {
        val entity = event.entity
        val from = event.from
        val to = event.to
        val block = entity.location.block

        if (entity.isOnGround || entity.location.isBlock) {
            if (entity.customName == "cannon") {
                entity.world.createExplosion(entity.location, 5f)
                entity.remove()
            }
        }
    }

    @EventHandler
    fun playerDeadEvent(event : EntityDeathEvent) {
        if(event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player
        player.setCooldown(Material.STICK, 0)
    }

}