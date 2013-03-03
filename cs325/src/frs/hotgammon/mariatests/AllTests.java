package frs.hotgammon.mariatests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({HandicapMonTests.class, FactoryCoreTests.class, PairSequenceDeterminerTests.class, LocationTests.class, BoardTests.class, CoreTests.class, BetaMonTests.class, AlternatingTurnTests.class, DeltaMonTests.class, GammaMonTests.class, SimpleMoveValidatorTests.class, SixMoveWinnerTests.class} )
public class AllTests {
}
