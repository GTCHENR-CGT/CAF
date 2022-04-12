package com.gtchenr.utils;


import joptsimple.internal.Strings;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import java.io.IOException;

public class ELKUtilTest {


    @Test
    public void getTest1() throws IOException {
        String[] includes = new String[]{"name","pic"};
        String[] excludes = null;
        String str = ELKUtil.get("book", "1",includes,excludes);
        System.out.println(str);

    }
}
