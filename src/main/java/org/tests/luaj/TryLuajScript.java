package org.tests.luaj;

import org.luaj.vm2.*;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;
import org.tests.luaj.libs.randomstreams;


public class TryLuajScript {
	public static void main(String[] args) {
		new TryLuajScript().runTest();
	}

	public void runTest(){
		//testAccessJavaCan();
		//testAccessJavaCant();

		testIncludingSimpleLib();
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
		LuaValue chunk = globals.loadfile("src/main/resources/coop.lua");
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
