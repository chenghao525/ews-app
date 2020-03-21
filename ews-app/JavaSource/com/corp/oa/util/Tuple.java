package com.corp.oa.util;

import java.util.ArrayList;

/**
 * 虽然不能解决 在sql中使用in这种情况带来的问题，
 * 但是可以解决instr中多个值查询的问题，请在使用in的地方用instr替代
 * @author wqj
 * @param <A>
 * @param <B>
 */
public class Tuple<A, B> {

    public final A first;
    public final B second;

    public Tuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    public String getFirst() {
        return (String)first;
    }

    public String[] getParameter() {
        if (this.second == null) {
            return null;
        }
        ArrayList<String> pl = (ArrayList<String>)this.second;
        if (pl.isEmpty()) {
            return null;
        }
        return pl.toArray(new String[pl.size()]);
    }

    @Override
    public String toString() {
        return "(" + first +"," + second + ")";
    }
}

