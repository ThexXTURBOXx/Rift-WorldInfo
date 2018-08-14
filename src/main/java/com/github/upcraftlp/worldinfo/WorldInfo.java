package com.github.upcraftlp.worldinfo;

import com.github.upcraftlp.worldinfo.api.WorldInfoRenderHandlers;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractSkeleton;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerElderGuardian;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerGhast;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerWitherSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.rift.listener.MinecraftStartListener;

@SuppressWarnings("unused")
public class WorldInfo implements MinecraftStartListener {

    private static final Logger log = LogManager.getLogger("WorldInfo");

    public static final String MODID = "world_info";

    public static Logger getLog() {
        return log;
    }

    @Override
    public void onMinecraftStart() {
        log.info("loaded World Info HUD!");
        registerVanillaEntityHandlers();
    }

    //actually sorted by network IDs
    private static void registerVanillaEntityHandlers() {
        WorldInfoRenderHandlers.addEntityHandler(EntityElderGuardian.class, new HandlerElderGuardian()); //4
        WorldInfoRenderHandlers.addEntityHandler(EntityWitherSkeleton.class, new HandlerWitherSkeleton()); //5
        //TODO 6 stray
        //TODO 23 husk
        //TODO 27 zombie villager
        //TODO 28 skeleton horse
        //TODO 29 zombie horse
        //TODO 31 donkey
        //TODO 32 mule
        //TODO 34 evoker
        //TODO 35 vex
        //TODO 36 vindicator
        //TODO 37 illusioner
        //TODO 50 creeper
        WorldInfoRenderHandlers.addEntityHandler(AbstractSkeleton.class, new HandlerAbstractSkeleton()); //51
        //TODO 52 spider
        //TODO 53 giant
        //TODO 54 zombie
        //TODO 55 slime
        WorldInfoRenderHandlers.addEntityHandler(EntityGhast.class, new HandlerGhast()); //56
        //TODO 57 zombie pigman
        //TODO 58 enderman
        //TODO 59 cave spider
        //TODO 60 silverfish
        //TODO 61 blaze
        //TODO 62 magma cube
        //TODO 63 ender dragon
        //TODO 64 wither
        //TODO 65 bat
        //TODO 66 witch
        //TODO 67 endermite
        //guardian: fine //68
        //TODO 69 Shulker

        //TODO 90 pig
        //TODO 91 sheep
        //TODO 92 cow
        //chicken: fine //93
        //TODO 94 squid
        //TODO 95 wolf
        //TODO 96 mooshroom
        //TODO 97 snow golem
        //TODO 98 ocelot
        //TODO 99 iron golem
        //TODO 100 horse
        //TODO 101 rabbit
        //TODO 102 polar bear
        //TODO 103 llama
        //TODO 105 parrot
        //TODO 120 villager

        //TODO player
    }
}
