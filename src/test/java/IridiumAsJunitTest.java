import au.com.agic.apptesting.TestRunner;
import au.com.agic.apptesting.utils.SystemPropertyUtils;
import au.com.agic.apptesting.utils.impl.SystemPropertyUtilsImpl;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mcasperson on 11/1/17.
 */
public class IridiumAsJunitTest {
	private final List<File> globalTempFiles = new ArrayList<File>();

	/**
		(Re)set a bunch of common system properties
 	*/
	@Before
	public void getBrowserList() {
		/*
			When using Iridium from a Maven artifact, you don't get the drivers, so you
			must set the path to the drivers yourself.
		 */
		System.setProperty("phantomjs.binary.path", "/Users/Shared/phantomjs-2.1.1-macosx/bin/phantomjs");
		/*
			Likewise you'll need to set paths for these drivers if you intend to test against
			these browsers.
		 */
		System.setProperty("webdriver.chrome.driver", "");
		System.setProperty("webdriver.opera.driver", "");
		System.setProperty("webdriver.ie.driver", "");
		System.setProperty("webdriver.gecko.driver", "");
		System.setProperty("webdriver.edge.driver", "");
		System.setProperty("enableScenarioScreenshots", "false");
		System.setProperty("saveReportsInHomeDir", "false");
		System.setProperty("phantomJSLoggingLevel", "NONE");
		System.setProperty("startInternalProxy", "");
		System.setProperty("tagsOverride", "");
		System.setProperty("dryRun", "");
		System.setProperty("importBaseUrl", "");
	}

	@After
	public void cleanUpFiles() {
		globalTempFiles.forEach(File::delete);
	}


	@Test
	public void exampleIridiumJunitTest() {
		System.setProperty("appURLOverride", "https://google.com");
		System.setProperty("testSource", this.getClass().getResource("/test.feature").toString());
		System.setProperty("testDestination", "PhantomJS");
		final int failures = new TestRunner().run(globalTempFiles);
		Assert.assertEquals(0, failures);
	}
}
