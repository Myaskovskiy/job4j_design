package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        byte b = -128;
        short sh = 32767;
        int i = 33;
        long l = 234234234;
        float f = 1.12F;
        double d = 1.234D;
        char ch = 65535;
        boolean bool = true;

        LOG.debug("byte : {}, short : {}, int : {}, long : {}", b, sh, i, l);
        LOG.debug("float : {}, double : {},", f, d);
        LOG.debug("char : {}, boolean : {},", ch, bool);
    }
}