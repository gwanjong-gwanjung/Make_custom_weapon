package me.gwanjong.gwanjung.Item.Weapon.ICBM

import me.gwanjong.gwanjung.tool.Boom
import me.gwanjong.gwanjung.tool.Timer
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerInteractEvent

var input = false
var target = ""
var playername = ""

class ICBMEvent : Listener {

    @EventHandler
    fun ICBMRightClick(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta ?: return
        if(event.player.inventory.itemInMainHand.itemMeta == null) return
        if (itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}ICBM") return

        if(player.getCooldown(Material.STICK) != 0) {
            player.sendMessage("${ChatColor.RED}아직 발사 준비가 되지 않았습니다")
            return
        }

        target = ""

        player.sendMessage("지정할 플레이어의 닉네임을 채팅창에 입력해주세요")
        input = true
        playername = event.player.name
    }



    @EventHandler
    fun onAsyncChat(event: PlayerChatEvent) {


        if(event.player.inventory.itemInMainHand.itemMeta == null) return
        if(event.player.inventory.itemInMainHand.itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}ICBM") return
        if(!input) return
        if (event.player.name != playername) return

        event.isCancelled = true
        val player = event.player
        val chat = event.message.lowercase()
        target = chat
        if(Bukkit.getPlayer(chat)== null) {
            player.sendMessage("${ChatColor.RED}해당하는 플레이어가 없습니다")
            input = false
            return
        }
        input = false
        val targetPlayer: Player? = Bukkit.getPlayer(chat)

        player.sendMessage("${ChatColor.LIGHT_PURPLE}타겟이 ${targetPlayer?.name}으로 지정되었습니다")

        targetPlayer?.sendTitle("So Long.......", "${ChatColor.RED}당신은 ${player.name}님의 타겟이 되었습니다", 40, 200, 40)
        player.setCooldown(Material.STICK, 100000)

        val timer : Timer

        class Time : Timer() {
            override fun Time(): Int { return 280 }
            override fun player(): Player? { return targetPlayer }
            override fun run() {
                Boom(targetPlayer!!.location, 20,100, 70)
                target = ""
            }
        }

        Time()

    }
}

@EventHandler
fun playerDeadEvent(event : EntityDeathEvent) {
    if(event.entity.type != EntityType.PLAYER) return
    val player = event.entity as Player
    player.setCooldown(Material.STICK, 0)
}

