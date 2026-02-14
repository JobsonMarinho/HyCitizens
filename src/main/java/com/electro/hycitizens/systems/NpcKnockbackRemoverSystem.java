package com.electro.hycitizens.systems;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.entity.knockback.KnockbackComponent;
import com.hypixel.hytale.server.core.entity.knockback.KnockbackSystems;
import com.hypixel.hytale.server.core.modules.physics.component.Velocity;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class NpcKnockbackRemoverSystem extends KnockbackSystems.ApplyKnockback {

    @NonNullDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.any();
    }

    @Override
    public void tick(float dt, int index, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        KnockbackComponent knockbackComponent = archetypeChunk.getComponent(index, KnockbackComponent.getComponentType());
        if (knockbackComponent != null) {
            knockbackComponent.setDuration(0);
        }

        Velocity velocityComponent = archetypeChunk.getComponent(index, Velocity.getComponentType());
        if (velocityComponent != null) {
            velocityComponent.getInstructions().clear();
        }

        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);
        commandBuffer.tryRemoveComponent(ref, KnockbackComponent.getComponentType());
    }
}
