

package frs.hotgammon.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import frs.hotgammon.tests.mariatests.HandicapMonTests;
import frs.hotgammon.tests.mariatests.PairSequenceDeterminerTests;


@RunWith(Suite.class)
@SuiteClasses({SemiMonTests.class, RandomRollDeterminerTests.class, EpsilonMonTests.class, HandicapMonTests.class, FactoryCoreTests.class, PairSequenceDeterminerTests.class, CoreTests.class,  AlternatingTurnTests.class, WinAfterSixTests.class, BetaMonTests.class, GammaMonTests.class, DeltaMonTests.class, BoardTests.class, LocationTests.class } )
public class AllTests {
}
