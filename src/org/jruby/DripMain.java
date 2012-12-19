package org.jruby.main;

import org.jruby.Ruby;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Initialization class for the "drip" launcher, to help pre-warm JRuby.
 *
 * DRIP_INIT needs to match the command line, for now.
 */
public class DripMain {
    private static void warmUpParser()
        throws IOException {
        Ruby ruby = Ruby.newInstance();

        // parse a large file 100 times
        String largeFile = System.getProperty("jruby.home") + "/lib/ruby/1.9/net/imap.rb";
        FileInputStream fis = new FileInputStream(largeFile);
        long size = fis.getChannel().size();
        byte[] bytes = new byte[(int)size];
        fis.read(bytes);

        for (int i = 0; i < 100; i++) {
            ruby.parseFromMain(new ByteArrayInputStream(bytes), "dummy");
        }
    }

    private static void initializeRuntime(String[] args) {
        org.jruby.Main.main(args);
    }

    public static void main(String[] args) throws IOException {
        //warmUpParser();
        initializeRuntime(args);
    }
}
