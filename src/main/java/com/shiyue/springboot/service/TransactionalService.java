package com.shiyue.springboot.service;



public interface TransactionalService {

    public void updateService();

    public void updateDaddy();

    public void updateDaddySuccess();

    public void updateDaddyHand();

    public void PROPAGATION_REQUIRED_A();

    public void propagation_supports_A();

    public void propagation_mandatory_A();

    public void propagation_REQUIRES_NEW_A();

    public void propagation_NESTED_A();

    public void UNCOMMITTED();

    public void READ_COMMITTED();

    public void jedisBut();

    public void jedisButOut();

    public void REPEATABLE_READ();

    public void propagation_NEVER();

    public void propagation_NOT_SUPPORTED_A();

    public void propagation_NESTED();

}
