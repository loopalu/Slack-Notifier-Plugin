package ee.loopalu.slacknotifier.gui;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import ee.loopalu.slacknotifier.configuration.SettingsManager.Settings;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import javax.swing.JComponent;

public class PluginConfigurationUI implements ConfigurableUi<Settings> {
    @SuppressWarnings("rawtypes")
    private final JPanel mainPanel = new JBPanel();
    private final JBTextField webhookField = new JBTextField();
    private final JBTextField channelIdField = new JBTextField();
    private final JBCheckBox enableSlackNotificationsField = new JBCheckBox();

    public PluginConfigurationUI() {
        buildMainPanel();
    }

    private void buildMainPanel() {
        mainPanel.setLayout(new VerticalFlowLayout(true, false));

        mainPanel.add(FormBuilder.createFormBuilder()
                .addComponent(new TitledSeparator("Slack Notifier settings"))
                .addComponent(new JBLabel("<html>The webhook and channel ID for Slack. IDE restart will be required for any changes to take effect.</html>"))
                .addLabeledComponent("Webhook", webhookField)
                .addLabeledComponent("Channel ID", channelIdField)
                .addLabeledComponent("Turn on sending of Slack notifications", enableSlackNotificationsField)
                .getPanel());
    }

    @Override
    public void reset(@NotNull final Settings settings) {

        webhookField.setText(settings.webhook);
        channelIdField.setText(settings.channelId);
        enableSlackNotificationsField.setSelected(settings.slackNotificationsEnabled);
    }

    @Override
    public boolean isModified(@NotNull final Settings settings) {
        final boolean sameHostname = webhookField.getText().equals(settings.webhook);
        final boolean samePort = channelIdField.getText().equals(settings.channelId);
        final boolean sameAddConsoleAppender =
                Boolean.compare(enableSlackNotificationsField.isSelected(), settings.slackNotificationsEnabled) == 0;
        return !sameHostname || !samePort || !sameAddConsoleAppender;
    }

    // TODO Make it restart EventListener with new settings.
    @Override
    public void apply(@NotNull final Settings settings) {
        settings.webhook = webhookField.getText();
        settings.channelId = channelIdField.getText();
        settings.slackNotificationsEnabled = enableSlackNotificationsField.isSelected();
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mainPanel;
    }
}