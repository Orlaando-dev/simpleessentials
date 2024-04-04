package commands

import Main
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.AnnotationParser

interface AnnotationFeature {
    fun registerFeature(
        main: Main,
        annotationParser: AnnotationParser<CommandSender?>
    )
}