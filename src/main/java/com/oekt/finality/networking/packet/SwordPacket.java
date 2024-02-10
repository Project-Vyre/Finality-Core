package com.oekt.finality.networking.packet;

import com.oekt.finality.item.custom.PowerfulSword;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.function.Supplier;

public class SwordPacket {
    public SwordPacket() {

    }
    public SwordPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean hannde(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

                PowerfulSword.spawnSlash(player);


        });
                return true;
    }
}
