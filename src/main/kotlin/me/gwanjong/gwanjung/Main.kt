package me.gwanjong.gwanjung

import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.Starforce.Starforce
import me.gwanjong.gwanjung.Starforce.StarforceScroll
import me.gwanjong.gwanjung.Starforce.startStarForce
import me.gwanjong.gwanjung.weapon.*
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(){

    override fun onEnable() {

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)
        //Bukkit.getPluginManager().registerEvents(HumanBowEvent(), this)


        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

        kommand {
            register("weapon") {
                requires { isPlayer && isOp }
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
                then("HumanBow") {
                    executes {
                        player.sendMessage("개발중")
                        //HumanBow(player)
                    }
                }

            }

        }


    }

    override fun onDisable() {

    }
}

