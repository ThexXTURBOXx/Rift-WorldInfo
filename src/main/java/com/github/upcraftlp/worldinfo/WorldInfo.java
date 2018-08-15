package com.github.upcraftlp.worldinfo;

import com.github.upcraftlp.worldinfo.api.RenderingHandlers;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractHorse;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractSkeleton;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractVillager;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerBlaze;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerCow;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerCreeper;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerElderGuardian;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerGhast;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerLlama;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerParrot;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerWitch;
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
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityLlama;
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
        RenderingHandlers.addInfoExclusion(EntityArmorStand.class);
        //TODO donkey
        //TODO mule
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
        //TODO rabbit
        //TODO polar bear
        //TODO player
        //TODO dolphin

        //wolf: fine
        //chicken: fine
        //guardian: fine
        //pig: fine
        //sheep: fine
        //vex: fine
        //zombie villager: fine
        RenderingHandlers.addEntityHandler(EntityWitch.class,           new HandlerWitch());
        RenderingHandlers.addEntityHandler(EntityLlama.class,           new HandlerLlama());
        RenderingHandlers.addEntityHandler(AbstractHorse.class,         new HandlerAbstractHorse()); //horse, skeleton horse, zombie horse
        RenderingHandlers.addEntityHandler(EntityParrot.class,          new HandlerParrot());
        RenderingHandlers.addEntityHandler(EntityElderGuardian.class,   new HandlerElderGuardian());
        RenderingHandlers.addEntityHandler(EntityWitherSkeleton.class,  new HandlerWitherSkeleton());
        RenderingHandlers.addEntityHandler(AbstractIllager.class,       new HandlerAbstractVillager<>()); //vindicator
        RenderingHandlers.addEntityHandler(EntityEvoker.class,          new HandlerAbstractVillager<>());
        RenderingHandlers.addEntityHandler(EntityCreeper.class,         new HandlerCreeper());
        RenderingHandlers.addEntityHandler(AbstractSkeleton.class,      new HandlerAbstractSkeleton()); //skeleton, stray
        RenderingHandlers.addEntityHandler(EntityZombie.class,          new HandlerZombie()); //husk, zombie pigman
        RenderingHandlers.addEntityHandler(EntityGhast.class,           new HandlerGhast());
        RenderingHandlers.addEntityHandler(EntityBlaze.class,           new HandlerBlaze());
        RenderingHandlers.addEntityHandler(EntityCow.class,             new HandlerCow()); //mooshroom
        RenderingHandlers.addEntityHandler(EntityVillager.class,        new HandlerAbstractVillager<>());
    }
}
