
package frs.hotgammon.tests.mariatests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import frs.hotgammon.tests.EpsilonMonTests;
import frs.hotgammon.tests.RandomRollDeterminerTests;
import frs.hotgammon.tests.SemiMonTests;


@RunWith(Suite.class)
@SuiteClasses({SemiMonTests.class, RandomRollDeterminerTests.class, EpsilonMonTests.class, HandicapMonTests.class, FactoryCoreTests.class, PairSequenceDeterminerTests.class, LocationTests.class, BoardTests.class, CoreTests.class, BetaMonTests.class, AlternatingTurnTests.class, DeltaMonTests.class, GammaMonTests.class, SimpleMoveValidatorTests.class, SixMoveWinnerTests.class} )
public class AllTests {
}
