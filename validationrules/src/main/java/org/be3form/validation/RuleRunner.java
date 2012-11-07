package org.be3form.validation;

import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;

import org.drools.KnowledgeBaseFactory;
import org.drools.KnowledgeBase;

import org.drools.runtime.StatefulKnowledgeSession;

import org.drools.io.impl.ClassPathResource;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.be3form.validation.RuleGroupFilter;
import org.be3form.validation.facts.Customer;
import org.be3form.validation.facts.IdentificationType;
import org.be3form.validation.creditfacts.CreditApplication;
import org.be3form.validation.resultfacts.ValidationResult;
import org.be3form.validation.messages.CreditValidationRequest;
import org.be3form.validation.messages.CreditValidationResponse;

public class RuleRunner {

  private KnowledgeBase kbase;

  public RuleRunner() {
  }

  public ValidationResult[] run(CreditValidationRequest request) {
    if (request == null) {
      System.out.println("----- Validation Request is not given -----");
      return null;
    }
    ArrayList factList = new ArrayList();
    factList.addAll(request.getCustomerList());
    factList.add(request.getCreditApplication());

    if (request.getRuleGroup() != null) {
      return run(new String[]{request.getRuleGroup().toString()}, factList.toArray());
    }
    else {
      return run(null, factList.toArray());
    }
  }

  public ValidationResult[] run(String[] ruleGroups, Object[] facts) {
    if (facts == null) {
      System.out.println("----- Input facts not given -----");
      return null;
    }

    System.out.println("----- Run the rules -----");
    StatefulKnowledgeSession ksession = getSession();
    if (ksession == null) {
      System.out.println("----- Knowledge session NOT CREATED");
      return null;
    }
    else {
      System.out.println("----- Knowledge session CREATED");
    }

    // create facts and insert them into the session
    for (int i = 0; i < facts.length; i++) {
      Object fact = facts[i];
      ksession.insert(fact);
    }
    
    // add global list of validation results
    List<ValidationResult> validationResults = new ArrayList<ValidationResult>();
    ksession.setGlobal("validationResults", validationResults);

    // fire the rules
    if (ruleGroups != null && ruleGroups.length > 0) {
      ksession.fireAllRules(new RuleGroupFilter(ruleGroups));
    }
    else {
      ksession.fireAllRules();
    }
    
    // see result objects - list of DR-s
    System.out.println("----- Objects from Working Memory");
    Collection retFacts = ksession.getObjects();
    for (Object o : retFacts) {
      System.out.println("Return object: " + o);
    }
    
    // see validation results
    System.out.println("----- Validation results");
    for (Object vr : validationResults) {
      System.out.println("Validation result: " + vr);
    }

    // clear knowledge session and remove all facts and result obejects
    ksession.dispose();

    return validationResults.toArray(new ValidationResult[validationResults.size()]);
  }

  public static void main(String[] args) {
    RuleRunner rr = new RuleRunner();

    rr.run(new String[]{"PRESCORING", "SCORING"}, rr.createTest1Facts());
  }

  private StatefulKnowledgeSession getSession() {
    StatefulKnowledgeSession ksession = null; 
    if (this.kbase == null) {
      KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
      kbuilder.add(new ClassPathResource("org/be3form/validation/creditvalidation.drl", getClass()), ResourceType.DRL);
      kbuilder.add(new ClassPathResource("org/be3form/validation/customervalidation.drl", getClass()), ResourceType.DRL);

      final DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
      dtableconfiguration.setInputType( DecisionTableInputType.XLS );
      
      kbuilder.add(new ClassPathResource("org/be3form/validation/customeragevalidation.xls", getClass()), ResourceType.DTABLE, dtableconfiguration);

      if (kbuilder.hasErrors()) {
        System.out.println(kbuilder.getErrors().toString());
        return null;
      }

      this.kbase = KnowledgeBaseFactory.newKnowledgeBase();
      this.kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    }

    ksession = this.kbase.newStatefulKnowledgeSession();

    return ksession;
  }

  public Object[] createTest1Facts() {
    Object[] facts = {
      new Customer("11111111", "71011802213", new IdentificationType("DOWOD_TOZSAMOSCI", "AFK661002")),
      new Customer("22222222", null, new IdentificationType("DOWOD_TOZSAMOSCI", "ATH540067")),
      new Customer("33333333", "00311033698", new IdentificationType("DOWOD_TOZSAMOSCI", "ATH540067")),
      new Customer("44444444", "45121254572", new IdentificationType("DOWOD_TOZSAMOSCI", "ATH540067")),
      
      new CreditApplication("33333333", "DSD"),
      new CreditApplication("44444444", "DSD"),
    };

    return facts;
  }
}
