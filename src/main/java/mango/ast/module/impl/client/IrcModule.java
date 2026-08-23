package mango.ast.module.impl.client;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.BackendMessageEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.util.render.ChatUtil;

public class IrcModule extends Module {

    public IrcModule() {
        super(Category.VISUAL);
    }

    @EventTarget
    public void onBackendMessage(BackendMessageEvent event) {
        mc.gui.getChat().addMessage(ChatUtil.translateToGradient("IRC",
                Astralis.getInstance().getFirstColor().getRGB(), Astralis.getInstance().getSecondColor().getRGB())
                .append(" » " + event.getMessage())
        );
    }
}
