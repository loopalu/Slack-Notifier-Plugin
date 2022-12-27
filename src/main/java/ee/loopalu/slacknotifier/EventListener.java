package ee.loopalu.slacknotifier;

import com.intellij.openapi.externalSystem.model.ProjectSystemId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;

public class EventListener extends ExternalSystemTaskNotificationListenerAdapter {

    private final ProjectSystemId SYSTEM_ID = new ProjectSystemId("GRADLE");
    private StringBuilder builder = new StringBuilder();

    @Override
    public void onStart(@NotNull ExternalSystemTaskId id, String workingDir) {
        if (SYSTEM_ID.equals(id.getProjectSystemId())) {
            System.out.println(id.getIdeProjectId()); // projectname:d25a8d34
            System.out.println(workingDir); // /home/username/workspace/projectname
            super.onStart(id,workingDir);
        }
    }

    // Triggers on console output.
    @Override
    public void onTaskOutput(@NotNull ExternalSystemTaskId id, @NotNull String text, boolean stdOut) {
        if (SYSTEM_ID.equals(id.getProjectSystemId())) {
            if (!text.equals("\n")) {
                builder.append(text).append("\n");
            }
            super.onTaskOutput(id, text, stdOut);
        }
    }

    @Override
    public void onEnd(@NotNull ExternalSystemTaskId id) {
        if (SYSTEM_ID.equals(id.getProjectSystemId())) {
            String output = builder.toString();
            System.out.println(output);
            writeOutput(output);
            builder = new StringBuilder();
            super.onEnd(id);
        }
    }

    @Override
    public void onFailure(@NotNull ExternalSystemTaskId id, @NotNull Exception e) {
        if (SYSTEM_ID.equals(id.getProjectSystemId())) {
            System.out.println(e.getMessage());
            super.onFailure(id, e);
        }
    }

    private void writeOutput(String output) {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}