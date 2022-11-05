package fr.imie.fomation.api.component;

/**
 * @author Nicolas Zanardo
 */
public class Command {
    private String language;
    private String option;
    private String command;

    public Command(String language, String option, String command) {
        this.command = command;
        this.language = language;
        this.option = option;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
