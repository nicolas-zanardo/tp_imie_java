package fr.imie.fomation.api.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProcessBuilderExec {
    ProcessBuilder processBuilder = null;
    ArrayList<Command> commands;


    public ProcessBuilderExec(ArrayList<Command> commands) {
        this.commands = commands;
        itCommands();
    }

    private void itCommands() {
        for (Command command: commands) {
            execCommand(command.getLanguage(), command.getOption(), command.getCommand());
        }
    }

    private void execCommand(String language, String option, String command) {
        processBuilder = new ProcessBuilder();
        processBuilder.command(language, option, command);
        startProcess(command);
    }

    private void startProcess(String command) {
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success! :" + command);
            } else {
                System.out.print("ERROR ! " + output);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
