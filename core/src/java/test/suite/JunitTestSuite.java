package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modules.ml.core.AttributesTest;
import modules.reader.CSVReaderTest;
import modules.struct.PairTest;
import modules.struct.ThreeLayerHashMapTest;
import modules.utilities.StringHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   CSVReaderTest.class,
   PairTest.class,
   ThreeLayerHashMapTest.class,
   StringHandlerTest.class,
   AttributesTest.class
   
})
public class JunitTestSuite { 
}  	