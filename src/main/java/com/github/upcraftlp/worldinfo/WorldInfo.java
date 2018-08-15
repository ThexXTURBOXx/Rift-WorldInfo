package com.github.upcraftlp.worldinfo;

import com.github.upcraftlp.worldinfo.api.WorldInfoRenderHandlers;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractSkeleton;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractVillager;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerBlaze;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerCow;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerCreeper;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerElderGuardian;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerGhast;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerParrot;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerWitherSkeleton;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerZombie;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityVillager;
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

    private static void registerVanillaEntityHandlers() {
        WorldInfoRenderHandlers.addInfoExclusion(EntityArmorStand.class);
        //TODO skeleton horse
        //TODO zombie horse
        //TODO donkey
        //TODO mule
        //TODO evoker
        //TODO spider
        //TODO giant
        //TODO slime
        //TODO enderman
        //TODO cave spider
        //TODO silverfish
        //TODO magma cube
        //TODO ender dragon
        //TODO wither
        //TODO bat
        //TODO witch
        //TODO endermite
        //TODO Shulker
        //TODO squid
        //TODO snow golem
        //TODO ocelot //smaller size!
        //TODO iron golem
        //TODO horse
        //TODO rabbit
        //TODO polar bear
        //TODO llama
        //TODO player

        //wolf: fine
        //chicken: fine
        //guardian: fine
        //pig: fine
        //sheep: fine
        //vex: fine
        //zombie villager: fine
        WorldInfoRenderHandlers.addEntityHandler(EntityParrot.class, new HandlerParrot());
        WorldInfoRenderHandlers.addEntityHandler(EntityElderGuardian.class, new HandlerElderGuardian());
        WorldInfoRenderHandlers.addEntityHandler(EntityWitherSkeleton.class, new HandlerWitherSkeleton());
        WorldInfoRenderHandlers.addEntityHandler(AbstractIllager.class, new HandlerAbstractVillager<>()); //vindicator
        WorldInfoRenderHandlers.addEntityHandler(EntityEvoker.class, new HandlerAbstractVillager<>());
        WorldInfoRenderHandlers.addEntityHandler(EntityCreeper.class, new HandlerCreeper());
        WorldInfoRenderHandlers.addEntityHandler(AbstractSkeleton.class, new HandlerAbstractSkeleton()); //skeleton, stray
        WorldInfoRenderHandlers.addEntityHandler(EntityZombie.class, new HandlerZombie()); //husk, zombie pigman
        WorldInfoRenderHandlers.addEntityHandler(EntityGhast.class, new HandlerGhast());
        WorldInfoRenderHandlers.addEntityHandler(EntityBlaze.class, new HandlerBlaze());
        WorldInfoRenderHandlers.addEntityHandler(EntityCow.class, new HandlerCow()); //mooshroom
        WorldInfoRenderHandlers.addEntityHandler(EntityVillager.class, new HandlerAbstractVillager<>());
    }
}
