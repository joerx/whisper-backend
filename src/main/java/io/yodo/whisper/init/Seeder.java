package io.yodo.whisper.init;

import io.yodo.whisper.entity.Shout;
import io.yodo.whisper.service.ShoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ConfigurationProperties("whisper.seed")
public class Seeder {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ShoutService shoutService;

    private boolean enabled = false;

    private List<Shout> shouts;

    public Seeder(ShoutService shoutService) {
        this.shoutService = shoutService;
    }

    @PostConstruct
    public void seed() {
        if (!enabled) {
            log.info("Seeding disabled, skipping");
            return;
        }
        seedShouts();
    }

    private void seedShouts() {
        if (shoutService.countAll() > 0) {
            log.info("Found shouts data in database, will skip seeding");
            return;
        };
        log.info("Seeding database with " + shouts.size() + " shouts");
        for (Shout s : shouts) {
            shoutService.create(s);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setShouts(List<Shout> shouts) {
        this.shouts = shouts;
    }
}
