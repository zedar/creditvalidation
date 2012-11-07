package org.be3form.validation;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

import org.drools.runtime.rule.AgendaFilter;
import org.drools.runtime.rule.Activation;

public class RuleGroupFilter implements AgendaFilter {
  private List<String> ruleGroups = new ArrayList<String>();

  public RuleGroupFilter(String ruleGroupName) {
    this.ruleGroups.add(ruleGroupName);
  }

  public RuleGroupFilter(List<String> ruleGroups) {
    this.ruleGroups.addAll(ruleGroups);
  }

  public RuleGroupFilter(String[] ruleGroups) {
    Collections.addAll(this.ruleGroups, ruleGroups);
  }

  public boolean accept(Activation activation) {
    if (activation != null) {
      if (activation.getRule() == null) {
        return true;
      }
      Map<String, Object> mattrs = activation.getRule().getMetaData();
      if (mattrs == null || mattrs.size() == 0) {
        return true;
      }
      else {
        for (Map.Entry<String, Object> entry : mattrs.entrySet()) {
          System.out.println("Entry : " + entry.getKey() + " Value: " + entry.getValue());
        }
      }
      for (String rgn : this.ruleGroups) {
        if (mattrs.containsKey(rgn)) {
          return true;
        }
      }
    }
    return false;
  }
}
