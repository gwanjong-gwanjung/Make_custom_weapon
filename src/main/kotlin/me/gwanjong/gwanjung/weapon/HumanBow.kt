package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
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
import org.bukkit.inventory.ItemStack


fun HumanBow(player: Player){
    val Lore = ArrayList<Component>()
    val HumanBow = MakeWeapon(ItemStack(Material.BOW), "인간활","활","이 활로 화살을 발사하면 화살이 발사된 위치에 자신이 텔레포트된다","능력 사용 후 무적타임 3초가 주어진다", 10000,Lore)
    Lore.add(Component.text("${ChatColor.BLUE}"))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))
    HumanBow.lore(Lore)
    player.inventory.addItem(HumanBow)

}

class HumanBowEvent(): Listener {

    @EventHandler
    fun onProjectileLaunch(event: EntityShootBowEvent) {
        val entity = event.entity
        if (entity.type != EntityType.PLAYER) return

        val player = event.entity as Player

        if (player.inventory.itemInMainHand.type != Material.BOW) return

        val item = player.inventory.itemInMainHand
        val displayName = item.itemMeta?.displayName ?: return

        if (displayName != "${ChatColor.LIGHT_PURPLE}인간활") return


        player.setCooldown(player.inventory.itemInMainHand.type,10000)

        event.projectile.addScoreboardTag("Human_Bow")

    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) { //투사체 착탄시 실행
        if (e.entity.scoreboardTags.contains("Human_Bow")) {  //만약 화살이 명령어로 생성되었다면
            val arrow = e.entity as Arrow //객체를 화살로 변환
            val player = arrow.shooter as Player
            player.noDamageTicks = 60
            arrow.world.createExplosion(arrow.location, 150f) //화살의 착탄 위치에 폭발 생성
            player.world.strikeLightning(arrow.location)
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