package org.infinispan.spring.boot.starter.issue;

import org.infinispan.spring.provider.SpringCache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.infinispan.spring.session.InfinispanEmbeddedSessionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;


@Configuration
public class DataGridSessionAutoConfiguration extends SpringHttpSessionConfiguration {

    @Bean
    public InfinispanEmbeddedSessionRepository embeddedSessionRepository(
            final SpringEmbeddedCacheManager cacheManager, final ApplicationEventPublisher eventPublisher)
    {
        final SpringCache cacheForSessions = cacheManager.getCache("sessions-cache");
        final InfinispanEmbeddedSessionRepository sessionRepository = new InfinispanEmbeddedSessionRepository(cacheForSessions);
        sessionRepository.setApplicationEventPublisher(eventPublisher);
        return sessionRepository;
    }

    @Bean
    public GreetingController greetingController() {
        return new GreetingController();
    }

}
