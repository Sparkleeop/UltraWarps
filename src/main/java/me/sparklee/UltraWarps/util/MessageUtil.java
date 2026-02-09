package me.sparklee.UltraWarps.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;

public class MessageUtil {

    private static final LegacyComponentSerializer SERIALIZER =
            LegacyComponentSerializer.builder().hexColors().build();

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public static void sendHeaderBody(CommandSender sender, String header, String body) {
        sender.sendMessage(color(header));
        sender.sendMessage(color(body));
    }

    public static Component color(String text) {
        return SERIALIZER.deserialize(text.replace("&", "§"));
    }
}
