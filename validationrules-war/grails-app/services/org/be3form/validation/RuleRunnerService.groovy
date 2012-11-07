package org.be3form.validation

import grails.validation.ValidationException
import groovy.transform.Synchronized;

import org.be3form.validation.RuleRunner;
import org.be3form.validation.messages.CreditValidationRequest;
import org.be3form.validation.messages.CreditValidationResponse;

class RuleRunnerService {

  private RuleRunner ruleRunner

  def run(CreditValidationRequest request) {
    if (!this.ruleRunner) {
      this.init()
    }
    if (!this.ruleRunner) {
      throw new ValidationException("Rule runner not initialized")
    }
    this.ruleRunner.run(request);
  }

  @Synchronized
  private init() {
    if (this.ruleRunner) {
      return
    }
    this.ruleRunner = new RuleRunner()
  }
}
