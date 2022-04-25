package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BaseConfig {

    @PostConstruct
    public void initRule(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //类型为QPS
        rule.setCount(1);
        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
        rules.add(rule);

        FlowRule rule1 = new FlowRule();
        rule1.setResource("hello1");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS); //类型为QPS
        rule1.setCount(1);
        rule1.setStrategy(RuleConstant.STRATEGY_DIRECT);
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
}
