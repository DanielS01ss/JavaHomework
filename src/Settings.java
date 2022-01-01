import java.util.HashMap;
import java.io.*;

public class Settings {
    static String STUDENTS_PATH = "studenti.csv";
    static String TEACHERS_PATH = "profesori.csv";
    static String COURSES_PATH= "cursuri.csv";
    static LOAD_TYPE loadType = LOAD_TYPE.HARDCODAT;
    static DISPLAY_TYPE displayType = DISPLAY_TYPE.GUI;
    static  HashMap<LOAD_TYPE, IDataLoader> dataLoaderHashMap = new HashMap<>() {{ put(LOAD_TYPE.HARDCODAT, new HardcodatDataManager()); put(LOAD_TYPE.FILE, new FileDataManager()); put(LOAD_TYPE.KEYBOARD, new KeyboardDataManager()); }  };
    static HashMap<DISPLAY_TYPE, IDisplayManager> displayHashMap = new HashMap<>() {{ put(DISPLAY_TYPE.CONSOLA, new ConsoleDisplay()); put(DISPLAY_TYPE.FISIER, new FileDisplay()); put(DISPLAY_TYPE.GUI, new GraphicUserInterfaceDisplay()); }  };

    static void populateSettings()
    {
        String studentsPath;
        String teachersPath;
        String coursesPath;
        String loadTypeStr;
        String displayTypeStr;

        try{
            BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));


            String line;
            line=reader.readLine().split("=")[1];
            studentsPath = line.substring(1,line.length()-1);
            line=reader.readLine().split("=")[1];
            teachersPath = line.substring(1,line.length()-1);
            line = reader.readLine().split("=")[1];
            coursesPath = line.substring(1,line.length()-1);
            line = reader.readLine().split("=")[1];
            loadTypeStr = line.substring(1,line.length()-1);
            line = reader.readLine().split("=")[1];
            displayTypeStr = line.substring(1,line.length()-1);


        } catch(IOException e)
        {
            System.out.println("There was an error opening the settings file");
            return;
        }

        if(loadTypeStr.equals("HARDCODAT"))
        {
           Settings.loadType = LOAD_TYPE.HARDCODAT;
            System.out.println(loadTypeStr);
        } else if (loadTypeStr.equals("KEYBOARD"))
        {
            Settings.loadType = LOAD_TYPE.KEYBOARD;
            System.out.println(loadTypeStr);
        } else if(loadTypeStr.equals("FILE"))
        {
            Settings.loadType = LOAD_TYPE.FILE;
            System.out.println(loadTypeStr);
        }

        if(displayTypeStr.equals("CONSOLE"))
        {
            displayType = DISPLAY_TYPE.GUI.CONSOLA;
        } else if (displayTypeStr.equals("FISIER"))
        {
            displayType = DISPLAY_TYPE.FISIER;

        } else if(displayTypeStr.equals("GUI"))
        {
            displayType = DISPLAY_TYPE.GUI;
        }

    }
}
