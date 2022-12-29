package ee.loopalu.slacknotifier.gui;

import com.intellij.openapi.options.ConfigurableBase;
import ee.loopalu.slacknotifier.configuration.SettingsManager;
import org.jetbrains.annotations.NotNull;

public class ConfigurationConfigurable extends ConfigurableBase<PluginConfigurationUI, SettingsManager.Settings> {

    protected ConfigurationConfigurable() {
        super("slacknotifier.settings", "Slack Notifier settings", null);
    }

    @NotNull
    @Override
    protected SettingsManager.Settings getSettings() {
        return SettingsManager.getInstance().getState();
    }

    @Override
    protected PluginConfigurationUI createUi() {
        return new PluginConfigurationUI();
    }
}