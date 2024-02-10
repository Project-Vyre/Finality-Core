package com.oekt.finality.networking;

import com.oekt.finality.Finality;
import com.oekt.finality.networking.packet.SwordPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMesseges {
    private static SimpleChannel INSTENCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Finality.MODID, "messeges"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTENCE = net;

        net.messageBuilder(SwordPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SwordPacket::new)
                .encoder(SwordPacket::toBytes)
                .consumerMainThread(SwordPacket::hannde)
                .add();
    }
    public static <MSG> void  sendToServer(MSG msg) {
        INSTENCE.sendToServer(msg);
    }
    public static <MSG> void  sendToPlayer(MSG msg, ServerPlayer player) {
        INSTENCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
