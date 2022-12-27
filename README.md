# Slack Notifier
IntelliJ plugin for sending notifications to Slack when build finishes or test run finishes.
***
## How to install and run
There are two options:
<ul>
    <li>Install the plugin manually from File > Settings > Plugins > gear icon > Install Plugin From Disk... > choose Slack-Notifier-0.1.jar</li>
    <li>To build and run it on your own run first `gradle buildPlugin` and then follow the first option. Plugin jar file will be in /build/libs folder.</li>
</ul>

***
## Done

<ul>
	<li>Plugin listens to Gradle events sent from Gradle system window and saves the output to a filename.txt.</li>
</ul>

***
## To do

<ul>
    <li>Sending the output to Slack instead of filename.txt.</li>
    <li>Adding menu for configuring Slack hook.</li>
    <li>Adding a menu option to tick when Slack messages will be sent out.</li>
    <li>Adding an option to listen also to Maven events</li>
    <li>Adding an option to listen to events from Terminal window.</li>
    <li>Adding a suitable documentation.</li>
    <li>Registering the plugin in Plugin Marketplace</li>
</ul>

***

