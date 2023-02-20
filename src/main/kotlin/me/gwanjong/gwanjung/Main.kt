package me.gwanjong.gwanjung

import io.github.monun.kommand.KommandArgument
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.Weapon.HumanBow
import me.gwanjong.gwanjung.Weapon.Hyperion
import me.gwanjong.gwanjung.Weapon.Singijeon
import me.gwanjong.gwanjung.WeaponEvent.ElderWandEvent
import me.gwanjong.gwanjung.WeaponEvent.HumanBowEvent
import me.gwanjong.gwanjung.WeaponEvent.HyperionEvent
import me.gwanjong.gwanjung.WeaponEvent.SingijeonEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

var cultural_language = false

class Main : JavaPlugin(), Listener{

    override fun onEnable() {

        cultural_language = false

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)
        Bukkit.getPluginManager().registerEvents(SingijeonEvent(), this)
        Bukkit.getPluginManager().registerEvents(HumanBowEvent(), this)

        HumanBowRecipe()
        HyperionRecipe()
        SingijeonRecipe()

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

    private fun HumanBowRecipe() {
        server.addRecipe(
            ShapedRecipe(
                NamespacedKey.minecraft("playerhead"),
                ItemStack(Material.PLAYER_HEAD)
            ).apply {
                shape("ABC", "ABC", "ABC")
                setIngredient('A', Material.REDSTONE)
                setIngredient('B', Material.BONE)
                setIngredient('C', Material.ROTTEN_FLESH)
            }
        )


        server.addRecipe(
            ShapedRecipe(
                NamespacedKey.minecraft("humanbow"),
                HumanBow()
            ).apply {
                shape(
                    "HHH",
                    "HBH",
                    "HHH"
                )
                setIngredient('H', ItemStack(Material.PLAYER_HEAD))
                setIngredient('B', ItemStack(Material.BOW))
            }
        )
    }

    private fun HyperionRecipe() {
        server.addRecipe(
            ShapedRecipe(
                NamespacedKey.minecraft("hyperion"),
                Hyperion()
            ).apply {
                shape("CCC", "CSC", "CCC")
                setIngredient('S', Material.IRON_SWORD)
                setIngredient('C', Material.END_CRYSTAL)
            }
        )

    }

    private fun SingijeonRecipe() {
        val tntarrow = ItemStack(Material.ARROW).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("${ChatColor.LIGHT_PURPLE}폭발화살"))
            }
        }

        server.addRecipe(
            ShapedRecipe(
                NamespacedKey.minecraft("tntarrow"),
                tntarrow
            ).apply {
                shape(
                    "AAA",
                    "ATA",
                    "AAA"
                )
                setIngredient('T', ItemStack(Material.TNT))
                setIngredient('A', ItemStack(Material.ARROW))
            }
        )

        server.addRecipe(
            ShapedRecipe(
                NamespacedKey.minecraft("singijeon"),
                Singijeon()
            ).apply {
                shape(
                    "TTT",
                    "TBT",
                    "TTT"
                )
                setIngredient('T', tntarrow)
                setIngredient('B', ItemStack(Material.BOW))
            }
        )

    }
}
