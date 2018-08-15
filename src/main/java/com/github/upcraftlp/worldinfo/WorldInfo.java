package com.github.upcraftlp.worldinfo;

import com.github.upcraftlp.worldinfo.api.RenderingHandlers;
import com.github.upcraftlp.worldinfo.api.entity.DefaultEntityHandler;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractFish;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractHorse;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerElderGuardian;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerGhast;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerLlama;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerParrot;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerRabbit;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerSquid;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerTropicalFish;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerVex;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractFish;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTropicalFish;
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
        //TODO endermite
        //TODO Shulker
        //TODO snow golem
        //TODO ocelot //smaller size!
        //TODO iron golem
        //TODO polar bear
        //TODO player

        //dolphin: fine
        //wolf: fine
        //chicken: fine
        //guardian: fine
        //pig: fine
        //sheep: fine
        //zombie villager: fine
        DefaultEntityHandler handlerVillager = new DefaultEntityHandler<>(4.5F, -0.1F);
        RenderingHandlers.addEntityHandler(AbstractIllager.class,       handlerVillager); //vindicator
        RenderingHandlers.addEntityHandler(EntityEvoker.class,          handlerVillager);
        RenderingHandlers.addEntityHandler(EntityVillager.class,        handlerVillager);
        RenderingHandlers.addEntityHandler(EntityVex.class,             new HandlerVex());
        RenderingHandlers.addEntityHandler(EntityRabbit.class,          new HandlerRabbit());
        RenderingHandlers.addEntityHandler(EntityLlama.class,           new HandlerLlama());
        RenderingHandlers.addEntityHandler(EntityTropicalFish.class,    new HandlerTropicalFish());
        RenderingHandlers.addEntityHandler(AbstractFish.class,          new HandlerAbstractFish()); //salmon, cod
        RenderingHandlers.addEntityHandler(AbstractHorse.class,         new HandlerAbstractHorse()); //horse, skeleton horse, zombie horse
        RenderingHandlers.addEntityHandler(EntityParrot.class,          new HandlerParrot());
        RenderingHandlers.addEntityHandler(EntityElderGuardian.class,   new HandlerElderGuardian());
        RenderingHandlers.addEntityHandler(EntityGhast.class,           new HandlerGhast());
        RenderingHandlers.addEntityHandler(EntitySquid.class,           new HandlerSquid());
        RenderingHandlers.addEntityHandler(EntityWitch.class,           new DefaultEntityHandler<>(6.5F, -0.1F));
        RenderingHandlers.addEntityHandler(EntityWitherSkeleton.class,  new DefaultEntityHandler<>(5.2F, 0.0F, 0.9F));
        RenderingHandlers.addEntityHandler(EntityCreeper.class,         new DefaultEntityHandler<>(3.2F));
        RenderingHandlers.addEntityHandler(AbstractSkeleton.class,      new DefaultEntityHandler<>(4.5F)); //skeleton, stray
        RenderingHandlers.addEntityHandler(EntityZombie.class,          new DefaultEntityHandler<>(4.35F)); //husk, zombie pigman, drowned
        RenderingHandlers.addEntityHandler(EntityBlaze.class,           new DefaultEntityHandler<>(2.3F, -0.6F));
        RenderingHandlers.addEntityHandler(EntityCow.class,             new DefaultEntityHandler<>(1.8F, -0.25F)); //mooshroom


    }
}
