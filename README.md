# Slack Notifier
IntelliJ plugin for sending notifications to Slack when build finishes or test run finishes.
***
## How to install the plugin and run it
There are two ways for installing the plugin:
<ul>
    <li>Install the plugin manually from File > Settings > Plugins > gear icon > Install Plugin From Disk... > choose Slack-Notifier-0.3.jar</li>
    <li>Build the plugin with `gradle buildPlugin` and then install the plugin manually following first option. Plugin jar file will be in /build/distributions folder.</li>
</ul>

***
## How to configure the plugin
By default plugin is switched off. You have to configure it in File > Settings > Other Settings > Slack Notifier settings. It has three configurable options:
<ul>
    <li>Webhook - The webhook of Slack APP. In the form of https://hooks.slack.com/services/XXX/YYY/ZZZ</li>
    <li>Channel ID - The ID of the channel you want to send notifications to. It is an alphanumberic string. Like 1GE1U2EGKJF</li>
    <li>Turn on sending of Slack notification - The checkbox for turning the notifications on/off. By default it is off.</li>
</ul>
For changes to take effect you have to restart your IDE.

***
## Done

<ul>
	<li>Plugin listens to Gradle events sent from Gradle system window and sends the output to a Slack channel.</li>
    <li>Plugin is configurable from IDE settings.</li>
</ul>

***
## To do

<ul>
    <li>Filter out all other Gradle outputs not relevant to test or build events.</li>
    <li>Format output.</li>
    <li>Restart EventListener upon configuration changes to apply changes without restarting IDE.</li>
    <li>Add an option to listen also to Maven events.</li>
    <li>Add an option to listen to events from Terminal window.</li>
    <li>Add a suitable documentation.</li>
    <li>Register the plugin in Plugin Marketplace.</li>
</ul>
