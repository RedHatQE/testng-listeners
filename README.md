# TestNG Listeners

Contains a couple of helpful listeners for [TestNG](http://testng.org/).  

* **GnomeTestNGListener** : produces screenshots of GNOME apps when a test fails
* **TestNGListener** : logs TestNG events such as test start/end/skip etc. to stdout using java.util.logging
* **BugzillaTestNGListener** : allows you to annotate your tests with a group corresponding to a bugzilla bug.  When the test executes, the bug will be checked to see if it's still open.  If so, the test will be skipped.

Also contains TestNGReportHandler, which is a handler for java.util.logging, that writes HTML to the TestNG Report log.