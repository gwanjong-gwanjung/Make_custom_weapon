package me.gwanjong.gwanjung

import io.github.monun.kommand.KommandArgument
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.weapon.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), Listener{


    override fun onEnable() {

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)
        Bukkit.getPluginManager().registerEvents(SingijeonEvent(), this)
        Bukkit.getPluginManager().registerEvents(this, this)
        Bukkit.getPluginManager().registerEvents(HumanBowEvent(), this)


        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

        kommand {
            register("weapon") {

                executes {
                    player.sendMessage("얻을 아이템을 정하세요")
                }
                then("give") {

                    then("ElderWand") {
                        executes {
                            player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                            ElderWand(player)
                        }
                    }

                    then("Hyperion") {
                        executes {
                            player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                            Hyperion(player)
                        }
                    }
                    then("HumanBow") {
                        executes {
                            player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                            HumanBow(player)
                        }
                    }

                    then("singijeon") {
                        executes {
                            player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                            singijeon(player)
                        }
                    }
                }

                then("develop") {
                    then("setcooltime") {
                        requires {
                            playerOrNull != null
                        }

                        then("item" to KommandArgument.item(), "tick" to int()) {
                            executes {
                                val item: ItemStack by it
                                val tick: Int by it
                                player.setCooldown(item.type, tick)
                                player.sendMessage("${ChatColor.LIGHT_PURPLE}${item}의 쿨타임을 ${tick}틱으로 설정하였습니다")
                            }
                        }
                    }

                }

            }

        }


    }
}
