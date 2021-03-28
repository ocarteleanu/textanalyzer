package textanalyzer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestNoTagsMethod.class, TestNumberWords.class, TestRemoveAmpersandSeq.class,
	TestTrimPunctuation.class})
public class AllTests {

}
