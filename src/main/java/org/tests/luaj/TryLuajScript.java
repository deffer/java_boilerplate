package org.tests.luaj;

import org.luaj.vm2.*;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;
import org.tests.luaj.libs.randomstreams;
import org.tests.luaj.libs.world;
import org.tests.objects.Apple;
import org.tests.objects.Fruit;
import org.tests.objects.Store;

import java.util.Arrays;
import java.util.List;


public class TryLuajScript {
	public static void main(String[] args) {
		new TryLuajScript().runTest();
	}

	public void runTest(){
		//testAccessJavaCan();
		//testAccessJavaCant();

		//testIncludingSimpleLib();
		testHookFromLua2Java();
		//testLuajavaReferencing();
	}

	public void testHookFromLua2Java(){
		Globals globals = initGlobals();
		globals.load(new world());
		LuaValue chunk = globals.loadfile("src/main/resources/unit.lua"); // hooks to event
		chunk.call();
		World.getInstance().populateWorld(); // generates event (calls listeners)

		System.out.println("At 15,15 - " + World.getInstance().getContentOf(15, 15)); // see if hook worked
	}

	public void testLuajavaReferencing(){
		Globals globals = JsePlatform.standardGlobals();
		LuaValue chunk = globals.loadfile("src/main/resources/ref.lua");
		chunk.call();
		LuaValue func = globals.get("checkStores");
		if (!func.isfunction())
			System.out.print("Not a function");
		else {
			Fruit fr = new Apple();
			List<Store> pass = Arrays.asList(new Store(fr), new Store(fr));
			func.call(CoerceJavaToLua.coerce(pass));
		}
	}

	public void testAccessJavaCan(){
		Globals globals = JsePlatform.standardGlobals();
		LuaValue chunk = getChunk(globals);
		chunk.call();
	}

	public void testAccessJavaCant(){
		Globals globals = initGlobals();
		LuaValue chunk = getChunk(globals);
		chunk.call();
	}

	public void testIncludingSimpleLib(){
		Globals globals = initGlobals();
		//Globals globals = JsePlatform.standardGlobals();
		globals.load(new randomstreams()); // same as calling require 'org.tests.luaj.libs.randomstreams' from lua
		LuaValue chunk = globals.loadfile("src/main/resources/streams.lua");
		chunk.call();
	}

	public LuaValue getChunk(Globals globals){
		//LuaValue chunk = globals.load("print 'hello, world'");
		LuaValue chunk = globals.loadfile("src/main/resources/swing.lua");
		return chunk;
	}

	public Globals initGlobals(){
		// return JsePlatform.standardGlobals();
		Globals globals = new Globals();
		globals.load(new JseBaseLib());
		globals.load(new PackageLib());
		globals.load(new Bit32Lib());
		globals.load(new TableLib());
		globals.load(new StringLib());
		globals.load(new CoroutineLib());
		globals.load(new JseMathLib());
		globals.load(new JseIoLib());
		globals.load(new JseOsLib());
		//globals.load(new LuajavaLib());
		LoadState.install(globals);
		LuaC.install(globals);
		return globals;
	}
}
