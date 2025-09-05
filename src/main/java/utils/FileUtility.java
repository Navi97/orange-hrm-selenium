package utils;

import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;

public class FileUtility {
    public static File waitForFileDownload(String downloadPath, int timeoutSeconds) {
        File dir = new File(downloadPath);
        FluentWait<File> wait = new FluentWait<>(dir)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);

        return wait.until((file) -> {
            File latest = getLatestModifiedFile(downloadPath);
            return (latest != null && !latest.getName().endsWith(".crdownload")) && !latest.getName().startsWith(".com.google") ? latest : null;
        });
    }
    private static File getLatestModifiedFile(String downloadPath) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if((files == null) || (files.length == 0)){
            return null;
        }
        File assumedFile = files[0];
        for (File file : files) {
            if (assumedFile.lastModified() < file.lastModified()) {
                assumedFile = file;
            }
        }
        return assumedFile;
    }
}
