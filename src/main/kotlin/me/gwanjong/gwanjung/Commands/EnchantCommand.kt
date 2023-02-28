package me.gwanjong.gwanjung.Commands

import io.github.monun.kommand.PluginKommand
import me.gwanjong.gwanjung.Main

object EnchantCommand {
    private lateinit var plugin: Main

    internal fun register(plugin: Main, kommand: PluginKommand) {
        EnchantCommand.plugin = plugin

    }
}