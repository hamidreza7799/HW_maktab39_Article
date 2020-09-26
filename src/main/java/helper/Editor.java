package helper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Editor {
    public String openEditor(String oldText , String fileName) throws IOException {
        File editor = new File(fileName + ".txt");
        editor.createNewFile();
        PrintWriter writer = new PrintWriter(editor);
        writer.print(oldText);
        writer.close();
        if (!Desktop.isDesktopSupported()) {
            System.out.println("your system can not read file");
            throw new IOException();
        }
        System.out.println("At now open a editor for you.After finish your writing enter any character to continue");
        Desktop desktop = Desktop.getDesktop();
        desktop.open(editor);
        System.out.print("Enter a character to continue: ");
        SingleTonScanner.getScanner().next();
        String result = Files.readString(Path.of(fileName + ".txt"));
        editor.delete();
        return result;

    }

}
