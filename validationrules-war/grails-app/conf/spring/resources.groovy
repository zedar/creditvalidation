// Place your Spring DSL code here
beans = {
  creditValidationSchemaValidator(org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor) {
    schema = "/WEB-INF/CreditValidation.xsd"
    validateRequest = true
    validateResponse = true
  }
}
