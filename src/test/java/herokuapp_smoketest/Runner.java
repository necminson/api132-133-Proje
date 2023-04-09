package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {S1_Post.class, S2_Put.class, S3_Delete.class, S4_Get.class}

)
public class Runner {

}
