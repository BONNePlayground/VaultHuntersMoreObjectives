//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.moreobjectives.events;


import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.event.Event;


/**
 * Custom Common Events thats hooks into VH Common Event registry :D
 */
public class ExtraCommonEvents
{
    /**
     * This event is fired before entity is created via spawner.
     */
    public static final SpawnerEntityCreateEvent SPAWNER_ENTITY_CREATE = register(new SpawnerEntityCreateEvent());


    /**
     * Register event to VH Common Event registry.
     * @param event The event that will be registered.
     * @return The event that was registered.
     * @param <T> The type of the event.
     */
    private static <T extends Event<?, ?>> T register(T event)
    {
        CommonEvents.REGISTRY.add(event);
        return event;
    }


    /**
     * Just class init to trigger everything.
     */
    public static void init()
    {
        // Adds cow logic to Vault Registry. This breaks clients without mod!
//        VaultRegistry.MOB_LOGIC.add(CowMobLogic.KEY);
    }
}
