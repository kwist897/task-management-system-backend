package org.solowev.taskmanager.base.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.List;

@Configuration
public class SharedConfig {
    @Bean
    Logger.Level logger(){
        return Logger.Level.FULL;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = List.of(
                new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter(),
                hierarchyVoter()
        );
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public AccessDecisionVoter<Object> hierarchyVoter() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER " +
                "ROLE_USER > ROLE_REFRESH_TOKEN");
        return new RoleHierarchyVoter(roleHierarchy);
    }
}
