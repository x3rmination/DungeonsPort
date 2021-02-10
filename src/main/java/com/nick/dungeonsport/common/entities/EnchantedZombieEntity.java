package com.nick.dungeonsport.common.entities;


import com.nick.dungeonsport.core.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class EnchantedZombieEntity extends ZombieEntity {

    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(ZombieEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> VILLAGER_TYPE = EntityDataManager.createKey(ZombieEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> DROWNING = EntityDataManager.createKey(ZombieEntity.class, DataSerializers.BOOLEAN);
    private static final Predicate<Difficulty> HARD_DIFFICULTY_PREDICATE = (p_213697_0_) -> {
        return p_213697_0_ == Difficulty.HARD;
    };
    private final BreakDoorGoal breakDoor = new BreakDoorGoal(this, HARD_DIFFICULTY_PREDICATE);
    private boolean isBreakDoorsTaskSet;
    private int inWaterTime;
    private int drownedConversionTime;

    public EnchantedZombieEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        //this.goalSelector.addGoal(4, new ZombieEntity.AttackTurtleEggGoal(this, 1.0D, 3));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::isBreakDoorsTaskSet));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
    }


    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        float f = difficultyIn.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
        if (spawnDataIn == null) {
            spawnDataIn = new ZombieEntity.GroupData(func_241399_a_(worldIn.getRandom()), true);
        }

        if (spawnDataIn instanceof ZombieEntity.GroupData) {
            ZombieEntity.GroupData zombieentity$groupdata = (ZombieEntity.GroupData)spawnDataIn;
            if (zombieentity$groupdata.isChild) {
                this.setChild(true);
                if (zombieentity$groupdata.field_241400_b_) {
                    if ((double)worldIn.getRandom().nextFloat() < 0.05D) {
                        List<ChickenEntity> list = worldIn.getEntitiesWithinAABB(ChickenEntity.class, this.getBoundingBox().grow(5.0D, 3.0D, 5.0D), EntityPredicates.IS_STANDALONE);
                        if (!list.isEmpty()) {
                            ChickenEntity chickenentity = list.get(0);
                            chickenentity.setChickenJockey(true);
                            this.startRiding(chickenentity);
                        }
                    } else if ((double)worldIn.getRandom().nextFloat() < 0.05D) {
                        ChickenEntity chickenentity1 = EntityType.CHICKEN.create(this.world);
                        chickenentity1.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
                        chickenentity1.onInitialSpawn(worldIn, difficultyIn, SpawnReason.JOCKEY, (ILivingEntityData)null, (CompoundNBT)null);
                        chickenentity1.setChickenJockey(true);
                        this.startRiding(chickenentity1);
                        worldIn.addEntity(chickenentity1);
                    }
                }
            }

            this.setBreakDoorsAItask(this.canBreakDoors() && this.rand.nextFloat() < f * 0.1F);
            this.setEquipmentBasedOnDifficulty(difficultyIn);
            this.setEnchantmentBasedOnDifficulty(difficultyIn);
        }

        if (this.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.rand.nextFloat() < 0.25F) {
                this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.inventoryArmorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        }

        this.applyAttributeBonuses(f);
        return spawnDataIn;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!super.attackEntityFrom(source, amount)) {
            return false;
        } else if (!(this.world instanceof ServerWorld)) {
            return false;
        } else {
            ServerWorld serverworld = (ServerWorld)this.world;
            LivingEntity livingentity = this.getAttackTarget();
            if (livingentity == null && source.getTrueSource() instanceof LivingEntity) {
                livingentity = (LivingEntity)source.getTrueSource();
            }

            int i = MathHelper.floor(this.getPosX());
            int j = MathHelper.floor(this.getPosY());
            int k = MathHelper.floor(this.getPosZ());

            net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent event = net.minecraftforge.event.ForgeEventFactory.fireZombieSummonAid(this, world, i, j, k, livingentity, this.getAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS).getValue());
            if (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.DENY) return true;
            if (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW  ||
                    livingentity != null && this.world.getDifficulty() == Difficulty.HARD && (double)this.rand.nextFloat() < this.getAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                ZombieEntity zombieentity = event.getCustomSummonedAid() != null && event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW ? event.getCustomSummonedAid() : EntityType.ZOMBIE.create(this.world);

                for(int l = 0; l < 50; ++l) {
                    int i1 = i + MathHelper.nextInt(this.rand, 7, 40) * MathHelper.nextInt(this.rand, -1, 1);
                    int j1 = j + MathHelper.nextInt(this.rand, 7, 40) * MathHelper.nextInt(this.rand, -1, 1);
                    int k1 = k + MathHelper.nextInt(this.rand, 7, 40) * MathHelper.nextInt(this.rand, -1, 1);
                    BlockPos blockpos = new BlockPos(i1, j1, k1);
                    EntityType<?> entitytype = zombieentity.getType();
                    EntitySpawnPlacementRegistry.PlacementType entityspawnplacementregistry$placementtype = EntitySpawnPlacementRegistry.getPlacementType(entitytype);
                    if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(entityspawnplacementregistry$placementtype, this.world, blockpos, entitytype) && EntitySpawnPlacementRegistry.canSpawnEntity(entitytype, serverworld, SpawnReason.REINFORCEMENT, blockpos, this.world.rand)) {
                        zombieentity.setPosition((double)i1, (double)j1, (double)k1);
                        if (!this.world.isPlayerWithin((double)i1, (double)j1, (double)k1, 7.0D) && this.world.checkNoEntityCollision(zombieentity) && this.world.hasNoCollisions(zombieentity) && !this.world.containsAnyLiquid(zombieentity.getBoundingBox())) {
                            if (livingentity != null)
                                zombieentity.setAttackTarget(livingentity);
                            zombieentity.onInitialSpawn(serverworld, this.world.getDifficultyForLocation(zombieentity.getPosition()), SpawnReason.REINFORCEMENT, (ILivingEntityData)null, (CompoundNBT)null);
                            serverworld.func_242417_l(zombieentity);
                            this.getAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS).applyPersistentModifier(new AttributeModifier("Zombie reinforcement caller charge", (double)-0.05F, AttributeModifier.Operation.ADDITION));
                            zombieentity.getAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS).applyPersistentModifier(new AttributeModifier("Zombie reinforcement callee charge", (double)-0.05F, AttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F) {
                entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }


    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        if (this.rand.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.rand.nextInt(3);
            if (i == 0) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
            }
        }

    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.23F)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0D)
                .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
    }
}
