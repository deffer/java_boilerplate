package org.tests.luaj;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExploreJSR {

	public static void main(String[] args) {
		listEngines();
		runSimpleScript("src/main/resources/coop.lua");
		//runFunctionCall("src/main/resources/coop.lua", "printFood"); // doesnt support Invocable
		testBindingsBasic();
		testLuaBindings("src/main/resources/coop.lua");

	}


	public static void testLuaBindings(String fileName) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("lua");
		try {

			engine.eval(new FileReader(fileName));

			Object a = engine.get("a");
			Object food = engine.get("food");
			System.out.println("food="+food.toString()+" of class "+food.getClass().toString());
			if (a==null)
				System.out.println("a is inaccessible (probably local)");
			else
				System.out.println("a="+a.toString()+" of class "+a.getClass().toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public static void testBindingsBasic() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("lua");
		try {

			Double rand = new Double(0.0);
			engine.put("randomNumber", rand);
			engine.put("copyNumber", rand);
			engine.put("finalNumber", rand);

			engine.eval("randomNumber = 5; finalNumber=randomNumber");
			// randomNumber=5
			// copyNumber=0.0
			// finalNumber=5

			Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
			Iterator<Map.Entry<String, Object>> it = bindings.entrySet().iterator();
			System.out.println("Bindings follow:");
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			System.out.println("** end of bindings list **");
			System.out.println("Java variable stays like "+rand);
			System.out.println("randomNumber is now class "+engine.get("randomNumber").getClass().toString());
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void runFunctionCall(String fileName, String functionName) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("lua");
		try {

			engine.eval(new FileReader(fileName));

			Invocable inv = (Invocable)engine;
			try {
				System.out.println("Invoking "+functionName+"...");
				inv.invokeFunction(functionName);
			}
			catch (NoSuchMethodException ex) {
				ex.printStackTrace();
			}

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void runSimpleScript(String fileName) {
		ScriptEngineManager manager = new ScriptEngineManager();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			ScriptEngine engine = manager.getEngineByExtension(ext);
			if (engine != null) {
				try {
					ScriptEngineFactory factory = engine.getFactory();
					System.out.println("Running " + fileName + " using engine " +
							factory.getEngineName() + " Version " +
							factory.getEngineVersion() + " for language " +
							factory.getLanguageName()+"\n");
					engine.eval(new FileReader(fileName));
				}catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}catch (ScriptException ex) {
					ex.printStackTrace();
				}finally {
					System.out.println("============================");
				}
			}
			else {
				System.err.println("Could not find scripting engine for " + fileName);
			}

	}

	public static void listEngines() {
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> engines = manager.getEngineFactories();
		if (engines.isEmpty()) {
			System.out.println("No scripting engines were found");
			return;
		}
		System.out.println("The following " + engines.size() +
				" scripting engines were found");
		System.out.println();
		for (ScriptEngineFactory engine : engines) {
			System.out.println("Engine name: " + engine.getEngineName());
			System.out.println("\tVersion: " + engine.getEngineVersion());
			System.out.println("\tLanguage: " + engine.getLanguageName());
			List<String> extensions = engine.getExtensions();
			if (extensions.size() > 0) {
				System.out.println("\tEngine supports the following extensions:");
				for (String e : extensions) {
					System.out.println("\t\t" + e);
				}
			}
			List<String> shortNames = engine.getNames();
			if (shortNames.size() > 0) {
				System.out.println("\tEngine has the following short names:");
				for (String n : engine.getNames()) {
					System.out.println("\t\t" + n);
				}
			}
			System.out.println("=========================");
		}
	}
}
