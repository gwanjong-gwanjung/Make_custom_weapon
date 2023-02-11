package me.gwanjong.gwanjung

import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.weapon.ElderWand
import me.gwanjong.gwanjung.weapon.ElderWandEvent
import me.gwanjong.gwanjung.weapon.Hyperion
import me.gwanjong.gwanjung.weapon.HyperionEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(){

    override fun onEnable() {

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)

        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

        kommand {
            register("weapon") {
                executes {
                    player.sendMessage("얻을 아이템을 정하세요")
                }

                then("ElderWand") {
                    executes {
                        ElderWand(player)
                    }
                }

                then("Hyperion") {
                    executes {
                        Hyperion(player)
                    }
                }

            }
        }


    }

    override fun onDisable() {

    }
}

