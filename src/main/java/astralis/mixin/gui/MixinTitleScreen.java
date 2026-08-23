package astralis.mixin.gui;

import mango.ast.Astralis;
import mango.ast.interfaces.Fonts;
import mango.ast.interfaces.IAccess;
import mango.ast.protection.Flags;
import mango.ast.ui.screens.client.MainMenu;
import mango.ast.ui.screens.altmanager.AltManagerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen implements IAccess, Fonts {
    protected MixinTitleScreen(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void onInit(CallbackInfo ci) {
        if ((Flags.isNotAuthenticated || !Objects.equals(Flags.authStatus, "gud boy") || !Flags.authGuiShown)) {
            mc.setScreen(Astralis.getInstance().getAuthScreen());
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "createNormalMenuOptions(II)I")
    @SuppressWarnings("All")
    private void addNormalWidgets(int y, int spacingY, CallbackInfoReturnable<Integer> ci) {
        TitleScreen screen = (TitleScreen) (Object) this;

        Button.Builder builder = Button.builder(Component.literal("Alt Manager"), button -> mc.setScreen(
                new AltManagerScreen()
        )).size(60, 20);

        addRenderableWidget(builder.pos(width - 65, height - 25).build());
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderMixin(GuiGraphics drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MainMenu.render(mouseX, mouseY);
    }
}
