package me.gwanjong.gwanjung.WeaponEvent

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent

class HumanBowEvent(): Listener {

    @EventHandler
    fun onProjectileLaunch(event: EntityShootBowEvent) {
        val entity = event.entity
        if (entity.type != EntityType.PLAYER) return

        val player = event.entity as Player

        if (player.inventory.itemInMainHand.type != Material.BOW) return

        val item = player.inventory.itemInMainHand
        val displayName = item.itemMeta?.displayName ?: return

        if (displayName == "${ChatColor.LIGHT_PURPLE}인간활" || displayName == "${ChatColor.LIGHT_PURPLE}동무 미사일을 쏘는 무기") {
            player.setCooldown(player.inventory.itemInMainHand.type,10000)

            event.projectile.addScoreboardTag("Human_Bow")
        } else { return }


    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) { //투사체 착탄시 실행
        if (e.entity.scoreboardTags.contains("Human_Bow")) {  //만약 화살이 명령어로 생성되었다면
            val arrow = e.entity as Arrow //객체를 화살로 변환
            val player = arrow.shooter as Player
            player.noDamageTicks = 60
            arrow.world.createExplosion(arrow.location, 150f) //화살의 착탄 위치에 폭발 생성
            player.world.strikeLightning(arrow.location)
            arrow.damage = 1000.0
            player.teleport(arrow)

            arrow.remove() //화살 삭제
        }
    }

    @EventHandler
    fun playerDeadEvent(event : EntityDeathEvent) {
        if(event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player
        player.setCooldown(Material.BOW, 0)
    }

}