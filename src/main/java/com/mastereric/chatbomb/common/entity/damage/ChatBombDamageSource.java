package com.mastereric.chatbomb.common.entity.damage;

import net.minecraft.entity.damage.DamageSource;

/**
 * Custom damage source allowing for custom death messages.
 */
public class ChatBombDamageSource extends DamageSource {
    public ChatBombDamageSource() {
        super("chatbomb");
        this.setScaledWithDifficulty();
        this.setExplosive();
    }
}
