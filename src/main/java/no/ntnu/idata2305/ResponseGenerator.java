package no.ntnu.idata2305;

public class ResponseGenerator {
    public static String generatorResponseHTML(long time1, long time2) {
        long timeInMs = time2 - time1;
        return ("<html><body>" +
                "Single threaded Server time: "
                + timeInMs + " ms"
                + "</body></html>");
    }

    public static String generatorResponseHeader(int contentLength) {
        return ("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + contentLength +
                "\r\n\r\n");
    }
}
