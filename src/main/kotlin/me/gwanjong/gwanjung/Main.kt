package me.gwanjong.gwanjung

import io.github.monun.kommand.kommand
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBowEvent
import me.gwanjong.gwanjung.Item.Weapon.HumanBow.HumanBowRecipe
import me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow.HyperMonsterBowEvent
import me.gwanjong.gwanjung.Item.Weapon.HyperMonsterBow.HyperMonsterBowRecipe
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.HyperionEvent
import me.gwanjong.gwanjung.Item.Weapon.Hyperion.HyperionRecipe
import me.gwanjong.gwanjung.Item.Weapon.ICBM.ICBMEvent
import me.gwanjong.gwanjung.Item.Weapon.ICBM.ICBMRecipe
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.SingijeonEvent
import me.gwanjong.gwanjung.Item.Weapon.Singijeon.SingijeonRecipe
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

var cultural_language = false

class Main : JavaPlugin(), Listener{

    override fun onEnable() {

        cultural_language = false

        server.pluginManager.registerEvents(Event(), this)
        server.pluginManager.registerEvents(HyperionEvent(), this)
        server.pluginManager.registerEvents(SingijeonEvent(), this)
        server.pluginManager.registerEvents(HumanBowEvent(), this)
        server.pluginManager.registerEvents(HyperMonsterBowEvent(), this)
        server.pluginManager.registerEvents(ICBMEvent(), this)

        HumanBowRecipe(server)
        HyperionRecipe(server)
        SingijeonRecipe(server)
        ICBMRecipe(server)
        HyperMonsterBowRecipe(server)

        setupCommands()

        logger.info("==============================")
        logger.info("        plugin is loaded      ")
        logger.info("==============================")

    }

    private fun setupCommands() = kommand {
        Command.register(this@Main, this)
    }
}
