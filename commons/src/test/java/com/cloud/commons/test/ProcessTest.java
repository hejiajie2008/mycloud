package com.cloud.commons.test;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class ProcessTest {
    @Test
    public void testOne() throws InterruptedException {
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();

        long lc=Runtime.getRuntime().totalMemory();



        System.out.println("process:"+jvmName+",lc="+lc+",cpu number="+Runtime.getRuntime().availableProcessors());

        TimeUnit.SECONDS.sleep(3);
    }
}
