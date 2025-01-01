package dk.jfq.floorballkamp.util.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LogFormatter extends SimpleFormatter {

    private final String format = LogManager.getLogManager().getProperty(LogFormatter.class.getName() + ".format");

    @Override
    public String format(LogRecord record) {
        String timestamp = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(record.getInstant().atOffset(ZoneOffset.UTC));
        // there is no way to make your own ISO_OFFSET_DATE_TIME with the same details as in the java.time.DateTimeFormatter but with a fixed nano size.
        // This is because of the private "toFormatter(Locale, ResolverStyle, Chronology)" method, so we have to hack it
        if (timestamp.length() < 27) {
            timestamp = String.format("%1$-26s", timestamp.substring(0, timestamp.indexOf('Z'))).replace(' ', '0') + "Z";
        }
        String source;
        if (record.getSourceClassName() != null) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
                source += "." + record.getSourceMethodName();
            }
        } else {
            source = record.getLoggerName();
        }
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        return String.format(format,
                timestamp,
                source,
                record.getLoggerName(),
                record.getLevel().getLocalizedName(),
                message,
                throwable);
    }
}
