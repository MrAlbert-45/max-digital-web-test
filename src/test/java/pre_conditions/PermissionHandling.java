package pre_conditions;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;

public class PermissionHandling {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Disable browser notifications
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        return options;
    }
}
