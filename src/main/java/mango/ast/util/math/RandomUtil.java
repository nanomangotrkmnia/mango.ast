package mango.ast.util.math;

import mango.ast.interfaces.IAccess;
import mango.ast.noise.FastNoiseLite;
import mango.ast.util.render.ChatUtil;

public class RandomUtil implements IAccess {
    public static float getAdvancedRandom(float min, float max) {
        long seed = System.nanoTime();

        FastNoiseLite noise = new FastNoiseLite((int) (seed & 0x7FFFFFFF));
        noise.SetFrequency(0.1f);

        float x = (seed % 10000L) * 0.001f;
        float y = ((seed >> 16) % 10000L) * 0.001f;

        float raw = noise.GetNoise(x, y);        // [-1, 1]
        float norm = (raw + 1f) * 0.5f;          // [0, 1]

        float fr = min + norm * (max - min);
        ChatUtil.printDebug(fr + " min : " + min + " max: " + max);
        return fr;
    }

    public static int getAdvancedRandomInt(int min, int max) {
        return (int) getAdvancedRandom(min, max + 1);
    }
}
