package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.Main
import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue


fun singijeon(player: Player){

    val Lore = ArrayList<Component>()
    val GuidedArrow = MakeWeapon(ItemStack(Material.BOW), "신기전","신기전","우클릭을 누르면 100개의 화살이 발사된다","좌클릭을 하면 화살이 삭제된다", 200 ,Lore)
    Lore.add(Component.text("${ChatColor.BLUE}"))
    Lore.add(Component.text("${ChatColor.BLUE}무기 아이템"))

    GuidedArrow.lore(Lore)
    player.inventory.addItem(GuidedArrow)

}


class SingijeonEvent(): Listener {

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
            player.sendMessage("${ChatColor.LIGHT_PURPLE}화살이 제거되었습니다")
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
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.damage = 1000.0
            arrow.addScoreboardTag("singijeon")
        }
        player.setCooldown(player.inventory.itemInMainHand.type,200)

    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) { //투사체 착탄시 실행
        if (e.entity.scoreboardTags.contains("singijeon")) {  //만약 화살이 명령어로 생성되었다면
            val arrow = e.entity as Arrow //객체를 화살로 변환
            arrow.world.createExplosion(arrow.location, 5f) //화살의 착탄 위치에 폭발 생성
            arrow.remove() //화살 삭제
        }
    }
    /*
    최신코드
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
            val random = Random()
            val pr = player.launchProjectile(
                Arrow::class.java,
                getDir(
                    player.location.yaw.toDouble(),
                    player.location.direction.y,
                    random.nextDouble() * 45 - 22.5,
                    player
                )!!.multiply(2)
            )


            pr.isCritical = true
            pr.damage = 1000.0
        }

    }


    private fun getDir(yaw: Double, dirY: Double, angleAdd: Double, player: Player): Vector //바라보는 방향을 벡터로 가져오는 함수
    {
        val dirX = Math.cos(Math.toRadians(yaw + 90 + angleAdd))
        val dirZ = Math.sin(Math.toRadians(yaw + 90 + angleAdd))
        return Vector(dirX, dirY, dirZ)
    }
     */


}