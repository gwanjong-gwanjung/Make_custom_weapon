package me.gwanjong.gwanjung

import io.github.monun.kommand.KommandArgument
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import me.gwanjong.gwanjung.Weapon.HumanBow.HumanBow
import me.gwanjong.gwanjung.Weapon.Hyperion.Hyperion
import me.gwanjong.gwanjung.Weapon.Singijeon.Singijeon
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

object Command {
    private lateinit var plugin: Main

    internal fun register(plugin: Main, kommand: PluginKommand) {
        this.plugin = plugin

        kommand.register("weapon") {

            executes {
                player.sendMessage("얻을 아이템을 정하세요")
            }

            then("inventory") {
                executes {
                    mainFrame(player)
                }
            }

            then("give") {

                then("ElderWand") {
                    executes {
                        /*
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        ElderWand(player)
                         */
                        if(cultural_language) {
                            player.sendMessage("간첩을 색출할 때까지 사용할 수 없습니다")
                        } else {
                            player.sendMessage("발사체 버그를 수정하기 전까지 사용 할 수 없습니다")
                        }
                    }
                }

                then("Hyperion") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(Hyperion())
                    }
                }
                then("HumanBow") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(HumanBow())
                    }
                }

                then("singijeon") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(Singijeon())
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


            then("language") {

                /*then("cultural_language") {
                    executes {
                        cultural_language = true
                        player.sendMessage("새로 지급되는 아이템은 이제부터" + "${ChatColor.LIGHT_PURPLE} 문화어" + "${ChatColor.WHITE}로 번역됩니다")
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}현재 번역률 100%")
                    }
                }
                 */

                then("korean") {
                    executes {
                        cultural_language = false
                        player.sendMessage("새로 지급되는 아이템은 이제부터" + "${ChatColor.LIGHT_PURPLE} 한국어" + "${ChatColor.WHITE}로 번역됩니다")
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}현재 번역률 100%")
                    }
                }
            }

        }
    }
}