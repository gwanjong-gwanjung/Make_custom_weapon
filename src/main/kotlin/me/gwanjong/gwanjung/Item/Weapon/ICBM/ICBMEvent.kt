package me.gwanjong.gwanjung.Item.Weapon.ICBM

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.ItemMeta

var input = false
var target = ""
var playername = ""

class ICBMEvent : Listener {

    @EventHandler
    fun ICBMRightClick(event: PlayerInteractEvent) {
        if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return

        val player = event.player
        val item = event.item ?: return
        val itemMeta = item.itemMeta as? ItemMeta ?: return
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

        fun boom() {
            val boom = 20f

            val player = event.player
            val location = player.location

            for (i in 0..50) {
                val boom10 = player.world.spawnEntity(Location(location.world, location.x, location.y + 50 - i, location.z), EntityType.ARMOR_STAND)
                player.world.createExplosion(boom10.location, boom)
            }

            for (i in 0..50) {

                val boom1 = player.world.spawnEntity(Location(location.world, location.x + i, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity
                val boom2 = player.world.spawnEntity(Location(location.world, location.x + i, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity
                val boom3 = player.world.spawnEntity(Location(location.world, location.x + i, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity

                val boom4 = player.world.spawnEntity(Location(location.world, location.x, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity

                val boom5 = player.world.spawnEntity(Location(location.world, location.x - i, location.y , location.z), EntityType.ARMOR_STAND) as LivingEntity
                val boom6 = player.world.spawnEntity(Location(location.world, location.x - i, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity
                val boom7 = player.world.spawnEntity(Location(location.world, location.x - i, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity

                val boom8 = player.world.spawnEntity(Location(location.world, location.x, location.y , location.z - i), EntityType.ARMOR_STAND) as LivingEntity
                val boom9 = player.world.spawnEntity(Location(location.world, location.x, location.y , location.z + i), EntityType.ARMOR_STAND) as LivingEntity


                player.world.createExplosion(boom1.location, boom)
                player.world.createExplosion(boom2.location, boom)
                player.world.createExplosion(boom3.location, boom)
                player.world.createExplosion(boom4.location, boom)
                player.world.createExplosion(boom5.location, boom)
                player.world.createExplosion(boom6.location, boom)
                player.world.createExplosion(boom7.location, boom)
                player.world.createExplosion(boom8.location, boom)
                player.world.createExplosion(boom9.location, boom)
            }

        }
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

        val plugin = Bukkit.getPluginManager().getPlugin("custom_weapon")
        val scheduler = plugin!!.server.scheduler
        scheduler.scheduleSyncDelayedTask(plugin, {

            boom()
            target = ""

            }, 280L
        )


    }
}