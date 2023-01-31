/**
 * Created by AlexMan
 */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    // Simulate the keyboard
    public static void main(String[] args) {
        synthesizer.GuitarString[] guitarStrings = new synthesizer.GuitarString[KEYBOARD.length()];
        for (double i = 0; i < KEYBOARD.length(); i++) {
            synthesizer.GuitarString gs = new synthesizer.GuitarString(
                    CONCERT_A * Math.pow(2, (i - 24) / 12));
            guitarStrings[(int) i] = gs;
        }

        // Listening Loop
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                double i = KEYBOARD.indexOf(c);
                if ((int) i == -1) {
                    System.out.println("Invalid Keyboard Input!");
                    continue;
                }
                guitarStrings[(int) i].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (synthesizer.GuitarString gs: guitarStrings) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (synthesizer.GuitarString gs: guitarStrings) {
                gs.tic();
            }
        }
    }
}
