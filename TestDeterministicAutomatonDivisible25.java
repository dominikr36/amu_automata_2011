package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy DeterministicAutomaton.
 */
public class TestDeterministicAutomaton extends TestCase {
    /**
     * Symulacja testu.
     */
    public final void testLessComplicated() {

        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

    /**
     * Deklaracja pozycji.
     */
        State q0a = spec.addState();
        State q1a = spec.addState();
        State q2a = spec.addState();
	State q3a = spec.addState();
	State q4a = spec.addState();
	State q5a = spec.addState();
	State q6a = spec.addState();

    /**
     * Deklaracja mo¿liwych przejœæ akceptowanych.
     */
	spec.addTransition(q0a, q0a, new CharTransitionLabel('0'));
        spec.addTransition(q0a, q1a, new CharTransitionLabel('1'));
        spec.addTransition(q1a, q2a, new CharTransitionLabel('0'));
        spec.addTransition(q2a, q2a, new CharTransitionLabel('0'));
  	spec.addTransition(q0a, q3a, new CharTransitionLabel('5’));
	spec.addTransition(q3a, q4a, new CharTransitionLabel('0’));
        spec.addTransition(q0a, q5a, new CharTransitionLabel('2'));
        spec.addTransition(q0a, q6a, new CharTransitionLabel('7'));
        spec.addTransition(q5a, q5a, new CharTransitionLabel('5’));
 	spec.addTransition(q6a, q5a, new CharTransitionLabel('5’));


        spec.markAsInitial(q0a);
        spec.markAsFinal(q6a);

        final DeterministicAutomaton automaton = new DeterministicAutomaton(spec);

        assertTrue(automaton.accepts("25"));
        assertTrue(automaton.accepts("50"));
        assertTrue(automaton.accepts("75"));
        assertTrue(automaton.accepts("100"));
        assertTrue(automaton.accepts("0125"));
        assertTrue(automaton.accepts("025"));
	assertTrue(automaton.accepts("025"));
        assertFalse(automaton.accepts("00"));
        assertFalse(automaton.accepts("123"));
        assertFalse(automaton.accepts("05"));
        assertFalse(automaton.accepts("121"));
        assertFalse(automaton.accepts("251"));
    }

