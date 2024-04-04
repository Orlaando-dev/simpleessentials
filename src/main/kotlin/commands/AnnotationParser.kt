package commands

import Main
import commands.util.PingCommand
import org.bukkit.command.CommandSender
import org.incendo.cloud.CommandManager
import org.incendo.cloud.annotations.AnnotationParser
import java.util.*

class AnnotationParser (
    main: Main,
    manager: CommandManager<CommandSender>
) {
    private val main: Main = main
    private val annotationParser =
        AnnotationParser(
            manager,
            CommandSender::class.java
        )

    init {
        // Set up the example modules.
        this.setupExamples()
    }

    private fun setupExamples() {
        FEATURES.forEach { feature ->
            feature.registerFeature(
                this.main,
                annotationParser
            )
        }
    }

    companion object {
        private val FEATURES: List<AnnotationFeature> = Arrays.asList<AnnotationFeature>(
            PingCommand()
        )
    }
}