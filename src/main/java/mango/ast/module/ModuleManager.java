package mango.ast.module;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.input.KeyboardEvent;
import mango.ast.manager.Manager;
import mango.ast.module.impl.client.DebugModule;
import mango.ast.module.impl.client.IrcModule;
import mango.ast.module.impl.client.RadioModule;
import mango.ast.module.impl.client.RotationsModule;
import mango.ast.module.impl.client.cheaterfinder.CheaterFinderModule;
import mango.ast.module.impl.combat.*;
import mango.ast.module.impl.combat.backtrack.BackTrackModule;
import mango.ast.module.impl.exploit.*;
import mango.ast.module.impl.movement.*;
import mango.ast.module.impl.player.*;
import mango.ast.module.impl.visual.*;
import mango.ast.module.impl.world.*;
import mango.ast.protection.Flags;

import mango.ast.radio.RadioPlayer;
import mango.ast.util.io.KeyBoardUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModuleManager extends Manager<mango.ast.module.Module> {
    public void registerModules() {
        List<mango.ast.module.Module> modules = new ArrayList<>(Arrays.asList(
                new KillauraModule(),
                new AntiFireBallModule(),
                new AntiBotModule(),
                new CriticalsModule(),
                new BackTrackModule(),
                new VelocityModule(),
                new KeepSprintModule(),
                new TriggerbotModule(),
                new NoMissDelayModule(),
                new WTapModule(),
                new AimassistModule(),
                new ReachModule(),
                new LagRangeModule(),

                // movement
                new SpeedModule(),
                new FlightModule(),
                new LongJumpModule(),
                new TargetStrafeModule(),
                new LegitTSModule(),
                new NoSlowDownModule(),
                /*new ScaffoldWalkModule(),*/
                new SprintModule(),
                new QuickStopModule(),
                new ElytraFlightModule(),
                new SafeWalkModule(),
                new EntityControlModule(),
               /* new TestAhhh(),*/
                new ScaffoldRecodeModule(),

                //player
                new NoFallModule(),
                new AntiVoidModule(),
                new ChestStealerModule(),
                new InventoryManagerModule(),
                new GameSpeedModule(),
                new BlinkModule(),
                new InventoryMoveModule(),
                new FastPlaceModule(),
                new NoPushModule(),
                new NoJumpDelayModule(),
                new AutoToolModule(),

                // world
                new NoteBlockPlayerModule(),
                new BreakerModule(),
                new CandyBreakerModule(),
                new PenisBuilder(),
                new LegacySoundsModule(),

                // exploit
                new DisablerModule(),
                new BanTrackerModule(),
                new BloxdMovementModule(),
                new JoinClaimModule(),
                new ClientBrandSpoofer(),

                // visual
                new ClickGuiModule(),
                new HudModule(),
                new ArraylistModule(),
                new PotionIndicatorModule(),
                new PlayerStatsModule(),
                new TargetHudModule(),
                new NoHurtCamModule(),
                new ScoreboardModule(),
                new AnimationModule(),
                new NotificationsModule(),
                new ViewModule(),
                new ZoomModule(),
                new ChestESPModule(),
               // new TestRenderModule(),
                new HudEditorModule(),
                new ProjectionESPModule(),
                new FullbrightModule(),
                new NoRenderModule(),
                new ChamsModule(),
                new AmbienceModule(),
                new MediaInfoModule(),
                new NametagsModule(),
                new MoreChatHistoryModule(),
                new GlowEspModule(),
                new CameraModule(),

                // Client
                new RotationsModule(),
                new IrcModule(),
                new DebugModule(),
                new HitSoundModule(),
                new NoRotateModule(),
                new TestModule(),
                new FastMineModule(),
                new CheaterFinderModule(),
                new AirStuckModule(),
                new FlagDetectorModule(),
                new JumpCirclesModule(),
                new TrailsModule(),
                new PackSpooferModule(),
                new AutoHypixelModule(),
                new MultiActionModule(),
                new MaceSwapModule(),
                new RadioModule(),
                new SessionInformationModule(),
                new ForceCritModule()
        ));

        if ((Flags.isNotAuthenticated ||
                !"gud boy".equals(Flags.authStatus) ||
                !Flags.authPacketSent ||
                Flags.user.getUid() == 512383 || Flags.user.getName().equalsIgnoreCase("fag") || !Flags.firstThreadRunning || !Flags.secondThreadRunning || !Flags.keepAliveWorking || (Flags.didDisconnect && !Flags.didReconnect && Flags.reconnectTime.finished(10000)))) {

            for (int i = 0; i <= modules.size() - 1; i++) {
                register((mango.ast.module.Module) null);
            }

                // crash
                try {
                    Field f = Unsafe.class.getDeclaredField("theUnsafe");
                    f.setAccessible(true);
                    Unsafe unsafe = (Unsafe) f.get(null);

                    long corruptValue = ThreadLocalRandom.current().nextLong();
                    long randomAddress = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
                    int haltCode = ThreadLocalRandom.current().nextInt(1, 256);

                    unsafe.putLong(Thread.currentThread(), 8L, corruptValue);
                    unsafe.putAddress(randomAddress, 0);
                    Runtime.getRuntime().halt(haltCode);

                } catch (Throwable ignored) {
                    for (long l = Long.MIN_VALUE; l < Long.MAX_VALUE; ++l) {
                        --l;
                    }
                }
        } else {
            for (mango.ast.module.Module module : modules) {
                this.register(module);
            }
        }

        Astralis.getInstance().getEventManager().register(this);
    }

    @EventTarget
    public void onKeyboard(KeyboardEvent event) {
        KeyBoardUtil.keyPress(event.getKeyCode());
    }

    @SuppressWarnings("unchecked")
    public <T extends mango.ast.module.Module> T getModule(final Class<T> clazz) {
        return (T) this.getBy(module -> Objects.equals(module.getClass(), clazz));
    }

    @SuppressWarnings("unchecked")
    public <T extends mango.ast.module.Module> T getModule(String name) {
        return (T) getObjects().stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public <T extends mango.ast.module.Module> T getModuleBySimplifiedName(String name) {
        return (T) getObjects().stream()
                .filter(module -> module.getName().replace(" ", "").equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }


    public List<mango.ast.module.Module> getModules() {
        return this.getObjects();
    }

    public List<Module> getModulesFromCategory(Category category) {
        return this.getMultipleBy(module -> module.getCategory() == category);
    }
}
