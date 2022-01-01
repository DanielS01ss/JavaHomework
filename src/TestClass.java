import javax.swing.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FISIER, GUI
}

public class TestClass {
	public static void main(String[] args) {
		/* Incarcare setari si populare*/
		Settings.populateSettings();
		IDataLoader dataLoader;
		List<Student> studenti = new ArrayList<Student>();
		List<Curs> cursuri = new ArrayList<Curs>();
		List<Profesor> profesori = new ArrayList<Profesor>();

		System.out.println(Settings.loadType);

		switch (Settings.loadType)
		{
			case HARDCODAT :
				studenti = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.HARDCODAT).createStudentsData());
				cursuri = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.HARDCODAT).createCoursesData());
				profesori = Arrays.asList( Settings.dataLoaderHashMap.get(LOAD_TYPE.HARDCODAT).createProfesorData());
				break;
			case FILE:
				studenti = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.FILE).createStudentsData());
				cursuri = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.FILE).createCoursesData());
				profesori = Arrays.asList( Settings.dataLoaderHashMap.get(LOAD_TYPE.FILE).createProfesorData());
				break;
			case KEYBOARD:
				studenti = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.KEYBOARD).createStudentsData());
				cursuri = Arrays.asList(Settings.dataLoaderHashMap.get(LOAD_TYPE.KEYBOARD).createCoursesData());
				profesori = Arrays.asList( Settings.dataLoaderHashMap.get(LOAD_TYPE.KEYBOARD).createProfesorData());
				break;
		   }

		ApplicationResources.cursuri = cursuri;
		ApplicationResources.studenti = studenti;
		ApplicationResources.profesori = profesori;

		ManagerCursuri m = new ManagerCursuri();
		m.cursuri = cursuri;

		User currentUser;
		UserAccountType accType = null;
		HashMap<String,String> accHolderInfo = new HashMap<String,String>();

		ApplicationResources.mg = m;
		UserInterface u = new UserInterface(m);
		ConsoleInterface cI = new ConsoleInterface(m);

		Thread UIThreadGUI = new Thread(u);
		Thread UIThreadConsole = new Thread(cI);
		UIThreadGUI.start();
		UIThreadConsole.start();

	}
}
