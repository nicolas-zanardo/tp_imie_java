package fr.imie.fomation.api._init_data;

import fr.imie.fomation.api.component.Command;
import fr.imie.fomation.api.component.ProcessBuilderExec;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class InitData {

    public static void setup()  {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<Command> commandList = new ArrayList<>();
        commandList.add(new Command("bash", "-c", "mysqladmin -u admin -p\"admin\" -f drop IMIE_formation_TEST"));
        commandList.add(new Command("bash", "-c", "mysqladmin -u admin -p\"admin\" -f create IMIE_formation_TEST"));
        commandList.add(new Command("bash", "-c", "mysql -u admin -p\"admin\" IMIE_formation_TEST < "+path+"/sql/IMIE_formation.sql"));
        new ProcessBuilderExec(commandList);
    }

}
