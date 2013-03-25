
package frs.hotgammon.tests.mariatests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import frs.hotgammon.tests.EpsilonMonTests;


@RunWith(Suite.class)
@SuiteClasses({EpsilonMonTests.class, HandicapMonTests.class, FactoryCoreTests.class, PairSequenceDeterminerTests.class, LocationTests.class, BoardTests.class, CoreTests.class, BetaMonTests.class, AlternatingTurnTests.class, DeltaMonTests.class, GammaMonTests.class, SimpleMoveValidatorTests.class, SixMoveWinnerTests.class} )
public class AllTests {
}
