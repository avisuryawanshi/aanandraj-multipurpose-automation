package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.EspressoOptions;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;

/**
 * Launches an Android Appium session with the chosen automation engine
 * (UiAutomator2, Espresso, or Selendroid) using modern Options classes.
 */
public final class DynamicEngineSwitching {

	private DynamicEngineSwitching() { /* utility class */ }

	public static AppiumDriver getAppiumDriver(String engineType) {

		try {
			final URL serverUrl = new URI("http://127.0.0.1:4723/").toURL();

			switch (engineType.toLowerCase()) {

				/* ---------- UiAutomator2 ---------- */
				case "uiautomator2" -> {
					UiAutomator2Options options = new UiAutomator2Options()
							.setPlatformName("Android")
							.setDeviceName("Redmi Note 12 Pro+ 5G")
							.setUdid("adb-xyz123456")
							.setPlatformVersion("14")
							.setAutomationName("UiAutomator2")
							.setAppPackage("com.example.anandraj_multipuprose_hall")
							.setAppActivity("com.example.anandraj_multipuprose_hall.MainActivity");
					return new AndroidDriver(serverUrl, options);
				}

				/* ---------- Espresso ---------- */
				case "espresso" -> {
					EspressoOptions options = new EspressoOptions()
							.setPlatformName("Android")
							.setDeviceName("Redmi Note 12 Pro+ 5G")
							.setUdid("adb-xyz123456")
							.setPlatformVersion("14")
							.setAutomationName("Espresso")
							.setAppPackage("com.example.anandraj_multipuprose_hall")
							.setAppActivity("com.example.anandraj_multipuprose_hall.MainActivity");
					// Add Espresso‑specific capabilities here (e.g., intent‑policy)
					return new AndroidDriver(serverUrl, options);
				}

				/* ---------- Selendroid (legacy <5.0) ---------- */
				/*case "selendroid":
					// Selendroid works only on Android <5.0 and needs separate .apk
					break;*/
				/* ---------- Selendroid (legacy <5.0) ---------- */
				/*case "selendroid" -> {
					SelendroidOptions options = new SelendroidOptions()
							.setPlatformName("Android")
							.setAutomationName("Selendroid")
							.setDeviceName("Android_Device_<5.0")
							.setUdid("adb-old123")
							.setAppPackage("com.example.anandraj_multipuprose_hall")
							.setAppActivity("com.example.anandraj_multipuprose_hall.MainActivity");
					return new AndroidDriver(serverUrl, String.valueOf(options));
				}*/

				default -> throw new IllegalArgumentException("Unsupported automation engine: " + engineType);
			}

		} catch (Exception e) {
			System.err.println("[ERROR] Driver setup failed: " + e.getMessage());
			return null;
		}
	}
}
