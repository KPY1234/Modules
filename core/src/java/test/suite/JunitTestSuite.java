package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modules.ml.core.AttributesTest;
import modules.ml.core.InstanceTest;
import modules.reader.CSVReaderTest;
import modules.struct.PairTest;
import modules.struct.ThreeLayerHashMapTest;
import modules.utilities.io.StandardIOTest;
import modules.utilities.string.StringHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   CSVReaderTest.class,
   PairTest.class,
   ThreeLayerHashMapTest.class,
   StringHandlerTest.class,
   AttributesTest.class,
   InstanceTest.class,
   StandardIOTest.class
   
})
public class JunitTestSuite { 
}  	