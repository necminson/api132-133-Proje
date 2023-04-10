package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {S1_Post.class, S2_Put.class, S3_Get.class, S4_Patch.class, S5_Delete.class, S6_Get_Negative.class}

)
public class Runner {

}
