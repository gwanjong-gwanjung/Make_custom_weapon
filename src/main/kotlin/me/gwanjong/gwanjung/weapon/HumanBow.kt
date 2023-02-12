package me.gwanjong.gwanjung.weapon

import me.gwanjong.gwanjung.Main
import me.gwanjong.gwanjung.MakeWeapon
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.inventory.ItemStack


fun HumanBow(player: Player){
    val Lore = ArrayList<Component>()
    val HumanBow = MakeWeapon(ItemStack(Material.BOW), "인간 활","활","이 활로 화살을 발사하면 자신이 화살이 되어 날아간다","들고 있을때 낙하 대미지를 입지 않는다", Lore)
    HumanBow.lore(Lore)
    player.inventory.addItem(HumanBow)

}

class HumanBowEvent(): Listener {

    @EventHandler
    fun onProjectileLaunch(event: ProjectileLaunchEvent) {
        val entity = event.entity
        val player = entity.shooter as Player
        val item = player.itemInHand
        val itemMeta = item!!.itemMeta
        val displayName = itemMeta!!.displayName
        if (entity !is Arrow && entity.shooter !is Player) return
        if (displayName != "${ChatColor.LIGHT_PURPLE}인간 활") return
        entity.customName = "HumanBow"
        var taskId: Int = -1

        taskId = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(Main(), {
            if (player.location.distance(entity.location) > 1) {
                player.teleport(entity.location)
            } else {
                Bukkit.getScheduler().cancelTask(taskId)
            }

        }, 0L, 1L)

    }

}