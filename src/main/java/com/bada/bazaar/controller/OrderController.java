package com.bada.bazaar.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Order")
@RequestMapping("/v1/orders")
public interface OrderController {
}
