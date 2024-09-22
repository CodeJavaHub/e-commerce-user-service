package com.ecom.userservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/form")
public class TemplateController {

  private static final String SIGNUP_FORM = "formTemplates/signupForm.json";

  @Autowired private ResourceLoader resourceLoader;

  @GetMapping("/signup/template")
  public JsonNode getFormSchema() throws IOException {
    // Load the form schema JSON from the resources folder
    Resource resource = resourceLoader.getResource("classpath:"+SIGNUP_FORM);
    ObjectMapper objectMapper = new ObjectMapper();

    // Parse JSON into JsonNode (or any other POJO class if needed)
    return objectMapper.readTree(resource.getInputStream());
  }
}
