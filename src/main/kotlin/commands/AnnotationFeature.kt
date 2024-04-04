package commands

import Main
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.AnnotationParser

/**
 * Some feature of the annotation system that we want to showcase.
 */
interface AnnotationFeature {
    fun registerFeature(
        main: Main,
        annotationParser: AnnotationParser<CommandSender?>
    )
}