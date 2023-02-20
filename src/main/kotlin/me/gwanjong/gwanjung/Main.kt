package me.gwanjong.gwanjung

import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.Weapon.ElderWand.ElderWandEvent
import me.gwanjong.gwanjung.Weapon.HumanBow.HumanBowEvent
import me.gwanjong.gwanjung.Weapon.HumanBow.HumanBowRecipe
import me.gwanjong.gwanjung.Weapon.Hyperion.HyperionEvent
import me.gwanjong.gwanjung.Weapon.Hyperion.HyperionRecipe
import me.gwanjong.gwanjung.Weapon.Singijeon.SingijeonEvent
import me.gwanjong.gwanjung.Weapon.Singijeon.SingijeonRecipe
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

var cultural_language = false

class Main : JavaPlugin(), Listener{

    override fun onEnable() {

        cultural_language = false

        Bukkit.getPluginManager().registerEvents(ElderWandEvent(), this)
        Bukkit.getPluginManager().registerEvents(HyperionEvent(), this)
        Bukkit.getPluginManager().registerEvents(SingijeonEvent(), this)
        Bukkit.getPluginManager().registerEvents(HumanBowEvent(), this)

        HumanBowRecipe(server)
        HyperionRecipe(server)
        SingijeonRecipe(server)

        setupCommands()

        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

    }

    private fun setupCommands() = kommand {
        Command.register(this@Main, this)
    }
}
