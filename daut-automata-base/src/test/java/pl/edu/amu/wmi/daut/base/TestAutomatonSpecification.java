package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Testy klasy AutomatonSpecification.
 */
public class TestAutomatonSpecification extends TestCase {
    
    /**
     * Prosta etykieta przejścia dla celów testowych.
     *
     * FIXME Powielony kod z TestNaiveAutomatonSpecification -
     * uporządkować po zrobieniu zadania #104.
     */
    private static class TestTransition extends TransitionLabel {
        /**
         * Konstruuje etykietę oznaczoną znakiem 'c'.
         */
        public TestTransition(char c) {
            ch_ = c;
        }

        public boolean canBeEpsilon() {
            return false;
        }

        public boolean canAcceptCharacter(char c) {
            return c == ch_;
        }

        public boolean isEmpty() {
            return false;
        }

        public char getChar() {
            return ch_;
        }

        protected TransitionLabel intersectWith(TransitionLabel label) {
            return label.canAcceptCharacter(ch_) ? this : new EmptyTransitionLabel();
        }

        private char ch_;
    }
    
    public final void testFromString() {
        //TEST 1 Język pusty
        {
            AutomatonSpecification pustyOjciec = new NaiveAutomatonSpecification();
        
            String slowo = "Automaton:\n-States: q0\n-Transitions:\n"+
         "q0 -a-> q0\nq0 -b-> q0\n-Initial state: q0\n-Final states:";
            
            try {
                pustyOjciec.fromString(slowo);
            } catch (Exception e) {
                assertTrue(false);
            }
        
            AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(pustyOjciec);
        
            assertFalse(pusteDziecko.accepts("aaa"));
            assertFalse(pusteDziecko.accepts("baba"));
            assertFalse(pusteDziecko.accepts(""));
        }
        //TEST 2
        {
            AutomatonSpecification masakra = new NaiveAutomatonSpecification();
        
            String slowo2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"+
         "q0 -a-> q1\nq1 -a-> q0\n-Initial state: q0\n-Final states: q1";
            
            try {
                masakra.fromString(slowo2);
            } catch (Exception e) {
                assertTrue(false);
            }
        
            AutomatonByRecursion angle = new AutomatonByRecursion(masakra);
        
            assertFalse(angle.accepts("aab"));
            assertFalse(angle.accepts(""));
            assertTrue(angle.accepts("aaa"));
        }
    }
}
