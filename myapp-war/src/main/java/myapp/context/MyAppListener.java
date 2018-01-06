package myapp.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class MyAppListener {

    private static final Logger LOG = LoggerFactory.getLogger(MyAppListener.class);

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        try {
            LOG.debug("Reached  ContextRefreshedEvent... ");

        } catch (Exception ex) {
            LOG.error("Error while initializing query helper:", ex);
        }
    }

}