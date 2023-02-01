package main.java.ru.coolspot.igotyou;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static AtomicInteger i = new AtomicInteger(0);
    private static AtomicReference<String> mode = new AtomicReference<>("");
    static String[][] lyrics = {
            {"Cher", "They say we're young and we don't know \nWe won't find out until we grow"},
            {"Sonny", "Well I don't know if all that's true \n'Cause you got me, and baby I got you"},
            {"Sonny", "Babe"},
            {"Sonny, Cher", "I got you babe \nI got you babe"},
            {"Cher", "They say our love won't pay the rent \nBefore it's earned, our money's all been spent"},
            {"Sonny", "I guess that's so, we don't have a pot \nBut at least I'm sure of all the things we got"},
            {"Sonny", "Babe"},
            {"Sonny, Cher", "I got you babe \nI got you babe"},
            {"Sonny", "I got flowers in the spring \nI got you to wear my ring"},
            {"Cher", "And when I'm sad, you're a clown \nAnd if I get scared, you're always around"},
            {"Cher", "So let them say your hair's too long \n'Cause I don't care, with you I can't go wrong"},
            {"Sonny", "Then put your little hand in mine \nThere ain't no hill or mountain we can't climb"},
            {"Sonny", "Babe"},
            {"Sonny, Cher", "I got you babe \nI got you babe"},
            {"Sonny", "I got you to hold my hand"},
            {"Cher", "I got you to understand"},
            {"Sonny", "I got you to walk with me"},
            {"Cher", "I got you to talk with me"},
            {"Sonny", "I got you to kiss goodnight"},
            {"Cher", "I got you to hold me tight"},
            {"Sonny", "I got you, I won't let go"},
            {"Cher", "I got you to love me so"},
            {"Sonny, Cher", "I got you babe \nI got you babe \nI got you babe \nI got you babe \nI got you babe"}
    };

    public static void main(String[] args) {

        Object monitor = new Object();
        Runnable code = () -> {
            while (i.intValue() < lyrics.length) {
                synchronized (monitor) {

                    if (i.intValue() == lyrics.length) {
                        break;
                    }

                    if (lyrics[i.intValue()][0].equals("Cher")) {
                        System.out.print("\033[95m");
                    }
                    if (lyrics[i.intValue()][0].equals("Sonny")) {
                        System.out.print("\033[36m");
                    }

                    if (mode.get().isBlank()) {
                        mode.set(Thread.currentThread().getName());
                        if (lyrics[i.intValue()][0].contains(Thread.currentThread().getName())) {
                            System.out.println(Thread.currentThread().getName() + ": " + lyrics[i.intValue()][1]);
                        }
                    } else if (!mode.get().equals(Thread.currentThread().getName())) {

                        if (lyrics[i.intValue()][0].contains(Thread.currentThread().getName())) {
                            System.out.println(Thread.currentThread().getName() + ": " + lyrics[i.intValue()][1]);
                        }
                        System.out.println("-----");
                        mode.set("");
                        i.incrementAndGet();
                    }
                    System.out.print("\033[0m");
                }
            }
        };

        Thread cher = new Thread(code);
        cher.setName("Cher");
        Thread sonny = new Thread(code);
        sonny.setName("Sonny");
        cher.start();
        sonny.start();
    }
}