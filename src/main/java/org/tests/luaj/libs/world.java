package org.tests.luaj.libs;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.tests.luaj.ProtectedClass;
import org.tests.luaj.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class world extends TwoArgFunction {

	@Override
	public LuaValue call(LuaValue modname, LuaValue env) {
		LuaValue library = newInstance();
		env.set( "world",  library);
		return library;
	}

	private LuaValue newInstance(){
		final Integer me = World.getInstance().newUnit();

		LuaValue w = tableOf();
		w.set("getG", new ZeroArgFunction() {
			@Override
			public LuaValue call() {
				return LuaValue.valueOf(World.getInstance().getG());
			}
		});

		w.set("getAt", new TwoArgFunction() {
			@Override
			public LuaValue call(LuaValue arg1, LuaValue arg2) {
				List<Integer> content = World.getInstance().getContentOf(arg1.checkint(), arg2.checkint());
				List<LuaValue> lc = new ArrayList<>();
				for (Integer i: content)
					lc.add(LuaValue.valueOf(i));
				return LuaValue.listOf(lc.toArray(new LuaValue[lc.size()]));
			}
		});

		w.set("moveTo", new TwoArgFunction() {
			@Override
			public LuaValue call(LuaValue arg1, LuaValue arg2) {
				World.getInstance().move(me, arg1.checkint(), arg2.checkint());
				return LuaValue.NONE;
			}
		});

		w.set("hookOn", new TwoArgFunction() {
			@Override
			public LuaValue call(LuaValue arg1, final LuaValue arg2) {
				String eventName = arg1.checkjstring();
				if (!arg2.isfunction()){
					throw new RuntimeException("Not a function");
				}
				World.getInstance().addListener(eventName, arg2);
				return LuaValue.NONE;
			}
		});

		return w;
	}
}
