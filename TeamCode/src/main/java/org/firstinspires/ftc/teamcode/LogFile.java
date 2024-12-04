package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
    private FileWriter logWriter;
    public String fullPath = null;

    public LogFile() {

        // Do not use the following as they may be attempting to write to protected spaces
        // String logFolder = Environment.getDataDirectory().getPath();            // /data
        // String logFolder = Environment.getDownloadCacheDirectory().getPath();   // /data/cache
        // String logFolder = Environment.getRootDirectory().getPath();            // /system
        // String logFolder = Environment.getStorageDirectory().getPath();         // Requires API level 30

        String logFolder = Environment.getExternalStorageDirectory().getPath(); // /storage/emulated/0 also maps to /sdcard

        // Define a unique file name
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        String fileName = logFolder + "/FIRST/logs/log_" + timestamp + ".txt";

        try {
            File logFile = new File(fileName);
            fullPath = logFile.getPath();
            if (!logFile.exists()) {
                logWriter = new FileWriter(logFile);
            } else {
                logWriter = new FileWriter(logFile, true); // Append mode
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        try {
            logWriter.write(message);
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String type, double message) {
        try {
            logWriter.write(type + ": " + message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (logWriter != null) {
                logWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
