class CreditValidationInterceptors {
  def creditValidationSchemaValidator

  def interceptors = {
    validation(endpoint: "creditValidation", interceptors: [creditValidationSchemaValidator]) {
    }
    stdout(endpoint: "creditValidation") {
      handleRequest = {messageContext, endpoint ->
        println "stdout:handleRequest"
        return true
      }
      handleResponse = {messageContext, endpoint ->
        println "stdout:handleResponse"
        ByteArrayOutputStream ostream = new ByteArrayOutputStream()
        messageContext.getResponse().writeTo(ostream)
        println ostream.toString()
        return true
      }
      handleFault = {messageContext, endpoint ->
        println "stdout:handleFault"
        return true
      }
    }
  }
}
