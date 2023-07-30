package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Sergey";
        int age = 33;
        byte id = 3;
        short home = 435;
        int sum = 65235;
        long uid = 33345643;
        double weight = 90.2;
        boolean child  = false;
        String street = "Kuibyshev";
        char liter = 'A';
        float contribution = 7.43F;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("User address. Street : {}, home : {}, liter : {}", street, home, liter);
        LOG.debug("Users addition information. weight : {}, id : {}, uid : {}, sum : {}, contribution : {}, child : {}",
                weight, id,  uid, sum, contribution, child);
    }
}