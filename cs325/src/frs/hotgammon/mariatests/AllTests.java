package frs.hotgammon.mariatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({LocationTests.class, BoardTests.class, CoreTests.class, BetaMonTests.class, AlternatingTurnTests.class, DeltaMonTests.class, GammaMonTests.class, SimpleMoveValidatorTests.class, SixMoveWinnerTests.class} )
public class AllTests {
}
