NOTE: this is a fork from [@eliasnogueira](https://github.com/eliasnogueira)'s repo, give him a high five or mention if you use this!

# Test framework

This repo holds a test framework that allows you to execute tests in parallel using [Appium](http://appium.io) and [TestNG](https://testng.org/doc/index.html).
Currently only has support for android tests.
In the future I'll try to add support for:
* iOS (running tests on the simulator)
* [Selenium Webdriver](https://www.seleniumhq.org/projects/webdriver/)
* [Zalenium](https://opensource.zalando.com/zalenium/)
* Dockerize everything to avoid installation issues
* API testing (probably with [RestAssured](http://rest-assured.io/))

## What you need to have installed
1. Java JDK (with _JAVA_HOME_ and _PATH_ configured)
2. IDE (and import this project using Gradle)
3. Android Studio (with _ANDROID_HOME_ and _PATH_ configured)
4. Android AVD created
5. XCode and an iPhone Simulator (for iOS execution)
6. Appium installed through npm
7. Gradle

## About the apps used for the tests
The Fastip app can be found in the app folder.
It was built from [ptraeg](https://github.com/ptraeg/mobile-apps-4-ways)'s repo.
 
### Inspect elements on Android
You can use the [uiautomatorviewer](https://developer.android.com/training/testing/ui-testing/uiautomator-testing.html) to inspect elements on Android devices or you can use [Appium Desktop](https://github.com/appium/appium-desktop).

## iOS

### Configurations
To execute the examples over the iOS platform you'l need:
* MacOS machine
* Xcode installed
* iPhone simulator
* Follow all the steps on [appium's docs](https://github.com/appium/appium-xcuitest-driver/blob/master/README.md)


### Inspect elementos on iOS
You also can use [Appium Desktop](https://github.com/appium/appium-desktop) or you can use the [Macaca App Inspector](https://macacajs.github.io/app-inspector/)

### Appium
Try to always have Appium and libraries updated.
* Verify the core Appium version on [npm appium site](https://www.npmjs.com/package/appium). To see your Appium version run `appium --version` on Terminal
* [Verify the Appium library version](https://github.com/appium/java-client)
   * If it differs from 'build.gradle' file, update it.

### Project execution
First you'll need to start the hub and the nodes.


Each node is configured through a json file in _json_ folder. Remember you gonna need to change some values, like _browserName_, _version_, _platform_, _url_, _host_, _port_, _hubPort_ and _hubHost_

The _port_ information is also linked on `launch_grid.sh` file, that pass this and other informations by parameter.

After change all this information for your execution, execute the suite.xml file.
Each _test_ have 3 parameters:
* platform
* udid
* platformVersion

This parameters are linked to the test files using TestNG, so when you execute the suite.xml file all these parameter will be used on test file.
   
### About the test
On the package _com.eliasnogueira_ you'l find the test script `TipTest.java`_ that uses the information on `config.properties` and `suite.xml`to execute the tests in Android or iOS

### Have trouble?

#### CapabilityNotPresentOnTheGridException
Please, read this post: [https://medium.com/@eliasnogueira/got-capabilitynotpresentonthegridexception-66cbc1aa06b7](https://medium.com/@eliasnogueira/got-capabilitynotpresentonthegridexception-66cbc1aa06b7)

### Any question, error or feedback?
Please fill an issue ;-)


### Learnings

When working with appium in parallel you need to pay attention to a few simple but very important things.

If you take a look at the start-commands.txt file and the json files for appium, you need to set different ports for a few different things:
* wda
* systemPort
* boostrapPort

I lost many hours of trial and error until I finally got a working solution with 2 devices, despite the fact that I had 3 devices configured but only 2 were running the tests.

Another thing, I could never point my tests to the exact device I wanted, even though I put the exact udid and android version on the capabilities when running the tests locally ,for some reason the tests always run on different devices.




