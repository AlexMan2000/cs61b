// Make sure to make this class a part of the synthesizer package
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        // Fill with zero
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        double prevDouble = -4.0;  // Any number that is not in the range [-0.5, 0.5]
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            double currentDouble = Math.random() - 0.5;
            while (currentDouble == prevDouble) {
                currentDouble = Math.random() - 0.5;
            }
            buffer.enqueue(currentDouble);
            prevDouble = currentDouble;
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        buffer.enqueue(DECAY * (buffer.dequeue() + buffer.peek()) / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // Return the correct thing.
        return buffer.peek();
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < buffer.capacity(); i++) {
//            double temp = buffer.dequeue();
//            sb.append(temp);
//            sb.append(" ");
//            buffer.enqueue(temp);
//        }
//        return sb.toString();
//    }

//    public void printBuffer() {
//        System.out.println(this);
//    }
}
