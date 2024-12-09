package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LogFile {
    private Telemetry telemetry;
    private FileWriter logWriter;
    public String fullPath = null;

    public void logCreate(Telemetry telemetry, String prefix, String extension) {

        this.telemetry = telemetry;

        String logFolder = Environment.getExternalStorageDirectory().getPath(); // /storage/emulated/0 also maps to /sdcard

        // Define a unique file name
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        String fileName = logFolder + "/FIRST/logs/" + prefix + "_" + timestamp + "." + extension;

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
}
