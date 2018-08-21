package com.github.upcraftlp.worldinfo;

import com.github.upcraftlp.worldinfo.api.RenderingHandlers;
import com.github.upcraftlp.worldinfo.api.entity.DefaultEntityHandler;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractFish;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerAbstractHorse;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerDolphin;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerElderGuardian;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerGhast;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerLlama;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerPolarBear;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerRabbit;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerShulker;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerSlime;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerSquid;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerTropicalFish;
import com.github.upcraftlp.worldinfo.client.handler.entity.HandlerVex;
import net.insomniakitten.pylon.annotation.Listener;
import net.insomniakitten.pylon.annotation.Mod;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPhantom;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.AbstractFish;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityDolphin;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTropicalFish;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.rift.listener.MinecraftStartListener;

import static com.github.upcraftlp.worldinfo.WorldInfo.*;

@Listener
@Mod(id = MODID, name = MOD_NAME, authors = "UpcraftLP", version = "@VERSION@")
public class WorldInfo implements MinecraftStartListener {

    private static final Logger log = LogManager.getLogger("WorldInfo");

    public static final String MODID = "world_info";
    public static final String MOD_NAME = "WorldInfo";

    public static Logger getLogger() {
        return log;
    }

    @Override
    public void onMinecraftStart() {
        log.info("loaded World Info HUD!");
        registerVanillaEntityHandlers();
    }

    @SuppressWarnings("unchecked")
    private static void registerVanillaEntityHandlers() {
        RenderingHandlers.addInfoExclusion(EntityArmorStand.class);
        //TODO player doesn't seem to work
        //TODO pufferfish

        //zombie villager: fine
        DefaultEntityHandler handlerVillager = new DefaultEntityHandler<>(4.5F, -0.1F);
        RenderingHandlers.addEntityHandler(AbstractIllager.class,       handlerVillager); //vindicator
        RenderingHandlers.addEntityHandler(EntityEvoker.class,          handlerVillager);
        RenderingHandlers.addEntityHandler(EntityVillager.class,        handlerVillager);
        RenderingHandlers.addEntityHandler(EntityVex.class,             new HandlerVex());
        RenderingHandlers.addEntityHandler(AbstractHorse.class,         new HandlerAbstractHorse()); //horse, skeleton horse, zombie horse
        RenderingHandlers.addEntityHandler(EntityElderGuardian.class,   new HandlerElderGuardian());
        RenderingHandlers.addEntityHandler(EntityGhast.class,           new HandlerGhast());
        RenderingHandlers.addEntityHandler(EntitySquid.class,           new HandlerSquid());
        RenderingHandlers.addEntityHandler(EntitySlime.class,           new HandlerSlime()); //magma cube
        RenderingHandlers.addEntityHandler(EntityDolphin.class,         new HandlerDolphin());
        RenderingHandlers.addEntityHandler(EntityRabbit.class,          new HandlerRabbit());
        RenderingHandlers.addEntityHandler(EntityLlama.class,           new HandlerLlama());
        RenderingHandlers.addEntityHandler(EntityTropicalFish.class,    new HandlerTropicalFish());
        RenderingHandlers.addEntityHandler(AbstractFish.class,          new HandlerAbstractFish()); //salmon, cod
        RenderingHandlers.addEntityHandler(EntityShulker.class,         new HandlerShulker());
        RenderingHandlers.addEntityHandler(EntityPolarBear.class,       new HandlerPolarBear());
        RenderingHandlers.addEntityHandler(EntityGuardian.class,        new DefaultEntityHandler<>(4.0F));
        RenderingHandlers.addEntityHandler(AbstractChestHorse.class,    new DefaultEntityHandler<>(3.7F, -0.2F));
        RenderingHandlers.addEntityHandler(EntityIronGolem.class,       new DefaultEntityHandler<>(6.0F, -0.1F, 0.9F));
        RenderingHandlers.addEntityHandler(EntityWither.class,          new DefaultEntityHandler<>(9.0F, -0.5F));
        RenderingHandlers.addEntityHandler(EntityCreeper.class,         new DefaultEntityHandler<>(3.2F));
        RenderingHandlers.addEntityHandler(EntityBlaze.class,           new DefaultEntityHandler<>(2.8F, -0.6F));
        RenderingHandlers.addEntityHandler(EntityZombie.class,          new DefaultEntityHandler<>(4.55F)); //husk, zombie pigman, drowned
        RenderingHandlers.addEntityHandler(EntityWitherSkeleton.class,  new DefaultEntityHandler<>(5.4F, 0.0F, 0.9F));
        RenderingHandlers.addEntityHandler(AbstractSkeleton.class,      new DefaultEntityHandler<>(4.7F)); //skeleton, stray
        RenderingHandlers.addEntityHandler(EntityOcelot.class,          new DefaultEntityHandler<>(2.0F, 0.1F, 1.4F));
        RenderingHandlers.addEntityHandler(EntityEndermite.class,       new DefaultEntityHandler<>(0.825F, 0.0F, 2.5F));
        RenderingHandlers.addEntityHandler(EntitySilverfish.class,      new DefaultEntityHandler<>(0.88F, -0.1F, 2.0F));
        RenderingHandlers.addEntityHandler(EntitySnowman.class,         new DefaultEntityHandler<>(3.6F, -0.15F));
        RenderingHandlers.addEntityHandler(EntityEnderman.class,        new DefaultEntityHandler<>(6.4F, -0.15F));
        RenderingHandlers.addEntityHandler(EntityBat.class,             new DefaultEntityHandler<>(1.3F, -0.15F, 2.0F));
        RenderingHandlers.addEntityHandler(EntityCaveSpider.class,      new DefaultEntityHandler<>(1.7F, -0.15F, 1.4F));
        RenderingHandlers.addEntityHandler(EntitySpider.class,          new DefaultEntityHandler<>(2.2F, -0.15F));
        RenderingHandlers.addEntityHandler(EntityParrot.class,          new DefaultEntityHandler<>(1.8F, 0.0F, 1.5F));
        RenderingHandlers.addEntityHandler(EntityCow.class,             new DefaultEntityHandler<>(2.85F, -0.25F)); //mooshroom
        RenderingHandlers.addEntityHandler(EntityGiantZombie.class,     new DefaultEntityHandler<>(25.0F, 0.0F, 0.3F));
        RenderingHandlers.addEntityHandler(EntityPhantom.class,         new DefaultEntityHandler<>(2.05F, 0.4F, 0.7F));
        RenderingHandlers.addEntityHandler(EntityWitch.class,           new DefaultEntityHandler<>(4.9F, -0.1F, 0.8F));
        RenderingHandlers.addEntityHandler(EntitySheep.class,             new DefaultEntityHandler<>(2.75F, -0.25F));
        RenderingHandlers.addEntityHandler(EntityWolf.class,             new DefaultEntityHandler<>(2.2F, -0.25F));
        RenderingHandlers.addEntityHandler(EntityPig.class,             new DefaultEntityHandler<>(2.2F, -0.25F));
        RenderingHandlers.addEntityHandler(EntityChicken.class,             new DefaultEntityHandler<>(2.2F, 0.0F, 1.1F));
    }
}
