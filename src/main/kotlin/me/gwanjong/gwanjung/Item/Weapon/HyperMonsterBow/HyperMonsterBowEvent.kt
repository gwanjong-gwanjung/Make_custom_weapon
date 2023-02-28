package me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow

import me.gwanjong.gwanjung.tool.Boom
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class HyperMonsterBowEvent : Listener {

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
    fun HyperMonsterBowEvent(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.LEFT_CLICK_BLOCK, Action.LEFT_CLICK_AIR)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta ?: return

        if (itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}HyperMonsterBow") return

        if(player.gameMode == GameMode.ADVENTURE || player.gameMode == GameMode.SURVIVAL) {
            if (countArrows(player) == 0) {
                player.sendMessage("${ChatColor.RED}화살이 부족합니다")
                return
            } else {
                player.inventory.removeItem(ItemStack(Material.ARROW))
            }
        }

        val arrow = player.launchProjectile(Arrow::class.java)
        arrow.damage = 4.0
        arrow.addScoreboardTag("HyperMonsterBow")
    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) {
        if (e.entity.scoreboardTags.contains("HyperMonsterBow")) {
            val arrow = e.entity as Arrow
            val world = arrow.world
            val random = (0..2)

            if(random.random() == 0) {
                val creeper = world.spawnEntity(arrow.location, EntityType.CREEPER)
                creeper.addScoreboardTag("HyperMonsterBowCreeper")
            }
            if(random.random() == 1) {
                val creeper = world.spawnEntity(arrow.location, EntityType.ZOMBIE)
                creeper.addScoreboardTag("HyperMonsterBowZombie")
            }
            if(random.random() == 1) {
                val creeper = world.spawnEntity(arrow.location, EntityType.SKELETON)
                creeper.addScoreboardTag("HyperMonsterBowSkeleton")
            }
        }
    }

    @EventHandler
    fun EntityDeathEvent(event: EntityDeathEvent) {
        val entity = event.entity
        val world = entity.world

        if(entity.type !in setOf(EntityType.SKELETON, EntityType.CREEPER, EntityType.ZOMBIE)) return

        if(entity.scoreboardTags.contains("HyperMonsterBowCreeper")) {
            Boom(entity.location,3,5,1)
        }

        if(entity.scoreboardTags.contains("HyperMonsterBowZombie")) {
            for(i in 0..5) {
                val zombie = world.spawnEntity(entity.location, EntityType.ZOMBIE)
                zombie.addScoreboardTag("HyperMonsterBowZombie")
            }
        }
    }

    @EventHandler
    fun EntityShootBowEvent(event: EntityShootBowEvent) {
        val entity = event.entity
        if(entity.type != EntityType.SKELETON) return

        if(!entity.scoreboardTags.contains("HyperMonsterBowSkeleton")) return

        event.isCancelled = true

        for (i in 0..99) {
            val arrow = entity.launchProjectile(Arrow::class.java)
            arrow.damage = 1.0

        }
    }


}