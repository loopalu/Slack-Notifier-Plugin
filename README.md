# Slack Notifier
IntelliJ plugin for sending notifications to Slack when build finishes or test run finishes.
***
## How to install and run
Before building the plugin you should replace `webhook` and `channelName` values in SlackClient class.

<ul>
    <li>First build the plugin with `gradle buildPlugin` and then Install the plugin manually from File > Settings > Plugins > gear icon > Install Plugin From Disk... > choose Slack-Notifier-0.2.jar. Plugin jar file will be in /build/distributions folder.</li>
</ul>

***
## Done

<ul>
	<li>Plugin listens to Gradle events sent from Gradle system window and sends the output to a Slack channel.</li>
</ul>

***
## To do

<ul>
    <li>Make webhook and channelName configurable from some settings file.</li>
    <li>Filter out all other Gradle outputs not relevant to test or build events.</li>
    <li>Format output.</li>
    <li>Add menu for configuring Slack hook.</li>
    <li>Add a menu option to tick when Slack messages will be sent out.</li>
    <li>Add an option to listen also to Maven events</li>
    <li>Add an option to listen to events from Terminal window.</li>
    <li>Add a suitable documentation.</li>
    <li>Register the plugin in Plugin Marketplace</li>
</ul>

***

