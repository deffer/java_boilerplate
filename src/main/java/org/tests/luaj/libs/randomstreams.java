package org.tests.luaj.libs;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;

import java.util.ArrayList;
import java.util.List;

public class randomstreams extends TwoArgFunction {

	public randomstreams(){

	}


	@Override
	public LuaValue call(LuaValue modname, LuaValue env) {
		LuaValue library = tableOf();
		library.set( "num", new num() );
		env.set( "rstreams", library );

		// also add a function
		LuaFunction func = new RandomPcStream();
		env.set("random_pc_stream", func);
		return library;
	}

	public static class num extends OneArgFunction {

		@Override
		public LuaValue call(LuaValue arg) {
			return LuaValue.valueOf(arg.checkint()*100);
		}
	}

	public static class RandomPcStream extends VarArgFunction {

		@Override
		public Varargs invoke(Varargs args) {
			List<LuaValue> result = new ArrayList<>();
			int count = args.arg(1).checkint();
			for (int i =0; i<count; i++){
				int val = (int)(Math.random()*100);
				result.add(LuaValue.valueOf(val));
			}
			return LuaValue.varargsOf(result.toArray(new LuaValue[result.size()]));
		}
	}
}
