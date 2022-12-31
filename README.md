# Slack Notifier
IntelliJ plugin for sending notifications to Slack when build finishes or test run finishes.
***
## How to install the plugin and run it
There are two ways for installing the plugin:
<ul>
    <li>Install the plugin manually from File > Settings > Plugins > gear icon > Install Plugin From Disk... > choose Slack-Notifier-1.2.jar</li>
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
## Slack connection errors
When the plugin fails to connect to chosen Slack webhook and/or to Slack channel then it will throw an exception. It will be shown 
in IDE Internal Errors (a little red blinking exclamation mark icon in the lower-right corner of IDE's main window). It will be in the 
following form:

java.lang.RuntimeException: Status code: 404 Error: channel_not_found: the Slack channel associated with your request does not exist.
at ee.loopalu.slacknotifier.slack.SlackClient.handleFailure(SlackClient.java:70)
at ee.loopalu.slacknotifier.slack.SlackClient.postSlackMessage(SlackClient.java:43)...

Possible status codes and responding error messages are the following:
<ul>
    <li>Status code 400. Error: invalid_payload: the data sent in your request cannot be understood as presented; verify your content body matches your content type and is structurally valid.</li>
    <li>Status code 403. Error: action_prohibited: the team associated with your request has some kind of restriction on the webhook posting in this context.</li>
    <li>Status code 404. Error: channel_not_found: the Slack channel and/or webhook associated with your request does not exist.</li>
    <li>Status code 410. Error: channel_is_archived: the Slack channel has been archived and doesn't accept further messages, even from your incoming webhook.</li>
    <li>Status code 500. Error: rollup_error: something strange and unusual happened that was likely not your fault at all.</li>
</ul>
Depending on error you either have to fix configured Slack webhook and/or Slack channel ID or talk with the team who manages chosen Slack channel and its webhook. With status code 500 the error comes 
from Slack itself and there is nothing to do.

***
## Done

<ul>
	<li>Plugin listens to Gradle build and test run events sent from Gradle system window and sends the output to a Slack channel.</li>
    <li>Plugin is configurable from IDE settings.</li>
    <li>When there is a connection error to Slack webhook and/or Slack channel then IDE Internal Error will show the reason.</li>
</ul>

***
## To do

<ul>
    <li>Format output.</li>
    <li>Restart EventListener upon configuration changes to apply changes without restarting IDE.</li>
    <li>Add an option to listen also to Maven events.</li>
    <li>Add an option to listen to events from Terminal window.</li>
    <li>Add a suitable documentation.</li>
    <li>Register the plugin in Plugin Marketplace.</li>
</ul>
