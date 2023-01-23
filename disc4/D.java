/**
 * Created by AlexMan
 */
class D {
    public static void main (String[] args) {
//        B a0 = new A();  // Compile Error, static checking
//        a0.m1();         // Cascading
//        a0.m2(16);     // Cascading
        A b0 = new B();
        System.out.println(b0.x);  // 5, found in A
        b0.m1();                   // Am1-> 5, found m1() in A
        b0.m2();                   // Bm2-> 5, found m2() in B
//        b0.m2(61);                 // Compile Error, m2(int) not found in A
        B b1 = new B();
        b1.m2(61);              // Bm2-> 61
        b1.m3();                   // Bm3-> called
        A c0 = new C();
        c0.m2();                   // Cm2-> 5, 5 found in A
//        C c1 = (A) new C();        // Compile Error
        A a1 = (A) c0;
        C c2 = (C) a1;
        c2.m3();                   // Bm3-> called
//        c2.m4();                   // X, double super not allowed, super is enough
        c2.m5();                   // Cm5-> 6
        ((C) c0).m3();             // Bm3-> called
//        (C) c0.m3();               // Compile Error, A doesn't have m3()
        b0.update();               // set x = 99
        b0.m1();                   // Am1-> 99
    }
 }