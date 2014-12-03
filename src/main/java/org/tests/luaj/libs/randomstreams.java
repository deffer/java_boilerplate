package org.tests.luaj.libs;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class randomstreams extends TwoArgFunction {

	public randomstreams(){
		System.out.println("Library: Constructor called");
	}


	@Override
	public LuaValue call(LuaValue modname, LuaValue env) {
		System.out.println("Library: Init called");
		LuaValue library = tableOf();
		library.set( "rs", new rs() );
		//library.set( "cosh", new cosh() );
		env.set( "randomstreams", library );
		return library;
	}

	public static class rs extends OneArgFunction {

		@Override
		public LuaValue call(LuaValue arg) {
			return LuaValue.valueOf(arg.checkint()*100);
		}
	}
}
