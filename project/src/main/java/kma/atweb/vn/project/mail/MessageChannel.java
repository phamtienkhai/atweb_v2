package kma.atweb.vn.project.mail;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public sealed abstract class MessageChannel permits EmailChannel {
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
    public MessageChannel() {
    }

    protected abstract void _send(Map<String, Object> input);

    public static void send(Map<String, Object> input) {
        MessageChannel messageChannel = new EmailChannel();
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                messageChannel._send(input);
            }
        });
    }
}

