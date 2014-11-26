package org.tests.luaj;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class TryLuajScript {
	public static void main(String[] args) {
		new TryLuajScript().runTest();
	}

	public void runTest(){
		Globals globals = JsePlatform.standardGlobals();
		LuaValue chunk = getChunk(globals);
		chunk.call();
	}

	public LuaValue getChunk(Globals globals){
		//LuaValue chunk = globals.load("print 'hello, world'");
		LuaValue chunk = globals.loadfile("src/main/resources/coop.lua");
		return chunk;
	}
}
