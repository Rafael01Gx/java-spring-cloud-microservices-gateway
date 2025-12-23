package com.food.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRouterFunctionsPagamentos() {
        return route("pagamento-service")
                .route(path("/api/pagamento/**"), http())
                .filter(stripPrefix(1))
                .filter(lb("PAGAMENTO-MS"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayRouterFunctionsPedidos() {
        return route("pedidos-service")
                .route(path("/api/pedidos/**"), http())
                .filter(stripPrefix(1))
                .filter(lb("PEDIDOS-MS"))
                .build();
    }

    }