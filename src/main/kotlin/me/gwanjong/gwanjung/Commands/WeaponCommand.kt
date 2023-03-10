package me.gwanjong.gwanjung.Commands

import io.github.monun.kommand.KommandArgument
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBow
import me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow.HyperMonsterBow
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.Hyperion
import me.gwanjong.gwanjung.Item.Weapon.ICBM.ICBM
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.Singijeon
import me.gwanjong.gwanjung.Item.Weapon.SummonersWand.SummonersWand
import me.gwanjong.gwanjung.Main
import me.gwanjong.gwanjung.UI.MainUI
import me.gwanjong.gwanjung.cultural_language
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

object WeaponCommand {
    private lateinit var plugin: Main

    internal fun register(plugin: Main, kommand: PluginKommand) {
        WeaponCommand.plugin = plugin

        kommand.register("weapon") {

            then("inventory") {
                executes {
                    MainUI(player)
                }
            }

            then("give") {

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

                then("HyperMonsterBow") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(HyperMonsterBow())
                    }
                }

                then("singijeon") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(Singijeon())
                    }
                }
                then("ICBM") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(ICBM())
                    }
                }
                then("SummonersWand") {
                    executes {
                        player.sendMessage("${ChatColor.LIGHT_PURPLE}무기를 지급하였습니다")
                        player.inventory.addItem(SummonersWand())
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