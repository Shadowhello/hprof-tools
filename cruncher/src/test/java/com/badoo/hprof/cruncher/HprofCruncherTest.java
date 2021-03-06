package com.badoo.hprof.cruncher;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;

public class HprofCruncherTest {

    @Test
    public void testCrunch() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HprofSource source = new HprofFileSource(new File("../test_files/crunch_test_in.hprof"));
        HprofCruncher.Config config = new HprofCruncher.Config.Builder().stats(true).build();
        HprofCruncher.crunch(source, out, config);
        // Verify the output
        verify(new FileInputStream("../test_files/crunch_test_out.bmd"), new ByteArrayInputStream(out.toByteArray()));
    }

    private void verify(InputStream expected, InputStream actual) throws IOException {
        byte[] expectedData = IOUtils.toByteArray(expected);
        byte[] actualData = IOUtils.toByteArray(actual);
        assertArrayEquals("Converted file does not match expected result", expectedData, actualData);
    }

}