package me.gwanjong.gwanjung.tool

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask

/**
 * run -> 동작이 끝나면 명령
 *
 * message -> 알림 사용설정
 *
 * timerSound -> 1초단위로 나는 소리
 *
 * Time - > 시간설정
 */
open class Timer {

    init {
        val plugin = Bukkit.getPluginManager().getPlugin("custom_weapon")
        val scheduler = plugin!!.server.scheduler
        var time = 0

        scheduler.runTaskTimer(plugin, { task: BukkitTask ->
            time++
            if((Time() - time)%20 == 0) {
                player()?.sendMessage("${(Time() - time)/20}${message()}")
                timerSound()
            }
            if (time == Time()) {
                task.cancel()
                run()
            }
        }, 20L, 1L)



    }

    open fun player(): Player? {return null}
    open fun Time() : Int {return 0}
    open fun message(): String {return "초 남았습니다"}
    open fun run() {}
    open fun timerSound() {}

}

