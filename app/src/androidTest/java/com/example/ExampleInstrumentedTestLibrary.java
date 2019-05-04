package com.example;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author xmqian
 * @date 19-5-4
 * @desc Android 单元测试类 依赖机器
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTestLibrary{
    @Test
    public void useAppContext() {
        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.example.myapplication34", appContext.getPackageName());
        System.out.println("Android单元测试");
    }
}
