package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Forbids visits to home url if not authenticated")
    public void forbidsVisitsToHomeUrlIfNotAuthenticated() {

    }

    @Test (priority = 20)
    @Description("Test #2: Forbids visits to profile url if not authenticated")
    public void forbidsVisitsToProfileUrlIfNotAuthenticated() {

    }

    @Test (priority = 30)
    @Description("Test #3: Forbids visits to admin cities url if not authenticated")
    public void forbidsVisitsToAdminCitiesUrlIfNotAuthenticated() {

    }

    @Test (priority = 40)
    @Description("Test #4: Forbids visits to admin users url if not authenticated")
    public void forbidsVisitsToAdminUsersUrlIfNotAuthenticated() {

    }

}
