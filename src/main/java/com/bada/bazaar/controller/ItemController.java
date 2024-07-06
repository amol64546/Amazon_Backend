package com.bada.bazaar.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Item")
@RequestMapping("/v1/items")
public interface ItemController {

}
