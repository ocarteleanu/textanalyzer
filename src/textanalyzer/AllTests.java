package textanalyzer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestNumberWords.class, TestNoTagsMethod.class,
	TestRemoveAmpersandSeq.class, TestTrimPunctuation.class})
public class AllTests {

}
