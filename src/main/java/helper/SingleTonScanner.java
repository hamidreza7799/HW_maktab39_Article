package helper;

import java.util.Scanner;

public final class SingleTonScanner {
    private static final Scanner scanner = new Scanner(System.in);

    private SingleTonScanner(){}

    public static synchronized Scanner getScanner(){
        return SingleTonScanner.scanner;
    }
}
