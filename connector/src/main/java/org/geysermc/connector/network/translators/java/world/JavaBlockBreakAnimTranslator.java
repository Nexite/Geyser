package org.geysermc.connector.network.translators.java.world;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockBreakAnimPacket;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import org.geysermc.connector.network.session.GeyserSession;
import org.geysermc.connector.network.translators.PacketTranslator;


public class JavaBlockBreakAnimTranslator extends PacketTranslator<ServerBlockBreakAnimPacket> {
    private GeyserSession session;
    @Override
    public void translate(ServerBlockBreakAnimPacket packet, GeyserSession session) {
        LevelEventPacket levelEventPacket= new LevelEventPacket();
//        PlayerEntity player = (PlayerEntity) session.getEntityCache().getEntityByJavaId(packet.getBreakerEntityId());
        Vector3f blockPositon = Vector3f.from(packet.getPosition().getX(),packet.getPosition().getY(),packet.getPosition().getZ());
        LevelChunkPacket levelChunkPacket = new LevelChunkPacket();
        switch (packet.getStage()) {
            case STAGE_1:
//                int bedrockId = BlockTranslator.getBedrockBlockId(session.getChunkCache().getBlockAt(packet.getPosition()));
//                int heldItem = player.getHand().getId();
//                double breakTime = Math.ceil(1);
                levelEventPacket.setPosition(blockPositon);
                levelEventPacket.setData(2000);
                levelEventPacket.setType(LevelEventType.BLOCK_START_BREAK);
                session.getUpstream().sendPacket(levelEventPacket);
                System.out.println("start packet sent");
                break;
            case STAGE_10:
                levelEventPacket.setData(0);
                levelEventPacket.setPosition(blockPositon);
                levelEventPacket.setType(LevelEventType.BLOCK_STOP_BREAK);
                session.getUpstream().sendPacket(levelEventPacket);
                System.out.println("stop packet sent");
                break;
        }


    }
}
